package org.lineageos.settings.device;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;
import androidx.preference.PreferenceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AlertSliderService extends Service {
    private static final String TAG = "OnePlusSliderService";
    private AudioManager mAudioManager;
    private FileObserver mObserver;
    private String mActiveNode = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        for (String path : TouchUtils.NODES_TRISTATE) {
            if (new File(path).exists()) {
                mActiveNode = path;
                break;
            }
        }

        if (mActiveNode != null) {
            startObserving(mActiveNode);
            readAndApplyCurrentState();
        } else {
            Log.w(TAG, "No tri-state sysfs/proc node found. Relying on KeyHandler.");
        }
    }

    private void startObserving(String path) {
        mObserver = new FileObserver(path, FileObserver.MODIFY) {
            @Override
            public void onEvent(int event, String file) {
                readAndApplyCurrentState();
            }
        };
        mObserver.startWatching();
    }

    private synchronized void readAndApplyCurrentState() {
        if (mActiveNode == null) return;
        try (BufferedReader br = new BufferedReader(new FileReader(mActiveNode))) {
            String line = br.readLine();
            if (line != null) {
                int position = Integer.parseInt(line.trim());
                applySliderPosition(position);
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error reading slider state", e);
        }
    }

    public void applySliderPosition(int position) {
        // Position: 1 = Top, 2 = Middle, 3 = Bottom
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int mode = 2; // Default Normal

        if (position == 1) {
            mode = Integer.parseInt(prefs.getString("pref_slider_top", "0"));
        } else if (position == 2) {
            mode = Integer.parseInt(prefs.getString("pref_slider_middle", "1"));
        } else if (position == 3) {
            mode = Integer.parseInt(prefs.getString("pref_slider_bottom", "2"));
        }

        switch (mode) {
            case 0:
                mAudioManager.setRingerModeInternal(AudioManager.RINGER_MODE_SILENT);
                break;
            case 1:
                mAudioManager.setRingerModeInternal(AudioManager.RINGER_MODE_VIBRATE);
                break;
            case 2:
            default:
                mAudioManager.setRingerModeInternal(AudioManager.RINGER_MODE_NORMAL);
                break;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        readAndApplyCurrentState();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mObserver != null) mObserver.stopWatching();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
