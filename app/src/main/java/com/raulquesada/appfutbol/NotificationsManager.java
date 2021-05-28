package com.raulquesada.appfutbol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.raulquesada.appfutbol.listeners.api.IGetLigaListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.APIManager;
import com.raulquesada.appfutbol.util.Lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import static android.content.Context.ALARM_SERVICE;

public class NotificationsManager implements IGetLigaListener, IGetPartidosListener {
    private static final String MESSAGE_PRE_PARTIDO = "¡Empieza el partido! Puedes revisar el resultado en directo";
    private static final String MESSAGE_POST_PARTIDO = "¡Terminado! Revisa el resultado en Favoritos";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_MESSAGE = "message";

    private SharedPreferences prefs; //Preferencias de la preference screen
    private ArrayList<Equipo> listaEquipos;
    private Equipo currentEquipo;
    private APIManager apiManager;
    private Context context;

    public NotificationsManager(ArrayList<Equipo> listaEquipos, Context context) {
        this.listaEquipos = listaEquipos;
        this.context = context;
        apiManager = new APIManager(context);
    }

    public void init(){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        apiManager.setGetLigaListener(this);
        apiManager.setGetPartidos(this);
        for (int i = 0; i < listaEquipos.size(); i++) {
            currentEquipo = listaEquipos.get(i);
            apiManager.getInfoLiga(currentEquipo.getDivision());
        }
    }

    @Override
    public void OnGetLiga(InfoLiga infoLiga) {
        apiManager.getJornada(currentEquipo.getDivision(),Integer.parseInt(infoLiga.getLeague().getCurrent_round()));
    }

    @Override
    public void OnGetPartidos(Jornada jornada) {
        for (Partido partido : jornada.getPartidos()){
            if (partido.getIdLocal().equals(currentEquipo.getId()) || partido.getIdVisitor().equals(currentEquipo.getId())){
                if (Lib.compareDates(partido.getDate())){
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
                today.set(Calendar.HOUR_OF_DAY,12);
                today.set(Calendar.MINUTE,29);
                today.set(Calendar.SECOND,45);

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
