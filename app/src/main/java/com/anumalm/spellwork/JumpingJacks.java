package com.anumalm.spellwork;

public class JumpingJacks implements Workout {
    private String name;
    private int amount;
    private boolean enabled;
    private int graphic;

    public JumpingJacks(int amount, String name, boolean enabled, int graphic) {
        this.amount = amount;
        this.name = name;
        this.enabled = enabled;
        this.graphic = graphic;
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
}
