package com.ull.feu.pude.curso.aplicaciones;


import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Actividad extends ListActivity {
	
	private static final int MENU_ADD_OR_DEL = Menu.FIRST;
	private static final int MENU_ADD = Menu.FIRST + 1;
	private static final int MENU_DEL = Menu.FIRST + 2;
	
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	SubMenu sub = menu.addSubMenu (SubMenu.NONE, MENU_ADD_OR_DEL, SubMenu.NONE, "Opciones")
    		.setIcon(android.R.drawable.ic_menu_more);
    	
    	sub.add(SubMenu.NONE, MENU_ADD, SubMenu.NONE, "Añadir elemento");
    	sub.add(SubMenu.NONE, MENU_DEL, SubMenu.NONE, "Eliminar elemento");
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
    	switch (item.getItemId()) {
    		case MENU_ADD:
    			Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    			break;
    
    		case MENU_DEL:
    			Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    			mArray.remove(mArray.size()-1);
    	        setListAdapter(new ArrayAdapter<String>(this,
    	                android.R.layout.simple_list_item_1, mArray));
    			break;
    	}
    	return super.onOptionsItemSelected(item);
    }
}