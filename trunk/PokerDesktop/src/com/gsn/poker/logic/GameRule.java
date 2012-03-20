package com.gsn.poker.logic;



public class GameRule {
    public static final int MAX_SUIT = 30;
    public static final int MAX_SUIT_CARD = 20;
    
    private class sCardTestTable{
        public Card[][] card = new Card[Card.eCARD_NONE][Card.eNONE_SHAPE];
    }
    
    public GameRule() {
        super();
    }
    public int groupCompare(CardGroup g1, CardGroup g2){
        return 0;
    }
    
    public int getGroupType(CardGroup g){
        if (KT_Thung_Pha_Sanh(g))
        {
                return CardGroup.eTHUNG_PHA_SANH;
        }
        if (KT_Tu_Quy(g))
        {
                return CardGroup.eTU_QUY;
        }
        if (KT_Cu_Lu(g))
        {
                return CardGroup.eCU_LU;
        }
        if (KT_Thung(g))
        {
                return CardGroup.eTHUNG;
        }
        if (KT_Sanh(g))
        {
                return CardGroup.eSANH;
        }
        if (KT_Sam_Co(g))
        {
                return CardGroup.eSAM_CO;
        }
        if (KT_Thu(g))
        {
                return CardGroup.eTHU;
        }
        if (KT_Doi(g))
        {
                return CardGroup.eDOI;
        }
        return CardGroup.eTHAU;
    }


    public void timPhom(CardGroup g1, CardGroup target)
    {
            if (Tim_Thung_Pha_Sanh(g1, target))
            {
                    return;
            }
    
            if (Tim_Tu_Quy(g1, target))
            {
                    return;
            }
    
            if (Tim_Cu_Lu(g1, target))
            {
                    return;
            }
    
            if (Tim_Thung(g1, target))
            {
                    return;
            }
    
            if (Tim_Sanh(g1, target))
            {
                    return;
            }
    
            if (Tim_Sam_Co(g1, target))
            {
                    return;
            }
    
            if (Tim_Thu(g1, target))
            {
                    return;
            }
    
            if (Tim_Doi(g1, target))
            {
                    return;
            }
    
            Tim_Thau(g1, target);
    }
    
    public int checkGroupType(CardGroup g1)
    {
    
            return g1.groupType;
    }
    
    public Boolean KT_Thung_Pha_Sanh(CardGroup g1)
    {
            if (g1.getCardsCount() < 5) return false;
            if (KT_Thung(g1))
            {
                    if (KT_Sanh(g1))
                    {
                            return true;
                    }
            }
            return false;
    }
    
