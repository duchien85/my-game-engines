package com.gsn.poker.play;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.poker.asset.PokerTexture;

public class Card extends Image{
	public enum EChat{
		RO, CO, TEP, BICH
	}
	
	public static Card createCard(int quan, EChat chat){
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
		return new Card(PokerTexture.labai.get( (quan - 7) * 4 + tmp ) );
	}
	
	private Card(TextureRegion region) {
		super(region);
	} 
}
