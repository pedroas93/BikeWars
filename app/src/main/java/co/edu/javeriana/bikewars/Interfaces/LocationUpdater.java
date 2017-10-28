package co.edu.javeriana.bikewars.Interfaces;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import co.edu.javeriana.bikewars.Logic.Ruta;

/**
 * Created by jairo on 28/10/17.
 */

public interface LocationUpdater {
    void updateLocation(MarkerOptions location, List<MarkerOptions> markers, Ruta route);
}