    public Boolean KT_Tu_Quy(CardGroup g1)
    {
            if (g1.getCardsCount() < 4) return false;
            int i, j;
            int[] count = new int[Card.eNONE_SHAPE] ;
            int cType;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] >= 4) return true;
                    }
            }
            return false;
    }
    
    public Boolean KT_Cu_Lu(CardGroup g1)
    {
            if (g1.getCardsCount() < 5) return false;
    
            int i;
            int ctGiong = 0;
            int ctKhac = 0;
            int tg = g1.cards.get(0).getType();
            
            for (i = 1; i < g1.getCardsCount(); i++)
            {
                    if (g1.cards.get(i).getType() == tg)
                    {
                            ctGiong++;
                    }
                    else
                    {
                            ctKhac++;
                    }
            }
    
            if (((ctGiong == 1) || (ctGiong == 2)) && (ctKhac == 1)) return true;
    
            return false;
    }
    
    public Boolean KT_Thung(CardGroup g1)
    {
            if (g1.getCardsCount() < 5) return false;
            int i, j;
            int[] count = new int[Card.eNONE_SHAPE] ;
            int iShape;
            
            for (i = 0; i < Card.eNONE_SHAPE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    iShape = g1.cards.get(i).getShape();
                    if ((iShape < Card.eNONE_SHAPE) && (iShape >= Card.eSPADE))
                    {
                            count[iShape]++;
                            if (count[iShape] >= 5) return true;
                    }
            }
            return false;
    }
    
    public Boolean KT_Sanh(CardGroup g1)
    {
            if (g1.getCardsCount() < 5) return false;
            int i, j;
            int[] count = new int[Card.eCARD_NONE];
            int cType;            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                    }
            } 
            // kiem tra 1 day
            int nDay = 0;
            for (i = 1; i < Card.eCARD_NONE; i++)
            {
                    if ((count[i] == count[i-1]) && (count[i] != 0))
                    {
                            nDay++;
                            if (nDay >= 4) return true;
                    }
                    else
                    {
                            nDay = 0;
                    }
            }    
            return false;
    }
    
     public Boolean KT_Sam_Co(CardGroup g1)
    {
            if (g1.getCardsCount() < 3) return false;
            int i, j;
            int[] count = new int[Card.eCARD_NONE];
            int cType;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] >= 3) return true;
                    }
            }
            return false;
    }
    
    public Boolean KT_Thu(CardGroup g1)
    {
            if (g1.getCardsCount() < 4) return false;
            int i, j;
            int[] count= new int[Card.eCARD_NONE];
            int cType;
            int nPair = 0;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
            
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] == 2)
                            {
                                    nPair++;
                                    if (nPair >= 2) return true;
                            }
                    }
            }
            return false;
    }
    
    public Boolean KT_Doi(CardGroup g1)
    {
            if (g1.getCardsCount() < 2) return false;
            int i, j;
            int[] count = new  int[Card.eCARD_NONE];
            int cType;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] >= 2) return true;
                    }
            }
            return false;
    }
    
    
    
    
    public Boolean Tim_Thung_Pha_Sanh(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eTHUNG_PHA_SANH;
    
            if (g1.getCardsCount() < 5) return false;
            int i, j;
            int[] count = new int[Card.eNONE_SHAPE];
            int iShape;
            int tg = 0;
            
            for (i = 0; i < Card.eNONE_SHAPE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    iShape = g1.cards.get(i).getShape();
                    if ((iShape < Card.eNONE_SHAPE) && (iShape >= Card.eSPADE))
                    {
                            count[iShape]++;
                            if (count[iShape] >= 5) 
                            {
                                    tg = iShape;
                            }
                    }
            }
    
            if (count[tg] >= 5)
            {
                    CardGroup g2  = new CardGroup();
                    // gom cac quan cung chat lam 1 nhom
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            iShape = g1.cards.get(i).getShape();
                            if (iShape == tg)
                            {
                                    Card newCard = g1.cards.get(i);
                                    g2.putCardIn(newCard);
                            }
                    }
                    // kiem tra xem co la sa?nh ko
                    Boolean IsSanh = Tim_Sanh(g2, target);
                    g2.clearAll();
                    if (IsSanh)
                    {
                            target.groupType = CardGroup.eTHUNG_PHA_SANH;
                    }
                    return IsSanh;
            }
            return false;
    }
    
    public Boolean Tim_Tu_Quy(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eTU_QUY;
    
            if (g1.getCardsCount() < 4) return false;
    
            int i, j;
            int[] count = new  int[Card.eCARD_NONE];
            int cType;
            int tg = 0;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] >= 4) 
                            {
                                    tg = cType;
                                    break;
                            }
                    }
            }
    
            if (count[tg] >= 4)
            {
                    j = 0;
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            cType = g1.cards.get(i).getType();
                            if (cType == tg)
                            {
                                    j++;
                                    Card newCard = g1.cards.get(i);
                                    target.putCardIn(newCard);
                                    if (j >= 4) break;
                            }
                    }
                    //AddMoreCardToMakeFullGroup(g1, target, 1);
                    target.sortCardTypeDown();
                    return true;
            }
    
            return false;
    }
    
    public Boolean Tim_Cu_Lu(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eCU_LU;    
            if (g1.getCardsCount() < 5) return false;    
            int i, j;
           int[] count = new  int[Card.eCARD_NONE];
            int cType;
            int tg = 0;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                    }
            }
    
            // kiem tra co 1 thu' + 1 doi
            int vt2 = -1;
            int vt3 = -1;
            for (i = Card.eCARD_NONE - 1; i >= 0; i--)
            {
                    if (count[i] >= 3)
                    {
                            if (vt3 < 0)
                            {
                                    vt3 = i;
                            }
                            else
                            {
                                    if (vt2 < 0)
                                    {
                                            vt2 = i;
                                    }
                            }
                    }
                    else
                    {
                            if (count[i] >= 2) 
                            {
                                    if (vt2 < 0)
                                    {
                                            vt2 = i;
                                    }
                            }
                    }
                    if ((vt2 >= 0) && (vt3 >= 0))
                    {
                            break;
                    }
            }
    
            if ((vt2 >= 0) && (vt3 >= 0) && (vt2 != vt3))
            {
                    // tim bo 3
                    j = 0;
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            cType = g1.cards.get(i).getType();
                            if (cType == vt3)
                            {
                                    j++;
                                    Card newCard = g1.cards.get(i);
                                    target.putCardIn(newCard);
                                    if (j >= 3) break;
                            }
                    }
                    // tim bo doi
                    j = 0;
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            cType = g1.cards.get(i).getType();
                            if (cType == vt2)
                            {
                                    j++;
                                    Card newCard = g1.cards.get(i);
                                    target.putCardIn(newCard);
                                    if (j >= 2) break;
                            }
                    }
                    //target.sortCardTypeDown();
                    return true;
            }
    
    
            return false;
    }
    
    public Boolean Tim_Thung(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eTHUNG;
    
            if (g1.getCardsCount() < 5) return false;
            int i, j;
            int[] count = new int[Card.eNONE_SHAPE];
            int iShape;
            int tg = 0;
            
            for (i = 0; i < Card.eNONE_SHAPE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    iShape = g1.cards.get(i).getShape();
                    if ((iShape < Card.eNONE_SHAPE) && (iShape >= Card.eSPADE))
                    {
                            count[iShape]++;
                            if (count[iShape] >= 5) 
                            {
                                    tg = iShape;
                                    break;
                            }
                    }
            }
    
            if (count[tg] >= 5)
            {
                    j = 0;
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            iShape = g1.cards.get(i).getShape();
                            if (iShape == tg)
                            {
                                    j++;
                                    Card newCard = g1.cards.get(i);
                                    target.putCardIn(newCard);
                                    if (j >= 5) break;
                            }
                    }
                    target.sortCardTypeDown();
                    return true;
            }
            return false;
    }
    
    public Boolean Tim_Sanh(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eSANH;
    
            if (g1.getCardsCount() < 5) return false;
            int i, j;
           int[] count = new  int[Card.eCARD_NONE];
            int cType;
            int vt = -1;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                    }
            }
    
            // kiem tra 1 da~y
            int nDay = 0;
            for (i = Card.eCARD_NONE - 1; i > 0; i--)
            {
                    if ((count[i] >= 1) && (count[i-1] >= 1))
                    {
                            nDay++;
                            if (nDay >= 4)
                            {
                                    vt = i - 1;
                                    break;
                            }
                    }
                    else
                    {
                            nDay = 0;
                    }
            }
    
            // lay ra bo
            if (vt >= 0)
            {
                    for (j = 0; j < 5; j++)
                    {
                            for (i = 0; i < g1.getCardsCount(); i++)
                            {
                                    cType = g1.cards.get(i).getType();
                                    if (cType == (j+vt))
                                    {
                                            Card newCard = g1.cards.get(i);
                                            target.putCardIn(newCard);
                                            break;
                                    }
                            }
                    }
                    target.sortCardTypeDown();
                    return true;
            }
            return false;
    }
    
    public Boolean Tim_Sam_Co(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eSAM_CO;
    
            if (g1.getCardsCount() < 3) return false;
    
            int i, j;
           int[] count = new  int[Card.eCARD_NONE];
            int cType;
            int tg = 0;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] >= 3) 
                            {
                                    tg = cType;
                                    break;
                            }
                    }
            }
    
            if (count[tg] >= 3)
            {
                    j = 0;
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            cType = g1.cards.get(i).getType();
                            if (cType == tg)
                            {
                                    j++;
                                    Card newCard = g1.cards.get(i);
                                    target.putCardIn(newCard);
                                    if (j >= 3) break;
                            }
                    }
                    //AddMoreCardToMakeFullGroup(g1, target, 2);
                    return true;
            }
            return false;
    }
    
    public Boolean Tim_Thu(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eTHU;
    
            if (g1.getCardsCount() < 4) return false;
            int i, j, k;
           int[] count = new  int[Card.eCARD_NONE];
            int cType;
            int nPair = 0;
            int[] Pairs = new int[2];
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] == 2)
                            {
                                    Pairs[nPair] = cType;
                                    nPair++;
                                    if (nPair >= 2) break;
                            }
                    }
            }
    
            if (nPair >= 2)
            {
                    for (j = 0; j < 2; j++)
                    {
                            k = 0;
                            for (i = 0; i < g1.getCardsCount(); i++)
                            {
                                    cType = g1.cards.get(i).getType();
                                    if (cType == Pairs[j])
                                    {
                                            k++;
                                            Card newCard = g1.cards.get(i);
                                            target.putCardIn(newCard);
                                            if (k >= 2) break;
                                    }
                            }
                    }
    
                    //AddMoreCardToMakeFullGroup(g1, target, 1);
                    return true;
            }
    
            return false;
    }
    
    public Boolean Tim_Doi(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eDOI;
    
            if (g1.getCardsCount() < 2) return false;
    
            int i, j;
           int[] count = new  int[Card.eCARD_NONE];
            int cType;
            int tg = 0;
            
            for (i = 0; i < Card.eCARD_NONE; i++)
            {
                    count[i] = 0;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    cType = g1.cards.get(i).getType();
                    if ((cType < Card.eCARD_NONE) && (cType >= Card.eCARD_2))
                    {
                            count[cType]++;
                            if (count[cType] >= 2) 
                            {
                                    tg = cType;
                                    break;
                            }
                    }
            }
    
            if (count[tg] >= 2)
            {
                    j = 0;
                    for (i = 0; i < g1.getCardsCount(); i++)
                    {
                            cType = g1.cards.get(i).getType();
                            if (cType == tg)
                            {
                                    j++;
                                    Card newCard = g1.cards.get(i);
                                    target.putCardIn(newCard);
                                    if (j >= 2) break;
                            }
                    }
    
                    //AddMoreCardToMakeFullGroup(g1, target, 3);
                    return true;
            }
    
            return false;
    }
    
    
    public Boolean Tim_Thau(CardGroup g1, CardGroup target)
    {
            // clear all data
            target.clearAll();
            target.groupType = CardGroup.eTHAU;
            
            AddMoreCardToMakeFullGroup(g1, target, 1);
    
    /*
            int i, j;
            int tg[10];
            int cType;
            int vt;
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    tg[i] = i;
            }
    
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    for (j = i+1; j < g1.getCardsCount(); j++)
                    {
                            if (g1.cards[tg[i]].getType() < g1.cards[tg[j]].getType())
                            {
                                    vt = tg[i];
                                    tg[i] = tg[j];
                                    tg[j] = vt;
                            }
                    }
            }
    
            for (i = 0; i < 5; i++)
            {
                    Card newCard = new CCard();
                    newCard.ID = g1.cards[tg[i]].ID;
                    target.putCardIn(newCard);
            }
            */
    
            return true;
    }
    
    public void AddMoreCardToMakeFullGroup(CardGroup g1, CardGroup target, int n)
    {
            if (target.getCardsCount() >= 5) return;
            
            int i, j;
            int count = 0;
            for (i = 0; i < g1.getCardsCount(); i++)
            {
                    Boolean CanAdd = true;
                    Card card = g1.cards.get(i);
                    for (j = 0; j < target.getCardsCount(); j++)
                    {
                            if (card.Id == target.cards.get(j).Id)
                            {
                                    CanAdd = false;
                            }
                    }
                    if (CanAdd)
                    {
                            count++;
                            target.putCardIn(card);
    
                            if (target.getCardsCount() >= 5) return;
                            if (count >= n) return;
                    }
            }
    }
    
    // 0. g1 = g2 ; 1. g1 > g2; 2. g1 < g2
    public int CompareGroup(CardGroup g1, CardGroup g2)
    {
            if (g1.groupType > g2.groupType) return 1;
            if (g1.groupType < g2.groupType) return 2;
    
            if ((g1.groupType == g2.groupType) && (g1.groupType > CardGroup.eNONE))
            {
                    if (g1.getCardsCount() != g2.getCardsCount()) return 0;
                    int i, t1, t2;
                    //CardGroup b1 = g1.bes
                    if (g1.groupType == CardGroup.eTHU){
                        t1 = g1.cards.get(0).getType();
                        t2 = g2.cards.get(0).getType();                  
                        if (t1 > t2) return 1;
                        if (t1 < t2) return 2;
                        t1 = g1.cards.get(3).getType();
                        t2 = g2.cards.get(3).getType();                  
                        if (t1 > t2) return 1;
                        if (t1 < t2) return 2;                        
                        if (t1 == t2) // so sa'nh khac cha't
                        {
                                t1 = g1.cards.get(0).getShape();
                                t2 = g2.cards.get(0).getShape();
                                if (t1 > t2) return 1;
                                if (t1 < t2) return 2;
                        }
                    }else
                    {
                        for (i = 0; i < g1.getCardsCount(); i++)
                        {                            
                                t1 = g1.cards.get(i).getType();
                                t2 = g2.cards.get(i).getType();
                                if (t1 > t2) return 1;
                                if (t1 < t2) return 2;
                                if (t1 == t2) // so sa'nh khac cha't
                                {
                                        t1 = g1.cards.get(i).getShape();
                                        t2 = g2.cards.get(i).getShape();
                                        if (t1 > t2) return 1;
                                        if (t1 < t2) return 2;
                                }
                        }
                    }
            }
    
            return 0;
    }
    
    // 0. card1 = card2 ; 1. card1 > card2; 2. card1 < card2
    public int CompareTwoCard(Card card1, Card card2)
    {
            if (card1.getType() > card2.getType()) return 1;
            if (card1.getType() < card2.getType()) return 2;
            if (card1.getShape() > card2.getShape()) return 1;
            if (card1.getShape() < card2.getShape()) return 2;
            return 0;
    }	

}




























