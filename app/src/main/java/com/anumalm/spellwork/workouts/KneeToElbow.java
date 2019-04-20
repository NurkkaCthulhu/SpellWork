package com.anumalm.spellwork.workouts;

import com.anumalm.spellwork.R;

public class KneeToElbow implements Workout {
    private String name;
    private int amount;
    private boolean enabled;
    private boolean completed;
    private boolean newest;
    private int graphic;

    public KneeToElbow(int amount, boolean enabled) {
        this.amount = amount;
        this.name = "Knee to Elbow";
        this.enabled = enabled;
        this.graphic = R.drawable.kneetoelbow;
        this.completed = false;
        this.newest = true;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getGraphic() {
        return graphic;
    }

    public void setGraphic(int graphic) {
        this.graphic = graphic;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isNewest() {
        return newest;
    }

    public void setNewest(boolean newest) {
        this.newest = newest;
    }
}
