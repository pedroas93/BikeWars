package co.edu.javeriana.bikewars.Logic;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.bikewars.Interfaces.LocationUpdater;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by jairo on 28/10/17.
 */

public class MapData{
    private static MapData instance = null;
    private static ReactiveLocationProvider provider;
    private static Disposable subscription;
    private Context context;
    private LatLng ubication;
    private List<MarkerOptions> markers;
    private List<LocationUpdater> listeners;

    private MapData(Context context) {
        ubication = new LatLng(4.624335, -74.063644);
        this.context = context;
        markers = new ArrayList<>();
        listeners = new ArrayList<>();
        provider = new ReactiveLocationProvider(context);
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
        synchronized (listeners){
            if(listeners.isEmpty()){
                LocationRequest mLocationRequest = new LocationRequest();
                mLocationRequest.setInterval(0);
                mLocationRequest.setFastestInterval(0);
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                subscription = provider.getUpdatedLocation(mLocationRequest).subscribe(new Consumer<Location>() {
                    @Override
                    public void accept(Location location) throws Exception {
                        synchronized (ubication){
                            ubication = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                        updateListeners();
                    }
                });
                listeners.add(listener);
            }else{
                listeners.add(listener);
            }
        }
    }

    public void unSuscribe(LocationUpdater client){
        synchronized (listeners){
            listeners.remove(client);
            if(listeners.isEmpty()){
                subscription.dispose();
            }
        }
    }

    public void updateListeners(){
        Log.i("Actualizando", "Ubicacion actualizada");
        synchronized (listeners){
                for(LocationUpdater listener: listeners){
                    synchronized (ubication){
                        listener.updateLocation(ubication);
                    }
                }
        }
    }
}
