package com.ull.feu.pude.apps.threads;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ull.feu.pude.apps.fragments.RequestFragment;

/**
 * Esta clase es un hilo dedicado a un uso muy espec�fico; en su m�todo run(), que es el �nico que normalment ese 
 * sobreescribe, se encarga de realizar una petici�n web, de traducirla a texto plano y de entreg�rsela a un receptor 
 * de mensajes que debe recibir la clase como par�metro.
 * 
 * @author Dinesh Harjani (Twitter: @dinesharjani G+: +Dinesh Harjani E-mail:goldrunner18725@gmail.com)
 * Hashtag: #droidissues
 *
 */

public class RequestThread extends Thread {
	
	private static final String LOG_TAG = RequestThread.class.getSimpleName();
	
	private static final String SERVICE_URL = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
	private static final String SERVICE_URL_APPENDICES = "&sensor=true";
	
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
	    	String url =  SERVICE_URL + latitude + "," + longitude + SERVICE_URL_APPENDICES;
	    	
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
			data.putString(RequestFragment.__RESPONSE_KEY__, responseString);
			
			// colocamos los datos
			msg.setData(data);
			
			// enviamos el mensaje
			if (myHandler != null)
				myHandler.sendMessage(msg);
			else
				Log.i(LOG_TAG, "Imposible enviar mensaje; el handler recibido es null.");
			
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
