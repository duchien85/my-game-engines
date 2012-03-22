package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.poker.asset.PokerTexture;

public class ChonQuanGroup extends Group {
	public BoardLayer boardLayer;
	public float pad = 20;
	public ChonQuanGroup(BoardLayer boardLayer, int id1, int id2) {
		this.boardLayer = boardLayer;
		CardImg card1 = new CardImg(id1);
		CardImg card2 = new CardImg(id2);		
		ActorUtility.setRatio(card2, -0.5f, 0f, card1.x + card1.width, 0);
		
		this.width = card2.x + card2.width;
		this.height = card2.height;
		
		width += 2 * pad;
		height += 2 * pad;
		
		addActor(card1);
		addActor(card2);
		
		for (Actor actor : this.getActors()){
			actor.x += pad;
			actor.y += pad;
		}
		
		
		
		Image bg = new Image(PokerTexture.BgAlpha);
		bg.width = width;
		bg.height = height;
		addActorAt(0, bg);
		
		
		
		
		
	}
}
