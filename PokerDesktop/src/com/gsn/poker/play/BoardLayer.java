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
	int pad = 10;
	PlayerGroup[] players = new PlayerGroup[5];
	public BoardLayer(float width, float height) {
		super(width, height);
		
		Image bg = new Image(PokerTexture.board);		
		bg.width = width;
		bg.height = height;
		addActor(bg);
		
		bobai = new Image(PokerTexture.baiUp);		
		ActorUtility.setCenter(bobai, width / 2, height / 2);
		addActor(bobai);
		
		ButtonGroup btnGroup = new ButtonGroup();
		ActorUtility.setRatio(btnGroup, 1f, 0f, width, 0);
		addActor(btnGroup);
		
		ImageButton menuBtn = new ImageButton(PokerTexture.menuBtn, PokerTexture.menuBtnDown);
		ActorUtility.setRatio(menuBtn, 1f, 1f, width, height);
		
		for (int i = 0; i < players.length; i++){
			players[i] = new PlayerGroup(i);
			addActor(players[i]);
		}
		
		ActorUtility.setRatio(players[0], 0.5f, 0, (width - btnGroup.width) / 2, 0);
		ActorUtility.setRatio(players[1], 1f, 0.5f, width, height / 2);
		ActorUtility.setRatio(players[2], 0.5f, 1f, width * 0.75f, height);
		ActorUtility.setRatio(players[3], 0.5f, 1f, width * 0.25f, height);
		ActorUtility.setRatio(players[4], 0, 0.5f, 0, height / 2);
		
		
		
		
		chia2LaDau();
	}
	
	private void chia2LaDau() {
		Image baiUp = new Image(PokerTexture.baiUp);
		baiUp.x = bobai.x;
		baiUp.y = bobai.y;
		
		baiUp.action(MoveTo.$(0, 0, 1));
		addActor(baiUp);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.F1:
			players[0].setText("Up bo", 2f);
			break;
		}
		return super.keyDown(keycode);
	}
}
