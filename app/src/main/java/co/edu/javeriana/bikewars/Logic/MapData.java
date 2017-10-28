package co.edu.javeriana.bikewars.Logic;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.bikewars.Interfaces.LocationUpdater;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by jairo on 28/10/17.
 */

public class MapData{
    private static MapData instance = null;
    private Context context;
    private LatLng ubication;
    private List<MarkerOptions> markers;
    private List<LocationUpdater> listeners;
    private OnLocationUpdatedListener locationListener;

    private MapData(Context context) {
        ubication = new LatLng(4.624335, -74.063644);
        this.context = context;
        markers = new ArrayList<>();
        listeners = new ArrayList<>();
        SmartLocation.with(context).location().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                Log.i("Actualizacion", "Listener activado");
                synchronized (ubication){
                    ubication = new LatLng(location.getLatitude(), location.getLongitude());
                }
                updateListeners();
            }
        });
    }

    public static MapData getInstance(Context context){
        if(instance==null){
            instance = new MapData(context);
        }
        return instance;
    }

    public LatLng getUbication(){
        synchronized (ubication){
            return ubication;
        }
    }

    public Marker addMarker(GoogleMap map, MarkerOptions options){
        markers.add(options);
        return map.addMarker(options);
    }

    public List<MarkerOptions> getMarkers(){
        return markers;
    }

    public void addListener(LocationUpdater listener){
        listeners.add(listener);
    }

    public void updateListeners(){
        for(LocationUpdater listener: listeners){
            synchronized (ubication){
                listener.updateLocation(ubication);
                Log.i("Actualizador", "Ubicacion actualizada.");
            }
        }
    }
}
