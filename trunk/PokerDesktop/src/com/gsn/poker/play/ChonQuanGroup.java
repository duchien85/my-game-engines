package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.gsn.engine.ActorUtility;

public class ChonQuanGroup extends Group {
	public BoardLayer boardLayer;	
	public ChonQuanGroup(BoardLayer boardLayer, int id1, int id2) {
		this.boardLayer = boardLayer;
		CardImg card1 = new CardImg(id1);
		CardImg card2 = new CardImg(id2);		
		ActorUtility.setRatio(card2, -0.5f, 0f, card1.x + card1.width, 0);
		
		addActor(card1);
		addActor(card2);
		
		this.width = card2.x + card2.width;
		this.height = card2.height;
	}
}
