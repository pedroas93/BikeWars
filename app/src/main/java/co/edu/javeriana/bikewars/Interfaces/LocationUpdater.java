package co.edu.javeriana.bikewars.Interfaces;

import com.google.android.gms.maps.model.LatLng;

import co.edu.javeriana.bikewars.Logic.Ruta;

/**
 * Created by jairo on 28/10/17.
 */

public interface LocationUpdater {
    void updateLocation(LatLng location);
    void updateRoute(Ruta route);
}
