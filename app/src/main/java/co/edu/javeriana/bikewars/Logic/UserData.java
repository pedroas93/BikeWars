package co.edu.javeriana.bikewars.Logic;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Todesser on 28/10/2017.
 */

public class UserData {
    private static final UserData ourInstance = new UserData();
    public static final String observablesRoot = "db/observables/";
    public static final String usersRoot = "db/users/";
    public static final String racesRoot = "db/races/";
    public static final String routesRoot = "db/routes/";
    public static final String groupsRoot = "db/groupes/";
    public static final String markersRoot = "db/markers/";
    public static final String mailBoxRoot = "db/mailBox/";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDb;

    public static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDb = FirebaseDatabase.getInstance();
    }

    public FirebaseUser getUser(){
        return mUser;
    }
}
