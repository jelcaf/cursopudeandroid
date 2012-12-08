package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ull.feu.pude.apps.R;

/**
 * 
 * En este {@link Activity} nos encargamos de cargar una lista desde XML y de permitir al usuario a–adirle un elemento.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initConfig();
	}

	private void initConfig() {
		// cargar layout
		setContentView(R.layout.main_activity);	
	}
	

}
