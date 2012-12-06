package com.ull.feu.pude.apps.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ull.feu.pude.apps.R;
import com.ull.feu.pude.apps.adapters.DbCryptoAlgorithmAdapter;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithmSQLiteHelper;

/**
 * 
 * Esta clase es la que solía realizar la mayor parte del trabajo en otros ejercicios, sin embargo en éste podemos 
 * darnos cuenta cómo casi todo el grueso del código recae sobre el {@link com.ull.feu.pude.apps.adapters.DbCryptoAlgorithmAdapter adaptador} 
 * y el {@link com.ull.feu.pude.apps.wrappers.DbCryptoAlgorithmWrapper wrapper} de las vistas.
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
     * Método privado donde conectaremos la lista con la base de datos. Fijaos que al contrario que en la versión inicial, aquí 
     * no tenemos problemas con la API del {@link SimpleCursorAdapter}, ya que nos hemos hecho nuestro propio adaptador. 
     */
    @SuppressLint("NewApi")
	private void initConfig(ViewGroup viewGroup) {
    	
    	miListView = (ListView) viewGroup.findViewById(R.id.myList);
    	db = (new DbCryptoAlgorithmSQLiteHelper(getActivity())).getWritableDatabase();
    	c = DbCryptoAlgorithm.getAll(db);
    	ListAdapter adapter;
    	
		adapter = new DbCryptoAlgorithmAdapter(getActivity(), c);
    	miListView.setAdapter(adapter);
    }
	
	/**
     * Este método es llamado justo antes de detruirse el fragment. Es aquí donde cerramos el cursor y la base de datos.
     */
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	c.close();
    	db.close();
    }
	
}
