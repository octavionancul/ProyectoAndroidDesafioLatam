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
