package com.gsn.poker;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gsn.poker.game.DataProvider;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.game.PokerGame;

public class AndroidPokerActivity extends AndroidApplication {
	String tag = "Poker Activity";

	
	PokerGame game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "on create activiy");
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		createWakeLock(config);
        game = new PokerGame();
		MyPoker.game = game;		
		initialize(game, config);
       		
		MyPoker.client.setListener(game);
		//game.onReceivedJson(MyPoker.json);
              
    }
}