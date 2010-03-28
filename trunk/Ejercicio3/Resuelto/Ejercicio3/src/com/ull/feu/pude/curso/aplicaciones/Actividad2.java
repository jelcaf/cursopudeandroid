package com.ull.feu.pude.curso.aplicaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad2 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        String data = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
           data = bundle.getString("clave");
        }

        TextView tV = (TextView) findViewById(R.id.TextView01);
        tV.setText(data);
        
        Button boton = (Button) findViewById (R.id.Button01);
        boton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
               finish(); // código de la función
            }
        });

        boton = (Button) findViewById (R.id.Button02);
        boton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("CLOSE", true);
                setResult(RESULT_OK, result);
                finish();
                // código de la función
            }
        });
    }
}