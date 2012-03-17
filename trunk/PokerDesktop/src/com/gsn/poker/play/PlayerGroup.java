package com.gsn.poker.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.play.Card.EChat;

public class PlayerGroup extends Group {
	CardGroup cardGroup;
	int pad = 5;
	int id;
	public PlayerGroup(int id) {
		this.id = id;
		Image avatar = new Image(PokerTexture.infoUser);
		addActor(avatar);		
		
		cardGroup = new CardGroup();
		ActorUtility.setRatio(cardGroup, 0, 0.5f, avatar.x + avatar.width + pad, avatar.height / 2);
		addActor(cardGroup);
		
		this.width = avatar.x + avatar.width + pad + cardGroup.width;
		this.height = avatar.height;
	}
	
	public void setText(String text, float duration){
		GsnLabel label = new GsnLabel(text, PokerTexture.fontLarge, new Color(0.8f, 0f, 0, 1));
		ActorUtility.setCenter(label, cardGroup.x + cardGroup.width / 2, cardGroup.y + cardGroup.height / 2);
		label.action(Delay.$(Remove.$(), duration));
		addActor(label);
		
	}
}
