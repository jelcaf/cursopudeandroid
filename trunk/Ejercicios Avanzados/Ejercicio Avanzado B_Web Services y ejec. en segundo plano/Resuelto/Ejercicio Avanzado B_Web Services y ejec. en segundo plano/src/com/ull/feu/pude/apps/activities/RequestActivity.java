package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ull.feu.pude.apps.threads.RequestThread;

public class RequestActivity extends Activity {
	
	public static final String __RESPONSE_KEY__ = "responseKey";
	
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
    	RequestThread requestThread = new RequestThread(latitudeET.getText().toString(), 
    			longitudeET.getText().toString(), myHandler);
    	requestThread.run();
    }
    
    Handler myHandler = new Handler() {
    	
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				// response
				resultET.setText((String)msg.getData().get(RequestActivity.__RESPONSE_KEY__));
			}
			if (msg.what == 1) {
				resultET.setText(getString(R.string.error));
			}
			// activamos todo
	    	latitudeET.setEnabled(true);
	    	longitudeET.setEnabled(true);
	    	requestButton.setEnabled(true);
		}
    };
}