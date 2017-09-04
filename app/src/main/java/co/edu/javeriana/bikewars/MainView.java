package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
    }

    public void friendsLaunch(View view){
        startActivity(new Intent(this, FriendsView.class));
    }

    public void routeLaunch(View view){
        startActivity(new Intent(this, RouteLobbyView.class));
    }

    public void racesLaunch(View view){
        startActivity(new Intent(this, RaceView.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.configMenuItem:
                startActivity(new Intent(this, ConfigView.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
