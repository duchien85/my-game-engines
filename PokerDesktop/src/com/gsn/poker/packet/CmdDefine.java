package com.gsn.poker.packet;

public class CmdDefine {
	public static final String CMD = "cmdID";
	
    public static final int CMD_CUSTOM_LOGIN = 0;
    //public static final int CMD_QUICK_JOIN_ROOM = 1;
    public static final int CMD_NEW_USER_JOIN = 2;
    public static final int CMD_JOIN_ROOM_SUCCESS = 3;
    public static final int CMD_USER_EXIT_ROOM = 4;
    public static final int CMD_REQUEST_READY = 5;
    public static final int CMD_NOTIFY_USER_READY = 6;
    public static final int CMD_NOTIFY_GAME_START = 7;
    public static final int CMD_NOTIFY_GAME_END = 8;
    public static final int CMD_NOTIFY_GAME_RESULT = 9;
    public static final int CMD_REQUEST_BASEINFO = 10;
    public static final int CMD_NOTIFY_BASEINFO = 11;
    public static final int CMD_NOTIFY_KICK_FROM_ROOM = 12;
    public static final int CMD_QUIT_ROOM_SUCCESS = 13;
    public static final int CMD_REQUEST_PLAYER_INFO = 14;
    public static final int CMD_NOTIFY_PLAYER_INFO = 15;
   
    // ######### new api
    // base server
    public static final int CMD_SELECT_CHANNEL = 200;
    public static final int CMD_EXIT_CHANNEL = 201;
    // channel
    public static final int CMD_UPDATE_LEAF = 203;
    public static final int CMD_CREATE_ROOM = 204;
    public static final int CMD_CHANGE_LEAF = 205;
    public static final int CMD_QUICK_PLAY = 206;
    public static final int CMD_JOIN_ROOM = 207;    
    // room
    public static final int CMD_TAKE_CHAIR = 208;
    public static final int CMD_NEW_USER_TAKE_CHAIR = 209;
    public static final int CMD_CHANGE_TO_WATCH = 210;
    
  

    public static final int CMD_UPDATE_MONEY = 50;
    public static final int CMD_REQUEST_FRIEND_LIST = 51;
    public static final int CMD_NOTIFY_FRIEND_LIST = 52;
    //public static final int CMD_QUICK_PLAY = 53;
    public static final int CMD_SEND_MESSAGE = 54;
    public static final int CMD_UPDATE_ROOM_LIST = 55;
    public static final int CMD_NOTIFY_DAILY_GIFT = 56;
  
    public static final int CMD_GET_DAILY_GIFT = 66;
    public static final int CMD_GET_DAILY_GIFT_SUCCESS = 67;
  
    public static final int CMD_JOIN_ROOM_FAIL = 57;
    public static final int CMD_UPDATE_AUTOKICK = 58;
    public static final int CMD_SEND_FEED = 60;
    public static final int CMD_UPDATE_FRIENDLIST = 63;
    public static final int CMD_SUPPORT_BEAN = 64;
    public static final int CMD_EMOTICON = 65;
    

    public static final int CMD_NOTIFY_PUBLIC_CARD = 100;
    public static final int CMD_REQUEST_SHOW_FIRST_CARD = 101;
    public static final int CMD_NOTIFY_SHOW_FIRST_CARD = 102;
    public static final int CMD_NOTIFY_CHANGE_TURN = 103;
    public static final int CMD_NOTIFY_BET_MONEY = 104; 
    public static final int CMD_NOTIFY_DEAL_CARD = 105;    
    public static final int CMD_REQUEST_FOLD = 106;
    public static final int CMD_NOTIFY_FOLD = 107;    
    public static final int CMD_CONFIG_CARDS = 111;
    public static final int CMD_CHEAT_MONEY = 112;
    public static final int CMD_NOTIFY_DEAL_CARD_DONE = 108;
    public static final int CMD_SEND_CHANGE_TURN = 109;
    public static final int CMD_NOTIFY_SHOW_CARD = 110; //hien thi quan bai
    
    
    public static final int CMD_MESS_SEND_MESSAGE = 30;
    public static final int CMD_MESS_SEND_NOTIFY_MESS = 31;
    public static final int CMD_MESS_REQUEST_GET_MY_MESS = 32;
    public static final int CMD_MESS_NOTIFY_MY_MESS = 33;
    public static final int CMD_MESS_SEND_STATUS_MESS_BAR = 34;
    public static final int CMD_MESS_NOTIFY_DELETE_MESSAGE = 35;
    
    public static final int CMD_SEND_GIFT_TO_FRIEND = 40;
    public static final int CMD_NOTIFY_GIFT_FROM_FRIEND = 41;
    public static final int CMD_SEND_UPDATE_MONEY_FROM_GIFT = 42;
    public static final int CMD_REQUEST_MY_GIFT = 43;
    public static final int CMD_MY_GIFT = 44;
    public static final int CMD_DISABLE_RECEIVE_GIFT = 45;
    public static final int CMD_TIME_INVITE_FRIEND = 46;
    public static final int CMD_SIGN_KEY_FEED = 47;
    public static final int CMD_RESET_MY_GIFT = 48;
    
}
