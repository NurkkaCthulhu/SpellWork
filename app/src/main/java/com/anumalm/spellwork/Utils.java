package com.anumalm.spellwork;

import android.view.View;

/**
 * Utils class holds useful methods that can be called from any other class.
 *
 * @author      Anu Malm     anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public final class Utils {

    /**
     * Private constructor so that Utils-object cannot be instantiated.
     */
    private Utils(){}

    /**
     * Sets the system UI visibility for the given view.
     *
     * After call, all the UI elements of Android are hidden unless dragged from the top,
     * and after that they'll disappear on their own.
     *
     * @param v         View that we want to hide the UI from.
     */
    public static void hideSystemUI(View v) {

        v.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
