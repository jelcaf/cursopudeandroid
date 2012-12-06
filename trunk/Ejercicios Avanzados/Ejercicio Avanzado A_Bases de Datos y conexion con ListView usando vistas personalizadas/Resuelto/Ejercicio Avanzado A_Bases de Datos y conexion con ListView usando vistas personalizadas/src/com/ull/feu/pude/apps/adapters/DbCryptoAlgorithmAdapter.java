package com.ull.feu.pude.apps.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.ull.feu.pude.apps.R;
import com.ull.feu.pude.apps.wrappers.DbCryptoAlgorithmWrapper;

/**
 * Definimos nuestro propio adaptador para la lista. Los métodos a implementar son pocos y sencillos.
 * Los beneficios superan estas pocas líneas que nos permiten personalizar enormemente el 
 * funcionamiento de nuestras listas.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */
public class DbCryptoAlgorithmAdapter extends CursorAdapter {
	
	/**
	 * Constructor. No hacemos nada más, salvo que lo que queramos hacer algo que lo requiera.
	 * 
	 * @param context
	 * @param c
	 */
	public DbCryptoAlgorithmAdapter(Context context, Cursor c) {
		super(context, c, false);
	}

	/**
	 * Este método es llamado cuando no es necesario crear una nueva vista para la fila.
	 * Lo que hacemos es cargar los datos que debe mostrar la vista a partir de la clase 
	 * envoltorio o Wrapper, utilizando el método getTag(). Previamente, en cada fila, 
	 * se le habrá asociado un tag o etiqueta, que es un objeto de apoyo para poder manejar
	 * lo que debe mostrar la vista.
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		DbCryptoAlgorithmWrapper wrapper = (DbCryptoAlgorithmWrapper) view.getTag();
		wrapper.populateFrom(cursor);
	}

	/**
	 * Cuando es necesario crear (o expandir) una nueva vista para mostrar en pantalla una fila, 
	 * se llama a este mÈtodo.
	 * Lo que haremos es expandir la vista que representa las filas, crear una clase Wrapper, 
	 * asignarle a la fila el wrapper y cargar los datos en las vistas que conforman la fila.
	 * 
	 * @return Vista que representa la fila apuntada por el cursor.
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View row = inflater.inflate(R.layout.list_item, parent, false);
		DbCryptoAlgorithmWrapper wrapper = new DbCryptoAlgorithmWrapper(row);
		row.setTag(wrapper);
		wrapper.populateFrom(cursor);
		return row;
	}
	
}
