package cl.octavionancul.proyectoandroiddesafiolatam.models;

import java.util.List;

public class SetGroup {
    private String key;
    private int volume;
    private List<Set> setList;

    public SetGroup() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public List<Set> getSetList() {
        return setList;
    }

    public void setSetList(List<Set> setList) {
        this.setList = setList;
    }
}
