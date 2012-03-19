package com.gsn.poker.play;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.game.DataProvider;

public class BoardLayer extends GsnLayer {
	Image bobai;
	int pad = 10;
	PlayerGroup[] players = new PlayerGroup[5];
	public BoardLayer(float width, float height) {
		super(width, height);
		int myChair = 2;
		Image bg = new Image(PokerTexture.board);		
		bg.width = width;
		bg.height = height;
		addActor(bg);
		
		bobai = new Image(PokerTexture.baiUp);		
		ActorUtility.setCenter(bobai, width / 2, height / 2);		
		addActor(bobai);
		DataProvider.bobai = bobai;
		
		ButtonGroup btnGroup = new ButtonGroup();
		ActorUtility.setRatio(btnGroup, 1f, 0f, width, 0);
		addActor(btnGroup);
		
		ImageButton menuBtn = new ImageButton(PokerTexture.menuBtn, PokerTexture.menuBtnDown);
		ActorUtility.setRatio(menuBtn, 1f, 1f, width, height);
		
		for (int i = 0; i < players.length; i++){
			players[i] = new PlayerGroup(this, i);
			addActor(players[i]);
		}
		
		ActorUtility.setRatio(players[0], 0.5f, 0, (width - btnGroup.width) / 2, 0);
		ActorUtility.setRatio(players[1], 1f, 0.5f, width, height / 2);
		ActorUtility.setRatio(players[2], 0.5f, 1f, width * 0.75f, height);
		ActorUtility.setRatio(players[3], 0.5f, 1f, width * 0.25f, height);
		ActorUtility.setRatio(players[4], 0, 0.5f, 0, height / 2);				
		
		UserInfo userInfo = new UserInfo();
		userInfo.name = "trungdv";
		userInfo.gold = 1212134;
		
		players[0].setAvailable(true);
		players[0].setUserInfo(userInfo);
		
		players[3].setAvailable(true);
		players[3].setUserInfo(userInfo);
		
		players[1].setAvailable(true);
		//players[1].setUserInfo(userInfo);
	}
	
	private void chia2LaDau(int id1, int id2) {
		for (int i = 0; i < players.length; i++){
			if (players[i].isAvailable()){
				players[i].nhanBai(52);
			}
		}
		
		for (int i = 0; i < players.length; i++){
			if (players[i].isAvailable()){
				players[i].nhanBai(52);
			}
		}
		showChonQuan(id1, id2);
	}
	
	private void showChonQuan(int id1, int id2){
		ChonQuanGroup chonGroup = new ChonQuanGroup(this, id1, id2);
		ActorUtility.setCenter(chonGroup, width / 2, height / 2);
		addActor(chonGroup);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.F1:
			chia2LaDau(34, 48);
			break;
		}
		return super.keyDown(keycode);
	}
}
