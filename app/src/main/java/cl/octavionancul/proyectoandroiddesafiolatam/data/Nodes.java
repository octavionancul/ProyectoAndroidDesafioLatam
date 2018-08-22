package cl.octavionancul.proyectoandroiddesafiolatam.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Nodes {
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference users(){
        return  root.child("users");
    }

    public DatabaseReference user(String key){
        return  users().child(key);
    }

    public DatabaseReference exercises(){
        return root.child("exercises");
    }

    public DatabaseReference userExercise(String uid){
       return exercises().child(uid);
    }

    public DatabaseReference sets(){
        return root.child("sets");

    }

    public DatabaseReference userSet(String uid){
        return sets().child(uid);
    }

    public DatabaseReference userSetExercise(String uid,String key){
        return sets().child(uid).child(key);
    }
}
