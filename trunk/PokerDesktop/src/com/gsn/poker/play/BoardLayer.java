package com.gsn.poker.play;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.game.DataProvider;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.packet.PacketFactory;

public class BoardLayer extends GsnLayer implements ClickListener {
	String tag = "Board Layer";
	Image bobai;
	int pad = 10;
	public int myChair = 2;

	PlayerGroup[] players = new PlayerGroup[5];
	ImageButton readyBtn;

	public BoardLayer(float width, float height) {
		super(width, height);
		int myChair = 2;
		Image bg = new Image(PokerTexture.board);
		bg.width = width;
		bg.height = height;
		addActor(bg);

		bobai = new Image(PokerTexture.baiUp);
		ActorUtility.setCenter(bobai, width / 2, height / 2);
		addActor(bobai);
		DataProvider.bobai = bobai;

		ButtonGroup btnGroup = new ButtonGroup();
		ActorUtility.setRatio(btnGroup, 1f, 0f, width, 0);
		addActor(btnGroup);

		ImageButton menuBtn = new ImageButton(PokerTexture.menuBtn, PokerTexture.menuBtnDown);
		ActorUtility.setRatio(menuBtn, 1f, 1f, width, height);

		for (int i = 0; i < players.length; i++) {
			players[i] = new PlayerGroup(this, i);
			addActor(players[i]);
		}

		ActorUtility.setRatio(players[0], 0.5f, 0, (width - btnGroup.width) / 2, 0);
		ActorUtility.setRatio(players[1], 1f, 0.5f, width, height / 2);
		ActorUtility.setRatio(players[2], 0.5f, 1f, width * 0.75f, height);
		ActorUtility.setRatio(players[3], 0.5f, 1f, width * 0.25f, height);
		ActorUtility.setRatio(players[4], 0, 0.5f, 0, height / 2);

		readyBtn = new ImageButton(PokerTexture.readyBtn, PokerTexture.readyBtnDown);
		ActorUtility.setCenter(readyBtn, width / 2, height / 2);
		addActor(readyBtn);
		readyBtn.setClickListener(this);

	}
	
	public void chia2LaDau(int id1, int id2) {
		card1 = id1;
		card2 = id2;
		for (int i = 0; i < players.length; i++) {
			if (players[i].isAvailable()) {
				players[i].addCardID(52);
			}
		}

		for (int i = 0; i < players.length; i++) {
			if (players[i].isAvailable()) {
				players[i].nhanBai(52);
			}
		}
		showChonQuan(id1, id2);
	}

	ChonQuanGroup chonGroup;

	private void showChonQuan(int id1, int id2) {
		chonGroup = new ChonQuanGroup(this, id1, id2);
		ActorUtility.setCenter(chonGroup, width / 2, height / 2);
		addActor(chonGroup);
	}

	int card1, card2;

	public void showFirstCard(int... cards) {
		chonGroup.remove();
		for (int i = 0; i < 5; i++) {
			if (cards[(i + myChair) % 5] != 52) {
				players[i].setCardID(1, cards[(i + myChair) % 5]);
			}
		}
		if (cards[myChair] == card1)
			players[0].setCardID(0, card2);
		else
			players[0].setCardID(0, card1);
		players[0].setDark(0, true);
	}

