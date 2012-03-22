package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;

public class CardGroup extends Group {
	final static int NO_CARD = 5;
	int numCard = 0;
	CardImg[] cards = new CardImg[NO_CARD];
	float pad = 5;
	int id;
	public CardGroup() {
		int pad;
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
	
	
	public void newGame(){
		for (int i = 0; i < NO_CARD;i++){
			cards[i].visible = false;
			numCard = 0;
		}
	}
	
	public void nhanBai(int id){
		cards[numCard].setId(id);
		cards[numCard].visible = true;
		numCard++;
	}

	
	public void showImg(Image anh, float duration) {
		addActor(anh);
		ActorUtility.setRatio(anh, 0.5f, 0, width / 2, pad);
		anh.action(Delay.$(Remove.$(), 2f));
	}
}
