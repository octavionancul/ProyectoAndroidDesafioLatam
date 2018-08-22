package cl.octavionancul.proyectoandroiddesafiolatam.views;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.adapters.SetsAdapter;
import cl.octavionancul.proyectoandroiddesafiolatam.adapters.SetsCallback;
import cl.octavionancul.proyectoandroiddesafiolatam.data.Nodes;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Exercise;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Set;

public class SetsActivity extends AppCompatActivity implements SetsCallback {

    private   String previusSet;
    LinearLayout linearLayout ;
    private TextView volumeTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
      //  linearLayout = findViewById(R.id.previusSetsLy);
        final Exercise exercise = (Exercise) getIntent().getSerializableExtra(ExercisesFragment.EXERCISE);
        volumeTv=findViewById(R.id.volumeTotalTv);
        getSupportActionBar().setTitle(exercise.getName());

    /*   final EditText setRepEt = findViewById(R.id.setRepEt);
       final EditText setWeightEt = findViewById(R.id.setWeightEt);
        Button saveSetBtn = findViewById(R.id.saveSetBtn);*/

     /*   String key = new Nodes().sets().push().getKey();
        Set set = new Set();
        set.setNumber(1);
        set.setReps(10);
        set.setWeight(20);
        Log.d("key set",key);
        new Nodes().userSet("jljnljhodhojsi").child(exercise.getKey()).child(key).setValue(set);*/
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String dateFormat = format.format(date);
        RecyclerView recyclerView = findViewById(R.id.setsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //Definir el usuario
        FirebaseRecyclerOptions<Set> options = new FirebaseRecyclerOptions.Builder<Set>()
                .setQuery( new Nodes().userSet(new CurrentUser().uid()).child(exercise.getKey()).child(dateFormat),Set.class)
                .setLifecycleOwner(this)
                .build();
        SetsAdapter adapter = new SetsAdapter(options,this);
        recyclerView.setAdapter(adapter);

/*
        saveSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int reps = Integer.parseInt(setRepEt.getText().toString());
               int weight = Integer.parseInt(setWeightEt.getText().toString());

               String key = new Nodes().sets().push().getKey();
        Set set = new Set();
        set.setKey(key);
        set.setExercise(exercise.getKey());
        set.setReps(reps);
        set.setWeight(weight);
        Log.d("key set",key);
        new Nodes().userSet("jljnljhodhojsi").child(exercise.getKey()).child(key).setValue(set);

               // Toast.makeText(SetsActivity.this, "dd", Toast.LENGTH_SHORT).show();
            }
        });

        setRepEt.setText(String.valueOf(exercise.getReps()));
        setWeightEt.setText(String.valueOf(exercise.getWeight()));*/

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child("sets").child(new CurrentUser().uid()).child(exercise.getKey()).orderByKey().limitToLast(2);

        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  String message = dataSnapshot.child("message").getValue().toString();
             //  long set = dataSnapshot.getChildrenCount();
               // Log.d("Count " , String.valueOf(dataSnapshot.getChildrenCount()));
               // List<Set> setList = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                     Set set = postSnapshot.getChildren().iterator().next().getValue(Set.class);
                 //  Log.d("Get Data", postSnapshot.getKey());
                    if(set.getDate().equals(dateFormat)){

                    }else{
                       previusSet=set.getDate();
                        Log.d("fecha distinta actual", set.getDate());
                    }
                    if(previusSet!=null) {
                      //buscar nodo con esta key y mostrar lista  en un RV


                   //    linearLayout.setVisibility(View.VISIBLE);

                        RecyclerView recyclerView = findViewById(R.id.sets2Rv);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SetsActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(SetsActivity.this, LinearLayoutManager.VERTICAL));

                        //Definir el usuario
                        FirebaseRecyclerOptions<Set> options = new FirebaseRecyclerOptions.Builder<Set>()
                                .setQuery( new Nodes().userSet(new CurrentUser().uid()).child(exercise.getKey()).child(previusSet),Set.class)
                                .setLifecycleOwner(SetsActivity.this)
                                .build();
                        SetsAdapter adapter = new SetsAdapter(options,SetsActivity.this);
                        recyclerView.setAdapter(adapter);


                        Log.d("set anterior", previusSet);
                    }

                    Log.d("Get Data2", set.getDate());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Handle possible errors.
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This is what you are looking for
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag(DialogFragmentSerie.TAG);
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                DialogFragment dialogFragment = DialogFragmentSerie.newInstance(exercise);
                dialogFragment.show(ft, DialogFragmentSerie.TAG);
            }
        });



    }

    @Override
    public void update(int volume) {
        volumeTv.setText(String.valueOf(volume));
      //  Toast.makeText(this, String.valueOf(volume), Toast.LENGTH_SHORT).show();
    }
}
