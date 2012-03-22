package com.gsn.poker.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.template.GsnLabel;
import com.gsn.poker.asset.PokerTexture;

public class PlayerGroup extends Group {
	protected boolean available;
	AvatarUser avatar;
	CardGroup cardGroup;
	int id;
	UserInfo info;
	int pad = 5;
	private BoardLayer boardLayer;
	Image effDenLuotImg;
	
	public PlayerGroup(int type, BoardLayer boardLayer, int id) {
		this.id = id;
		this.boardLayer = boardLayer;
				
		avatar = new AvatarUser();		
		cardGroup = new CardGroup();
		
		this.width = avatar.width + pad + cardGroup.width;
		this.height = avatar.height;
		
		if (type == 0){
			ActorUtility.setRatio(avatar, 0f, 0f, 0, 0);
			ActorUtility.setRatio(cardGroup, 0, 0.5f, avatar.x + avatar.width + pad, avatar.height / 2);
		} else {
			ActorUtility.setRatio(avatar, 1, 0.5f, width, height / 2);
			ActorUtility.setRatio(cardGroup, 0, 0.5f, 0, height / 2);			
		}
		
		this.width += 2 * pad;
		this.height += 2 * pad;
		avatar.x += pad;
		avatar.y += pad;
		cardGroup.x += pad;
		cardGroup.y += pad;
		
		Image bg = new Image(PokerTexture.BgAlpha);
		bg.width = width;
		bg.height = height;
		
		addActor(bg);
		addActor(avatar);
		addActor(cardGroup);
		
		effDenLuotImg = new Image(new NinePatch(GdxUtility.convertListRegionToArray(PokerTexture.vien)));
		effDenLuotImg.width = width;
		effDenLuotImg.height = height;
		addActor(effDenLuotImg);
		effDenLuotImg.visible = false;
		
		setAvailable(false);
	}
	
	public boolean isAvailable(){
		return available;
	}
	
	public void setCardID(int stt, int id){
		cardGroup.cards[stt].setId(id);
	}
	
	public int getCardID(int stt){
		return cardGroup.cards[stt].getId();
	}
	
	public void setDark(int stt, boolean dark){
		cardGroup.cards[stt].setDark(dark);
	}
	
	public void addCardID(int id){
		cardGroup.nhanBai(id);
	}
	
	public void setAvailable(boolean _available){
		available = _available;
		visible = available;
		
		
	}
	
	public void setText(String text, float duration){
		GsnLabel label = new GsnLabel(text, PokerTexture.fontLarge, new Color(1f, 1f, 1f, 1));
		ActorUtility.setCenter(label, cardGroup.x + cardGroup.width / 2, cardGroup.y + cardGroup.height / 2);
		label.action(Delay.$(Remove.$(), duration));
		addActor(label);
		
	}
	
	public void setUserInfo(UserInfo info){
		this.info = info;		
		avatar.setGold(info.gold);
		avatar.setName(info.name);
	}
	
	public void nhanBai(int idCard){
		cardGroup.nhanBai(idCard);
	}

	public void newGame() {
		cardGroup.newGame();
		
	}

	public void chiaBai(int cardID, float duration) {
		
		
	}

	public void effDenLuot() {
		effDenLuotImg.visible = true;
	}
	
	public void effQuaLuot() {
		effDenLuotImg.visible = false;
	}

	public void upBo() {
		cardGroup.showImg(new Image(PokerTexture.chuUpBo), 2f);
		
	}
}
