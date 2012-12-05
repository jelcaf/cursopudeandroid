package com.ull.feu.pude.curso.aplicaciones;


import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Eejercicio Básico C - Menus y Dialogs
 * 
 * @author jelcaf
 * Hashtag: #droidissues
 *
 */
public class Actividad extends ListActivity {
	
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
        
        
        // Use an existing ListAdapter that will map an array of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArray));
    }    
    
}