package org.lineageos.settings.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreferenceCompat;

public class OnePlusSettingsFragment extends PreferenceFragmentCompat
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.oneplus_settings, rootKey);

        bindSwitch("pref_dt2w");
        bindSwitch("pref_game_mode");
        bindSwitch("pref_edge_limit");
        bindSwitch("pref_gestures");
        bindSwitch("pref_dc_dimming");
        bindSwitch("pref_hbm");

        SeekBarPreference vib = findPreference("pref_vib_strength");
        if (vib != null) vib.setOnPreferenceChangeListener(this);

        bindList("pref_slider_top");
        bindList("pref_slider_middle");
        bindList("pref_slider_bottom");
        bindList("pref_refresh_rate");
    }

    private void bindSwitch(String key) {
        SwitchPreferenceCompat pref = findPreference(key);
        if (pref != null) pref.setOnPreferenceChangeListener(this);
    }

    private void bindList(String key) {
        ListPreference pref = findPreference(key);
        if (pref != null) pref.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        Context ctx = getContext();

        if ("pref_dt2w".equals(key)) {
            return TouchUtils.writeNodeArray(TouchUtils.NODES_DT2W, (Boolean) newValue);
        } else if ("pref_game_mode".equals(key)) {
            return TouchUtils.writeNodeArray(TouchUtils.NODES_GAME_MODE, (Boolean) newValue);
        } else if ("pref_edge_limit".equals(key)) {
            return TouchUtils.writeNodeArray(TouchUtils.NODES_EDGE_LIMIT, (Boolean) newValue);
        } else if ("pref_gestures".equals(key)) {
            return TouchUtils.writeNodeArray(TouchUtils.NODES_GESTURES, (Boolean) newValue);
        } else if ("pref_dc_dimming".equals(key)) {
            return TouchUtils.writeNodeArray(TouchUtils.NODES_DC, (Boolean) newValue);
        } else if ("pref_hbm".equals(key)) {
            return TouchUtils.writeNodeArray(TouchUtils.NODES_HBM, (Boolean) newValue);
        } else if ("pref_vib_strength".equals(key)) {
            int level = (Integer) newValue;
            return TouchUtils.writeNodeArray(TouchUtils.NODES_VIBRATOR, String.valueOf(level));
        } else if ("pref_refresh_rate".equals(key)) {
            if (ctx != null) {
                float rate = Float.parseFloat((String) newValue);
                if (rate == 0) {
                    Settings.System.putFloat(ctx.getContentResolver(), Settings.System.MIN_REFRESH_RATE, 60.0f);
                    Settings.System.putFloat(ctx.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, 120.0f);
                } else {
                    Settings.System.putFloat(ctx.getContentResolver(), Settings.System.MIN_REFRESH_RATE, rate);
                    Settings.System.putFloat(ctx.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, rate);
                }
            }
            return true;
        } else if (key.startsWith("pref_slider_")) {
            if (ctx != null) {
                ctx.startService(new Intent(ctx, AlertSliderService.class));
            }
            return true;
        }
        return false;
    }
}
