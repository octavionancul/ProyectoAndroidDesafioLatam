package cl.octavionancul.proyectoandroiddesafiolatam.views;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviusSetsFragment extends Fragment implements SetsCallback {
    //private FloatingActionButton fab;
    private   String previusSet;
    public PreviusSetsFragment() {
        // Required empty public constructor
    }

    public static PreviusSetsFragment newInstance(){

        return new PreviusSetsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previus_sets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
       // fab.setVisibility(View.GONE);
        final Exercise exercise = (Exercise) getActivity().getIntent().getSerializableExtra(ExercisesFragment.EXERCISE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String dateFormat = format.format(date);
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

                        RecyclerView recyclerView = view.findViewById(R.id.sets2Rv);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

                        //Definir el usuario
                        FirebaseRecyclerOptions<Set> options = new FirebaseRecyclerOptions.Builder<Set>()
                                .setQuery( new Nodes().userSet(new CurrentUser().uid()).child(exercise.getKey()).child(previusSet),Set.class)
                                .setLifecycleOwner((LifecycleOwner) getContext())
                                .build();
                        SetsAdapter adapter = new SetsAdapter(options, (SetsCallback) getContext());
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



    }

    @Override
    public void update(int volume) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
