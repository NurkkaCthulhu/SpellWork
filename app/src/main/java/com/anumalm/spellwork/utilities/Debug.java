package com.anumalm.spellwork.utilities;

import android.util.Log;

import com.anumalm.spellwork.BuildConfig;

/**
 * Custom debug class.
 *
 * Used to print debug messages. Also makes sure that the log messages are not in build.
 *
 * @author      Anu Malm    anu.malm@tuni.fi
 * @version     2019.03.24
 * @since       1.0
 */
public class Debug {

    // Tells how many debug.logs we print. One level = one indent in class's code.
    private static final int DEBUG_LEVEL = 3;

    /**
     * Prints debug messages to log.
     *
     * @param tag           Used for Logcat filtering
     * @param source        Class/method that uses the Debug.log
     * @param message       Debug message
     * @param level         Indent level of the message.
     */
    public static void log(String tag, String source, String message, int level) {
        // BuildConfig.DEBUG is automatically turned to "false" at build.
        if(level <= DEBUG_LEVEL && BuildConfig.DEBUG) {
            Log.d(tag, "Debugging " + source + ": " + message);
        }
    }
}
