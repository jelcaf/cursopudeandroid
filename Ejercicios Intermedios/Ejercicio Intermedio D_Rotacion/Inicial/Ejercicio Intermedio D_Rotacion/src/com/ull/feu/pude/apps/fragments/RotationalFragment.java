package com.ull.feu.pude.apps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
 * En este {@link Fragment} queremos descubrir cómo Android es capaz de simplificarnos el trabajo de adaptar la interfaz 
 * gráfica de forma distinta a como ya hemos visto antes, además de entender qué es lo que verdaderamente ocurre durante la 
 * Rotación, repasando el Ciclo de Vida de Activities y Fragments en Android, y por supuesto aprender a manejar nuestros datos 
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
	private EditText destinationText;
	
	private String x;
	
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
    	Log.i(LOG_TAG, "initConfig()");
    	source = (Spinner) fragmentBody.findViewById(R.id.sourceLanguage);
    	sourceText = (EditText) fragmentBody.findViewById(R.id.sourceText);
    	destination = (Spinner) fragmentBody.findViewById(R.id.destinationLanguage);
    	destinationText = (EditText) fragmentBody.findViewById(R.id.destinationText);
    	x = "Inicializado.";
    	Log.d(LOG_TAG, "Valor de x = " + x);
    	
    	// Array con las opciones de los Spinners
    	String[] languages = new String[] {"Español", "Inglés", "Francés", "Alemán"};
    	
    	// Adaptador del Spinner lenguaje fuente
    	ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, languages);
    	sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	source.setAdapter(sourceAdapter);
    	
    	// x guarda el contenido de el texto fuente
    	sourceText.addTextChangedListener(new TextWatcher() {
	    	public void afterTextChanged(Editable s) {
	    		// despuÈs de que el texto cambie, asignamos x
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
    	ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, languages);
    	destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	destination.setAdapter(destinationAdapter);
    	// dejamos seleccionado el segundo elemento por defecto
    	destination.setSelection(1);
    }
	
}
