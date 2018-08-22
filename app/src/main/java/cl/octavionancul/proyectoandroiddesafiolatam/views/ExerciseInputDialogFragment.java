package cl.octavionancul.proyectoandroiddesafiolatam.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.data.Nodes;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Exercise;

public class ExerciseInputDialogFragment extends DialogFragment {

    public static ExerciseInputDialogFragment newInstance() {
        return new ExerciseInputDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_exercise_input, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final EditText nameEt = view.findViewById(R.id.inputNameEt);
        final EditText setsEt = view.findViewById(R.id.inputSetsEt);
        final EditText repsEt = view.findViewById(R.id.inputRepsEt);
        final EditText weightEt = view.findViewById(R.id.inputWeightEt);

        Button button = view.findViewById(R.id.saveExerciseBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString();
                String sets = setsEt.getText().toString();
                String reps = repsEt.getText().toString();
                String weight = weightEt.getText().toString();

                if(name.trim().length()==0) {

                }else if (sets.trim().length()==0) {

                }else if (reps.trim().length()==0){

                }else if (weight.trim().length()==0){

                }else{

                int nsets = Integer.parseInt(sets);
                int nreps = Integer.parseInt(reps);
                int nweight = Integer.parseInt(weight);

                    String key = new Nodes().exercises().push().getKey();

        Exercise exe = new Exercise();
        exe.setKey(key);
     //   exe.setDescription("Ejercicio  con barra");
        exe.setName(name);
        exe.setOwner(new CurrentUser().uid());
        exe.setReps(nreps);
        exe.setSets(nsets);
        exe.setWeight(nweight);
        exe.setVolume(nreps*nsets*nweight);

        Log.d("key",key);

      new Nodes().userExercise(new CurrentUser().uid()).child(key).setValue(exe);
                    dismiss();

                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }
}
