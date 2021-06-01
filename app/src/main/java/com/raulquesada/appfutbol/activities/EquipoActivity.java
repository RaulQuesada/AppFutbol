package com.raulquesada.appfutbol.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.fragments.equipo.FragmentDetalleEquipoPlantilla;
import com.raulquesada.appfutbol.fragments.equipo.FragmentDetalleEquipoResultadosPartidos;
import com.raulquesada.appfutbol.fragments.equipo.FragmentEquipo;
import com.raulquesada.appfutbol.listeners.button.IButtonPartidosListener;
import com.raulquesada.appfutbol.listeners.button.IButtonPlantillaListener;
import com.raulquesada.appfutbol.listeners.button.IButtonResultadosListener;
import com.raulquesada.appfutbol.models.Equipo;

public class EquipoActivity extends AppCompatActivity implements IButtonPlantillaListener, IButtonPartidosListener, IButtonResultadosListener {
    private FragmentEquipo frgEquipo;// Fragment estáico del equipo
    private Fragment f = null;//Fragment donde se muestra la plantilla, resultados o partidos
    private Toolbar toolbar;//toolbar de la activity equipo
    private MenuItem favouriteTeam;//item para añadir/eliminar el equipo a Favoritos
    private FirebaseFirestore db;//base de datos Firebase
    private CollectionReference reference;//referencia a la base dedatos
    private Equipo eQC;//equipo
    private boolean favorito;//Si el equipo es favorto o no

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

        //Obtenemos el equipo
        Intent i = getIntent();
        eQC = (Equipo)i.getSerializableExtra("Equipo");

        //set toolbar
        toolbar = findViewById(R.id.toolbarEquipo);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_vector_test);
            getSupportActionBar().setTitle("Info Equipo");
        }

        //instancia de la base de datos
        db = FirebaseFirestore.getInstance();
        reference = db.collection(MainActivity.TAG);
        //obtengo si el equipo es favorito o no
        reference.whereEqualTo("id",eQC.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    if (!task.getResult().isEmpty()){
                        favorito = true;
                    }else {
                        favorito = false;
                    }
                    setFavouriteIcon();
                    favouriteTeam.setVisible(true);
                }else {
                    Log.e("hola","Error al obtener los documentos" + task.getException());
                }
            }
        });

        frgEquipo = (FragmentEquipo)getSupportFragmentManager().findFragmentById(R.id.FrgEquipo);

        //listeners
        frgEquipo.setButtonPlantillaListener(this);
        frgEquipo.setButtonPartidosListener(this);
        frgEquipo.setButtonResultadosListener(this);

        frgEquipo.setEquipo(eQC);

        //Llamo a los métodos inicialización del fagment
        frgEquipo.init();
        f = new FragmentDetalleEquipoPlantilla(eQC);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrgEquipoDetalle, f)
                .commit();
    }

    /**
     * Si el usuario pincha en Plantilla, el fragment cambia al de plantilla
     */
    @Override
    public void OnPlantillaButtonPressed() {
        f = new FragmentDetalleEquipoPlantilla(eQC);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrgEquipoDetalle, f)
                .commit();
    }

    /**
     * Si el usuario pincha en Partidos, el fragment cambia al de partidos
     */
    @Override
    public void OnPartidosButtonPressed() {
        f = new FragmentDetalleEquipoResultadosPartidos(eQC, false);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrgEquipoDetalle, f)
                .commit();
    }

    /**
     * Si el usuario pincha en Resultados, el fragment cambia al de resultados
     */
    @Override
    public void OnResultadosButtonPressed() {
        f = new FragmentDetalleEquipoResultadosPartidos(eQC, true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrgEquipoDetalle, f)
                .commit();
    }

    /**
     * Inflo el menu
     * @param menu de la toolbar
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_equipo, menu);
        favouriteTeam = menu.findItem(R.id.action_favourite);
        return true;
    }

    /**
     * Cuando el usuario pincha en un elemento del menu
     * @param item del menu
     * @return super.onOptionsItemSelected(item)
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Item para volver atrás
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                int extra = getIntent().getIntExtra(MainActivity.EXTRA_MAIN,1);
                i.putExtra(MainActivity.EXTRA_MAIN,extra);//Obtengo el fragment al que hay que volver
                i.putExtra(MainActivity.EXTRA_DIVISION,eQC.getDivision());
                startActivity(i);
                break;

                //Item añadir/eliminar equipos favoritos
            case R.id.action_favourite:
                if (favorito){//En caso de que ya este en favoritos, se elimina
                    reference.document(eQC.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Eliminado correctamente",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error de red",Toast.LENGTH_SHORT).show();
                        }
                    });
                    favorito = false;
                }else {//En caso de que no este en favoritos, se añade
                    reference.document(eQC.getId()).set(eQC).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),eQC.getTeam()+" añadido a \"Favoritos\"",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error de red",Toast.LENGTH_SHORT).show();
                        }
                    });
                    favorito = true;
                }
                setFavouriteIcon();//Cambio el icono
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método encaragado de cambiar el icono en caso de que el equipo sea favorito o no
     */
    private void setFavouriteIcon(){
        if (favorito){
            favouriteTeam.setIcon(android.R.drawable.btn_star_big_on);
        }else {
            favouriteTeam.setIcon(android.R.drawable.btn_star_big_off);
        }
    }
}