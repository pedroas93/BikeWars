package co.edu.javeriana.bikewars.Logic;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Todesser on 28/10/2017.
 */

class UserData {
    private static final UserData ourInstance = new UserData();
    private static final String observablesRoot = "db/observables/";
    private static final String usersRoot = "db/users/";
    private static final String racesRoot = "db/races/";
    private static final String routesRoot = "db/routes/";
    private static final String groupsRoot = "db/groupes/";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDb;
    private Usuario user;
    private List<UsuarioObservable> amigos;

    static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDb = FirebaseDatabase.getInstance();
    }
}
