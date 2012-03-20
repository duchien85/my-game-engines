package com.gsn.poker.play;

import com.gsn.engine.myplay.GsnScreen;

public class PlayScreen extends GsnScreen {
	public BoardLayer boardLayer;
	public PlayScreen(float width, float height) {
		super(width, height);
		boardLayer = new BoardLayer(width, height);
		addLayer(boardLayer);
	}

	@Override
	public void setInputListener() {
		boardLayer.setInputListener();
	}

}
