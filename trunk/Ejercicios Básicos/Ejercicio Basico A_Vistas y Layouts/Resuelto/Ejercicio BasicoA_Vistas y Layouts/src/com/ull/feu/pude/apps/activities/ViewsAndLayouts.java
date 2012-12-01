package com.ull.feu.pude.apps.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ull.feu.pude.apps.R;

/**
 * 
 * Ejercicio cuyo objetivo es que los alumnos se sientan c—modos con los dos aspectos de programaci—n en Android: XML y Java, 
 * al mismo tiempo que introducimos propiedades b‡sicas de los elementos de interfaz de usuario (Views y ViewGroups).
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class ViewsAndLayouts extends Activity {
	
	private CheckBox enableViews;
	private TextView eventsTextView;
	private LinearLayout myViewsLayout;
	private RadioButton myRadioButton;
	private int clickTimes;
	private ToggleButton toggleVisibility;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // llamamos a nuestro método de inicialización
        initConfig();
    }
    
    private void initConfig() {
    	setContentView(R.layout.main);
    	enableViews = (CheckBox) findViewById(R.id.enableViews);
    	eventsTextView = (TextView) findViewById(R.id.eventsTextView);
    	myViewsLayout = (LinearLayout) findViewById(R.id.myViewsLayout);
    	myRadioButton = (RadioButton) findViewById(R.id.myRadioButton);
    	toggleVisibility = (ToggleButton) findViewById(R.id.toggleVisibility);
    	
    	/*
    	 *  Asignamos un comportamiento al checkbox, de forma que cuando cambie se llame al 
    	 *  método que tenemos dentro. Si el CheckBox está marcado (isChecked), ponemos el 
    	 *  LinearLayout como visible, y sino hacemos que desaparezca.
    	*/
    	
    	enableViews.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					myViewsLayout.setVisibility(View.VISIBLE);
				}
				else {
					myViewsLayout.setVisibility(View.GONE);
					
					// Opcional
					// Visible = 0, Invisible = 1, Gone = 2
					/*
					if (!toggleVisibility.isChecked()) {
						myViewsLayout.setVisibility(View.GONE);
					}
					else
						myViewsLayout.setVisibility(myViewsLayout.getVisibility() - 1);
					*/
				}
			}
		});
    	/*
    	 * Con el RadioButton, lo que queremos es "escuchar" cada vez que cambia de estado. Debido a sus 
    	 * características, cuando un RadioButton es marcado, no puede volver a ser desmarcado, a menos 
    	 * que lo forcemos nosotros.
    	 */
    	/*
    	myRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				eventsTextView.setText("myRadioButton ha cambiado de estado " + clickTimes + " veces.");				
			}
		});
    	*/
    	clickTimes = 0;
    	myRadioButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickTimes++;
				eventsTextView.setText("Clicked " + clickTimes + " times.");
			}
		});
    	// Opcional
    	/*
    	myRadioButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (myRadioButton.isChecked())
					myRadioButton.setChecked(false);
				else
					myRadioButton.setChecked(true);
			}
		});
		*/
    	toggleVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!enableViews.isChecked()) {
					if (isChecked)
						myViewsLayout.setVisibility(View.INVISIBLE);
					else
						myViewsLayout.setVisibility(View.GONE);
				}
			}
		});
    }
}