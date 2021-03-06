package com.ull.feu.pude.apps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.ull.feu.pude.apps.R;

/**
 * 
 * En este {@link Fragment} queremos descubrir c�mo Android es capaz de simplificarnos el trabajo de adaptar la interfaz 
 * gr�fica de forma distinta a como ya hemos visto antes, adem�s de entender qu� es lo que verdaderamente ocurre durante la 
 * Rotaci�n, repasando el Ciclo de Vida de Activities y Fragments en Android, y por supuesto aprender a manejar nuestros datos 
 * cuando el usuario decida rotar su dispositivo.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class RotationalFragment extends Fragment {

	private static final String LOG_TAG = RotationalFragment.class.getSimpleName();
	
	private Spinner source;
	private EditText sourceText;
	private Spinner destination;
	
	// comentamos para evitar "ruido" en el LogCat
	// private String x;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, "onCreate()");
		if (savedInstanceState != null) {
			if (savedInstanceState.getString("x") != null) 
				Log.i(LOG_TAG, "Valor de x almacenado en el Bundle = " + savedInstanceState.getString("x"));
		}
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.main_fragment, container, false);      
        initConfig((ViewGroup)mainView);
        
        // �ste es un punto donde podemos operar sobre los datos que se encuentren dentro del savedInstanceState
        if (savedInstanceState != null) {
			if (savedInstanceState.getString("x") != null) 
				Log.i(LOG_TAG, "Valor de x almacenado en el Bundle = " + savedInstanceState.getString("x"));
		}
        
        return mainView;
    }
	
	private void initConfig(ViewGroup fragmentBody) {
    	Log.i(LOG_TAG, "initConfig()");
    	source = (Spinner) fragmentBody.findViewById(R.id.sourceLanguage);
    	sourceText = (EditText) fragmentBody.findViewById(R.id.sourceText);
    	destination = (Spinner) fragmentBody.findViewById(R.id.destinationLanguage);
    	// comentamos para evitar "ruido" en el LogCat
    	/*
    	x = "Inicializado.";
    	Log.i(LOG_TAG, "Valor de x = " + x);
    	*/
    	
    	// Array con las opciones de los Spinners
    	String[] languages = new String[] {"Espa�ol", "Ingl�s", "Franc�s", "Alem�n"};
    	
    	// Adaptador del Spinner lenguaje fuente
    	ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, languages);
    	sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	source.setAdapter(sourceAdapter);
    	
    	// ya no nos es necesario
    	/*
    	// x guarda el contenido de el texto fuente
    	sourceText.addTextChangedListener(new TextWatcher() {
	    	public void afterTextChanged(Editable s) {
	    		// despu�s de que el texto cambie, asignamos x
	    		x = s.toString();
	    		Log.i(LOG_TAG, "Valor de x = " + x);
	      	}
	       	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	       		// nada que hacer
	       	}
	       	public void onTextChanged(CharSequence s, int start, int before, int count) {
	       		// nada que hacer
	       	}
    	});
    	*/
    	
    	// Adaptador del Spinner lenguaje destino
    	ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, languages);
    	destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	destination.setAdapter(destinationAdapter);
    	// dejamos seleccionado el segundo elemento por defecto
    	destination.setSelection(1);
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// �ste es otro punto donde podemos operar sobre los datos que se encuentren dentro del savedInstanceState
	}
	
	@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onSaveInstanceState()");
    	savedInstanceState.putString("x", sourceText.getText().toString());
    	// Aqu� podemos guardar variables y datos que nos hagan falta
    }

	
}
