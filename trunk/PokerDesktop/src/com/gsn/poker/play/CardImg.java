package com.gsn.poker.play;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.logic.Card;

public class CardImg extends Image {

	public int id;

	public CardImg(int id) {
		super(getTextureCard(id));
		this.id = id;
	}
	
	public void setId(int id){
		this.id = id;
		super.setRegion(getTextureCard(id));
	}
	
	public int getId(){
		return id;
	}
	
	public void hide(){
		super.setRegion(getTextureCard(52));
	}
	
	public void show(){
		setRegion(getTextureCard(id));
	}

	public static AtlasRegion getTextureCard(int id) {
		if (id == 52)
			return PokerTexture.baiUp;
		int la = id / 4;
		int chat = id % 4;

		int tmp = 0;
		switch (chat) {
		case Card.eSPADE:
			tmp = 0;
			break;
		case Card.eCLUB:
			tmp = 3;
			break;
		case Card.eDIAMOND:
			tmp = 2;
			break;
		case Card.eHEART:
			tmp = 1;
			break;
		}
		
		return (PokerTexture.labai.get( (la - 7) * 4 + tmp ));
	}

}
