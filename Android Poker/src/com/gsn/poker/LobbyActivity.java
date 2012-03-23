package com.gsn.poker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gsn.engine.DownloaderAndroid;
import com.gsn.engine.mercurry.MercuryClient.IMercuryListener;
import com.gsn.poker.PokerService.PokerBinder;
import com.gsn.poker.game.DataProvider;
import com.gsn.poker.game.MyPoker;
import com.gsn.poker.game.PokerGame;
import com.gsn.poker.packet.CmdDefine;
import com.gsn.poker.packet.PacketFactory;
import com.gsn.poker.play.UserInfo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LobbyActivity extends Activity implements IMercuryListener{		
	String tag = "Lobby Activity";
	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(tag, "on disconnect service");			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(tag, "on Connect Service");			
			PokerBinder binder = (PokerBinder) service;
			MyPoker.client = binder.client;						
			MyPoker.client.downloader = new DownloaderAndroid();
			MyPoker.client.downloader.cacheDir = getExternalCacheDir();
			//MyPoker.chatInput = new LoginDlg(ChessActivity.this, R.layout.chat);
			
			MyPoker.client.setListener(LobbyActivity.this);					
		}
	};
	
	public void onBackPressed() {
		super.onBackPressed();
	};
	
	PokerGame game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "on resume");
        
        setContentView(R.layout.main);
		Button btn = (Button)this.findViewById(R.id.okBtn);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				MyPoker.client.connect();
			}
		});
        		       
        this.bindService(new Intent(this, PokerService.class), conn, Context.BIND_AUTO_CREATE);
       // setContentView(R.layout.main);
    }
	@Override
	public void onConnected() {
		String name = "t" + (int) (Math.random() * 1000);
		String s = "{\"handler\": \"login\", \"username\":\"" +  name + "\", \"password\":\"dawdwad\"}";		
		MyPoker.client.send(s);		
	}
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onException(Exception ex) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReceived(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReceivedJson(JSONObject json) {
		try {
			if (json.has("cmdID") && json.getInt("cmdID") != 203)
				Log.i(tag, "receive : " + json);
			if (DataProvider.dangChuyen)
				DataProvider.json.add(json);
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
				DataProvider.json.add(json);
				DataProvider.dangChuyen = true;
				Intent intent = new Intent(this, AndroidPokerActivity.class);
				startActivity(intent);
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	protected void onResume() {	
		super.onResume();
		Log.i(tag, "on resume");
		if (MyPoker.client != null)
			MyPoker.client.setListener(this);
	}
}
