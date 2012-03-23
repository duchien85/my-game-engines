package com.gsn.poker.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;
import com.gsn.poker.asset.PokerTexture;

public class BetInfoGroup extends Group {
	public static String formatNumber(int number){		
		String s = String.valueOf(number);
		
		String tmp = "";
		int count = 0;
		for (int i = s.length() - 1; i >= 0; i--){
			count++;
			tmp = s.charAt(i) + tmp;
			if (count == 3 && i != 0){
				tmp = "," + tmp;
				count = 0;
			}				
		}
		return tmp;
	}
	
	public BetInfoGroup(int bet) {
		Image icon = new Image(PokerTexture.iconUSDcuoc);
		ActorUtility.setRatio(icon, 0, 0.5f, 0, PokerTexture.fontLarge.getLineHeight() * 0.6f);
		
		GsnLabel label = new GsnLabel(formatNumber(bet), PokerTexture.fontLarge, new Color(1, 1, 0, 1));
		ActorUtility.setRatio(label, 0, 0, icon.x + icon.width, 0);
		
		addActor(icon);
		addActor(label);
		this.height = icon.height;
	}	
}
