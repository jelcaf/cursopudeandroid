package com.ull.feu.pude.curso.aplicaciones;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Eejercicio B‡sico A - ListViews
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
        
    }
    
    private String[] mArray = {
            "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez"
    };
}