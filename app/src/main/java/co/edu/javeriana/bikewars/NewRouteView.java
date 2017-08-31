package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewRouteView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route_view);
    }

    public void selectionLaunch(View view){
        startActivity(new Intent(this, SelectionView.class));
    }

    public void mainLaunch(View view){
        startActivity(new Intent(this, MainView.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void shareLaunch(View view){
        startActivity(new Intent(this, ShareView.class));
    }
}
