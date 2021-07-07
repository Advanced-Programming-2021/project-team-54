package sample.model;


import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {

    private int lp;
    private HashMap<String, Integer> mainDeck;
    private HashMap<String, Integer> sideDeck;
    private ArrayList<String> grave = new ArrayList<String>();
    private HashMap<Integer, Card> monsterField = new HashMap<Integer, Card>();
    private HashMap<Integer, Card> spellTrapField = new HashMap<Integer, Card>();
    private ArrayList<Card> inHandCard = new ArrayList<Card>();
    private ArrayList<Card> fieldZoneCard = new ArrayList<Card>();

    public GameBoard(Deck deck, int lp) {
        this.lp = lp;
        this.mainDeck = new HashMap<String, Integer>(deck.getMainDeck());
        this.sideDeck = new HashMap<String, Integer>(deck.getSideDeck());
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public void increaseLp(int amount) {
        lp += amount;
    }

    public int getNumberOfInHandCard() {
        return inHandCard.size();
    }

    public int getNumberOfMainDeck() {
        int counter = 0;
        for (String name : mainDeck.keySet()) {
            counter += mainDeck.get(name);
        }
        return counter;
    }

    public HashMap<Integer, Card> getSpellTrapField() {
        return this.spellTrapField;
    }

    public HashMap<Integer, Card> getMonsterField() {
        return this.monsterField;
    }

    public int getNumberOfGrave() {
        return grave.size();
    }

    public int getNumberOfFieldZone() {
        return fieldZoneCard.size();
    }

    public  ArrayList<Card> getFieldZoneCard(){
        return fieldZoneCard;
    }
    public ArrayList<Card> getInHandCard(){
        return inHandCard;
    }

    public int getNumberOfMonsterField(){
        return monsterField.size();
    }
    public void putMonsterInMonsterField(Card card){
        for(int i = 1 ; i < 6 ; i++){
            if(!monsterField.containsKey(i)){
                monsterField.put(i,card);
                return;
            }
        }
    }
    public void removeMonsterFromMonsterField(int number){
        monsterField.remove(number);

    }
    public void removeMonsterFromMonsterField(Card card){
        for(int i = 1 ; i <= 5 ; i++){
            if(monsterField.containsKey(i)){
                if (monsterField.get(i)== card){
                    monsterField.remove(i);
                    return;
                }

            }
        }

    }
    public void removeCardFromHand(Card card){
        for(int i = 0 ; i < inHandCard.size() ; i++){
            if(card==inHandCard.get(i)){
                inHandCard.remove(i);
                return;
            }

        }
    }
    public void sendCardToGrave(Card card){
        grave.add(card.getCardName());
    }
}
