package com.gsn.poker.play;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.engine.template.GsnEnableButton;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.packet.PacketFactory;

public class BoardLayer extends GsnLayer implements ClickListener {
	static final public String NHUONG_TO = "nhuong to";
	static final public String THEO = "theo";
	static final float TIME_DEAL = 0.2f;
	static final float TIME_DEAL_TURN = 2f;
	static final public String TO = "to";
	static final public String TO_GAP_DOI = "to gap doi";
	
	static final public String TO_NUA = "to mot nua";
	static final public String TO_TAT_CA = "to tat ca";
	static final public String TO_TU = "to mot phan tu";
	static final public String UP_BO = "up bo";
	Image bobai;
	GsnEnableButton[] btnArr = new GsnEnableButton[8];
	ButtonGroup btnGroupOne;
	ButtonGroup btnGroupTwo;
	

	int card1, card2;
	ChonQuanGroup chonGroup;
	
	int minBet;

	public int myChair = 2;
	
	float pad = 10;
	
	PlayerGroup[] players = new PlayerGroup[5];
	ImageButton readyBtn;

	String tag = "Board Layer";

	public BoardLayer(float width, float height) {
		super(width, height);
		int myChair = 2;
		Image bg = new Image(PokerTexture.board);
		bg.width = width;
		bg.height = height;
		addActor(bg);

		// bobai = new Image(PokerTexture.baiUp);
		// ActorUtility.setCenter(bobai, width / 2, height / 2);
		// addActor(bobai);
		// DataProvider.bobai = bobai;

		// ButtonGroup btnGroup = new ButtonGroup();
		// ActorUtility.setRatio(btnGroup, 1f, 0f, width, 0);
		// addActor(btnGroup);

		ImageButton menuBtn = new ImageButton(PokerTexture.menuBtn, PokerTexture.menuBtnDown);
		ActorUtility.setRatio(menuBtn, 1f, 1f, width, height);

		for (int i = 0; i < players.length; i++) {
			if (i == 1 || i == 2)
				players[i] = new PlayerGroup(1, this, i);
			else
				players[i] = new PlayerGroup(0, this, i);
			addActor(players[i]);
		}

		ActorUtility.setRatio(players[0], 0.5f, 0, width / 2, 0);
		ActorUtility.setRatio(players[1], 1f, 0.5f, width, height / 2);
		ActorUtility.setRatio(players[2], 1f, 1f, width, height);
		ActorUtility.setRatio(players[3], 0f, 1f, 0, height);
		ActorUtility.setRatio(players[4], 0, 0.5f, 0, height / 2);

		readyBtn = new ImageButton(PokerTexture.readyBtn, PokerTexture.readyBtnDown);
		ActorUtility.setCenter(readyBtn, width / 2, height / 2);
		// addActor(readyBtn);
		readyBtn.setClickListener(this);

		players[0].setAvailable(true);
		players[3].setAvailable(true);
		players[4].setAvailable(true);

		players[2].setAvailable(true);
		//players[1].setAvailable(true);
				
		addBtn();
	}

