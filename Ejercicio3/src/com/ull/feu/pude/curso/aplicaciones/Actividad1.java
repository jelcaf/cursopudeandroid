package com.ull.feu.pude.curso.aplicaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Actividad1 extends Activity {
    private static final int SUB_ACTIVIDAD = 0;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);
        
        Button boton = (Button) findViewById (R.id.Button01);
        boton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
            	EditText ed = (EditText) findViewById(R.id.EditText01);
            	String texto = ed.getText().toString();
            	Intent i = new Intent (getApplicationContext(), Actividad2.class);
            	i.putExtra("clave", texto);
            	startActivityForResult(i, SUB_ACTIVIDAD);
                //startActivity(i);
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == SUB_ACTIVIDAD) {
    		if (resultCode == RESULT_OK) {
    			boolean suma = data.getBooleanExtra("CLOSE", false);
    			if (suma)
    				finish();
    		}
    	}
    }

}