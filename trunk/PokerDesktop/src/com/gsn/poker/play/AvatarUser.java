package com.gsn.poker.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;
import com.gsn.poker.asset.PokerTexture;

public class AvatarUser extends Group {
	GsnLabel nameLabel;
	GsnLabel goldLabel;
	
	public AvatarUser() {
		Image avatar = new Image(PokerTexture.infoUser);
		addActor(avatar);
		this.width = avatar.width;
		this.height = avatar.height;		
	}
	
	public void setName(String name){
		if (nameLabel != null)
			nameLabel.remove();
		nameLabel = new GsnLabel(ActorUtility.getShortName(name), PokerTexture.fontMedium, new Color(1f, 1f, 1f, 1f));
		ActorUtility.setRatio(nameLabel, 0.5f, 0.5f, width / 2, height);
		addActor(nameLabel);
	}
	
	public void setGold(int gold){
		if (goldLabel != null)
			goldLabel.remove();
		goldLabel = new GsnLabel(String.valueOf(gold), PokerTexture.fontMedium, new Color(1f, 1f, 0.1f, 1f));
		ActorUtility.setRatio(goldLabel, 0.5f, 0f, width / 2, 0);
		addActor(goldLabel);
	}
}
