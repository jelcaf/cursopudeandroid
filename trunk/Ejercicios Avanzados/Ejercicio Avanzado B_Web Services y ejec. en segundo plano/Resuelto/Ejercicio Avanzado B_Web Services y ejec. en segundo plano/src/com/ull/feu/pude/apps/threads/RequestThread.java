package com.ull.feu.pude.apps.threads;

import android.os.Handler;

public class RequestThread extends Thread {
	
	private Handler myHandler;
	
	public RequestThread(Handler myHandler) {
		super();
		this.myHandler = myHandler;
	}
	
	@Override
	public void run() {
		
	}
}
