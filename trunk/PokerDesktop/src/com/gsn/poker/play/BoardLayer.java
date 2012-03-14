package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.poker.asset.PokerTexture;

public class BoardLayer extends GsnLayer {

	public BoardLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(PokerTexture.board);
		addActor(bg);
	}

}
