package com.gsn.poker.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class CardGroup {
    private Random rnd;
    public static final int eNONE   = 0;        
    public static final int eTHAU   = 1 ;
    public static final int eDOI    = 2 ;
    public static final int eTHU    = 3;
    public static final int eSAM_CO = 4;
    public static final int eSANH   = 5;
    public static final int eTHUNG  = 6;
    public static final int eCU_LU  = 7;
    public static final int eTU_QUY = 8;
    public static final int eTHUNG_PHA_SANH = 9;
    public int groupType = eNONE;
    public List<Card> cards = new LinkedList();
    
    public CardGroup() {
        super();
    }
    
    public Card takeCardOut(int index){
        if (index >= 0){
            return cards.remove(index);
        }
        else{
            return null;
        }
    }
    
    public Card takeRandomCardOut(){
        if (rnd == null){
            rnd = new Random();
        }
        int index = rnd.nextInt(cards.size());
        return takeCardOut(index);
    }
    
    public void putCardIn(Card card){
        cards.add(card);
    }
    
    public int size(){
        return cards.size();
    }
    
    public int getCardsCount(){
        return cards.size();
    }
    public void clearAll(){
        if (cards.size() > 0){
            cards.clear();
        }
        groupType=eNONE;
    }
    
    public Card getCard(int index){
        return cards.get(index);
    }
    
    public Card getLast(){
        return cards.get(cards.size()-1);
    }
    
    public void sortCardTypeDown(){
        int i,j;
        int cType;
        int n;

        n = size();
        // arrange
        Card c1, c2;
        for (i = 0; i < n; i++){
            for (j = i + 1; j < n; j++){
                c1 = cards.get(i);
                c2 = cards.get(j);
                if (c1.getType() < c2.getType()){
                    swapTwoCard(i, j);
                }
                else {
                    if (c1.getType() == c2.getType()){
                        if (c1.getShape() < c2.getShape()){
                            swapTwoCard(i, j);
                        }
                    }
                }
            }
        } 
    }
    
    public int getNumEatCard(){
        int count = 0;
        for (int i = 0; i < size(); i++){
            if (cards.get(i).isEaten){
                count++;
            }
        }
        return count;
    }
    
    public Boolean checkCardDuplicate(){
        return false;
    }
    
    public void swapTwoCard(int index1, int index2){
        Collections.swap(cards, index1, index2);
    }
    
    public int getSum(){
        int sum = 0;
        for (int i = 0; i < size(); i++){
            sum += (cards.get(i).getType() + 1);
        }
        return sum;
    }
    
    public void insertCard(Card card, int index){
        cards.add(index, card);
    }
}
