package cl.octavionancul.proyectoandroiddesafiolatam.views;

import android.os.Handler;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.octavionancul.proyectoandroiddesafiolatam.data.Nodes;
import cl.octavionancul.proyectoandroiddesafiolatam.models.Set;

public class CreateSerie {
    private Callback callback;

    public CreateSerie(Callback callback) {
        this.callback = callback;
    }

    void withData(String exercise, String sets, String weight) {
        //TODO DELETE THE 2 HANDLERS AND RUNNABLE ONLY DEMO
        try {
            //int numberReps = Integer.parseInt(reps);
            int numberSets = Integer.parseInt(sets);
            int numberWeight = Integer.parseInt(weight);

            if (/*numberReps > 0 && */numberSets > 0 && numberWeight > 0) {
                //TODO HERE CALL YOUR NODES AND CREATE THE OBJECT ON THE RTD

            /*    int reps = Integer.parseInt(setRepEt.getText().toString());
                int weight = Integer.parseInt(setWeightEt.getText().toString());*/
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                String dateFormat = format.format(date);
                String key = new Nodes().sets().push().getKey();
                Set set = new Set();
                set.setKey(key);
                set.setDate(dateFormat);
                set.setExercise(exercise);
                set.setReps(numberSets);
                set.setWeight(numberWeight);
                Log.d("key set",key);


                new Nodes().userSet(new CurrentUser().uid()).child(exercise).child(dateFormat).child(key).setValue(set);
             //   new Nodes().userSet(new CurrentUser().uid()).child(exercise).child(key).setValue(set);
                callback.done();
            /*    new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 1000);*/
            } else {
                callback.cannotBeZero();
            }

        } catch (NumberFormatException e) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    callback.allFieldRequired();
                }
            }, 1000);
        }
    }

    public interface Callback {

        void done();
        void allFieldRequired();
        void cannotBeZero();
        //TODO call error if the creation failed
        void error();

    }
}
