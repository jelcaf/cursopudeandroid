package com.ull.feu.pude.apps.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ull.feu.pude.apps.activities.R;
import com.ull.feu.pude.apps.threads.RequestThread;

/**
 * Este Fragment es el medio a través del cual el usuario puede solicitar información a cerca de una determinada posición 
 * GPS (longitud, latitud). Básicamente, su función es la de recoger los datos, solicitar que se realice la petición, 
 * recibir el mensaje correspondiente y mostrárselo al usuario. La petición en sí se realiza en un hilo por separado 
 * representado por la clase {@link RequestThread} que se ejecuta en segundo plano.
 * 
 * Nota: veréis que esta clase tiene un código un tanto extraño para "apagar" un determinado aviso de error; esto es porque 
 * no resulta nada recomendable tener un {@link Handler} como objeto interno sin que esté unido a ningún contexto en sí (el 
 * Handler va unido al ciclo de ejecución de la aplicación), por lo que podría quedarse en memoria de forma eterna. Una 
 * recomendación un tanto compleja es unirlo al Contexto que lo crea (la {@link Activity} del fragment) encapsulándolo en 
 * un elemento del tipo {@link WeakReference}, pero decididmos que añadir tal código al ejercicio os haría más difícil entenderlo.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

@SuppressLint("HandlerLeak")
public class RequestFragment extends Fragment {

	// llave para colocar / obtener los mensajes
	public static final String __RESPONSE_KEY__ = "responseKey";
	
	private EditText latitudeET;
	private EditText longitudeET;
	private EditText resultET;
	private Button requestButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, null);
		initConfig((ViewGroup)view);
		return view;
	}
	
	private void initConfig(ViewGroup viewGroup) {
		// Obtener Views
    	latitudeET = (EditText) viewGroup.findViewById(R.id.latitudeET);
    	longitudeET = (EditText) viewGroup.findViewById(R.id.longitudeET);
    	resultET = (EditText) viewGroup.findViewById(R.id.resultET);
    	resultET.setVisibility(View.INVISIBLE);
    	requestButton = (Button) viewGroup.findViewById(R.id.requestButton);
    	
    	// Asignar listeners
    	requestButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				makeRequest();
			}
		});
	}
	
	private void makeRequest() {
    	// desactivar el botón y los EditTexts para prevenir errores
    	latitudeET.setEnabled(false);
    	longitudeET.setEnabled(false);
    	requestButton.setEnabled(false);
    	
    	
    	// realizar la petición web
    	RequestThread requestThread = new RequestThread(latitudeET.getText().toString(), 
    			longitudeET.getText().toString(), myHandler);
    	
    	// Atención: NUNCA utilizar el método "run()", siempre el "start()"
    	// La diferencia es que run() se ejecuta en este mismo hilo de ejecución, 
    	// mientras que start() automáticamente crea otro y se encarga de llevar 
    	// a cabo la ejecución
    	requestThread.start();
    }
	
	// receptor de mensajes
	Handler myHandler = new Handler() {
    	
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				// se obtuvo respuesta
				resultET.setText((String)msg.getData().get(__RESPONSE_KEY__));
			}
			if (msg.what == 1) {
				// se produjo un error
				resultET.setText(getString(R.string.error));
			}
			// activamos todo
			resultET.setVisibility(View.VISIBLE);
	    	latitudeET.setEnabled(true);
	    	longitudeET.setEnabled(true);
	    	requestButton.setEnabled(true);
		}
    };
	
}
