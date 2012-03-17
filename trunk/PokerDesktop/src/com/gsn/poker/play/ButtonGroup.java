package com.gsn.poker.play;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.poker.asset.PokerTexture;

public class ButtonGroup extends Group {
	int pad = 5;
	public ButtonGroup() {
		ImageButton[] btn = new ImageButton[9];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++){
				ImageButton tmp;
				tmp = new ImageButton(PokerTexture.boChonBtn);
				tmp.x = (tmp.width + pad) * j;
				tmp.y = (tmp.height + pad) * i;
				btn[i * 3 + j] = tmp;
				addActor(tmp);
			}
		this.width = btn[8].x + btn[8].width;
		this.height = btn[8].y + btn[8].height;
	}
}
