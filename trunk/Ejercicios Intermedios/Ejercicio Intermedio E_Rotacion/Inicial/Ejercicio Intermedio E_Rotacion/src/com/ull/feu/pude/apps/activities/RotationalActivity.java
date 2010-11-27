package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.ull.feu.pude.apps.R;

public class RotationalActivity extends Activity {
	private static final String LOG_TAG = RotationalActivity.class.getSimpleName();
	
	private Spinner source;
	private EditText sourceText;
	private Spinner destination;
	private EditText destinationText;
	
	private String x;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate()");
        initConfig();
    }
    
    private void initConfig() {
    	setContentView(R.layout.main);
    	Log.i(LOG_TAG, "initConfig()");
    	source = (Spinner) findViewById(R.id.sourceLanguage);
    	sourceText = (EditText) findViewById(R.id.sourceText);
    	destination = (Spinner) findViewById(R.id.destinationLanguage);
    	destinationText = (EditText) findViewById(R.id.destinationText);
    	x = "Inicializado.";
    	Log.d(LOG_TAG, "Valor de x = " + x);
    	
    	// Array con las opciones de los Spinners
    	String[] languages = new String[] {"Español", "Inglés", "Francés", "Alemán"};
    	
    	// Adaptador del Spinner lenguaje fuente
    	ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
    	sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	source.setAdapter(sourceAdapter);
    	
    	// x guarda el contenido de el texto fuente
    	sourceText.addTextChangedListener(new TextWatcher() {
	    	public void afterTextChanged(Editable s) {
	    		// después de que el texto cambie, asignamos x
	    		x = s.toString();
	    		Log.d(LOG_TAG, "Valor de x = " + x);
	      	}
	       	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	       		// nada que hacer
	       	}
	       	public void onTextChanged(CharSequence s, int start, int before, int count) {
	       		// nada que hacer
	       	}
    	});
    	
    	// Adaptador del Spinner lenguaje destino
    	ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
    	destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	destination.setAdapter(destinationAdapter);
    	// dejamos seleccionado el segundo elemento por defecto
    	destination.setSelection(1);
    }
}