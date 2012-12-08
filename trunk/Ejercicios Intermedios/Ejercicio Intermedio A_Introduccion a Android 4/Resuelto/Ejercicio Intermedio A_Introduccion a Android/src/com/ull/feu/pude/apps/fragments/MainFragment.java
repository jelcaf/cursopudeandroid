package com.ull.feu.pude.apps.fragments;

import java.util.ArrayList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ull.feu.pude.apps.R;
// import android.app.Fragment;

/**
 * 
 * Éste es el {@link Fragment} que se encarga de todo el trabajo en esta aplicación. Se encarga de cargar la lista desde XML, 
 * de reaccionar cuando el botón correcto es pulsado, de llamar al Dialog y de registrar un nuevo elemento en la lista 
 * de versiones.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class MainFragment extends Fragment {

	private ListView versionList;
	private ArrayAdapter<String> versionAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// este Fragment utiliza menús
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		// expandimos una nueva vista
		View v = inflater.inflate(R.layout.main_fragment, container, false);
		// llamamos al initConfig() pasándole lo que es el ViewGroup con el contenido de la pantalla
		initConfig((ViewGroup)v);
		// retornamos
		return v;
	}
	
	private void initConfig(ViewGroup viewGroup) {
		// asociar Views
		versionList = (ListView) viewGroup.findViewById(R.id.versionList);
				
		// obtenemos la lista de versiones desde el XML de forma que podamos modificarla
		ArrayList<String> listaDeVersiones = new ArrayList<String>();
		String[] lista = getResources().getStringArray(R.array.versionesDeAndroid);
		for (String s : lista)
			listaDeVersiones.add(s);
				
		// hacemos un Adaptador utilizando como base la lista de versiones que tenemos 
		versionAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaDeVersiones);
				
		// asociamos el adaptador al ListView
		versionList.setAdapter(versionAdapter);
	}
	
	private void solicitarNuevaVersion() {
		
		// necesitamos que el Dialog sea final para poder referenciarlo dentro del método onClick() que 
		// hemos delcarado más abajo
		final Dialog dialogoNuevaVersion = new Dialog(getActivity());
		
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Expande el menú añade ítems a la Barra de Aplicaciones si está presente
		inflater.inflate(R.menu.main, menu);
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
