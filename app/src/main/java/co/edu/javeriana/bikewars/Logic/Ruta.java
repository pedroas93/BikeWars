package co.edu.javeriana.bikewars.Logic;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by Todesser on 28/10/2017.
 */

public class Ruta {
    private MarkerOptions salida;
    private MarkerOptions llegada;
    private PolylineOptions ruta;

    public Ruta(MarkerOptions salida, MarkerOptions llegada, PolylineOptions ruta) {
        this.salida = salida;
        this.llegada = llegada;
        this.ruta = ruta;
    }

    public MarkerOptions getSalida() {
        return salida;
    }

    public void setSalida(MarkerOptions salida) {
        this.salida = salida;
    }

    public MarkerOptions getLlegada() {
        return llegada;
    }

    public void setLlegada(MarkerOptions llegada) {
        this.llegada = llegada;
    }

    public PolylineOptions getRuta() {
        return ruta;
    }

    public void setRuta(PolylineOptions ruta) {
        this.ruta = ruta;
    }
}
