package com.ull.feu.pude.apps.activities;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RequestActivity extends Activity {
	
	private EditText latitudeET;
	private EditText longitudeET;
	private EditText resultET;
	private Button requestButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }
    
    private void initConfig() {
    	setContentView(R.layout.main);
    	
    	// Obtener Views
    	latitudeET = (EditText) findViewById(R.id.latitudeET);
    	longitudeET = (EditText) findViewById(R.id.longitudeET);
    	resultET = (EditText) findViewById(R.id.resultET);
    	requestButton = (Button) findViewById(R.id.requestButton);
    	
    	// Asignar listeners
    	requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				makeRequest();
			}
		});
    }
    
    private void makeRequest() {
    	// desactivar el bot—n y los EditTexts para prevenir errores
    	latitudeET.setEnabled(false);
    	longitudeET.setEnabled(false);
    	requestButton.setEnabled(false);
    	
    	
    	// realizar la petici—n web
    	try {
    		
    		// obtenemos una conexi—n
    		DefaultHttpClient httpClient = new DefaultHttpClient();
	    	String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitudeET.getText().toString() + "," + longitudeET.getText().toString() + "&sensor=false";
	    	// creamos una petici—n de tipo GET (par‡metros en la URL)
	    	HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			// ejecutamos la petici—n
			HttpResponse response = httpClient.execute(request);
			
			// convertimos la respuesta en string
			String responseString = EntityUtils.toString(response.getEntity());
			
			// escribimos el resultado
			resultET.setText(responseString);
			
			// cerramos la conexi—n 
			httpClient.getConnectionManager().shutdown();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	// activamos todo
    	latitudeET.setEnabled(true);
    	longitudeET.setEnabled(true);
    	requestButton.setEnabled(true);
    	
    	//RequestThread requestThread = new RequestThread(myHandler);
    	//requestThread.run();
    }
    
    Handler myHandler = new Handler() {
    	
		@Override
		public void handleMessage(Message msg) {
			
		}
    };
}