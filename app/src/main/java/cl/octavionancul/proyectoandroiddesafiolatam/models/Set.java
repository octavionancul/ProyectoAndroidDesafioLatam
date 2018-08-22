package cl.octavionancul.proyectoandroiddesafiolatam.models;

import com.google.firebase.database.Exclude;

public class Set {

    private int number,reps,weight;
    private String key;
    private String exercise;
    private String date;

    public Set() {
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Exclude
    public int getVolume() {
        return reps * weight;
    }
}
