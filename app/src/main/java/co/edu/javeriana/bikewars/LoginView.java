package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class LoginView extends AppCompatActivity {

    //Firebase
    private FirebaseAuth mAuth;
    //GUI
    private EditText userTxt, passTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        userTxt = (EditText) findViewById(R.id.loginUserTxt);
        passTxt = (EditText) findViewById(R.id.loginPassTxt);
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(getBaseContext(), MainView.class));
                    finish();
                }
            }
        });
    }

    public void login(View context){
        mAuth.signInWithEmailAndPassword(userTxt.getText().toString(), passTxt.getText().toString());
    }

    public void newUserLaunch(View view){
        startActivity(new Intent(getBaseContext(), NewUserView.class));
    }
}
