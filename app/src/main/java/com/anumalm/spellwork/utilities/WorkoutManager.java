package com.anumalm.spellwork.utilities;

import com.anumalm.spellwork.workouts.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutManager {
    List<Workout> workouts;

    public void addEnabledWorkoutsToList() {
        workouts = new ArrayList<>();
    }

}
