package com.gsn.poker.packet;

import org.json.JSONException;
import org.json.JSONObject;

public class PacketFactory {
	private static JSONObject create() {
		JSONObject json = new JSONObject();
		try {
			json.put("ext", "Pokerhk");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public static JSONObject createLogin() {
		JSONObject json = null;
		try {
			String s = "{\"ext\":\"Pokerhk\",\"_cmd\":\"14\",\"params\":{}}";
			json = new JSONObject(s);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject createSelectChannel(int channelID) {
		JSONObject json = null;
		try {
			String s = "{\"ext\":\"Pokerhk\",\"_cmd\":\"200\",\"params\":{\"channelId\":" + channelID + "}}";
			json = new JSONObject(s);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return json;
	}

	public static JSONObject createQuickPlay() {		 
		 JSONObject json = null;
			try {
				String s = "{\"ext\":\"Pokerhk\",\"_cmd\":\"206\",\"params\":{}}";
				json = new JSONObject(s);
			} catch (JSONException e) {
				e.printStackTrace();
			}		
			return json;
	}
		
	public static JSONObject createReady() {		 
		 JSONObject json = null;
			try {
				String s = "{\"_cmd\":\"5\",\"ext\":\"Pokerhk\",\"params\":{}}";
				json = new JSONObject(s);
			} catch (JSONException e) {
				e.printStackTrace();
			}		
			return json;
	}
	
}
