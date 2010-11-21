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
 * Clase que representa en forma de objeto Java la información que se guarda en cada fila de la base de datos.
 * 
 * @author Dinesh Harjani (goldrunner192287@gmail.com)
 *
 */
public class DbCryptoAlgorithm {

	/*
	 * Llaves de la base de datos
	 * Son los nombres con los que se identificará cada variable en la base de datos
	 * 
	 */
	
	public static final String __CRYPTOALGORITHMDB_LLAVE_ID__ = "_id";
	public static final String __CRYPTOALGORITHMDB_LLAVE_NOMBRE__ = "name";
	public static final String __CRYPTOALGORITHMDB_LLAVE_TIPOCIF__ = "cypherType";
	public static final String __CRYPTOALGORITHMDB_LLAVE_TIPOSIM__ = "cypherSymmetry";
	public static final String __CRYPTOALGORITHMDB_LLAVE_SEGURO__ = "isSafe";
	public static final String __CRYPTOALGORITHNDB_LLAVE_LONGMIN__ = "minKeyLength";
	
	/*
	 * Declaración de variables y tipos auxiliares
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
	 * Métodos
	 */
	
	/**
	 * Método que, dada la base de datos, devuelve un cursor con todas las filas.
	 * @param db Base de datos donde se encuentran las filas a recuperar
	 * @return Cursor con todas las filas de la tabla {@code DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD}
	 */
	public static Cursor getAll(SQLiteDatabase db) {
		Cursor c = db.rawQuery("SELECT * FROM " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD, null);
		return c;
	}
	
	/**
	 * Método que transforma una fila de la base de datos en un objeto Java de tipo DbCryptoAlgorithm.
	 * @param db La instancia de la base de datos donde se encuentra la fila
	 * @param id El id de la fila a transformar
	 * @return Objeto de tipo DbCryptoAlgorithm, o null si no se encuentra la fila
	 */
	public static DbCryptoAlgorithm loadFrom(SQLiteDatabase db, long id) {		
		Cursor c = db.rawQuery("SELECT * FROM " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD + 
				" WHERE " + DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_ID__ + " = " + id, null);
		
		c.moveToFirst();
		// si hay un elemento en el cursor, entonces lo hemos encontrado
		if (c.isFirst()) {
			DbCryptoAlgorithm dbCA = new DbCryptoAlgorithm();
			dbCA.loadFrom(c);
			return dbCA;
		}
		else
			return null;
	}

	/**
	 * Método que se encarga de recoger los datos de la fila apuntada por el cursor y asignárselos a 
	 * los campos de esta instancia.
	 * @param c Cursor apuntando a la fila que se quiere se representada
	 * @return Objeto representado, el mismo al que se invocó la llamada
	 */
	public DbCryptoAlgorithm loadFrom(Cursor c) {
		id = c.getLong(c.getColumnIndex(__CRYPTOALGORITHMDB_LLAVE_ID__));
		Log.i(LOG_TAG, "Cargando de cursor la fila con id= " + id);
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
	 * Método que se encarga de transformar los datos guardados por el objeto en una fila de 
	 * la base de datos. Si este objeto ya existe en la base de datos, debe ser actualizado, y si no,
	 * debe ser creado en la misma.
	 * @param db Instancia de la base de datos donde se guardará el objeto
	 * @return Código de resultado de la operación
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
