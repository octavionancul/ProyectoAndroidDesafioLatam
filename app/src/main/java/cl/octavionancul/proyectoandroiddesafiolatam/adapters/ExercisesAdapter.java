package cl.octavionancul.proyectoandroiddesafiolatam.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Exercise;

public class ExercisesAdapter extends FirebaseRecyclerAdapter<Exercise,ExercisesAdapter.ExerciseHolder>{


    private ExercisesCallback callback;

    public ExercisesAdapter(@NonNull FirebaseRecyclerOptions<Exercise> options,ExercisesCallback callback) {
        super(options);
        this.callback=callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ExerciseHolder holder, int position, @NonNull Exercise model) {

    holder.nameTv.setText(model.getName());
    holder.setsTv.setText(String.valueOf(model.getSets()));
    holder.repsTv.setText(String.valueOf(model.getReps()));
        holder.weightTv.setText(String.valueOf(model.getWeight()));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Exercise exercise = getItem(holder.getAdapterPosition());

            callback.clicked(exercise);
        }
    });

    }

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_exercise, parent, false);
        return new ExercisesAdapter.ExerciseHolder(view);
    }

    public static class ExerciseHolder extends RecyclerView.ViewHolder{

        private TextView nameTv,setsTv,repsTv,weightTv;
        public ExerciseHolder(View itemView) {
            super(itemView);

           nameTv = itemView.findViewById(R.id.nameTv);
           setsTv= itemView.findViewById(R.id.setsTv);
           repsTv= itemView.findViewById(R.id.repsTv);
            weightTv=itemView.findViewById(R.id.weightTv);


        }
    }
}
