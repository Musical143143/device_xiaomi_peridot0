package org.lineageos.settings.device;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TouchUtils {
    private static final String TAG = "OnePlusSettings";

    // Primary & Fallback Nodes for OPlus SM7675 Kernel 6.1
    public static final String[] NODES_DT2W = {
        "/proc/touchpanel/double_tap_enable",
        "/sys/devices/platform/goodix_ts.0/double_tap_enable"
    };

    public static final String[] NODES_GAME_MODE = {
        "/proc/touchpanel/game_switch_enable",
        "/proc/touchpanel/high_rate_mode"
    };

    public static final String[] NODES_EDGE_LIMIT = {
        "/proc/touchpanel/oplus_tp_limit_enable",
        "/proc/touchpanel/edge_limit_enable"
    };

    public static final String[] NODES_GESTURES = {
        "/proc/touchpanel/gesture_enable",
        "/proc/touchpanel/tp_gesture"
    };

    public static final String[] NODES_DC = {
        "/sys/kernel/oplus_display/dimlayer_bl_en",
        "/sys/devices/virtual/mi_display/main_display/dimlayer_bl"
    };

    public static final String[] NODES_HBM = {
        "/sys/kernel/oplus_display/hbm",
        "/sys/kernel/oplus_display/hbm_mode"
    };

    public static final String[] NODES_VIBRATOR = {
        "/sys/class/leds/vibrator/level",
        "/sys/class/leds/vibrator/vmax"
    };

    public static final String[] NODES_TRISTATE = {
        "/proc/tri-state-key/tri_state",
        "/sys/devices/platform/soc/soc:tri_state_key/tri_state"
    };

    public static boolean writeNodeArray(String[] paths, String value) {
        for (String path : paths) {
            File f = new File(path);
            if (f.exists() && f.canWrite()) {
                try (FileOutputStream fos = new FileOutputStream(f)) {
                    fos.write(value.getBytes());
                    fos.flush();
                    return true;
                } catch (IOException e) {
                    Log.e(TAG, "Failed writing to: " + path, e);
                }
            }
        }
        return false;
    }

    public static boolean writeNodeArray(String[] paths, boolean enable) {
        return writeNodeArray(paths, enable ? "1" : "0");
    }
}
