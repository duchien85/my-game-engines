package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.Group;

public class CardGroup extends Group {
	final static int NO_CARD = 5;
	int numCard = 0;
	CardImg[] cards = new CardImg[NO_CARD];
	int pad;
	int id;
	public CardGroup() {		
		for (int i = 0; i < NO_CARD; i++){			
			cards[i] = new CardImg(52);
			pad = (int) (cards[i].width / 2);
			if (i == 0){
				cards[i].x = 0;
				cards[i].y = 0;
			} else {
				cards[i].x = cards[i - 1].x + pad;
				cards[i].y = 0;
			}
			addActor(cards[i]);
			cards[i].visible = false;		
		}		
		this.width = cards[NO_CARD - 1].x + cards[NO_CARD - 1].width;
		this.height = cards[NO_CARD - 1].height;				
	}
	
	
	
	public void nhanBai(int id){
		cards[numCard].setId(id);
		cards[numCard].visible = true;
		numCard++;
	}
}
