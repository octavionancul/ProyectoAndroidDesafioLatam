package cl.octavionancul.proyectoandroiddesafiolatam.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;

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
public class SetsFragment extends Fragment implements SetsCallback{

    LinearLayout linearLayout ;
    public SetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Exercise exercise = (Exercise) getActivity().getIntent().getSerializableExtra(ExercisesFragment.EXERCISE);
        Log.d("ejercicio fragment",exercise.getName());

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String dateFormat = format.format(date);
        RecyclerView recyclerView = view.findViewById(R.id.setsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        //Definir el usuario
        FirebaseRecyclerOptions<Set> options = new FirebaseRecyclerOptions.Builder<Set>()
                .setQuery( new Nodes().userSet(new CurrentUser().uid()).child(exercise.getKey()).child(dateFormat),Set.class)
                .setLifecycleOwner(this)
                .build();
        SetsAdapter adapter = new SetsAdapter(options,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void update(int volume) {

    }
}
