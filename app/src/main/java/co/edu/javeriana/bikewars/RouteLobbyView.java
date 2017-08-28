package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RouteLobbyView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_lobby_view);
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
}
