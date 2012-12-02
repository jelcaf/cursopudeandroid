package com.ull.feu.pude.apps.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ull.feu.pude.apps.R;

/**
 * 
 * Al igual que en otros ejercicios, esta {@link FragmentActivity Activity} resulta ser el contenedor de
 * un {@link Fragment} donde verdaderamente realizamos todo el trabajo de este ejercicio.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class LocationActivity extends FragmentActivity {
		
	
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