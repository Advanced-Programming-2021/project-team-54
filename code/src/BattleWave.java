

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattleWave {

    private boolean setOrSummon;
    private int phase = 0;
    private Player self;
    private Player opponent;
    private ArrayList<Card> cardsThatSummonInThisPhases = new ArrayList<>();
    private ArrayList<Card> selectedCard = new ArrayList<>();
    private ArrayList<Card> cardsThatAttacked = new ArrayList<>();
    private boolean changedPosition;

    public Card getSelectedCard() {
        return selectedCard.get(0);
    }

    public void setChangedPosition(boolean changedPosition) {
        if (this.changedPosition)
            return;
        this.changedPosition = changedPosition;
    }

    public boolean getChangedPosition() {
        return changedPosition;
    }

    public BattleWave(Player opponent, Player self) {
        this.opponent = opponent;
        this.self = self;
    }

    public void setSetOrSummon(boolean state) {
        if (setOrSummon)
            return;
        setOrSummon = state;
    }

    public boolean getSetOrSummon() {
        return setOrSummon;
    }

    public void battleWaveController(String input) {



        Pattern summonRegex = Pattern.compile("^summon$");
        Matcher matcher = summonRegex.matcher(input);
        if (matcher.find()) {

            summonCard();
            return;
        }
        Pattern ShowBoardRegex = Pattern.compile("show");
        matcher = ShowBoardRegex.matcher(input);
        if (matcher.find()){


            showSelectedCard();
            return;
        }
        matcher = Pattern.compile("set -- position (attack|defense)").matcher(input);
        if (matcher.find()) {
            changeMonsterPosition(matcher);
            return;
        }
        matcher = Pattern.compile("flip-summon").matcher(input);
        if (matcher.find()) {

            flipSummon();
            return;
        }
        matcher = Pattern.compile("attack ([\\d]+)").matcher(input);
        if (matcher.find()) {

            attack(matcher);
            return;
        }

        matcher = Pattern.compile("^next phase$").matcher(input);
        if (matcher.find()) {
            goToNextPhase();
            return;
        }
        matcher = Pattern.compile("^select -d$").matcher(input);
        if (matcher.find()) {
            deselectingCard();
            return;
        }
        if(PlayGame.doesHaveSelectionPatterns(input)!=-1){

            selectCard(input);
            return;

        }
        matcher = Pattern.compile("^set$").matcher(input);
        if(matcher.find()){

            setCard();
            return;



        }







    }

    public void addCardToListThatSummonOrSetInThisBattleWave(Card card) {
        cardsThatSummonInThisPhases.add(card);
    }

    public int getPhase() {
        return phase;
    }

    public Player getSelf() {
        return self;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setSelf(Player self) {
        this.self = self;
    }

    public void selecting(Card card) {
        selectedCard.add(card);
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void summonCard() {
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInHand()) {
            System.out.println("you can’t summon this card");
            return;
        }
        if (phase != 2 && phase != 4) {
            System.out.println("action not allowed in this phase");
            return;
        }
        if (self.getGameBoard().getNumberOfMonsterField() == 5) {
            System.out.println("monster card zone is full");
            return;
        }
        if (getSetOrSummon()) {
            System.out.println("you already summoned/set on this turn");
            return;
        }
        Monster monster = (Monster) selectedCard.get(0);
        Action.summonAction(monster, self, this);


    }

    public void setCard() {
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInHand()) {
            System.out.println("you can’t set this card");
            return;
        }
        if(phase !=2 && phase != 4){
            System.out.println("you can’t do this action in this phase");
            return;
        }
        if(selectedCard.get(0).getCardKind()==1){
            if(self.getGameBoard().getMonsterField().size()  >=  5){
                System.out.println("monster card zone is full");
                return;
            }
            self.getGameBoard().putMonsterInMonsterField(selectedCard.get(0));
            self.getGameBoard().removeCardFromHand(selectedCard.get(0));
            selectedCard.get(0).setState(Card.State.DH);
        }else{

            if(self.getGameBoard().getSpellTrapField().size() >= 5){
                System.out.println("spell card zone is full");
                return;
            }
            self.getGameBoard().putCardSpellOrTrapInSpellTrapField(selectedCard.get(0));
            self.getGameBoard().removeCardFromHand(selectedCard.get(0));
            selectedCard.get(0).setState(Card.State.H);
        }
        System.out.println("set successfully");


    }

    public void changeMonsterPosition(Matcher matcher) {
        String state = matcher.group(1);
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInMonsterField()) {
            System.out.println("you can’t change this card position");
            return;
        }
        if (phase != 2 && phase != 4) {
            System.out.println("you can’t do this action in this phase");
            return;
        }
        if (state.contentEquals("attack") && selectedCard.get(0).getState() == Card.State.OO) {
            System.out.println("this card is already in the wanted position");
            return;
        }
        if (state.contentEquals("defense") && selectedCard.get(0).getState() == Card.State.DO) {
            System.out.println("this card is already in the wanted position");
            return;
        }
        if (changedPosition) {
            System.out.println("you already changed this card position in this turn");
            return;
        }

        Action.changePosition(selectedCard.get(0),this);
        setChangedPosition(true);
        System.out.println("monster card position changed successfully");

    }

    public void flipSummon() {
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInMonsterField()) {
            System.out.println("you can’t change this card position");
            return;
        }
        if (phase != 2 && phase != 4) {
            System.out.println("you can’t do this action in this phase");
            return;
        }
        if (isThisCardSummonInThisPhases(selectedCard.get(0)) || !(selectedCard.get(0).getState() == Card.State.DH)) {
            System.out.println("you can’t flip summon this card");
            return;


        }
        Action.flipSummon(selectedCard.get(0),this);
        System.out.println("flip summoned successfully");

    }

    public boolean isSelectedCardInHand() {
        Card card = selectedCard.get(0);
        ArrayList<Card> Cards = self.getGameBoard().getInHandCard();
        for (Card card1 : Cards) {
            if (card1 == card)
                return true;
        }
        return false;
    }

    public boolean isSelectedCardInMonsterField() {
        Card card = selectedCard.get(0);
        HashMap<Integer, Card> field = self.getGameBoard().getMonsterField();
        for (Integer i : field.keySet()) {
            if (field.get(i) == card)
                return true;
        }
        return false;
    }

    public boolean isThisCardSummonInThisPhases(Card card) {
        for (Card card1 : cardsThatSummonInThisPhases) {
            if (card == card1)
                return true;
        }
        return false;
    }

    public void attack(Matcher matcher) {
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInMonsterField()) {
            System.out.println("you can’t attack with this card");
            return;
        }
        if (phase != 3) {
            System.out.println("you can’t do this action in this phase");
            return;
        }
        if (doesSelectedCardAlreadyAttacked()) {
            System.out.println("this card already attacked");
            return;
        }
        int num = Integer.parseInt(matcher.group(1));
        if (!opponent.getGameBoard().getMonsterField().containsKey(num)) {
            System.out.println("there is no card to attack here");
            return;
        }

        Action.battle(selectedCard.get(0),self,opponent,opponent.getGameBoard().getMonsterField().get(num),num,this);
    }

    public boolean doesSelectedCardAlreadyAttacked() {
        for (Card card :
                cardsThatAttacked) {
            if (card == selectedCard.get(0))
                return true;
        }
        return false;
    }

    public void drawPhase() {
        if (self.getGameBoard().getInHandCard().size() >= 5)
            return;
        Card card = self.getGameBoard().takeCardFromShuffleAndRemove();
        System.out.println("new card added to the hand : " + card.getCardName());
        self.getGameBoard().putCardInHand(card);

    }

    public void goToNextPhase() {
        if (phase <= 6) {
            phase++;
            switch (phase) {
                case 0:
                    System.out.println("phase: drawphase");
                    break;
                case 1:
                    System.out.println("phase: standbyphase");
                    break;
                case 2:
                    System.out.println("phase: mainphase1");
                    break;
                case 3:
                    System.out.println("phase: battlephase");
                    break;
                case 4:
                    System.out.println("phase: mainphase2");
                    break;
                case 5:
                    System.out.println("phase: end");
                    break;
            }
        }
    }

    public void deselectingCard() {
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        System.out.println("card deselected");
        selectedCard.remove(0);
        return;
    }

    public void selectCard(String input){
       int num =  PlayGame.finalCardSelection(input,self,opponent,this);
       if(num == 1){

           System.out.println("invalid selection");
       }else if(num == 2){
           System.out.println("card selected");

       }else{

           System.out.println("no card found in the given position");
       }
    }

    public void setCards(){
        if(selectedCard.size()==0){
            System.out.println("no card is selected yet");
            return;
        }
        if(!isSelectedCardInHand()){
            System.out.println("you cant set this card");
            return;
        }

        if(selectedCard.get(0).getCardKind()==1){
            if(phase != 2 && phase != 4){
                System.out.println("you can’t do this action in this phase");
                return;
            }
            if(self.getGameBoard().getMonsterField().size()>=5){
                System.out.println("monster card zone is full");
                return;
            }
            if(setOrSummon){
                System.out.println("you already summoned/set on this turn");
                return;
            }

        }else{



        }




    }

    public ArrayList<Card> getListOfSelectedCard(){
        return selectedCard;
    }


    public void showSelectedCard(){
        showCardDetails(selectedCard.get(0));
    }
    public void showCardDetails(Card card){
        if(card.getCardKind()==1){
            Monster monster = (Monster)card;
            System.out.println(monster.getCardName()+" : "+monster.getCardDescription());
            System.out.println("kind : "+monster.getCardKind());
        }else {
            System.out.println(card.getCardName()+" : "+card.getCardDescription());
        }
    }
}
