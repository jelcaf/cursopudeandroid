/**
 * 
 */
package com.ull.feu.pude.apps.db;

import com.ull.feu.pude.apps.db.DbCryptoAlgorithm.TipoDeCifrado;
import com.ull.feu.pude.apps.db.DbCryptoAlgorithm.TipoDeSimetria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Ésta es la clase que tiene acceso directo a la tabla con la información sobre los algoritmos criptográficos.
 * Aquí están los métodos que permiten crear la tabla, actualizarla y borrarla.
 * 
 * @author Dinesh Harjani (goldrunner192287@gmail.com)
 *
 */
public class DbCryptoAlgorithmSQLiteHelper extends SQLiteOpenHelper {
	
	private static final String LOG_TAG = "DbCryptoAlgorithmSQLiteHelper";
	
	public static final String NOMBRE_BD = "cryptoalgorithms.db";
	public static final String NOMBRE_TABLA_BD = "cryptoalgorithms";
	private static final int VERSION_ESQUEMA = 1;
	
	public DbCryptoAlgorithmSQLiteHelper(Context context) {
		super(context, NOMBRE_TABLA_BD, null, VERSION_ESQUEMA);
	}

	/**
	 * Este método se ejecuta una sola vez, cuando se va a crear la tabla.
	 * @param db Base de datos en la que se creará la tabla
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD + " (" + 
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_ID__ + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__ + " TEXT, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_TIPOSIM__ + " INTEGER, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_TIPOCIF__ + " INTEGER, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_SEGURO__ + " INTEGER, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHNDB_LLAVE_LONGMIN__ + " INTEGER);");
		construirBD(db);
	}

	/**
	 * También se ejecuta una vez, cuando se detecta que hay una nueva versión. Aquí debe estar el código 
	 * que actualice la versión anterior del esquema/tabla a la nueva. El código que está aquí no es 
	 * NADA recomendable para aplicaciones comerciales, salvo que sea la primera versión o se sepa que no 
	 * va a haber cambios (improbable).
	 * @param db Base de datos a actualizar.
	 * @param versionAntigua Versión del esquema o instancia de la base de datos que se va a actualizar
	 * @param versionNueva Versión más reciente del esquema de la base de datos, a la que se va a actualizar
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
		Log.w(LOG_TAG, "Actualizando la base de datos. Destruyendo datos antiguos.");
		db.execSQL("DROP TABLE IF EXISTS " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD);
		onCreate(db);
	}
	
	/**
	 * Se encarga de llenar de algoritmos la tabla inicial.
	 * @param db Base de datos en la que se añadirán las filas
	 */
	private void construirBD(SQLiteDatabase db) {
		DbCryptoAlgorithm dbCA;
		// Cifrado de César
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("Cifrado de Cesar");
		dbCA.setTipoCifrado(TipoDeCifrado.Flujo);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(false);
		dbCA.setLongitudMinimaClave(0);
		dbCA.save(db);
		// Cifrado de Vigenere
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("Cifrado de Vigenere");
		dbCA.setTipoCifrado(TipoDeCifrado.Flujo);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(false);
		dbCA.setLongitudMinimaClave(1);
		dbCA.save(db);
		// Cifrado A5/1
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("A5/1");
		dbCA.setTipoCifrado(TipoDeCifrado.Flujo);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(false);
		dbCA.setLongitudMinimaClave(54);
		dbCA.save(db);
		// Cifrado RC4
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("RC4");
		dbCA.setTipoCifrado(TipoDeCifrado.Flujo);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(false);
		dbCA.setLongitudMinimaClave(40);
		dbCA.save(db);
		// Cifrado RSA
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("RSA");
		dbCA.setTipoCifrado(TipoDeCifrado.Flujo);
		dbCA.setTipoSimetria(TipoDeSimetria.Asimetrico);
		dbCA.setSeguro(true);
		dbCA.setLongitudMinimaClave(768);
		dbCA.save(db);
		// Cifrado DES
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("DES");
		dbCA.setTipoCifrado(TipoDeCifrado.Bloque);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(false);
		dbCA.setLongitudMinimaClave(56);
		dbCA.save(db);
		// Cifrado Blowfish
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("Blowfish");
		dbCA.setTipoCifrado(TipoDeCifrado.Bloque);
		dbCA.setTipoSimetria(TipoDeSimetria.Asimetrico);
		dbCA.setSeguro(true);
		dbCA.setLongitudMinimaClave(128);
		dbCA.save(db);
		// Cifrado AES
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("AES");
		dbCA.setTipoCifrado(TipoDeCifrado.Bloque);
		dbCA.setTipoSimetria(TipoDeSimetria.Asimetrico);
		dbCA.setSeguro(true);
		dbCA.setLongitudMinimaClave(128);
		dbCA.save(db);
	}
}
