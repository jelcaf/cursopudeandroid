package com.ull.feu.pude.curso.aplicaciones;

import android.app.Activity;
import android.os.Bundle;
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
    }
}