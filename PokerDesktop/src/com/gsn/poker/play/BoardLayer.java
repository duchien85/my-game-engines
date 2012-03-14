package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.poker.asset.PokerTexture;

public class BoardLayer extends GsnLayer {

	public BoardLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(PokerTexture.board);
		
		bg.width = width;
		bg.height = height;
		
		Image bobai = new Image(PokerTexture.baiUp);
		//bobai.rotation = 60;
		ActorUtility.setCenter(bobai, width / 2, height / 2);
		
		ImageButton menuBtn = new ImageButton(PokerTexture.menuBtn, PokerTexture.menuBtnDown);
		ActorUtility.setRatio(menuBtn, 1f, 1f, width, height);
		
		addActor(bg);
		addActor(bobai);
		addActor(menuBtn);
	}

}
