package com.vng.poker;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.gsn.engine.mercurry.MercuryClient;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.game.PokerGame;

public class Desktop {

	public static void createGame(int mode) {
		switch (mode) {
		case 1:
			createGame(240, 320);
			break;
		case 2:
			createGame(320, 480);
			break;
		case 3:
			createGame(540, 960);
			break;
		case 4:
			createGame(480, 800);
			break;
		}
	}
	
	public static void binder(){
		MyPoker.game = game;
		
		
		MercuryClient client = new MercuryClient("10.198.36.66", 443, game);
		client.connect();
		
		MyPoker.client = client;
	}
	
	static PokerGame game;
	
	public static void createGame(int width, int height) {
		game  = new PokerGame();		
		//new LwjglApplication(game, "My Caro", height, width, false);
		new JoglApplication(game, "My Caro", height, width, false);
		binder();
	}

	public static void main(String[] args) {
		createGame(2);
	}
}
