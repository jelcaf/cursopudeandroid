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
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Actividad extends ListActivity {
	
	private static final int MENU_ADD = Menu.FIRST;
	private static final int MENU_DEL = Menu.FIRST + 1;
	
	private static final int DIALOGO_NUEVO_DATO = 1;
	
	private int posicionDondeInsertar = -1;
	
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
                                mArray.add (posicionDondeInsertar, editText.getText().toString());
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
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	menu.add (Menu.NONE, MENU_ADD, Menu.NONE, "Añadir elemento a continuación");
    	menu.add (Menu.NONE, MENU_DEL, Menu.NONE, "Eliminar elemento seleccionado");
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    
    	switch (item.getItemId()) {
    		case MENU_ADD:
    			Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    			posicionDondeInsertar = info.position+1;
    			showDialog(DIALOGO_NUEVO_DATO);
    			return true;
    		case MENU_DEL:
    			Toast.makeText(this, "Eliminado elemento: "+mArray.get(info.position), Toast.LENGTH_SHORT).show();
    			mArray.remove(info.position);
    			actualizarArray();
    	}
		return super.onContextItemSelected(item);
    }
}