	private void addBtn() {
		for (int i = 0; i < 8; i++){
			switch (i){
			case 0:
				btnArr[i] = new GsnEnableButton(i, TO, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 1:
				btnArr[i] = new GsnEnableButton(i, TO_TAT_CA, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 2:
				btnArr[i] = new GsnEnableButton(i, TO_NUA, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 3:
				btnArr[i] = new GsnEnableButton(i, TO_TU, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 4:
				btnArr[i] = new GsnEnableButton(i, TO_GAP_DOI, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 5:
				btnArr[i] = new GsnEnableButton(i, NHUONG_TO, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 6:
				btnArr[i] = new GsnEnableButton(i, THEO, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			case 7:
				btnArr[i] = new GsnEnableButton(i, UP_BO, PokerTexture.xepBtn, PokerTexture.xepBtnDown, PokerTexture.xepBtnDown);
				break;
			}			
			btnArr[i].setClickListener(this);			
		}
		btnGroupOne = new ButtonGroup(btnArr[0], btnArr[1], btnArr[2], btnArr[3]);
		btnGroupOne.visible = false;
		addActor(btnGroupOne);
		ActorUtility.setRatio(btnGroupOne, 0, 0, pad, pad);
		
		btnGroupTwo = new ButtonGroup(btnArr[4], btnArr[5], btnArr[6], btnArr[7]);
		btnGroupTwo.visible = false;
		addActor(btnGroupTwo);
		ActorUtility.setRatio(btnGroupTwo, 1, 0, width - pad, pad);
	}

	public void changeTurn(int playerTurn, JSONObject json) {
		changeTurn(playerTurn);
		try {
			minBet = json.getInt("minbet");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void changeTurn(int playerTurn){
		players[(playerTurn + 5 - myChair) % 5].setText("Chuyển Lượt", 5f);
		for (int i = 0; i < 5; i++)
			players[i].effQuaLuot();
		
		players[getChair(playerTurn)].effDenLuot();
		
		if (playerTurn == myChair){
			btnGroupOne.visible = true;
			btnGroupTwo.visible = true;
		} else {
			btnGroupOne.visible = false;
			btnGroupTwo.visible = false;
		}		
	}
	
	public float chia1vong(final int first, final int... idCard) {
		final float dur = TIME_DEAL;
		float time = 0;
		
		for (int i = 0; i < 5; i++)
			players[i].effQuaLuot();
		
		for (int i = 0; i < 5; i++) {
			Timer timer = new Timer();
			final int tmp = i;			
			if (players[getChair(first + i)].isAvailable()) {
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						chiaBai(getChair(first + tmp), idCard[(first + tmp) % 5], dur);
					}
				}, (int) (time * 1000));
				time += dur;
			}			
		}
		return time;
	}

	public void chia2LaDau(final int id1, final int id2) {
		card1 = id1;
		card2 = id2;
		float time = chia1vong(myChair, 52, 52, 52, 52, 52);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				chia1vong(myChair, 52, 52, 52, 52, 52);
				
			}
		}, (int) (1000 * time));
		
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			
			@Override
			public void run() {
				showChonQuan(id1, id2);				
			}
		}, (int) (2 * 1000 * time));
		
	}

	public void chiaBai(final int playerID, final int cardID, final float duration) {
		// players[playerID].chiaBai(cardID, duration);
		if (cardID == 0)
			return;
		Image card = new Image(PokerTexture.baiUp);
		float x = players[playerID].x + players[playerID].cardGroup.x + players[playerID].cardGroup.cards[players[playerID].cardGroup.numCard].x;
		float y = players[playerID].y + players[playerID].cardGroup.y + players[playerID].cardGroup.cards[players[playerID].cardGroup.numCard].y;
		ActorUtility.setRatio(card, 0.5f, 1, width / 2, height);
		card.action(Sequence.$(MoveTo.$(x, y, duration), Remove.$()));
		addActor(card);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				players[playerID].addCardID(cardID);
			}
		}, (int) (duration * 1000));
	}

	@Override
	public void click(Actor actor, float x, float y) {
		if (actor == readyBtn) {
			readyBtn.visible = false;
			MyPoker.client.send(PacketFactory.createReady());
		}
		
		if (actor instanceof GsnEnableButton){
			Gdx.app.log(tag, "click button : " + ((GsnEnableButton) actor).nameButton);
		}

	}

	public void dealCard(JSONObject json) {
		try {
			JSONArray cardIdArr = json.getJSONArray("cardID");			
			final int first = json.getInt("firstTurnId");
			final int[][] arr = new int[3][5];
			int nCard = 0;
			for (int i = 0; i < cardIdArr.length(); i++) {
				JSONObject card = cardIdArr.getJSONObject(i);
				int n = card.getInt("nCard");
				if (nCard < n)
					nCard = n;
//				if (nCard == 0)
//					continue;
				JSONArray cardID = card.getJSONArray("cardID");
				for (int j = 0; j < 3; j++) {
					int tmp = cardID.getInt(j);
					arr[j][i] = tmp;
					//arr[j][i] = 40;
				}
			}		
			
			float time = 0;
			for (int i = 0; i < nCard; i++){
				Timer timer = new Timer();
				final int tmp = i;
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						chia1vong(first, arr[tmp]);		
					}
				}, (int)(time * 1000));
				time += getTimeChia1vong(first, arr[tmp]);				 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void endGame(JSONObject json) {
		try {
			JSONArray result = json.getJSONArray("resultType");
			for (int i = 0; i < 5; i++)
				players[i].newGame();
			switch (result.getInt(myChair)) {
			case 1:
				win();
				break;
			case 2:
				lose();
				break;
			}
			for (int i = 0; i < 5; i++) {
				int type = result.getInt(i);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void fold(int nChair) {
		players[(nChair + 5 - myChair) % 5].setText("FOLD", 3);

	}
	public int getChair(int id) {
		return (id + 5 - myChair) % 5;
	}

	public int getID(int chair) {
		return (chair + myChair) % 5;
	}

	public float getTimeChia1vong(final int first, final int... idCard){
		final float dur = TIME_DEAL;
		float time = 0;
		for (int i = 0; i < 5; i++) {			
			if (players[getChair(first + i)].isAvailable() && idCard[(first + i) % 5] != 0 ) {
				time += dur;
			}			
		}
		return time;
		
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
				s = "{\"firstTurnId\":1,\"cmdID\":105,\"cardID\":[{\"nCard\":1,\"cardID\":[30,0,0]},{\"nCard\":1,\"cardID\":[38,0,0]},{\"nCard\":1,\"cardID\":[22,0,0]},{\"nCard\":0,\"cardID\":[0,0,0]},{\"nCard\":0,\"cardID\":[0,0,0]}]}";
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
				MyPoker.client.send(PacketFactory.createFold());
				break;
			case Keys.Q:
				changeTurn(2);
				break;
			case Keys.W:
				changeTurn(1);
				break;
			case Keys.E:
				players[0].upBo();
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return super.keyDown(keycode);
	}

	private void lose() {
		Gdx.app.log(tag, "lose");
		readyBtn.visible = true;
	}

	public void setUserInfor(int chair, UserInfo info) {
		int stt = (chair + 5 - myChair) % 5;
		players[stt].setUserInfo(info);
		players[stt].setAvailable(true);
	}

	private void showChonQuan(int id1, int id2) {
		chonGroup = new ChonQuanGroup(this, id1, id2);
		ActorUtility.setCenter(chonGroup, width / 2, height / 2);
		addActor(chonGroup);
		//this.root.addActorAt(this.root.getActors().size() - 1, chonGroup);
	}

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

	public void start() {
		Gdx.app.log(tag, "Game Start");
	}

	private void updateMoney(JSONObject json) {
		try {
			int nChair = json.getInt("nChair");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void userExit(int chair) {
		int stt = (chair + 5 - myChair) % 5;
		players[stt].setAvailable(false);
	}

	private void win() {
		Gdx.app.log(tag, "win");
		readyBtn.visible = true;
	}
}
