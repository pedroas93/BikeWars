package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import co.edu.javeriana.bikewars.Interfaces.LocationUpdater;
import co.edu.javeriana.bikewars.Logic.MapData;

public class RouteLobbyView extends AppCompatActivity implements OnMapReadyCallback, LocationUpdater{

    MapFragment mapFragment;
    GoogleMap map;
    Marker ubicacion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_lobby_view);
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

    @Override
    protected void onStop() {
        super.onStop();

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
}
