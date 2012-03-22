package com.gsn.poker.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnEnableButton;
import com.gsn.poker.asset.PokerTexture;

public class ButtonGroup extends Group {
	private String tag = "Button Group";
	float pad = 5;
	public ButtonGroup(GsnEnableButton... buttons) {
		if (buttons.length != 4){
			Gdx.app.log(tag , "dau vao khac 4");
		}
		ActorUtility.setRatio(buttons[0], 0, 0, 0, 0);
		ActorUtility.setRatio(buttons[1], 0, 0, buttons[0].x + buttons[0].width + pad, buttons[0].y);
		ActorUtility.setRatio(buttons[2], 0, 0, buttons[0].x, buttons[0].y + buttons[0].height + pad);
		ActorUtility.setRatio(buttons[3], 0, 0, buttons[0].x + buttons[0].width + pad, buttons[0].y + buttons[0].height+ pad);
		
		for (int i = 0; i < 4; i++){
			addActor(buttons[i]);
		}
		
		this.width = buttons[3].x + buttons[3].width;
		this.height = buttons[3].y + buttons[3].height;
	}
}
