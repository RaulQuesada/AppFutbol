package com.raulquesada.appfutbol.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.raulquesada.appfutbol.listeners.api.IGetLigaParaPartidoListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosParaUnPartidoListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.APIManager;
import com.raulquesada.appfutbol.util.Lib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import static android.content.Context.ALARM_SERVICE;

public class NotificationsManager implements IGetLigaParaPartidoListener, IGetPartidosParaUnPartidoListener {
    /**
     * The constant MESSAGE_PRE_PARTIDO.
     */
    private static final String MESSAGE_PRE_PARTIDO = "¡Empieza el partido! Puedes revisar el resultado en directo";
    /**
     * The constant MESSAGE_POST_PARTIDO.
     */
    private static final String MESSAGE_POST_PARTIDO = "¡Terminado! Revisa el resultado en Favoritos";
    /**
     * The constant EXTRA_TITLE.
     */
    public static final String EXTRA_TITLE = "title";
    /**
     * The constant EXTRA_MESSAGE.
     */
    public static final String EXTRA_MESSAGE = "message";

    /**
     * The Prefs.
     */
    private SharedPreferences prefs; //Preferencias de la preference screen
    /**
     * The Lista equipos.
     */
    private ArrayList<Equipo> listaEquipos;
    /**
     * The Api manager.
     */
    private APIManager apiManager;
    /**
     * The Context.
     */
    private Context context;

    /**
     * Instantiates a new Notifications manager.
     *
     * @param listaEquipos the lista equipos
     * @param context      the context
     */
    public NotificationsManager(ArrayList<Equipo> listaEquipos, Context context) {
        this.listaEquipos = listaEquipos;
        this.context = context;
        apiManager = new APIManager(context);
    }

    /**
     * Init.
     */
    public void init(){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        apiManager.setGetLigaParaPartidoListener(this);
        apiManager.setGetPartidosParaUnPartidoListener(this);
        for (int i = 0; i < listaEquipos.size(); i++) {
            apiManager.getInfoLigaParaPartido(listaEquipos.get(i).getDivision(), listaEquipos.get(i));
        }
    }

    /**
     * Cuando recibo la información de la liga desde la API
     * @param infoLiga información de la liga
     * @param equipo al que obtener partido favorito para notificar
     */
    @Override
    public void OnGetLigaParaPartido(InfoLiga infoLiga, Equipo equipo) {
        apiManager.getJornadaParaPartido(equipo.getDivision(),Integer.parseInt(infoLiga.getLeague().getCurrent_round()),equipo);
    }

    /**
     * Cuando recibo los partidos de un equipo
     * @param jornada del partido
     * @param equipo del partido
     */
    @Override
    public void OnGetPartidosParaUnPartido(Jornada jornada, Equipo equipo) {
        for (Partido partido : jornada.getPartidos()){
            //Si el partido contiene al equipoo
            if (partido.getIdLocal().equals(equipo.getId()) || partido.getIdVisitor().equals(equipo.getId())){
                if (Lib.compareDates(partido.getDate())){//Si el partido es hoy
                    prepareAlarm(partido);
                }else {
                    Log.i("MATCHES","No hay partidos");
                }
            }
        }
    }
    /**
     * Metodo que prepara la alarma para ser lanzada
     */
    private void prepareAlarm(Partido partido){
        if (!listaEquipos.isEmpty()){
            String title = partido.getLocal()+"-"+partido.getVisitor();
            if (prefs.getBoolean("opcionNotiPrePartido",false)){
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY,Integer.parseInt(partido.getHour()));
                today.set(Calendar.MINUTE,Integer.parseInt(partido.getMinute()));
                today.set(Calendar.SECOND,0);

                setAlarm(today.getTimeInMillis(),context,title,MESSAGE_PRE_PARTIDO);
            }
            if (prefs.getBoolean("opcionNotiPostPartido",false)){
                Calendar today = Calendar.getInstance();
                int minuteMatchEnds;
                int hourMatchEnds;
                //Calculo la hora en la que acaba el partido
                //En ningún caso sobrepasaría el día ya que el partido que más tarde puede
                //llegar a jugarse es a las 22:00 y acabaría a las 23:50
                //Impuesto por la Real Federación Española de Fútbol.
                if (Integer.parseInt(partido.getMinute())<10){
                    minuteMatchEnds = Integer.parseInt(partido.getMinute())+50;
                    hourMatchEnds = Integer.parseInt(partido.getHour())+1;
                }else {
                    minuteMatchEnds = 50-Integer.parseInt(partido.getMinute());
                    hourMatchEnds = Integer.parseInt(partido.getHour())+2;
                }

                today.set(Calendar.HOUR_OF_DAY,hourMatchEnds);
                today.set(Calendar.MINUTE,minuteMatchEnds);
                today.set(Calendar.SECOND,0);

                setAlarm(today.getTimeInMillis(),context,title,MESSAGE_POST_PARTIDO);
            }
        }
    }

    /**
     * Metodo para establecer una alarma
     * @param timestamp hora de la alarma
     * @param context contexto
     */
    private static void setAlarm(Long timestamp, Context context, String title, String message){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra(NotificationsManager.EXTRA_TITLE,title);
        alarmIntent.putExtra(NotificationsManager.EXTRA_MESSAGE,message);
        PendingIntent pIntent;
        pIntent = PendingIntent.getBroadcast(context, UUID.randomUUID().hashCode(), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmIntent.setData(Uri.parse("custom://"+System.currentTimeMillis()));
        alarmManager.set(AlarmManager.RTC_WAKEUP,timestamp,pIntent);
    }
}
