package com.cgi.formation.monstaffing.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

/**
 * Created by barruetr on 23/02/2018.
 */

public class HockeyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Your own code to create the view
        // ...

        checkForUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ... your own onResume implementation
        checkForCrashes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }
}
