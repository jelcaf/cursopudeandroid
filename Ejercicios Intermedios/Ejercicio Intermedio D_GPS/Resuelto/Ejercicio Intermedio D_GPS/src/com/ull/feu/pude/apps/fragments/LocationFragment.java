package com.ull.feu.pude.apps.fragments;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ull.feu.pude.apps.R;

/**
 * 
 * Con este {@link Fragment} pretendemos que los alumnos le pierdan el miedo a utilizar servicios más avanzados de Android como 
 * son la geo-localización. No solo es este código relativamente simple, sino que además es eficiente 
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class LocationFragment extends Fragment {

	private static final String LOG_TAG = LocationFragment.class.getSimpleName();
	
	private TextView longitutdeTextView;
	private TextView latitudeTextView;
	private ToggleButton toggleButton;
	private LocationManager locMgr;
	private LocationListener onLocationChange;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.main_fragment, container, false);      
        initConfig((ViewGroup)mainView);
        return mainView;
    }
	
    private void initConfig(ViewGroup fragmentBody) {
    	
    	// enlazamos nuestras variables con las vistas del XML
    	longitutdeTextView = (TextView) fragmentBody.findViewById (R.id.longitudeTextView);
    	latitudeTextView = (TextView) fragmentBody.findViewById (R.id.latitudeTextView);
    	toggleButton = (ToggleButton) fragmentBody.findViewById (R.id.toggleGPS);
    	locMgr = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    	
    	// inicializamos el listener para las actualizaciones de posición
    	onLocationChange = new LocationListener() {

			@Override
			public void onLocationChanged(android.location.Location location) {
				longitutdeTextView.setText("Longitud: " + location.getLongitude());
				latitudeTextView.setText("Latitud: " + location.getLatitude());
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
    	
    	// inicializamos los TextViews con la última posición conocida, si la tenemos
    	android.location.Location location = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	if (location == null)
    		location = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	if (location != null) {
    		longitutdeTextView.setText("Longitud: " + location.getLongitude());
			latitudeTextView.setText("Latitud: " + location.getLatitude());
    	}
    	else {
    		longitutdeTextView.setText("Longitud: No disponible");
			latitudeTextView.setText("Latitud: No disponible");
    	}
    	
    	// programamos el botón con estado para que active o desactive nuestra solicitud al sistema operativo
    	// de recibir actualizaciones de posición
    	toggleButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (toggleButton.isChecked()){
					// activamos localización
					enableLocation();
				}
				else {
					// desactivamos la localización
					disableLocation();
				}
			}
		});
    }
    
    private void enableLocation() {
    	locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
				0, 0f, onLocationChange);
    	// siempre y cuando NO estemos utilizando el emulador
    	// podemos utilizar este método perfectamente
    	if (!isRunningOnEmulator()) {
    		locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 
				0, 0f, onLocationChange);
    	}
    }
    
    private void disableLocation() {
    	// independientemente de cuántos proveedores de posición tenemos
    	// (GPS o Red), esta única línea se ocupa de des-registrarnos de todos
    	locMgr.removeUpdates(onLocationChange);
    }
    
    /**
     * @return true si estamos estamos siendo ejecutados en un emulador
     */
    private boolean isRunningOnEmulator() {
    	return (Build.MODEL.contains("sdk"));
    }
    
    // Opcionales
    
    @Override
    public void onResume() {
    	super.onResume();
    	// si el botón está activado (estamos solicitando co-ordenadas GPS)
    	if (toggleButton.isChecked()) {
    		// activamos localización
    		enableLocation();
    	}
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	// si el botón está activado (estamos solicitando co-ordenadas GPS)
    	if (toggleButton.isChecked()) {
    		// desactivamos la localización
    		disableLocation();
    	}
    }
	
}
