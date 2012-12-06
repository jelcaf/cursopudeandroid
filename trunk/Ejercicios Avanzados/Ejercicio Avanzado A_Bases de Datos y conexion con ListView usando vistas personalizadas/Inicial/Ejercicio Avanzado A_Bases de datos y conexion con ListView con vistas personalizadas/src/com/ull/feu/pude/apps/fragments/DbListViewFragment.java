package com.ull.feu.pude.apps.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ull.feu.pude.apps.R;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithmSQLiteHelper;

/**
 * 
 * Esta clase es la que sol�a realizar la mayor parte del trabajo en otros ejercicios, sin embargo en �ste podemos 
 * darnos cuenta c�mo casi todo el grueso del c�digo recae sobre el {@link SimpleCursorAdapter} y las clases encargadas de representar
 * los distintos elementos de la base de datos ({@link DbCryptoAlgorithm} y {@link DbCryptoAlgorithmSQLiteHelper}) 
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class DbListViewFragment extends Fragment {

	private ListView miListView;
	private Cursor c;
	private SQLiteDatabase db;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.main_fragment, container, false);
		initConfig((ViewGroup)view);
		return view;
	}
	
	/**
     * M�todo privado donde conectamos la lista con la base de datos. Fijaos en que aqu� nos encontramos con un peque�o problema, 
     * y es que el Adaptador est�ndar de Android que queremos utilizar, el {@link SimpleCursorAdapter}, resulta que tiene 
     * APIs desfasadas seg�n la versi�n de Android, por tanto nos vemos obligados a preguntarle al SO en qu� versi�n nos 
     * encontramos y utilizar la que corresponde en dicho caso (la t�cnica utilizado se conoce como "Java Reflection").
     */
    @SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void initConfig(ViewGroup viewGroup) {
    	
    	miListView = (ListView) viewGroup.findViewById(R.id.myList);
    	db = (new DbCryptoAlgorithmSQLiteHelper(getActivity())).getWritableDatabase();
    	c = DbCryptoAlgorithm.getAll(db);
    	ListAdapter adapter;
    	
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			// c�digo para Android Antiguo (antes de 3.0 o Honeycomb)
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, c,
					new String[] { DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__, },
					new int[] { R.id.rowTitle, });
		} else {
			// c�digo para Android Moderno (3.0 Honeycomb o superior)
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, c,
					new String[] { DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__, },
					new int[] { R.id.rowTitle, }, 0);
		}
    	miListView.setAdapter(adapter);
    }
	
    /**
     * Este m�todo es llamado justo antes de detruirse el fragment. Es aqu� donde cerramos el cursor y la base de datos.
     */
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	c.close();
    	db.close();
    }
	
}
