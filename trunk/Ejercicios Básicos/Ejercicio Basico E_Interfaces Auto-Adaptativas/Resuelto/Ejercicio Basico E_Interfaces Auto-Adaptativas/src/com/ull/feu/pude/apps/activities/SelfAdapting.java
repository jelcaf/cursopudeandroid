package com.ull.feu.pude.apps.activities;

import com.ull.feu.pude.apps.R;

import android.app.Activity;
import android.os.Bundle;

public class SelfAdapting extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.adaptive_main);
    }
}