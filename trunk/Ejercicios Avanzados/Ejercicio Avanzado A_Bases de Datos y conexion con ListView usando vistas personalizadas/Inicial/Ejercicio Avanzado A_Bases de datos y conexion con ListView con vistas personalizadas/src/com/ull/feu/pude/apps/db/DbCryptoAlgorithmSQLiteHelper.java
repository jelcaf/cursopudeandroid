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
 * Esta es la clase que tiene acceso directo a la tabla con la información sobre los algoritmos criptográficos.
 * Aquí están los métodos que permiten crear la tabla, actualizarla y borrarla.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
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
	 * 
	 * Cuando se van a realizar varias operaciones seguidas y queremos que funcionen como una sola (mantener las normas ACID 
	 * de una base de datos relacional) es conveniente utilizar la estructura try / finally que sigue a continuación. Lo que ocurre 
	 * es que todas las acciones que se lleven a cabo entre un beginTransaction() y un endTransaction() funcionan como una sola, y
	 * si algo falla entre ellas se vuelve al estado anterior. Por eso resulta conveniente utilizarlas aquí.
	 * 
	 * @param db Base de datos en la que se creará e incializará la estructura de BD
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		try {
			crearTablasBD(db);
			inicializarBD(db);
			db.setTransactionSuccessful();
		}
		finally {
			db.endTransaction();
		}
	}

	/**
	 * También se ejecuta una vez, cuando se detecta que hay una nueva versión. Aquí debe estar el código 
	 * que actualice de la versión anterior del esquema/tabla a la nueva. El código que aquí se muestra no es 
	 * NADA recomendable para aplicaciones comerciales, salvo que sea la primera versión o se sepa que no 
	 * va a haber cambios (improbable). De nuevo, encapsulamos todas las operaciones de la BD en una sola transacción, 
	 * y esta vez añadimos un bloque catch para escribir en el log que tuvimos un fallo catastrófico al actualizar.
	 * 
	 * @param db Base de datos a actualizar.
	 * @param versionAntigua Versión del esquema o instancia de la base de datos que se va a actualizar
	 * @param versionNueva Versión más reciente del esquema de la base de datos, a la que se va a actualizar
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
		Log.w(LOG_TAG, "Actualizando la base de datos. Destruyendo datos antiguos.");
		db.beginTransaction();
		try {
			db.execSQL("DROP TABLE IF EXISTS " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD);
			crearTablasBD(db);
			inicializarBD(db);
			db.setTransactionSuccessful();
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG_TAG, "Se ha producido un error catastrófico al actualizar de la versión " + versionAntigua + "" +
					" a la versión " + versionNueva);
		}
		finally {
			db.endTransaction();
		}
	}
	
	/**
	 * Éste es un pequeño método que nos hemos creado para poder separar el código que construye nuestras tablas en la base
	 * de datos.
	 * 
	 * @param db la BD donde queremos construir nuestra estructura de tablas
	 */
	private void crearTablasBD(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DbCryptoAlgorithmSQLiteHelper.NOMBRE_TABLA_BD + " (" + 
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_ID__ + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_NOMBRE__ + " TEXT, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_TIPOSIM__ + " INTEGER, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_TIPOCIF__ + " INTEGER, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHMDB_LLAVE_SEGURO__ + " INTEGER, " +
				DbCryptoAlgorithm.__CRYPTOALGORITHNDB_LLAVE_LONGMIN__ + " INTEGER);");
	}
	
	/**
	 * Se encarga de llenar de algoritmos la tabla inicial.
	 * @param db Base de datos en la que se insertarán las filas por defecto
	 */
	private void inicializarBD(SQLiteDatabase db) {
		DbCryptoAlgorithm dbCA;
		// Cifrado de César
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("Cifrado de César");
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
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(true);
		dbCA.setLongitudMinimaClave(128);
		dbCA.save(db);
		// Cifrado Twofish
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("Twofish");
		dbCA.setTipoCifrado(TipoDeCifrado.Bloque);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(true);
		dbCA.setLongitudMinimaClave(256);
		dbCA.save(db);
		// Cifrado AES
		dbCA = new DbCryptoAlgorithm();
		dbCA.setNombre("AES");
		dbCA.setTipoCifrado(TipoDeCifrado.Bloque);
		dbCA.setTipoSimetria(TipoDeSimetria.Simetrico);
		dbCA.setSeguro(true);
		dbCA.setLongitudMinimaClave(128);
		dbCA.save(db);
	}
}
