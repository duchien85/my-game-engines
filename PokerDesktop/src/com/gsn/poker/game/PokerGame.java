package com.gsn.poker.game;

import com.gsn.engine.myplay.GsnGame;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.play.PlayScreen;

public class PokerGame extends GsnGame {
	PlayScreen playScreen;
	
	@Override
	public void create() {
		super.create();
		PokerTexture.create();
		PokerTexture.loadAll();
		
		playScreen = new PlayScreen(width, height);
		
		setScreen(playScreen);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
