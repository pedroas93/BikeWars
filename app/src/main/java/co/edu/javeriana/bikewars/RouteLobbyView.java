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
import com.google.android.gms.maps.model.MarkerOptions;

public class RouteLobbyView extends AppCompatActivity implements OnMapReadyCallback{

    MapFragment mapFragment;
    GoogleMap map;

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
    public void onMapReady(GoogleMap map) {
        this.map = map;
        LatLng ubicacion = new LatLng(4.6275604, -74.0640883);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
        map.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title("Marker"));
        map.setIndoorEnabled(false);
    }
}
