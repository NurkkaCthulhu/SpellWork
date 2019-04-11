package com.anumalm.spellwork;

public class JumpingJacks implements Workout {
    private String name;
    private int amount;
    private boolean enabled;
    private boolean completed;
    private boolean newest;
    private int graphic;

    public JumpingJacks(int amount, String name, boolean enabled, int graphic) {
        this.amount = amount;
        this.name = name;
        this.enabled = enabled;
        this.graphic = graphic;
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
