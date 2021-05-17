package Action;
import Game.*;

public class Action {
    public static void summon(Card card, Player self) {
        GameBoard gameBoard = self.getGameBoard();
        for (int i = 1; i <= 5; i++) {
            if (!gameBoard.getMonsterField().containsKey(i)) {
                gameBoard.getMonsterField().put(i, card);
                gameBoard.removeCardFromHand(card);
            }
        }
    }

    public static void summonAction(Card card, Player player) {
        switch (card.getCardName()) {
            case "battle_Ox":
                Battle_Ox.summon(card, player);
                break;
            case "Axe_Raider":
                Axe_Raider.summon(card, player);
                break;



        }
    }

    public static boolean attack(Card attacker, Player self, Player opponent, int numberOfCardThatBeenAttacked) {

        return true;

    }

    public static boolean defence(Card attacker, Player self, Player opponent, Card defence) {
        return true;

    }

    public static boolean attackAction(Card card, Player dPlayer, Player aPlayer, int numberOfCardThatBeenAttacked) {
        switch (card.getCardName()) {
            case "Battle_Ox":
                return Battle_Ox.attack(card, aPlayer, dPlayer, numberOfCardThatBeenAttacked);
            case "Axe_Raider":
                return Axe_Raider.attack(card, aPlayer, dPlayer, numberOfCardThatBeenAttacked);
            case "Yomi_Ship":
                return Yomi_Ship.attack(card, aPlayer, dPlayer, numberOfCardThatBeenAttacked);
        }
        return true;
    }

    public static boolean defenceAction(Card attackCard, Card defenderCard, Player dPlayer, Player aPlayer) {
        switch (defenderCard.getCardName()) {
            case "Battle_Ox":
                return Battle_Ox.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Axe_Raider":
                return Axe_Raider.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Yomi_Ship":
                return Yomi_Ship.defence(attackCard, aPlayer, dPlayer, defenderCard);
        }
        return true;
    }

    public static void battle(Card attacker, Player self, Player opponent, Card defence, int defnum) {

        if (attackAction(attacker, opponent, self, defnum) && defenceAction(attacker, defence, opponent, self)) {
            Monster attackerCard = (Monster)attacker;
            Monster defenderCard = (Monster) defence;
            if (defence.getState() == Card.State.DO) {
                if(attackerCard.getAttackPower() < defenderCard.getDefencePower()){
                    self.getGameBoard().increaseLp(-(defenderCard.getDefencePower()-attackerCard.getAttackPower()));

                }
                if(attackerCard.getAttackPower() > defenderCard.getDefencePower()){

                    opponent.getGameBoard().sendCardToGrave(defence);
                    opponent.getGameBoard().removeMonsterFromMonsterField(defnum);
                }
                else{

                }

            } else if (defence.getState() == Card.State.DH) {

            } else if (defence.getState() == Card.State.OO) {
                if(attackerCard.getAttackPower() > defenderCard.getAttackPower()){
                    opponent.getGameBoard().removeMonsterFromMonsterField(defence);
                    opponent.getGameBoard().sendCardToGrave(defence);
                    opponent.getGameBoard().increaseLp(-(attackerCard.getAttackPower()-defenderCard.getAttackPower()));

                }else if(attackerCard.getAttackPower() < defenderCard.getAttackPower()){
                    self.getGameBoard().removeMonsterFromMonsterField(attacker);
                    self.getGameBoard().sendCardToGrave(attacker);
                    self.getGameBoard().increaseLp(-(defenderCard.getAttackPower()-attackerCard.getAttackPower()));


                }else{
                    self.getGameBoard().removeMonsterFromMonsterField(attacker);
                    self.getGameBoard().sendCardToGrave(attacker);
                    opponent.getGameBoard().removeMonsterFromMonsterField(defence);
                    opponent.getGameBoard().sendCardToGrave(defence);

                }

            }
        }
    }


}

