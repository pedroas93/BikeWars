package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
    }

    public void configLaunch(View view){
        startActivity(new Intent(this, ConfigView.class));
    }

    public void friendsLaunch(View view){
        startActivity(new Intent(this, FriendsView.class));
    }

    public void routeLaunch(View view){
        startActivity(new Intent(this, RouteLobbyView.class));
    }
}
