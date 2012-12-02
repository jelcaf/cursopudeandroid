package com.ull.feu.pude.apps.activities;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.ull.feu.pude.apps.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class Hello extends MapActivity {
	
	HelloItemizedOverlay itemizedoverlay;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        int latitud = 0;
        int longitud = 0;
        
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
        	latitud = bundle.getInt("latitud");
        	longitud = bundle.getInt("longitud");
        }
        else {
        	return;
        }
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedoverlay = new HelloItemizedOverlay(drawable, Hello.this);
        
        GeoPoint point = new GeoPoint(longitud, latitud);
        OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "Estas aquí!!");
        
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}