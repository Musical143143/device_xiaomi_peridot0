package org.lineageos.settings.device;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class OnePlusSettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
            .beginTransaction()
            .replace(android.R.id.content, new OnePlusSettingsFragment())
            .commit();
    }
}
