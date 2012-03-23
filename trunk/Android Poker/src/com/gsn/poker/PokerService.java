package com.gsn.poker;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.gsn.engine.mercurry.MercuryClient;
import com.gsn.poker.game.MyPoker;

public class PokerService extends Service {
	public class PokerBinder extends Binder {
		public MercuryClient client;
	}

	PokerBinder binder;
	MercuryClient client;

	@Override
	public IBinder onBind(Intent arg0) {
		binder.client = client;
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		binder = new PokerBinder();
		client = new MercuryClient(MyPoker.server, 443, MyPoker.game);
	}
}
