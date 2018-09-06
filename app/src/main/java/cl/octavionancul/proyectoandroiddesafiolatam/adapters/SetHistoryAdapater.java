package cl.octavionancul.proyectoandroiddesafiolatam.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.models.SetHistory;

public class SetHistoryAdapater extends RecyclerView.Adapter<SetHistoryAdapater.SetHistoryHolder> {


    private List<SetHistory> setHistoryList;

    public SetHistoryAdapater(List<SetHistory> setHistoryList) {
        this.setHistoryList = setHistoryList;
    }

    @NonNull
    @Override
    public SetHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_set_history,parent,false);
        SetHistoryHolder viewHolder = new SetHistoryHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetHistoryHolder holder, int position) {
        SetHistory setHistory = setHistoryList.get(position);
        holder.dateTv.setText(setHistory.getDate());
        holder.volumeTv.setText(String.valueOf(setHistory.getVolume()));
    }

    @Override
    public int getItemCount() {
        return setHistoryList.size();
    }

    public static class SetHistoryHolder extends RecyclerView.ViewHolder{

        private TextView dateTv,volumeTv;
        public SetHistoryHolder(View itemView) {
            super(itemView);

            dateTv= itemView.findViewById(R.id.dateTv);
            volumeTv=itemView.findViewById(R.id.volumeTv);

        }
    }
}
