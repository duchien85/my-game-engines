package com.gsn.poker.game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.gsn.engine.mercurry.MercuryClient.IMercuryListener;
import com.gsn.engine.myplay.GsnGame;
import com.gsn.poker.asset.PokerTexture;
import com.gsn.poker.packet.CmdDefine;
import com.gsn.poker.packet.PacketFactory;
import com.gsn.poker.play.PlayScreen;
import com.gsn.poker.play.UserInfo;

public class PokerGame extends GsnGame implements IMercuryListener {
	PlayScreen playScreen;
	private String tag = "Poker Game";

	@Override
	public void create() {
		super.create();
		PokerTexture.create();
		PokerTexture.loadAll();

		playScreen = new PlayScreen(width, height);

		setScreen(playScreen);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		MyPoker.client.disconnect();
	}

	@Override
	public void onConnected() {

		// MyPoker.client.send(PacketFactory.createLogin());
		String name = "t" + (int) (Math.random() * 1000);
		String s = "{\"handler\": \"login\", \"username\":\"" +  name + "\", \"password\":\"dawdwad\"}";
		MyPoker.client.send(s);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceived(String s) {
		//

	}
	int myChair;

	@Override
	public void onReceivedJson(JSONObject json) {
		try {
			if (json.has("cmdID") && json.getInt("cmdID") != 203)
				Gdx.app.log(tag, "receive : " + json);
			if (json.has("loginOK")) {
				if (json.getInt("loginOK") == 0) 
					MyPoker.client.send(PacketFactory.createLogin());
				else 
					Gdx.app.log(tag, "login faiulre");
				return;
			}
			int cmd;

			cmd = json.getInt("cmdID");
			switch (cmd) {
			case CmdDefine.CMD_NOTIFY_PLAYER_INFO:
				MyPoker.client.send(PacketFactory.createSelectChannel(0));
				break;			
			case CmdDefine.CMD_SELECT_CHANNEL:
				MyPoker.client.send(PacketFactory.createQuickPlay());
				break;
			case CmdDefine.CMD_JOIN_ROOM_SUCCESS:
				myChair = json.getInt("myChair");
				playScreen.boardLayer.myChair = myChair;
				JSONArray playerStatus = json.getJSONArray("playerStatus");
				JSONArray playerList = json.getJSONArray("playerList");
				
				for (int i = 0; i < 5; i++)
					if (playerStatus.getInt(i) == 1){
						JSONObject jp = playerList.getJSONObject(i);
						String name = jp.getString("uName");
						String avatar = jp.getString("avtURL");
						int gold = jp.getInt("bean");
						int uid = jp.getInt("uId");
						UserInfo info = new UserInfo();
						info.name = name;
						info.id = uid;
						info.gold = gold;
						info.avatar = avatar;
						playScreen.boardLayer.setUserInfor(i, info);
					}
				break;
			case CmdDefine.CMD_NEW_USER_JOIN:
				int nChair = json.getInt("nChair");
				UserInfo info = new UserInfo();
				JSONObject jp = json.getJSONObject("playerInfo");
				info.name = jp.getString("uName");
				info.id = jp.getInt("uId");
				info.gold = jp.getInt("bean");
				info.avatar = jp.getString("avtURL");
				playScreen.boardLayer.setUserInfor(nChair, info);
				break;
			case CmdDefine.CMD_USER_EXIT_ROOM:
				nChair = json.getInt("nChair");
				playScreen.boardLayer.userExit(nChair);
				break;
			case CmdDefine.CMD_NOTIFY_GAME_START:
				playScreen.boardLayer.start();
				break;
			case CmdDefine.CMD_NOTIFY_PUBLIC_CARD:
				JSONArray card1 = json.getJSONArray("card1");
				JSONArray card2 = json.getJSONArray("card2");
				playScreen.boardLayer.chia2LaDau(card1.getInt(myChair), card2.getInt(myChair));
				break;
			case CmdDefine.CMD_NOTIFY_SHOW_FIRST_CARD:
				int[] cards = new int[5];
				JSONArray arr = json.getJSONArray("cardID");
				for (int i = 0; i < 5; i++)
					cards[i] = arr.getInt(i);
				playScreen.boardLayer.showFirstCard(cards);
				break;
			case CmdDefine.CMD_JOIN_ROOM_FAIL:
				Gdx.app.log(tag, "ko vao dc room!!!");
				break;
			case CmdDefine.CMD_NOTIFY_CHANGE_TURN:
				int playerTurn = json.getInt("playerTurn");
				playScreen.boardLayer.changeTurn(playerTurn);				
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onException(Exception ex) {
		// TODO Auto-generated method stub

	}

}
