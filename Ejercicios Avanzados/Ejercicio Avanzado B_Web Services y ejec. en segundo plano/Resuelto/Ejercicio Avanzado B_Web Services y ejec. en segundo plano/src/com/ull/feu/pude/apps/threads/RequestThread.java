package com.ull.feu.pude.apps.threads;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.ull.feu.pude.apps.activities.RequestActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class RequestThread extends Thread {
	
	private Handler myHandler;
	private String latitude;
	private String longitude;
	
	public RequestThread(String latitude, String longitude, Handler myHandler) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.myHandler = myHandler;
	}
	
	@Override
	public void run() {
		try {
    		
    		// obtenemos una conexi�n
    		DefaultHttpClient httpClient = new DefaultHttpClient();
	    	String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=false";
	    	// creamos una petici�n de tipo GET (par�metros en la URL)
	    	HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			// ejecutamos la petici�n
			HttpResponse response = httpClient.execute(request);
			
			// convertimos la respuesta en string
			String responseString = EntityUtils.toString(response.getEntity());
			
			// escribimos el resultado
			Message msg = new Message();
			// de qu� trata el mensaje
			msg.what = 0;
			// a�adir datos
			Bundle data = new Bundle();
			data.putString(RequestActivity.__RESPONSE_KEY__, responseString);
			// colocamos los datos
			msg.setData(data);
			// enviamos el mensaje
			myHandler.sendMessage(msg);
			
			// cerramos la conexi�n 
			httpClient.getConnectionManager().shutdown();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		// mensaje vac�o (sin datos)
    		myHandler.sendEmptyMessage(1);
    	}
	}
}
