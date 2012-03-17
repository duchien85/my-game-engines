package com.gsn.poker.play;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.play.Card.EChat;

public class BoardLayer extends GsnLayer {
	Image bobai;
	public BoardLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(PokerTexture.board);
		
		bg.width = width;
		bg.height = height;
		
		bobai = new Image(PokerTexture.baiUp);
		//bobai.rotation = 60;
		ActorUtility.setCenter(bobai, width / 2, height / 2);
		
		chia2LaDau();
		
		
		ImageButton menuBtn = new ImageButton(PokerTexture.menuBtn, PokerTexture.menuBtnDown);
		ActorUtility.setRatio(menuBtn, 1f, 1f, width, height);
		
		
	}
	
	private void chia2LaDau() {
		Image baiUp = new Image(PokerTexture.baiUp);
		baiUp.x = bobai.x;
		baiUp.y = bobai.y;
		
		baiUp.action(MoveTo.)
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.F1:			
			break;
		}
		return super.keyDown(keycode);
	}
}
