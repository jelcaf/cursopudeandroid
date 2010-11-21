package com.cursopude;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class Preferencias extends PreferenceActivity {
	
	public static String __USERNAME_KEY__ = "text_preference";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initConfig();
	}
	
	private void initConfig() {
		addPreferencesFromResource(R.xml.settings);
	}
}

