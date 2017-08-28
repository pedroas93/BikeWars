package co.edu.javeriana.bikewars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FriendsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_view);
    }

    public void newGroup(View view){
        startActivity(new Intent(this, NewGroupView.class));
    }

    public void searchFriends(View view){

    }
}
