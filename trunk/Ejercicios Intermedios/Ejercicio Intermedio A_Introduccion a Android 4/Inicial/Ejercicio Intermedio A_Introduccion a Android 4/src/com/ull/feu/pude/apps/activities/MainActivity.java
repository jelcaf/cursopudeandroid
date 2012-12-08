package com.ull.feu.pude.apps.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ull.feu.pude.apps.R;

/**
 * 
 * En este {@link Activity} nos encargamos de cargar una lista desde XML y de permitir al usuario añadirle un elemento.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class MainActivity extends Activity {

	private ListView versionList;
	private ArrayAdapter<String> versionAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initConfig();
	}

	private void initConfig() {
		// cargar layout
		setContentView(R.layout.main);
		
		// asociar Views
		versionList = (ListView) findViewById(R.id.versionList);
		
		// obtenemos la lista de versiones desde el XML de forma que podamos modificarla
		ArrayList<String> listaDeVersiones = new ArrayList<String>();
		String[] lista = getResources().getStringArray(R.array.versionesDeAndroid);
		for (String s : lista)
			listaDeVersiones.add(s);
		
		// hacemos un Adaptador utilizando como base la lista de versiones que tenemos 
		versionAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listaDeVersiones);
		
		// asociamos el adaptador al ListView
		versionList.setAdapter(versionAdapter);
	}
	
	private void solicitarNuevaVersion() {
		
		// necesitamos que el Dialog sea final para poder referenciarlo dentro del método onClick() que 
		// hemos delcarado más abajo
		final Dialog dialogoNuevaVersion = new Dialog(this);
		
		// le damos un título al Dialog
		dialogoNuevaVersion.setTitle(getString(R.string.dialogo_titulo));
		
		// le damos un contenido (Views) al Dialog
		dialogoNuevaVersion.setContentView(R.layout.nueva_version_dialogo);
		
		// definimos qué tiene que hacer el botón del Dialog cuando el usuario lo pulse
		((Button)dialogoNuevaVersion.findViewById(R.id.botonDialogoNuevaVersion)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// obtenemos la nueva versión
				String nuevaVersion = ((EditText)dialogoNuevaVersion.findViewById(R.id.textoDialogoNuevaVersion)).getText().toString();
				
				// la añadimos a través del adaptador (el cual actualizará automáticamente los datos)
				versionAdapter.add(nuevaVersion);
				
				// hacemos desaparecer al dialog
				dialogoNuevaVersion.dismiss();
			}
		});
		
		// mostramos el dialog
		dialogoNuevaVersion.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Expande el menú añade ítems a la Barra de Aplicaciones si está presente
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// por defecto no hemos tratado el evento (delegamos el trabajo al SO)
		boolean eventoProcesado = false;
		
		switch (item.getItemId()) {
		case (R.id.menu_nuevo):
			solicitarNuevaVersion();
			// hemos procesado el evento nosotros
			eventoProcesado = true;
			break;
		default:
			break;
		}
		
		return eventoProcesado;
	}

}
