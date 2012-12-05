package com.ull.feu.pude.curso.aplicaciones;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Eejercicio Bsico A - ListViews Resuelto
 * 
 * @author jelcaf
 * Hashtag: #droidissues
 *
 */
public class Actividad extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use an existing ListAdapter that will map an array of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArray));
        
        ListView l = getListView();
        l.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
        	public boolean onItemLongClick(AdapterView<?> l, View v, int position, long id) {
        		Toast.makeText(getApplicationContext(), "Pulsación larga: "+position+" -> "+mArray[position], Toast.LENGTH_SHORT).show();
                          
        		return true;
        	}
        });
        
        /*l.setOnItemClickListener( new AdapterView.OnItemClickListener(){
        	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        		Toast.makeText(getApplicationContext(), "PulsaciÛn corta: "+position+" -> "+mArray[position], Toast.LENGTH_SHORT).show();
        	}
        });*/
    }
    
    /**
     * Sobreescribimos el mÈtodo que se encarga de detectar una pulsaciÛn sobre el item de la lista
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	Toast.makeText(this, "Posición: "+position+" -> "+mArray[position], Toast.LENGTH_LONG).show(); 
    }
    
    
    
    private String[] mArray = {
            "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez"
    };
}