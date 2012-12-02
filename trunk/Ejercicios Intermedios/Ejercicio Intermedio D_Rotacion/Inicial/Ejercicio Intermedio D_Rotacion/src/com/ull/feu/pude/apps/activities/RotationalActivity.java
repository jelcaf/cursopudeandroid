package com.ull.feu.pude.apps.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.ull.feu.pude.apps.R;

/**
 * 
 * Nuevamente, esta {@link Activity} es un veh’culo para poder utilizar el {@link Fragment} donde 
 * verdaderamente se encuentra la implementaci—n de este ejercicio.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class RotationalActivity extends FragmentActivity {
	
	private static final String LOG_TAG = RotationalActivity.class.getSimpleName();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate()");
        initConfig();
    }

    private void initConfig() {
    	setContentView(R.layout.main_activity);
    }
    
}