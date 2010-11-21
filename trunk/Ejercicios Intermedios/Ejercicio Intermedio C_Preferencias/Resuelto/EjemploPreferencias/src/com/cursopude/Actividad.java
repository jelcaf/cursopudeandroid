package com.cursopude;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Actividad extends Activity {
    private static final int MENU_PREFERENCIAS = 0;

    TextView tv;
    SharedPreferences p;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public void onResume () {
    	super.onResume();
        p = PreferenceManager.getDefaultSharedPreferences(this); 
        String texto = p.getString(Preferencias.__USERNAME_KEY__, "Ninguna Preferencia");
     	
        tv = (TextView) findViewById (R.id.tv);
        tv.setText(texto);
    }
    
	
    /* Creates the menu items */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_PREFERENCIAS, 0, "Preferencias");
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		switch(item.getItemId()) {
			case MENU_PREFERENCIAS:
	    		Intent i = new Intent(this, Preferencias.class);
				startActivity(i);
		}
		
		return super.onOptionsItemSelected(item);
	}
}