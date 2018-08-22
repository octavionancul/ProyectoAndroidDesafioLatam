package cl.octavionancul.proyectoandroiddesafiolatam.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import cl.octavionancul.proyectoandroiddesafiolatam.views.CurrentUser;
import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.data.Nodes;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Set;

public class SetsAdapter extends FirebaseRecyclerAdapter<Set,SetsAdapter.SetHolder>{

    private int volume=0 ;
    private SetsCallback callback;
    public SetsAdapter(@NonNull FirebaseRecyclerOptions<Set> options,SetsCallback callback) {
        super(options);
        this.callback=callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull final SetHolder holder, int position, @NonNull Set model) {

        holder.numberTv.setText(String.valueOf(position+1));
        holder.repsTv.setText(String.valueOf(model.getReps()));
        holder.weightTv.setText(String.valueOf(model.getWeight()));
//Usar otro adapter y list item para sumar el volumen
        volume=volume+(model.getWeight()*model.getReps());
        Log.d("volume", String.valueOf(volume));
        callback.update(volume);
        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener la key del set para eliminar.
                Set set = getItem(holder.getAdapterPosition());
                Log.d("key delete set", String.valueOf(set.getKey()));
               new Nodes().sets().child(new CurrentUser().uid()).child(set.getExercise()).child(set.getDate()).child(set.getKey()).removeValue();
            }
        });


    }

    @NonNull
    @Override
    public SetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_set, parent, false);
        return new SetsAdapter.SetHolder(view);
    }

    public static class SetHolder extends RecyclerView.ViewHolder{

        private TextView numberTv,repsTv,weightTv;
        private ImageView deleteIv;

        public SetHolder(View itemView) {
            super(itemView);

            numberTv=itemView.findViewById(R.id.setNumTv);
            repsTv=itemView.findViewById(R.id.setRepTv);
            weightTv=itemView.findViewById(R.id.setWeightTv);
            deleteIv=itemView.findViewById(R.id.deleteSetBtn);
        }
    }


}
