package com.raulquesada.appfutbol.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.fragments.clasificacion.FragmentClasificacion;
import com.raulquesada.appfutbol.fragments.directo.FragmentDirecto;
import com.raulquesada.appfutbol.fragments.equipo.FragmentEquipo;
import com.raulquesada.appfutbol.fragments.favorito.FragmentFavorito;
import com.raulquesada.appfutbol.fragments.jornada.FragmentJornada;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposFavoritosListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.notifications.NotificationsManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IGetEquiposFavoritosListener {
    public static final String EXTRA_MAIN = "extramain";//extra para saber que fragment inicializar
    public static final String EXTRA_DIVISION = "extradivision";//extra para saber que liga esta seleccionada
    public static final String TAG ="favouriteTeams";
    public static final int PRIMERA_DIVISION = 1;//final primera division
    public static final int SEGUNDA_DIVISION = 2;//final segunda division

    private FirebaseFirestore db;//base de datos Firebase
    private CollectionReference reference;//referencia a la base dedatos
    private Toolbar toolbar;//toolbar de la activity MainActivity
    private NavigationView navigationView;//NavigationView, menu lateral
    private DrawerLayout drawer;//Layout menú lateral
    private Button bLogin;//Botón para iniciar sesión
    private BottomNavigationView bottomNavigationView;//BottomNavigationView, menu inferior
    private View navView;//Vista del NavigationView
    private SharedPreferences myPrefs;//Mis preferencias
    private SharedPreferences prefs; //Preferencias de la preference screen
    private SharedPreferences.Editor editor;//editor de preferencias
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private GoogleSignInClient mGoogleSignInCliente;//Sign in Google
    private FirebaseAuth mAuth;//FireBase Auth
    private NotificationsManager notificationsManager;//notification manager
    private Bundle bundle;//savedInstanceState

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = savedInstanceState;

        //inicializo toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicializo menu inferior
        bottomNavigationView = findViewById(R.id.bottomMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //inicializar drawer
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //inicializar menu lateral
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Inicializo vista y sus componentes en el menu lateral
        navView = navigationView.getHeaderView(0);
        bLogin = navView.findViewById(R.id.bLogin);
        ImageView ivSesion = navView.findViewById(R.id.ivFotoUser);
        ivSesion.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Obtengo cliente Google
        mGoogleSignInCliente = GoogleSignIn.getClient(this,gso);

        //Inicializo base de datos FireBase
        db = FirebaseFirestore.getInstance();
        reference = db.collection(TAG);

        //preferencias
        myPrefs = getSharedPreferences("Admin", Context.MODE_PRIVATE);
        editor = myPrefs.edit();

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Selecciono por defecto la liga que hay en las preferencias
        switch (prefs.getString("opcionAbrirAppLiga","LaLiga Santander")){
            case "LaLiga Santander":
                editor.putInt("division", PRIMERA_DIVISION).apply();
                setTitle("LaLiga Santander");
                navigationView.setCheckedItem(R.id.primeraDivision);
                break;
            case "LaLiga SmartBank":
                editor.putInt("division", SEGUNDA_DIVISION).apply();
                setTitle("LaLiga SmartBank");
                navigationView.setCheckedItem(R.id.segundaDivision);
                break;
        }

        //Obtengo los equipos favoritos del usuario
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    if (!task.getResult().isEmpty()){
                        onGetEquiposFavoritos(task);
                    }else {
                        initFragment();
                    }
                }else {
                    Log.e(MainActivity.class.getSimpleName(),"Error al obtener los documentos" + task.getException());
                }
            }
        });

        //Cuando el usuario pulsa el boton inicia/cierra sesión
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myPrefs.getBoolean("log",false)){
                    signOut();
                }else {
                    signIn();
                }
            }
        });
    }

    /**
     * Cuando el usuario pulsa uno de los items del menu inferior
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.fragmentClasificacion://Selecciona Clasificación
                selectedFragment = new FragmentClasificacion();
                break;
            case R.id.fragmentJornada://Selecciona Jornada
                selectedFragment = new FragmentJornada();
                break;
            case R.id.fragmentDirecto://Selecciona Directo
                selectedFragment = new FragmentDirecto();
                break;
            case R.id.fragmentFavorito://Selecciona Favorito
                selectedFragment = new FragmentFavorito(listaEquipos,myPrefs.getBoolean("log",false));
                break;
            case R.id.fragmentPreference://Selecciona Configuración
                Intent i = new Intent(MainActivity.this, OpcionesActivity.class);
                startActivity(i);
                break;
        }
        //Set title de la activity, edpendiendo de la liga seleccioanda
        if (myPrefs.getInt("division",1)==1){
            setTitle("LaLiga Santander");
        }else {
            setTitle("LaLiga SmartBank");
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, selectedFragment)
                    .commit();
        }
        return true;
    };
    @Override
    /**
     * Cuando el usuario pincha en algunas de las ligas del menu laterla
     */
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment f;
        // Se ha hecho click en algún item del NavigationView
        int id = item.getItemId();

        if (id == R.id.primeraDivision) {
            editor.putInt("division", PRIMERA_DIVISION).apply();//pongo en las preferencias LaLiga Santander
            f = new FragmentClasificacion();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("LaLiga Santander");
        } else if (id == R.id.segundaDivision) {
            editor.putInt("division", SEGUNDA_DIVISION).apply();//pongo en las preferencias LaLiga SmartBank
            f = new FragmentClasificacion();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("LaLiga SmartBank");
        }
        drawer.closeDrawer(GravityCompat.START);
        bottomNavigationView.setSelectedItemId(R.id.fragmentClasificacion);
        return true;
    }

    /**
     * Iniciar Sesión
     */
    private void signIn(){
        Intent signInIntent = mGoogleSignInCliente.getSignInIntent();
        startActivityForResult(signInIntent, 1);
        editor = myPrefs.edit();
        editor.putBoolean("log", true).apply();
    }

    /**
     * Cerrar sesión
     */
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        TextView tvUser = navView.findViewById(R.id.tvNameUser);
        ImageView ivUser = navView.findViewById(R.id.ivFotoUser);

        tvUser.setText("Invitado");
        bLogin.setText("Iniciar Sesión");
        ivUser.setVisibility(View.GONE);

        editor = myPrefs.edit();
        editor.putBoolean("log", false).apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * Handle cuenta inicia sesión
     * @param task resultado handle
     */
    private void handleSignInResult(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount acc = task.getResult(ApiException.class);
            Toast.makeText(MainActivity.this, "Login correcto", Toast.LENGTH_LONG).show();
            firebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(MainActivity.this, "Error en el Login", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Autenticación ed Google
     * @param acc cuenta
     */
    private void firebaseGoogleAuth(GoogleSignInAccount acc){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUser(user);
                }else {

                }
            }
        });
    }

    /**
     * Actualizar usuario
     * @param user usuario firebase
     */
    private void updateUser(FirebaseUser user){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account!=null && user!=null){
            TextView tvUser = navView.findViewById(R.id.tvNameUser);
            ImageView ivUser = navView.findViewById(R.id.ivFotoUser);
            tvUser.setText(user.getEmail());
            ivUser.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(user.getPhotoUrl())
                    .into(ivUser);

            bLogin.setText("Cerrar sesión");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUser(currentUser);
    }

    /**
     * listener cuando recibe los equipos favoritos
     * @param task
     */
    @Override
    public void onGetEquiposFavoritos(Task<QuerySnapshot> task) {
        for (QueryDocumentSnapshot document : task.getResult()) {
            listaEquipos.add(document.toObject(Equipo.class));
        }
        notificationsManager = new NotificationsManager(listaEquipos,getApplicationContext());
        notificationsManager.init();
        initFragment();
    }

    /**
     * Inicializar fragment
     */
    private void initFragment(){
        int extra;
        int division;
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                extra = Integer.MIN_VALUE;
                division = Integer.MIN_VALUE;
            } else {
                extra= extras.getInt(EXTRA_MAIN);
                division= extras.getInt(EXTRA_DIVISION);
            }
        } else {
            extra= (int) getIntent().getExtras().getSerializable(EXTRA_MAIN);
            division= (int) getIntent().getExtras().getSerializable(EXTRA_DIVISION);
        }
        switch (division){
            case 1:
                editor.putInt("division", PRIMERA_DIVISION).apply();
                setTitle("LaLiga Santander");
                navigationView.setCheckedItem(R.id.primeraDivision);
                break;
            case 2:
                editor.putInt("division", SEGUNDA_DIVISION).apply();
                setTitle("LaLiga SmartBank");
                navigationView.setCheckedItem(R.id.segundaDivision);
                break;
        }
        if (extra==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentFavorito(listaEquipos,myPrefs.getBoolean("log",false))).commit();
            bottomNavigationView.setSelectedItemId(R.id.fragmentFavorito);
        }else if (extra==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentClasificacion()).commit();
            bottomNavigationView.setSelectedItemId(R.id.fragmentClasificacion);
        }else{
            switch (prefs.getString("opcionAbrirAppPestanya","Clasificación")){
                case "Clasificación":
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentClasificacion()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.fragmentClasificacion);
                    break;
                case "En Directo":
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentDirecto()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.fragmentDirecto);
                    break;
                case "Favoritos":
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentFavorito(listaEquipos,myPrefs.getBoolean("log",false))).commit();
                    bottomNavigationView.setSelectedItemId(R.id.fragmentFavorito);
                    break;
                case "Resultado":
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentJornada()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.fragmentJornada);
                    break;
            }
        }
    }
}