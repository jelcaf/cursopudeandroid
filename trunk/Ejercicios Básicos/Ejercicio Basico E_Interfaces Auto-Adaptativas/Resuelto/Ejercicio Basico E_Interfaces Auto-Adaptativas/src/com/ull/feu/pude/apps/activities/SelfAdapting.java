package com.ull.feu.pude.apps.activities;

import com.ull.feu.pude.apps.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * Sencillo ejercicio en el que se intenta que los alumnos juegan y descubran c—mo funciona el RelativeLayout para que puedan 
 * construir interfaces capaces de adaptarse por s’ solas a los distintos tipos de dispositivos con sus espec’ficos formatos 
 * de pantalla. El XML "main" es est‡tico, utilizando un LinearLayout, mientras que el "adaptive_main" es el correcto, 
 * basado en un RelativeLayout.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class SelfAdapting extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.adaptive_main);
    }
}