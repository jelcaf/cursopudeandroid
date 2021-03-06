package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ull.feu.pude.apps.R;

public class Location extends Activity {
	
	private static final String LOG_TAG = "LocationActivity";
	
	private TextView longitutdeTextView;
	private TextView latitudeTextView;
	private ToggleButton toggleButton;
	private Button boton;
	private LocationManager locMgr;
	private LocationListener onLocationChange;
	
	int latitud;
	int longitud;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	locMgr.removeUpdates(onLocationChange);
    }
    
    private void initConfig() {
    	setContentView(R.layout.main);
    	longitutdeTextView = (TextView) findViewById (R.id.longitudeTextView);
    	latitudeTextView = (TextView) findViewById (R.id.latitudeTextView);
    	toggleButton = (ToggleButton) findViewById (R.id.toggleGPS);
    	locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	boton = (Button) findViewById(R.id.Button01);
    	
    	onLocationChange = new LocationListener() {

			@Override
			public void onLocationChanged(android.location.Location location) {
				longitutdeTextView.setText("Longitud: " + location.getLongitude());
				latitudeTextView.setText("Latitud: " + location.getLatitude());
				longitud = (int) (location.getLongitude() * 1E6);
				latitud = (int) (location.getLatitude() * 1E6);
				Log.i(LOG_TAG, "Received Location update.");
			}
			@Override
			public void onProviderDisabled(String provider) {
				Log.i(LOG_TAG, "Location provider " + provider + " has been disabled.");				
			}

			@Override
			public void onProviderEnabled(String provider) {
				Log.i(LOG_TAG, "Location provider " + provider + " has been enabled.");				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				Log.i(LOG_TAG, "Location provider " + provider + " has changed status to " + status);
			}
    	};
    	android.location.Location location = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	if (location == null)
    		location = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	if (location != null) {
    		longitutdeTextView.setText("Longitud: " + location.getLongitude());
			latitudeTextView.setText("Latitud: " + location.getLatitude());
			longitud = (int) (location.getLongitude() * 1E6);
			latitud = (int) (location.getLatitude() * 1E6);
    	}
    	else {
    		longitutdeTextView.setText("Longitud: No disponible");
			latitudeTextView.setText("Latitud: No disponible");
    	}
    	
    	toggleButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (toggleButton.isChecked()){
					locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
							0, 0f, onLocationChange);
				}
				else {
					locMgr.removeUpdates(onLocationChange);
				}
			}
		});
    	
    	boton.setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
    			// c�digo de la funci�n
    			Intent i = new Intent (Location.this, Hello.class);
    			i.putExtra("longitud", longitud);
    			i.putExtra("latitud", latitud);
    			startActivity(i);
    		}
    	});
    }
    
}