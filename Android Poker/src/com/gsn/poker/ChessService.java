package com.gsn.poker;


import org.json.JSONObject;

import com.gsn.engine.mercurry.MercuryClient;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.game.PokerGame;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ChessService extends Service {
	public class ChessBinder extends Binder {
		public MercuryClient client;
	}

	ChessBinder binder;
	MercuryClient client;

	@Override
	public IBinder onBind(Intent arg0) {
		binder.client = client;
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		binder = new ChessBinder();
		client = new MercuryClient(MyPoker.server, 443, null);
		client.setListener(new MercuryClient.IMercuryListener() {
			
			@Override
			public void onReceivedJson(JSONObject json) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onReceived(String s) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDisconnected() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onConnected() {
				// TODO Auto-generated method stub
				
			}
		});
		//(MyPoker.server, 443, MyPoker.game);
	}
}
