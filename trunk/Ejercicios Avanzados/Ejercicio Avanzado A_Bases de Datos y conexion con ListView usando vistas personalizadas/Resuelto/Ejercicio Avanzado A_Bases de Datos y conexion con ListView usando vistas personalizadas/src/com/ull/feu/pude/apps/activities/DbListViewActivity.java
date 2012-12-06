package com.ull.feu.pude.apps.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ull.feu.pude.apps.R;

/**
 * Actividad simple que, como ya estamos acostumbrados, delega todo el trabajo a un Fragment.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class DbListViewActivity extends FragmentActivity {
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }
    
    private void initConfig() {
    	setContentView(R.layout.main_activity);
    }
    
}