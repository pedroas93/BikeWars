package co.edu.javeriana.bikewars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SocialView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_view);
    }

    public void done(View view){
        finish();
    }
}
