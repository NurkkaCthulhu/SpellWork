package com.anumalm.spellwork;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anumalm.spellwork.utilities.Debug;
import com.anumalm.spellwork.utilities.MusicManager;
import com.anumalm.spellwork.utilities.Utils;

/**
 * Screen that shows the current workouts for the user.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class WorkoutActivity extends SpellworkActivity {

    /**
     * Overrides AppCompatActivity's onCreate-method.
     *
     * Sets the layout for the screen, hides system UI, gets the TextView and settings.
     *
     * @param savedInstanceState        Bundle that's somewhere in AppCompatActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        RelativeLayout top = findViewById(R.id.workoutBox_top);
        RelativeLayout middle = findViewById(R.id.workoutBox_middle);
        RelativeLayout bottom = findViewById(R.id.workoutBox_bottom);

        top.setOnClickListener(v -> {
            Debug.log("WORKOUT", "WorkoutActivity/onCreate", "top clicked", 1);
            increaseCurrency();
            top.setVisibility(View.INVISIBLE);
        });
        middle.setOnClickListener(v -> {
            Debug.log("WORKOUT", "WorkoutActivity/onCreate", "middle clicked", 1);
            increaseCurrency();
            middle.setVisibility(View.INVISIBLE);
        });
        bottom.setOnClickListener(v -> {
            Debug.log("WORKOUT", "WorkoutActivity/onCreate", "bottom clicked", 1);
            increaseCurrency();
            bottom.setVisibility(View.INVISIBLE);
        });

    }

    /**
     * Increase player's currency and save it to settings.
     */
    private void increaseCurrency() {
        SharedPreferences settings = getSharedPreferences("UserSettings", 0);
        SharedPreferences.Editor editor = settings.edit();
        int currentCurrency = settings.getInt("currency", -1);
        currentCurrency += 5;
        editor.putInt("currency", currentCurrency);
        editor.apply();
        showCustomToast();
    }

    /**
     * Shows a custom toast when user has clicked a workout.
     */
    private void showCustomToast() {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.spellwork_toast, findViewById(R.id.spellwork_toast));

        ImageView image = layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.currency);

        TextView text = layout.findViewById(R.id.text);
        text.setText("Well done! You gained 5 currency.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    /**
     * Called when back button has been clicked.
     *
     * Return the user to MainActivity (-> finishes this Activity).
     *
     * @param v                 The click source.
     */
    public void backButton(View v) {
        Utils.playButtonSound();
        MusicManager.setPause(false);
        finish();
    }
}
