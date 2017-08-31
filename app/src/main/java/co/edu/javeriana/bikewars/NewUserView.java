package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewUserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_view);
    }

    public void createUserBtn(View view){
        createUser();
        startActivity(new Intent(this, MainView.class));
        finish();
    }

    private void createUser() {
        //Aqui se crea al usuario.
    }
}
