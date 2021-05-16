import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phase {

    private boolean setOrSummon;
    private int phase = 0;
    private Player self;
    private Player opponent;
    private ArrayList<Card> cardsThatSummonInThisPhases = new ArrayList<>();
    private ArrayList<Card> selectedCard = new ArrayList<>();
    private boolean changedposition;

    public void setChangedposition(boolean changedposition) {
        if(this.changedposition)
            return;
        this.changedposition = changedposition;
    }

    public boolean getChangeposition(){
        return changedposition;
    }

    public Phase(Player opponent, Player self) {
        this.opponent = opponent;
        this.self = self;
    }

    public void setSetOrSummon(boolean state){
        if(setOrSummon)
            return;
        setOrSummon = state;
    }

    public boolean getSetOrSummon(){
        return setOrSummon;
    }

    public void phaseController(String input) {
        Pattern summonRegex = Pattern.compile("^summon$");
        Matcher matcher = summonRegex.matcher(input);
        if (matcher.find()) {

            summonCard();
            return;
        }
        matcher = Pattern.compile("set -- position (attack|defense)").matcher(input);
        if(matcher.find()){
            changeMonsterPosition(matcher);
            return;
        }
        matcher = Pattern.compile("flip-summon").matcher(input);
        if(matcher.find()){

            flipSummon();
            return;
        }
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

    public void deselect() {
        selectedCard.remove(0);
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void summonCard() {
        if (selectedCard.size() == 0) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInhand()) {
            System.out.println("you can’t summon this card");
            return;
        }
        if(phase!=2&&phase!=4){
            System.out.println("action not allowed in this phase");
            return;
        }
        if(self.getGameboard().getNumberOfMonsterField()==5){
            System.out.println("monster card zone is full");
            return;
        }
        if(getSetOrSummon()){
            System.out.println("you already summoned/set on this turn");
            return;
        }
        Mounster mounster = (Mounster) selectedCard.get(0);
        if(mounster.getLevel()<=4){
            self.getGameboard().putMonsterInMonsterfield(selectedCard.get(0));
            self.getGameboard().removeCardFromHand(selectedCard.get(0));
            setSetOrSummon(true);
            selectedCard.get(0).setState(Card.State.OO);
            System.out.println("summoned successfully");
            cardsThatSummonInThisPhases.add(selectedCard.get(0));
            return;

        }
        if(mounster.getLevel()==5||mounster.getLevel()==6){
            if(self.getGameboard().getNumberOfMonsterField()<1){
                System.out.println("there are not enough cards for tribute");
                return;
            }
            while (true){
                System.out.println("please write the number of monster that you want to tribute");
                int number = Integer.parseInt(Controller.scanner.nextLine());
                if(!self.getGameboard().getMounsterField().containsKey(number)){
                    System.out.println("there no monsters one this address");
                    continue;
                }
                self.getGameboard().sendCardToGrave(self.getGameboard().getMounsterField().get(number));
                self.getGameboard().removeMonsterFromMonsterField(number);
                self.getGameboard().putMonsterInMonsterfield(selectedCard.get(0));
                System.out.println("summoned successfully");
                setSetOrSummon(true);
                selectedCard.get(0).setState(Card.State.OO);
                cardsThatSummonInThisPhases.add(selectedCard.get(0));
                return;
            }

        }
        if(mounster.getLevel()>=7){
            if(self.getGameboard().getNumberOfMonsterField()<2){
                System.out.println("there are not enough cards for tribute");
                return;
            }
            while(true){
                System.out.println("please write the number of monsters that you want to tribute");
                int num1 = Controller.scanner.nextInt();
                int num2 = Controller.scanner.nextInt();
                if(!self.getGameboard().getMounsterField().containsKey(num1)||!self.getGameboard().getMounsterField().containsKey(num2)){
                    System.out.println("there no monsters one this address");
                    continue;
                }
                self.getGameboard().sendCardToGrave(self.getGameboard().getMounsterField().get(num1));
                self.getGameboard().removeMonsterFromMonsterField(num1);
                self.getGameboard().sendCardToGrave(self.getGameboard().getMounsterField().get(num2));
                self.getGameboard().removeMonsterFromMonsterField(num2);
                self.getGameboard().putMonsterInMonsterfield(selectedCard.get(0));
                setSetOrSummon(true);
                System.out.println("summoned successfully");
                cardsThatSummonInThisPhases.add(selectedCard.get(0));
                selectedCard.get(0).setState(Card.State.OO);
                return;
            }

        }



    }

    public void setCard(){
        if(selectedCard.size()==0){
            System.out.println("no card is selected yet");
            return;
        }
        if(!isSelectedCardInhand()){
            System.out.println("you can’t set this card");
            return;
        }


    }

    public void changeMonsterPosition(Matcher matcher){
        String state = matcher.group(1);
        if(selectedCard.size()==0){
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInMonsterField()){
            System.out.println("you can’t change this card position");
            return;
        }
        if(phase!=2&&phase!=4){
            System.out.println("you can’t do this action in this phase");
            return;
        }
        if(state.contentEquals("attack")&&selectedCard.get(0).getState()== Card.State.OO){
            System.out.println("this card is already in the wanted position");
            return;
        }
        if(state.contentEquals("defense") && selectedCard.get(0).getState()== Card.State.DO){
            System.out.println("this card is already in the wanted position");
            return;
        }
        if(changedposition){
            System.out.println("you already changed this card position in this turn");
            return;
        }
        if(state.contentEquals("attack"))
        selectedCard.get(0).setState(Card.State.OO);
        if(state.contentEquals("defense"))
            selectedCard.get(0).setState(Card.State.DO);
        setChangedposition(true);
        System.out.println("monster card position changed successfully");

    }

    public void flipSummon(){
        if(selectedCard.size()==0){
            System.out.println("no card is selected yet");
            return;
        }
        if (!isSelectedCardInMonsterField()){
            System.out.println("you can’t change this card position");
            return;
        }
        if(phase!=2&&phase!=4){
            System.out.println("you can’t do this action in this phase");
            return;
        }
        if (isThisCardSummonInThisPhases(selectedCard.get(0))||!(selectedCard.get(0).getState()== Card.State.DH)){
            System.out.println("you can’t flip summon this card");
            return;


        }
        selectedCard.get(0).setState(Card.State.OO);
        System.out.println("flip summoned successfully");

    }

    public boolean isSelectedCardInhand() {
        Card card = selectedCard.get(0);
        ArrayList<Card> Cards = self.getGameboard().getInHandCard();
        for (Card card1 : Cards) {
            if (card1 == card)
                return true;
        }
        return false;
    }

    public boolean isSelectedCardInMonsterField(){
        Card card = selectedCard.get(0);
        HashMap<Integer, Card> field= self.getGameboard().getMounsterField();
        for (Integer i : field.keySet()) {
            if (field.get(i) == card)
                return true;
        }
        return false;
    }

    public boolean isThisCardSummonInThisPhases(Card card){
        for(Card card1:cardsThatSummonInThisPhases){
            if(card==card1)
                return true;
        }
        return false;
    }

}
