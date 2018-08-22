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
import android.widget.EditText;
import android.widget.TextView;

import cl.octavionancul.proyectoandroiddesafiolatam.R;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Exercise;

public class DialogFragmentSerie extends DialogFragment implements View.OnClickListener, CreateSerie.Callback {

    public static final String TAG = "YOUR.PACKAGE.COM.DialogFragmentSerie.tag.TAG";
    private View closeIv, setsTil, repsTil, weightTil, btn, loading;
    private TextView errorTv;
    private EditText reps;
    private EditText weight;
    private Exercise exercise;

    public static DialogFragmentSerie newInstance(Exercise exercise) {

        DialogFragmentSerie f = new DialogFragmentSerie();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putSerializable("exe",exercise);
        f.setArguments(args);
        return f;
       // return new DialogFragmentSerie();
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
        return inflater.inflate(R.layout.fragment_dialog_serie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       exercise = (Exercise) getArguments().getSerializable("exe");

        reps = view.findViewById(R.id.reps2Et);
        reps.setText(String.valueOf(exercise.getReps()));

        weight = view.findViewById(R.id.weight2Et);
        weight.setText(String.valueOf(exercise.getWeight()));
        Log.d("key fragment dialog", String.valueOf(exercise.getReps()));
        closeIv = view.findViewById(R.id.closeIv);
        //setsTil = view.findViewById(R.id.setsTil);
        repsTil = view.findViewById(R.id.repsTil);
        weightTil = view.findViewById(R.id.weightTil);
        btn = view.findViewById(R.id.serieBtn);
        loading = view.findViewById(R.id.seriePb);
        errorTv = getView().findViewById(R.id.errorTv);


        btn.setOnClickListener(this);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void loading(){
        setCancelable(false);
        closeIv.setVisibility(View.GONE);
        //setsTil.setVisibility(View.GONE);
        repsTil.setVisibility(View.GONE);
        weightTil.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        errorTv.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    private void restore(){
        closeIv.setVisibility(View.VISIBLE);
        //setsTil.setVisibility(View.VISIBLE);
        repsTil.setVisibility(View.VISIBLE);
        weightTil.setVisibility(View.VISIBLE);
        btn.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        setCancelable(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );
    }


    @Override
    public void onClick(View view) {
        loading();
       // EditText sets = getView().findViewById(R.id.setsEt);
//       EditText reps = getView().findViewById(R.id.repsEt);

   //     EditText weight = getView().findViewById(R.id.weightEt);
        new CreateSerie(this).withData(
               exercise.getKey(),
                reps.getText().toString(),
                weight.getText().toString()
        );
    }

    @Override
    public void done() {
        dismiss();
    }

    @Override
    public void allFieldRequired() {
        restore();
        setError("Todos los campos son requeridos");
    }

    @Override
    public void cannotBeZero() {
        restore();
        setError("No puede ser cero");
    }

    private void setError(String error){
        errorTv.setText(error);
        errorTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void error() {
        restore();
    }

}
