package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.javeriana.bikewars.Logic.UserData;

public class SearchView extends AppCompatActivity {

    ListView resultados;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        resultados = (ListView) findViewById(R.id.searchResultList);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference(UserData.routesRoot);
    }

    public void mainLaunch(View view){
        startActivity(new Intent(this, MainView.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