	public void dealCard(JSONObject json) {
		try {
			JSONArray cardIdArr = json.getJSONArray("cardID");
			for (int i = 0; i < cardIdArr.length(); i++) {
				JSONObject card = cardIdArr.getJSONObject(i);
				int nCard = card.getInt("nCard");
				if (nCard == 0)
					continue;
				JSONArray cardID = card.getJSONArray("cardID");
				for (int j = 0; j < nCard; j++) {
					int tmp = cardID.getInt(j);
					players[(i + 5 - myChair) % 5].addCardID(tmp);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		String s;
		JSONObject json;
		try {
			switch (keycode) {
			case Keys.F1:
				chia2LaDau(36, 48);
				break;
			case Keys.F2:
				showFirstCard(34, 35, 36, 52, 52);
				break;
			case Keys.F3:
				s = "{\"firstTurnId\":2,\"cmdID\":105,\"cardID\":[{\"nCard\":1,\"cardID\":[30,0,0]},{\"nCard\":1,\"cardID\":[38,0,0]},{\"nCard\":1,\"cardID\":[22,0,0]},{\"nCard\":0,\"cardID\":[0,0,0]},{\"nCard\":0,\"cardID\":[0,0,0]}]}";

				json = new JSONObject(s);
				dealCard(json);

				break;
			case Keys.F4:
				s = " {\"card2\":[34,35,48,52,52],\"cmdID\":100,\"card1\":[37,21,36,52,52]}";

				json = new JSONObject(s);
				showPublicCard(json);

				break;
			case Keys.F5:
				s = "{\"chipGet\":[1057,0,0,0,0],\"cmdID\":9,\"resultType\":[1,2,2,0,0]}";
				json = new JSONObject(s);
				endGame(json);
				break;
			case Keys.F6:
				s = "{\"bean\":10689,\"dogfall\":0,\"score\":734,\"lostCount\":0,\"winCount\":1,\"nChair\":0,\"coin\":3,\"cmdID\":50,\"escapeCount\":0}";
				json = new JSONObject(s);
				updateMoney(json);
				break;
			case Keys.A:				 
				 MyPoker.client.send(PacketFactory.createTheo(minBet));
				break;
			case Keys.S:
				//up bo							
				MyPoker.client.send(PacketFactory.createFold());
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return super.keyDown(keycode);
	}

	public void endGame(JSONObject json) {
		try {
			JSONArray result = json.getJSONArray("resultType");
			for (int i = 0; i < 5;i++)
				players[i].newGame();
			switch (result.getInt(myChair)){			
			case 1:
				win();
				break;
			case 2:
				lose();
				break;
			}
			for (int i = 0; i < 5; i++){
				int type = result.getInt(i);				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	private void lose() {
		Gdx.app.log(tag, "lose");
		readyBtn.visible = true;
	}

	private void win() {
		Gdx.app.log(tag, "win");		
		readyBtn.visible = true;
	}

	private void updateMoney(JSONObject json) {
		try {
			int nChair = json.getInt("nChair");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showPublicCard(JSONObject json) {
		try {
			JSONArray card1 = json.getJSONArray("card1");
			JSONArray card2 = json.getJSONArray("card2");
			for (int i = 0; i < 5; i++) {
				int tmp1 = card1.getInt((i + myChair) % 5);
				int tmp2 = card2.getInt((i + myChair) % 5);
				if (tmp1 == 52)
					continue;
				if (players[i].getCardID(1) == tmp1)
					players[i].setCardID(0, tmp2);
				else
					players[i].setCardID(0, tmp1);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setUserInfor(int chair, UserInfo info) {
		int stt = (chair + 5 - myChair) % 5;		
		players[stt].setUserInfo(info);
		players[stt].setAvailable(true);
	}

	public void userExit(int chair) {
		int stt = (chair + 5 - myChair) % 5;		
		players[stt].setAvailable(false);		
	}

	@Override
	public void click(Actor actor, float x, float y) {
		if (actor == readyBtn){
			readyBtn.visible = false;
			MyPoker.client.send(PacketFactory.createReady());			
		}
		
	}

	public void start() {
		Gdx.app.log(tag, "Game Start");		
	}
	
	int minBet;

	public void changeTurn(int playerTurn, JSONObject json) {
		players[ (playerTurn + 5 - myChair) % 5].setText("Chuyển Lượt", 5f);		
		try {
			minBet = json.getInt("minbet");
		} catch (JSONException e) {		
			e.printStackTrace();
		}
	}

	public void fold(int nChair) {
		players[(nChair + 5 - myChair) % 5].setText("FOLD", 3);
		
	}	
}
