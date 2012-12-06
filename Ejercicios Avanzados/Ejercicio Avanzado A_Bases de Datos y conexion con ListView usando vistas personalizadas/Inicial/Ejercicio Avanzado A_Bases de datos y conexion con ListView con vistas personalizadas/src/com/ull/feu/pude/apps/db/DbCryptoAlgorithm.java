/**
 * 
 */
package com.ull.feu.pude.apps.db;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * Clase que representa en forma de objeto Java la informaci—n que se guarda en cada fila de la base de datos.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */
public class DbCryptoAlgorithm {

	/*
	 * Llaves de la base de datos
	 * Son los nombres con los que se identificar‡ cada variable en las columnas de la base de datos
	 * 
	 */
	
	public static final String __CRYPTOALGORITHMDB_LLAVE_ID__ = "_id";
	public static final String __CRYPTOALGORITHMDB_LLAVE_NOMBRE__ = "name";
	public static final String __CRYPTOALGORITHMDB_LLAVE_TIPOCIF__ = "cypherType";
	public static final String __CRYPTOALGORITHMDB_LLAVE_TIPOSIM__ = "cypherSymmetry";
	public static final String __CRYPTOALGORITHMDB_LLAVE_SEGURO__ = "isSafe";
	public static final String __CRYPTOALGORITHNDB_LLAVE_LONGMIN__ = "minKeyLength";
	
	/*
	 * Declaraci—n de variables y tipos auxiliares
	 * (Los inicializamos)
	 */
	
	public enum TipoDeCifrado {
		Bloque,
		Flujo
	}
	
	public enum TipoDeSimetria {
		Simetrico,
		Asimetrico
	}
	private static final String LOG_TAG = "DbCryptoAlgorithm";
	private long id = -1;
	private String nombre = "";
	private TipoDeCifrado tipoCifrado;
	private TipoDeSimetria tipoSimetria;
	private boolean seguro = false;
	private int longitudMinimaClave = -1;
	
	/*
	 * MŽtodos
	 */
	
	/**
	 * MŽtodo que, dada la base de datos, devuelve un cursor con todas las filas.
	 * NOTA: El cursor que se devuelve permanece abierto
	 * 
	 * @param db Base de datos donde se encuentran las filas a recuperar
	 * @return Cursor con todas las filas de la tabla {@code DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD}
	 */
	public static Cursor getAll(SQLiteDatabase db) {
		Cursor c = db.rawQuery("SELECT * FROM " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD, null);
		return c;
	}
	
	/**
	 * MŽtodo que transforma una fila de la base de datos en un objeto Java de tipo {@link DbCryptoAlgorithm}.
	 * 
	 * @param db La instancia de la base de datos donde se encuentra la fila
	 * @param id El id de la fila a transformar
	 * @return un objeto de tipo {@link DbCryptoAlgorithm}, o null si no se encuentra la fila
	 */
	public static DbCryptoAlgorithm loadFrom(SQLiteDatabase db, long id) {		
		Cursor c = db.rawQuery("SELECT * FROM " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD + 
				" WHERE " + DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_ID__ + " = " + id, null);
		
		// inicializamos a null
		DbCryptoAlgorithm dbCA = null;
		c.moveToFirst();
		// si hay un elemento en el cursor, entonces lo hemos encontrado
		if ((c.isFirst() && (c.getCount() == 1))) {
			dbCA = new DbCryptoAlgorithm();
			dbCA.loadFrom(c);
		}
		
		// ÁÁmuy importante cerrar el cursor!!
		c.close();
		
		return dbCA;
	}

