package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ull.feu.pude.apps.R;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithmSQLiteHelper;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm.TipoDeCifrado;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm.TipoDeSimetria;

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
     * Nótese los cambios, ya que ahora utilizamos nuestro propio adaptador para la lista.
     */
    private void initConfig() {
    	setContentView(R.layout.main);
    	miListView = (ListView) findViewById(R.id.myList);
    	db = (new DbCryptoAlgorithmSQLiteHelper(this)).getWritableDatabase();
    	c = DbCryptoAlgorithm.getAll(db);
    	startManagingCursor(c);
    	ListAdapter adapter = new DbCryptoAlgorithmAdapter(this, c);
    	miListView.setAdapter(adapter);
    }
    
    /**
     * Definimos nuestro propio adaptador para la lista. Los métodos a implementar son pocos y sencillos.
     * Los beneficios superan estas pocas líneas que nos permiten personalizar enormemente el 
     * funcionamiento de nuestras listas.
     * @author Dinesh Harjani (goldrunner192287@gmail.com)
     *
     */
    private class DbCryptoAlgorithmAdapter extends CursorAdapter {
    	
    	/**
    	 * Constructor. No hacemos nada más, salvo que lo que queramos hacer lo requiera.
    	 * @param context
    	 * @param c
    	 */
		public DbCryptoAlgorithmAdapter(Context context, Cursor c) {
			super(context, c);
		}

		/**
		 * Este método es llamado cuando no es necesario crear una nueva vista para la fila.
		 * Lo que hacemos es cargar los datos que debe mostrar la vista a partir de la clase 
		 * envoltorio o Wrapper, utilizando el método getTag(). Previamente, en cada fila, habrá asociado 
		 * un tag o etiqueta, que es un objeto de apoyo para poder manejar lo que debe mostrar 
		 * la vista.
		 */
		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			DbCryptoAlgorithmWrapper wrapper = (DbCryptoAlgorithmWrapper) view.getTag();
			wrapper.populateFrom(c);
		}

		/**
		 * Cuando es necesario crear (o inflar) una nueva vista para mostrar en pantalla una fila, 
		 * se llama a este método.
		 * Lo que haremos es inflar la vista que representa las filas, crear una clase Wrapper, 
		 * asignarle a la fila el wrapper y cargar los datos en las vistas que conforman la fila.
		 * @return Vista que representa la fila apuntada por el cursor.
		 */
		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.list_item, parent, false);
			DbCryptoAlgorithmWrapper wrapper = new DbCryptoAlgorithmWrapper(row);
			row.setTag(wrapper);
			wrapper.populateFrom(c);
			return row;
		}
    	
    }
    
    /**
     * Esta clase, que llamaremos Wrapper, se encarga de gestionar lo que se debe mostrar 
     * para cada fila de la base de datos. Aquí se traduce un objeto de tipo DbCryptoAlgorithm en 
     * una vista con el esquema list_item.xml
     * @author Dinesh Harjani (goldrunner192287@gmail.com)
     *
     */
    private class DbCryptoAlgorithmWrapper {
    	
    	private CheckBox seguro;
    	private TextView nombre;
    	private TextView tipoCifrado;
    	private TextView tipoSimetria;
    	private View row = null;
    	
    	/**
    	 * Guardamos la fila.
    	 * @param view
    	 */
    	DbCryptoAlgorithmWrapper (View view) {
    		this.row = view;
    	}
    	
    	/**
    	 * A partir de la fila en cuestión, cargamos el contenido de las distintas filas.
    	 * @param c Cursor apuntando a la fila a representar
    	 */
    	void populateFrom (Cursor c) {
    		DbCryptoAlgorithm dbCA = new DbCryptoAlgorithm();
    		dbCA.loadFrom(c);
    		String aux;
    		aux = dbCA.getNombre() + " (Clave de " + dbCA.getLongitudMinimaClave() + " bits)";
    		getNombre().setText(aux);
    		aux = "Cifrado de tipo ";
    		if (dbCA.getTipoCifrado() == TipoDeCifrado.Bloque)
    			aux += "Bloque, ";
    		if (dbCA.getTipoCifrado() == TipoDeCifrado.Flujo)
    			aux += "Flujo, ";
    		getTipoCifrado().setText(aux);
    		aux = " soporta cifrado ";
    		if (dbCA.getTipoSimetria() == TipoDeSimetria.Simetrico)
    			aux += "Simétrico";
    		if (dbCA.getTipoSimetria() == TipoDeSimetria.Asimetrico)
    			aux += "Asimétrico";
    		//getTipoSimetria().setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
    		getTipoSimetria().setText(aux);
    		if (!dbCA.isSeguro()) {
    			getSeguro().setChecked(false);
    			row.setBackgroundColor(Color.RED);
    		}
    		else {
    			getSeguro().setChecked(true);
    			row.setBackgroundColor(Color.GREEN);
    		}
    		getNombre().setTextColor(Color.BLACK);
			getTipoCifrado().setTextColor(Color.BLACK);
			getTipoSimetria().setTextColor(Color.BLACK);
    	}

    	/**
    	 * (Similar para los demás getters)
    	 * Nos devuelve el TextView que representará el nombre del algoritmo criptográfico.
    	 * Nótese que solamente busca la vista correspondiente si la referencia actual es null; esto es
    	 * importante porque este código debe ejecutarse muy rápidamente.
    	 * @return TextView donde pondremos el nombre del algoritmo.
    	 */
		public TextView getNombre() {
			if (nombre == null)
				nombre = (TextView) row.findViewById(R.id.rowTitle);
			return nombre;
		}
		
		public CheckBox getSeguro() {
			if (seguro == null)
				seguro = (CheckBox) row.findViewById(R.id.rowCheckBox);
			return seguro;
		}

		public TextView getTipoCifrado() {
			if (tipoCifrado == null)
				tipoCifrado = (TextView) row.findViewById(R.id.rowLowerLeft);
			return tipoCifrado;
		}

		public TextView getTipoSimetria() {
			if (tipoSimetria == null)
				tipoSimetria = (TextView) row.findViewById(R.id.rowLowerCenterLeft);
			return tipoSimetria;
		}    	
    }
    
}