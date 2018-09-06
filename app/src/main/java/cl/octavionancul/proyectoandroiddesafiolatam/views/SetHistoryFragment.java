package cl.octavionancul.proyectoandroiddesafiolatam.views;


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

import java.util.ArrayList;
import java.util.List;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.adapters.SetHistoryAdapater;
import cl.octavionancul.proyectoandroiddesafiolatam.models.SetHistory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetHistoryFragment extends Fragment {

  private SetHistoryAdapater adapter;
    public SetHistoryFragment() {
        // Required empty public constructor
    }

    public static SetHistoryFragment newInstance(){

        return new SetHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.setHistoryRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        List<SetHistory> setHistoryList = new ArrayList<>();

        setHistoryList.add(new SetHistory("20180820",800));
        setHistoryList.add(new SetHistory("20180821",820));
        setHistoryList.add(new SetHistory("20180822",850));
        setHistoryList.add(new SetHistory("20180823",870));
        setHistoryList.add(new SetHistory("20180824",890));

        adapter = new SetHistoryAdapater(setHistoryList);
        recyclerView.setAdapter(adapter);
    }
}