	/**
	 * MŽtodo encargado de recoger los datos de la fila apuntada por el cursor y asign‡rselos a 
	 * los campos de esta instancia.
	 * 
	 * @param c Cursor apuntando a la fila que se quiere se representada
	 * @return Objeto representado, siendo el mismo al que se invoc— la llamada pero actualizado
	 */
	public DbCryptoAlgorithm loadFrom(Cursor c) {
		id = c.getLong(c.getColumnIndex(__CRYPTOALGORITHMDB_LLAVE_ID__));
		Log.i(LOG_TAG, "Cargando desde el cursor la fila con id= " + id);
		nombre = c.getString(c.getColumnIndex(__CRYPTOALGORITHMDB_LLAVE_NOMBRE__));
		int aux;
		aux = c.getInt(c.getColumnIndex(__CRYPTOALGORITHMDB_LLAVE_TIPOCIF__));
		switch (aux) {
			case 0: 
				tipoCifrado = TipoDeCifrado.Bloque; break;
			default:
				tipoCifrado = TipoDeCifrado.Flujo; break;				
		}
		aux = c.getInt(c.getColumnIndex(__CRYPTOALGORITHMDB_LLAVE_TIPOSIM__));
		switch (aux) {
			case 0:
				tipoSimetria = TipoDeSimetria.Asimetrico; break;
			default:
				tipoSimetria = TipoDeSimetria.Simetrico; break;
		}
		aux = c.getInt(c.getColumnIndex(__CRYPTOALGORITHMDB_LLAVE_SEGURO__));
		seguro = false;
		if (aux == 1)
			seguro = true;
		longitudMinimaClave = c.getInt(c.getColumnIndex(__CRYPTOALGORITHNDB_LLAVE_LONGMIN__));
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDeCifrado getTipoCifrado() {
		return tipoCifrado;
	}

	public void setTipoCifrado(TipoDeCifrado tipoCifrado) {
		this.tipoCifrado = tipoCifrado;
	}

	public TipoDeSimetria getTipoSimetria() {
		return tipoSimetria;
	}

	public void setTipoSimetria(TipoDeSimetria tipoSimetria) {
		this.tipoSimetria = tipoSimetria;
	}

	public boolean isSeguro() {
		return seguro;
	}

	public void setSeguro(boolean seguro) {
		this.seguro = seguro;
	}

	public int getLongitudMinimaClave() {
		return longitudMinimaClave;
	}

	public void setLongitudMinimaClave(int longitudMinimaClave) {
		this.longitudMinimaClave = longitudMinimaClave;
	}
	
	/**
	 * MŽtodo que se encarga de transformar los datos guardados por el objeto en una fila de 
	 * la base de datos. Si este objeto ya existe en la base de datos, debe ser actualizado, y si no,
	 * debe ser insertado en la misma.
	 * 
	 * @param db Instancia de la base de datos donde se guardará el objeto
	 * @return el nœmero de filas afectadas por la operaci—n (0 -> error, 1 -> Žxito, m‡s de 1 -> se actualiz— m‡s de una fila)
	 */
	public long save(SQLiteDatabase db) {
		ContentValues cv = new ContentValues();
		long res = -1;
		if (id != -1)
			cv.put(DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_ID__, id);
		
		cv.put(DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__, nombre);
		int aux;
		if (tipoCifrado == TipoDeCifrado.Bloque)
			aux = 0;
		else
			aux = 1;
		cv.put(DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_TIPOCIF__, aux);
		
		if (tipoSimetria == TipoDeSimetria.Asimetrico)
			aux = 0;
		else aux = 1;
		cv.put(DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_TIPOSIM__, aux);
		
		if (seguro)
			aux = 1;
		else
			aux = 0;
		cv.put(DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_SEGURO__, aux);
		cv.put(DbCryptoAlgorithm.__CRYPTOALGORITHNDB_LLAVE_LONGMIN__, longitudMinimaClave);
		
		if (id == -1) 
		{
			Log.i(LOG_TAG, "Guardando como nueva fila en la base de datos");
			res = db.insert(DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD, 
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__, cv);
		}
		else {
			// actualizamos
			Log.i(LOG_TAG, "Actualizando fila de la base de datos con id=  " + id);
			res = db.update(DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD, cv
					, DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_ID__ + " = " + id, null);
		}
		return res;
	}
}
