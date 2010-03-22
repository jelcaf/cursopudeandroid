package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ull.feu.pude.apps.R;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithmSQLiteHelper;

public class DbListViewActivity extends Activity {
	
	private ListView miListView;
	private Cursor c;
	private SQLiteDatabase db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }
    
    /**
     * Llamado justo antes de detruirse la actividad.
     * Siempre hay que cerrar la base de datos aquí.
     */
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	db.close();
    }
    
    /**
     * Método privado donde conectaremos la lista con la base de datos.
     */
    private static String[] FROM = {DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__, };
    private static int[] TO = {R.id.rowTitle, };
    private void initConfig() {
    	setContentView(R.layout.main);
    	miListView = (ListView) findViewById(R.id.myList);
    	db = (new DbCryptoAlgorithmSQLiteHelper(this)).getWritableDatabase();
    	c = DbCryptoAlgorithm.getAll(db);
    	startManagingCursor(c);
    	ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item, c, 
    			new String[] {DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__, }, 
    			new int[] {R.id.rowTitle, });
    	miListView.setAdapter(adapter);
    }
}