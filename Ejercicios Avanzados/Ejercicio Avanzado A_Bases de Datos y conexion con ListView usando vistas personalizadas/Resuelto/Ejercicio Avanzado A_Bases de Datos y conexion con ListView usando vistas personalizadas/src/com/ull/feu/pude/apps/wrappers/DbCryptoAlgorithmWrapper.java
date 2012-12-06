package com.ull.feu.pude.apps.wrappers;

import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ull.feu.pude.apps.R;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm.TipoDeCifrado;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm.TipoDeSimetria;

/**
 * Esta clase, que llamaremos Wrapper, se encarga de gestionar lo que se debe mostrar 
 * para cada fila de la base de datos. Aquí se traduce un objeto de tipo DbCryptoAlgorithm en 
 * una vista con el esquema list_item.xml
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */
public class DbCryptoAlgorithmWrapper {
	
	private CheckBox seguro;
	private TextView nombre;
	private TextView tipoCifrado;
	private TextView tipoSimetria;
	private DbCryptoAlgorithm dbCA;
	private View row = null;
	
	/**
	 * Guardamos la fila.
	 * @param view
	 */
	public DbCryptoAlgorithmWrapper (View view) {
		row = view;
		dbCA = new DbCryptoAlgorithm();
	}
	
	/**
	 * A partir de la fila en cuestión, cargamos el contenido de las distintas filas.
	 * @param c Cursor apuntando a la fila a representar
	 */
	public void populateFrom (Cursor c) {
		// reutilizamos siempre el mismo objeto dbCA
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
	 * Nos devuelve el TextView que representar· el nombre del algoritmo criptogáfico.Fijaos en que
	 * solamente se busca la vista correspondiente si la referencia actual es null; esto es importante 
	 * porque el findViewById() es una operación relativamente costosa que conviene realizar el menor 
	 * número de veces posible.
	 * 
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
