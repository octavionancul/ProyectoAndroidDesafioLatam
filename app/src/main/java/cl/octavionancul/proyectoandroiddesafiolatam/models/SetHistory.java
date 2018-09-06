package cl.octavionancul.proyectoandroiddesafiolatam.models;

public class SetHistory {

    private String date;
    private int volume;
    public SetHistory(String date, int volume) {
        this.date = date;
        this.volume = volume;
    }



    public SetHistory() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }




}
