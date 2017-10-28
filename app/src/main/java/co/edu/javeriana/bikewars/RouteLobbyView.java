package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import co.edu.javeriana.bikewars.Interfaces.LocationUpdater;
import co.edu.javeriana.bikewars.Logic.MapData;

public class RouteLobbyView extends AppCompatActivity implements OnMapReadyCallback, LocationUpdater{

    MapFragment mapFragment;
    GoogleMap map;
    Marker ubicacion = null;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_lobby_view);
        mAuth = FirebaseAuth.getInstance();
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mainMap);
        mapFragment.getMapAsync(this);
    }

    public void newRouteLaunch(View view){
        startActivity(new Intent(this, NewRouteView.class));
    }

    public void historicLaunch(View view){
        startActivity(new Intent(this, HistoricView.class));
    }

    public void searchRouteLaunch(View view){
        startActivity(new Intent(this, SearchView.class));
    }

    public void friendsLaunch(View view){
        startActivity(new Intent(this, FriendsView.class));
    }

    public void racesLaunch(View view){
        startActivity(new Intent(this, RaceView.class));
    }

    @Override
    protected void onStop(){
        super.onStop();
        MapData.getInstance(getBaseContext()).unSuscribe(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        MapData data = MapData.getInstance(getBaseContext());
        for(MarkerOptions mark: data.getMarkers()){
            googleMap.addMarker(mark);
        }
        MapData.getInstance(getBaseContext()).addListener(this);
    }

    @Override
    public void updateLocation(LatLng location) {
        if(ubicacion!=null){
            ubicacion.remove();
            ubicacion = map.addMarker(new MarkerOptions().position(location).title("Ubicacion"));
        }else {
            ubicacion = map.addMarker(new MarkerOptions().position(location).title("Ubicacion"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainMenuLogout:
                mAuth.signOut();
                startActivity(new Intent(getBaseContext(), LoginView.class));
                finish();
                return true;
            case R.id.mainMenuConfig:
                // TODO: 27/10/2017 Lanzamiento de configuracion.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
