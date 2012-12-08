package com.ull.feu.pude.curso.aplicaciones;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;

/**
 * Ejercicio B‡sico D - Menus contextuales
 * 
 * @author Jorge Carballo (Twitter: @jelcaf G+: +Jorge Carballo E-mail:jelcaf@gmail.com)
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */
public class Actividad extends ListActivity {
	
	private static final int DIALOGO_NUEVO_DATO = 1;
	
    ArrayList<String> mArray = new ArrayList<String>(); 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mArray.add("cero");
        mArray.add("uno");
        mArray.add("dos");
        mArray.add("tres");
        mArray.add("cuatro");
        mArray.add("cinco");
        mArray.add("seis");
        mArray.add("siete");
        mArray.add("ocho");
        mArray.add("nueve");
        mArray.add("diez");
        
        actualizarArray();
        
        registerForContextMenu(this.getListView());
    }
    
    /**
     * Sobreescribimos el método que se encarga de crear un nuevo Dialog
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
                case DIALOGO_NUEVO_DATO:
                // This example shows how to add a custom layout to an AlertDialog
                LayoutInflater factory = LayoutInflater.from(this);
                final View textEntryView = factory.inflate(R.layout.dialog_text_entry, null);
                return new AlertDialog.Builder(Actividad.this)
                    .setTitle("Inserta nuevo dato")
                    .setView(textEntryView)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            /* Insertamos el nuevo elemento por el final */
                                EditText editText = (EditText) textEntryView.findViewById (R.id.dato_edit);
                                mArray.add (editText.getText().toString());
                                actualizarArray();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            /* No hacemos nada */
                        }
                    })
                    .create();
        }
        return null;
    }
    
    public void actualizarArray() {
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArray));
    }
    

}