package com.ull.feu.pude.curso.aplicaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @author Jorge Carballo (Twitter: @jelcaf G+: +Jorge Carballo E-mail:jelcaf@gmail.com)
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */
public class Actividad1 extends Activity {
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
                startActivity(i);
            }
        });
        

    }
}