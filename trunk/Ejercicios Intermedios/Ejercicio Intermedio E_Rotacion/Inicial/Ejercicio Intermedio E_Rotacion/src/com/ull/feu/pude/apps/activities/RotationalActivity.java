package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.ull.feu.pude.apps.R;

public class RotationalActivity extends Activity {
	
	private Spinner source;
	private EditText sourceText;
	private Spinner destination;
	private EditText destinationText;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }
    
    private void initConfig() {
    	setContentView(R.layout.main);
    	source = (Spinner) findViewById(R.id.sourceLanguage);
    	sourceText = (EditText) findViewById(R.id.sourceText);
    	destination = (Spinner) findViewById(R.id.destinationLanguage);
    	destinationText = (EditText) findViewById(R.id.destinationText);
    	
    	// Array con las opciones de los Spinners
    	String[] languages = new String[] {"Español", "Inglés", "Francés", "Alemán"};
    	
    	// Adaptador del Spinner lenguaje fuente
    	ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
    	sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	source.setAdapter(sourceAdapter);
    	
    	// Adaptador del Spinner lenguaje destino
    	ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
    	destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	destination.setAdapter(destinationAdapter);
    	// dejamos seleccionado el segundo elemento por defecto
    	destination.setSelection(1);
    }
}