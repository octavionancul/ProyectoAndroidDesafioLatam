package cl.octavionancul.proyectoandroiddesafiolatam.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.adapters.ExercisesAdapter;
import cl.octavionancul.proyectoandroiddesafiolatam.adapters.ExercisesCallback;
import cl.octavionancul.proyectoandroiddesafiolatam.data.Nodes;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Exercise;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExercisesFragment extends Fragment implements ExercisesCallback {

    public static final String EXERCISE = "cl.octavionancul.proyectoandroiddesafiolatam.KEY.EXERCISE";

    public ExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.exercisesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        //Definir el usuario
        FirebaseRecyclerOptions<Exercise> options = new FirebaseRecyclerOptions.Builder<Exercise>()
                .setQuery( new Nodes().userExercise(new CurrentUser().uid()),Exercise.class)
                .setLifecycleOwner(getActivity())
                .build();
        ExercisesAdapter adapter = new ExercisesAdapter(options,this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void clicked(Exercise exercise) {
        Intent intent = new Intent(getActivity(),SetsActivity.class);
        intent.putExtra(EXERCISE,exercise);
        startActivity(intent);
       // Toast.makeText(getContext(), exercise.getName(), Toast.LENGTH_SHORT).show();
    }
}
