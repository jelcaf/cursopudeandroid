package com.ull.feu.pude.apps.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Actividad simple que encapsula al Fragment responsable del comportamiento de esta aplicaci—n..
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class RequestActivity extends FragmentActivity {
	
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