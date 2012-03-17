package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.play.Card.EChat;

public class CardGroup extends Group {
	final static int NO_CARD = 5;
	Card[] cards = new Card[NO_CARD];
	int pad;
	int id;
	public CardGroup() {		
		for (int i = 0; i < NO_CARD; i++){			
			cards[i] = Card.createCard(8 + i, EChat.RO);
			pad = (int) (cards[i].width / 2);
			if (i == 0){
				cards[i].x = 0;
				cards[i].y = 0;
			} else {
				cards[i].x = cards[i - 1].x + pad;
				cards[i].y = 0;
			}
			addActor(cards[i]);
		}
		
		this.width = cards[NO_CARD - 1].x + cards[NO_CARD - 1].width;
		this.height = cards[NO_CARD - 1].height;
	}
}
