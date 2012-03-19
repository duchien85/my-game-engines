package com.gsn.poker.logic;

public class Card {
    // define card type    
    public static final int eCARD_2 = 0;
    public static final int eCARD_3 = 1;
    public static final int eCARD_4 = 2;
    public static final int eCARD_5 = 3;
    public static final int eCARD_6 = 4;
    public static final int eCARD_7 = 5;
    public static final int eCARD_8 = 6;
    public static final int eCARD_9 = 7;
    public static final int eCARD_10 = 8;
    public static final int eCARD_J = 9;
    public static final int eCARD_Q = 10;
    public static final int eCARD_K = 11;
    public static final int eCARD_A = 12;
    public static final int eCARD_NONE = 13;
  
    
    // define card shape
    public static final int eSPADE = 0;
    public static final int eCLUB = 1;
    public static final int eDIAMOND = 2;
    public static final int eHEART = 3;
    public static final int eNONE_SHAPE = 4;
    
    // fields
    public int Id;
    public Boolean isEaten = false;;
    public Boolean isInSuit = false;
    public Boolean isSelected = false;;
    public Boolean IsDark;

    public Card() {
        super();
    }
    
    public int getType(){
        if (Id == 52){
            return eCARD_NONE;
        }
        else {
            return Id/4;
        }
    }
    
    public int getShape(){
        if (Id == 52){
            return eNONE_SHAPE;
        }
        else {
            return Id%4;
        }
    }
    
    public static int getCardCode(int cardType, int cardShape){
        return cardType * 4 + cardShape;
    }
    
    public void setCardId(int cardType, int cardShape){
        Id = cardType * 4 + cardShape;
    }
    
}
