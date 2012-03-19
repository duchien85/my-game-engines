package com.gsn.poker.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.game.DataProvider;
import com.gsn.poker.logic.Card;

public class PlayerGroup extends Group {
	protected boolean available;
	AvatarUser avatar;
	CardGroup cardGroup;
	int id;
	UserInfo info;
	int pad = 5;
	private BoardLayer boardLayer;
	
	public PlayerGroup(BoardLayer boardLayer, int id) {
		this.id = id;
		this.boardLayer = boardLayer;
		avatar = new AvatarUser();
		addActor(avatar);		
		
		cardGroup = new CardGroup();
		ActorUtility.setRatio(cardGroup, 0, 0.5f, avatar.x + avatar.width + pad, avatar.height / 2);
		addActor(cardGroup);
		
		this.width = avatar.x + avatar.width + pad + cardGroup.width;
		this.height = avatar.height;
		
		setAvailable(false);
	}
	
	public boolean isAvailable(){
		return available;
	}
	
	public void setAvailable(boolean _available){
		available = _available;
		visible = available;
		
		
	}
	
	public void setText(String text, float duration){
		GsnLabel label = new GsnLabel(text, PokerTexture.fontLarge, new Color(0.8f, 0f, 0, 1));
		ActorUtility.setCenter(label, cardGroup.x + cardGroup.width / 2, cardGroup.y + cardGroup.height / 2);
		label.action(Delay.$(Remove.$(), duration));
		addActor(label);
		
	}
	
	public void setUserInfo(UserInfo info){
		this.info = info;		
		avatar.setGold(info.gold);
		avatar.setName(info.name);
	}
	
	public void nhanBai(int idCard){
		cardGroup.nhanBai(idCard);
	}
}
