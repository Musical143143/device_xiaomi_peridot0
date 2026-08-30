package org.lineageos.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.preference.PreferenceManager;

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()) &&
            !Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction())) {
            return;
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        TouchUtils.writeNodeArray(TouchUtils.NODES_DT2W, prefs.getBoolean("pref_dt2w", true));
        TouchUtils.writeNodeArray(TouchUtils.NODES_GAME_MODE, prefs.getBoolean("pref_game_mode", false));
        TouchUtils.writeNodeArray(TouchUtils.NODES_EDGE_LIMIT, prefs.getBoolean("pref_edge_limit", true));
        TouchUtils.writeNodeArray(TouchUtils.NODES_GESTURES, prefs.getBoolean("pref_gestures", false));
        TouchUtils.writeNodeArray(TouchUtils.NODES_DC, prefs.getBoolean("pref_dc_dimming", false));
        TouchUtils.writeNodeArray(TouchUtils.NODES_HBM, prefs.getBoolean("pref_hbm", false));

        int vibLevel = prefs.getInt("pref_vib_strength", 80);
        TouchUtils.writeNodeArray(TouchUtils.NODES_VIBRATOR, String.valueOf(vibLevel));

        // Restore Refresh Rate
        float rate = Float.parseFloat(prefs.getString("pref_refresh_rate", "0"));
        if (rate == 0) {
            Settings.System.putFloat(context.getContentResolver(), Settings.System.MIN_REFRESH_RATE, 60.0f);
            Settings.System.putFloat(context.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, 120.0f);
        } else {
            Settings.System.putFloat(context.getContentResolver(), Settings.System.MIN_REFRESH_RATE, rate);
            Settings.System.putFloat(context.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, rate);
        }

        // Start Alert Slider Background Watcher
        context.startService(new Intent(context, AlertSliderService.class));
    }
}
