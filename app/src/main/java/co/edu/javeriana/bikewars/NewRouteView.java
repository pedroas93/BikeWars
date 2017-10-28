package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import co.edu.javeriana.bikewars.Logic.MapData;
import co.edu.javeriana.bikewars.Logic.Ruta;

public class NewRouteView extends AppCompatActivity implements OnMapReadyCallback {

    private MapFragment mapFragment;
    private GoogleMap map;
    private Address salida, llegada;
    private MarkerOptions salidaMarker, llegadaMarker;
    private Marker salidaMark, llegadaMark;
    private PolylineOptions poli;
    private Polyline poliRuta;
    private Button salidaBtn, llegadaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route_view);
        salidaBtn = (Button) findViewById(R.id.newRouteStartBtn);
        llegadaBtn = (Button) findViewById(R.id.newRouteEndBtn);
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.newRouteMap);
        mapFragment.getMapAsync(this);
    }

    public void selectionSalidaLaunch(View view){
        startActivityForResult(new Intent(getBaseContext(), SelectionView.class), 1);
    }
    public void selectionLlegadaLaunch(View view){
        startActivityForResult(new Intent(getBaseContext(), SelectionView.class), 2);
    }

    public void mainLaunch(View view){
        MapData.getInstance(getBaseContext()).setRoute(new Ruta(salidaMarker, llegadaMarker, poli));
        finish();
    }

    public void shareLaunch(View view){
        startActivity(new Intent(this, ShareView.class));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        LatLng ubicacion = new LatLng(4.6275604, -74.0640883);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 11));
        map.setIndoorEnabled(false);
        salidaBtn.setEnabled(true);
        llegadaBtn.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == 1){
                    if(salidaMark!=null){
                        salidaMark.remove();
                    }
                    salida = (Address)data.getExtras().get("endPoint");
                    salidaBtn.setText(salida.getFeatureName());
                    salidaMarker = new MarkerOptions().position(new LatLng(salida.getLatitude(), salida.getLongitude())).title(salida.getFeatureName());
                    salidaMark = map.addMarker(salidaMarker);
                    if(llegada!=null){
                        trazarRuta(new LatLng(salida.getLatitude(), salida.getLongitude()), new LatLng(llegada.getLatitude(), llegada.getLongitude()));
                    }
                }else{
                    Toast.makeText(this, "Debe seleccionar una opcion valida", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(resultCode == 1){
                    if(llegadaMark!=null){
                        llegadaMark.remove();
                    }
                    llegada = (Address)data.getExtras().get("endPoint");
                    llegadaBtn.setText(llegada.getFeatureName());
                    llegadaMarker = new MarkerOptions().position(new LatLng(llegada.getLatitude(), llegada.getLongitude())).title(llegada.getFeatureName());
                    llegadaMark = map.addMarker(llegadaMarker);
                    if(salida!=null){
                        trazarRuta(new LatLng(salida.getLatitude(), salida.getLongitude()), new LatLng(llegada.getLatitude(), llegada.getLongitude()));
                    }
                }
                else{
                    Toast.makeText(this, "Debe seleccionar una opcion valida", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void trazarRuta(LatLng inicio, LatLng llegada){
        if(poliRuta!=null){
            poliRuta.remove();
        }
        Routing ruta = new Routing.Builder()
                .travelMode(Routing.TravelMode.DRIVING)
                .withListener(new RoutingListener() {
                    @Override
                    public void onRoutingFailure(RouteException e) {
                        Log.i("Ruta", "Fallo");
                    }

                    @Override
                    public void onRoutingStart() {
                        Log.i("Ruta", "Inicio");
                    }

                    @Override
                    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
                        Log.i("Ruta", "Entro en la ruta");
                        Route route = arrayList.get(i);
                        PolylineOptions poly = new PolylineOptions();
                        poly.color(Color.argb(255,171, 85, 251));
                        poly.width(10);
                        poly.addAll(route.getPoints());
                        poli=poly;
                        poliRuta = map.addPolyline(poly);
                        Toast.makeText(getBaseContext(), "La distancia es de: " + route.getDistanceText(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRoutingCancelled() {
                        Log.i("Ruta", "Cancelo");
                    }
                })
                .waypoints(inicio, llegada)
                .build();
        ruta.execute();
    }
}
