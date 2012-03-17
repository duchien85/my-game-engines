package com.gsn.poker.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.poker.asset.PokerTexture;

public class Card extends Image{
	public enum EChat{
		RO, CO, TEP, BICH
	}
	
	public static AtlasRegion getBaiUp(){
		return PokerTexture.baiUp;
	}
	
	public static AtlasRegion getRegion(int quan, EChat chat){
		int tmp = 0;
		switch (chat){
		case BICH:
			tmp = 0;
			break;
		case CO:
			tmp = 1;
			break;
		case RO:
			tmp = 2;
			break;
		case TEP:
			tmp = 3;
			break;
		}
		return PokerTexture.labai.get( (quan - 7) * 4 + tmp );
	}
	
	public static Card createCard(int quan, EChat chat){		
		return new Card(getRegion(quan, chat));
	}
	
	private Card(TextureRegion region) {
		super(region);
	} 
}
