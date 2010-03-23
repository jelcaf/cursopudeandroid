package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ull.feu.pude.apps.R;

/**
 * 
 * Clase que recoge los datos de dos EditTexts, los convierte de Strings a enteros, calcula el m�dulo 
 * de A entre B, y lo devuelve en un TextView.
 * 
 * @author Dinesh Harjani (goldrunner192287@gmail.com)
 *
 */

public class ModActivity extends Activity {
	
	/**
	 * Cuando declaramos una variable como static y final, conseguimos que sea lo m�s pr�ximo a una 
	 * constante de C/C++. La dejamos como privada porque cada clase debe tener su propio LOG_TAG �nico 
	 * que la identifique.
	 * �Para qu� lo usamos? Para escribir en el LogCat las acciones m�s importantes que vaya 
	 * realizando nuestr actividad. 
	 */
	private static final String LOG_TAG = "ModActivity";
	
	/*
	 * Variables (referencias)
	 */
	private EditText editTextA;
	private EditText editTextB;
	private TextView textViewA;
	private TextView textViewB;
	private TextView result;
	private Button action;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }
    
    /**
     * Tendremos este m�todo para inicializar la actividad, y lo separaremos del constructor de la actividad.
     */
    private void initConfig() {
    	setContentView(R.layout.main);
    	// unimos las referencias a las vistas de nuestro layout
    	textViewA = (TextView) findViewById(R.id.tvA);
    	editTextA = (EditText) findViewById(R.id.etA);
    	textViewB = (TextView) findViewById(R.id.tvB);
    	editTextB = (EditText) findViewById(R.id.etB);
    	result = (TextView) findViewById(R.id.tvResult);
    	action = (Button) findViewById(R.id.btAction);
    	// ponemos etiquetas a textView A, textView B y a Result
    	textViewA.setText(getString(R.string.tituloA));
    	textViewB.setText(getString(R.string.tituloB));
    	result.setText("Introduzca valores y pulse el bot�n");
    	action.setText("M�dulo");
    	
    	// definimos la acci�n del bot�n
    	action.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String res = "";
				String A = editTextA.getText().toString();
				String B = editTextB.getText().toString();
				try {
					int a = Integer.parseInt(A);
					int b = Integer.parseInt(B);
					res = "Resultado = " + myMod(a,b);
				}
				catch (Exception e) {
					Log.e(LOG_TAG, "Excepci�n al realizar el m�dulo de " 
							+ A + " entre " + B);
					res = "Se produjo una excepci�n.";
					Toast toast = Toast.makeText(getApplicationContext(), 
							"Perd�n, se produjo una excepci�n.", 
							Toast.LENGTH_LONG);
		        	toast.show();
				}
				result.setText(res);
			}
		});
    	Log.i(LOG_TAG, "Fin del initConfig(). Actividad lista.");
    }
    
    /**
     * Funci�n que, dados dos n�meros enteros, devuelve el m�dulo
     */
    private int myMod(int A, int B) {
    	return A % B;
    }
}