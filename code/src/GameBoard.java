

import java.util.*;

public class GameBoard {

    private int lp;
    private HashMap<String, Integer> mainDeck;
    private HashMap<String, Integer> sideDeck;
    private ArrayList<String> grave = new ArrayList<>();
    private HashMap<Integer, Card> monsterField = new HashMap<>();
    private HashMap<Integer, Card> spellTrapField = new HashMap<>();
    private ArrayList<Card> inHandCard = new ArrayList<>();
    private ArrayList<Card> fieldZoneCard = new ArrayList<>();
    private ArrayList<String> shuffledDeck = new ArrayList<>();

    public GameBoard(Deck deck, int lp) {
        this.lp = lp;
        this.mainDeck = new HashMap<>(deck.getMainDeck());
        this.sideDeck = new HashMap<>(deck.getSideDeck());
    }

    public ArrayList<String> getGrave() {
        return this.grave;
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

    public ArrayList<Card> getFieldZoneCard() {
        return fieldZoneCard;
    }

    public ArrayList<Card> getInHandCard() {
        return inHandCard;
    }

    public int getNumberOfMonsterField() {
        return monsterField.size();
    }

    public void putMonsterInMonsterField(Card card) {
        for (int i = 1; i < 6; i++) {
            if (!monsterField.containsKey(i)) {
                monsterField.put(i, card);
                return;
            }
        }
    }

    public void removeMonsterFromMonsterField(int number) {
        monsterField.remove(number);

    }

    public void removeMonsterFromMonsterField(Card card) {
        for (int i = 1; i <= 5; i++) {
            if (monsterField.containsKey(i)) {
                if (monsterField.get(i) == card) {
                    monsterField.remove(i);
                    return;
                }

            }
        }

    }

    public void removeCardFromHand(Card card) {
        for (int i = 0; i < inHandCard.size(); i++) {
            if (card == inHandCard.get(i)) {
                inHandCard.remove(i);
                return;
            }

        }
    }

    public void sendCardToGrave(Card card) {
        grave.add(card.getCardName());

    }

    public void removeCardFromDeck(String name) {
        int number = mainDeck.get(name);
        if (number == 1) {
            mainDeck.remove(name);
        } else {
            mainDeck.replace(name, mainDeck.get(name) - 1);
        }
    }

    public void shuffleDeck() {

        for (String name : mainDeck.keySet()) {
            int num = mainDeck.get(name);

            for (int i = 1; i <= num; i++) {
                shuffledDeck.add(name);
            }
        }
        Collections.shuffle(shuffledDeck);

    }

    public void putCardInHand(Card card) {

        inHandCard.add(card);

    }

    public Card takeCardFromShuffleAndRemove() {
        String name = shuffledDeck.get(0);
        shuffledDeck.remove(0);
        Card card = Card.getCardByName(name);
        return card;
    }


    public void makeGameBoardReady() {
        shuffleDeck();
        for (int i = 0; i <= 5; i++) {
            Card card = takeCardFromShuffleAndRemove();
            inHandCard.add(card);
        }
    }


    public void setMonsterField(HashMap<Integer, Card> field) {
        this.monsterField = field;
    }

    public void setSpellTrapField(HashMap<Integer, Card> field) {

        this.spellTrapField = field;
    }


    public void putCardSpellOrTrapInSpellTrapField(Card card) {
        for (int i = 1; i < 6; i++) {
            if (!spellTrapField.containsKey(i)) {
                spellTrapField.put(i, card);
                return;
            }
        }
    }

    public void removeCardFromSpellTrap(Card card){
        for (int i:
             spellTrapField.keySet()) {
            if(spellTrapField.get(i)==card){
                spellTrapField.remove(i);
                return;
            }
        }
    }
}
