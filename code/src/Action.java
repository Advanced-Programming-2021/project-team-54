import h.jj;

import java.util.ArrayList;
import java.util.HashMap;

public class Action {
    public static void summon(Card card, Player self, BattleWave battleWave) {
        GameBoard gameBoard = self.getGameBoard();
        Monster monster = (Monster) card;
        if (monster.getLevel() <= 4) {
            gameBoard.putMonsterInMonsterField(monster);
            gameBoard.removeCardFromHand(monster);
            battleWave.setSetOrSummon(true);
            monster.setState(Card.State.OO);
            battleWave.addCardToListThatSummonOrSetInThisBattleWave(monster);
            System.out.println("summoned successfully");
            return;

        }
        if (monster.getLevel() == 5 || monster.getLevel() == 6) {
            if (gameBoard.getNumberOfMonsterField() < 1) {
                System.out.println("there are not enough cards for tribute");
                return;
            }
            while (true) {
                System.out.println("please write the number of monster that you want to tribute");
                int number = Integer.parseInt(Controller.scanner.nextLine());
                if (!gameBoard.getMonsterField().containsKey(number)) {
                    System.out.println("there no monsters one this address");
                    continue;
                }
                gameBoard.sendCardToGrave(gameBoard.getMonsterField().get(number));
                gameBoard.removeMonsterFromMonsterField(number);
                gameBoard.putMonsterInMonsterField(monster);
                gameBoard.removeCardFromHand(monster);
                monster.setState(Card.State.OO);
                battleWave.addCardToListThatSummonOrSetInThisBattleWave(monster);
                battleWave.setSetOrSummon(true);
                System.out.println("summoned successfully");
                return;
            }
        }
        if (monster.getLevel() == 7 || monster.getLevel() == 8) {

            if (gameBoard.getNumberOfMonsterField() < 2) {
                System.out.println("there are not enough cards for tribute");
                return;
            }

            while (true) {
                System.out.println("please write the number of monsters that you want to tribute");
                int num1 = Controller.scanner.nextInt();
                int num2 = Controller.scanner.nextInt();
                if (!gameBoard.getMonsterField().containsKey(num1) || !gameBoard.getMonsterField().containsKey(num2)) {
                    System.out.println("there no monsters one this address");
                    continue;
                }
                gameBoard.sendCardToGrave(gameBoard.getMonsterField().get(num1));
                gameBoard.removeMonsterFromMonsterField(num1);
                gameBoard.sendCardToGrave(gameBoard.getMonsterField().get(num2));
                gameBoard.removeMonsterFromMonsterField(num2);
                gameBoard.putMonsterInMonsterField(monster);
                gameBoard.removeCardFromHand(monster);
                monster.setState(Card.State.OO);
                battleWave.addCardToListThatSummonOrSetInThisBattleWave(monster);
                battleWave.setSetOrSummon(true);
                System.out.println("summoned successfully");
                return;

            }
        }

    }

    public static void changeOOToDO(Card card , BattleWave battleWave){
        card.setState(Card.State.DO);
        System.out.println("changed to DO Successful");
    }

    public static void changeDOToOO(Card card , BattleWave battleWave){
        card.setState(Card.State.OO);
        System.out.println("changed to OO Successful");
    }

    public static void changePosition(Card card , BattleWave battleWave){
        if(card.getState()== Card.State.OO){
        changeOOToDO(card,battleWave);
        }else{

            changeDOToOO(card,battleWave);
        }

    }

    public static void summonAction(Card card, Player player, BattleWave battleWave) {
        switch (card.getCardName()) {
            case "Command_Knight":
                Command_Knight.summon(card, player, battleWave);
                break;
            case "Crab_Turtle":
                Crab_Turtle.summon(card, player, battleWave);
                break;
            case "Skull_Guardian":
                Skull_Guardian.summon(card, player, battleWave);
                break;
            case "Gate_Guardian":
                Gate_Guardian.summon(card, player, battleWave);
                break;
            case "Beast_King_Barbaros":
                Beast_King_Barbaros.summon(card, player, battleWave);
                break;
            case "The_Calculator":
                The_Calculator.summon(card, player, battleWave);
                break;
            case "The_Tricky":
                The_Tricky.summon(card, player, battleWave);
                break;


        }
        summon(card, player, battleWave);
    }

    public static boolean attackAction(Card card, Player dPlayer, Player aPlayer, int defNum, BattleWave battleWave) {

        return attack(card, aPlayer, dPlayer, defNum, battleWave);

    }

    public static boolean defenceAction(Card attackCard, Card defenderCard, Player dPlayer, Player aPlayer) {
        switch (defenderCard.getCardName()) {
            case "Yomi_Ship":
                return Yomi_Ship.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Suijin":
                return Suijin.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Marshmallon":
                return Marshmallon.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Texchanger":
                return Texchanger.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Exploder_Dragon":
                return Exploder_Dragon.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Command_Knight":
                return Command_Knight.defence(attackCard, aPlayer, dPlayer, defenderCard);
        }
        return defence(attackCard, aPlayer, dPlayer, defenderCard);
    }

    public static void flipSummonAction(Card card, BattleWave battleWave) {
        switch (card.getCardName()) {
            case "Command_Knight":
                Command_Knight.flipSummon(card, battleWave);
                break;
            case "ManEater_Bug":
                ManEater_Bug.flipSummon(card, battleWave);
                break;
        }
        flipSummon(card, battleWave);
    }

    public static boolean attack(Card attacker, Player self, Player opponent, int numberOfCardThatBeenAttacked, BattleWave battleWave) {
        Monster defender = (Monster) opponent.getGameBoard().getMonsterField().get(numberOfCardThatBeenAttacked);
        if (defender.getState() == Card.State.OO) {
            if (((Monster) attacker).getAttackPower() >
                    ((Monster) opponent.getGameBoard().getMonsterField().get(numberOfCardThatBeenAttacked)).getAttackPower()) {
                int damage = (((Monster) attacker).getAttackPower() - defender.getAttackPower());
                opponent.getGameBoard().increaseLp(-(((Monster) attacker).getAttackPower() - defender.getAttackPower()));
                opponent.getGameBoard().sendCardToGrave(defender);
                opponent.getGameBoard().removeMonsterFromMonsterField(defender);
                System.out.println("your opponent’s monster is destroyed and your opponent receives" + damage + " battle damage");
                return true;
            }
            if (defender.getAttackPower() == ((Monster) attacker).getAttackPower()) {
                self.getGameBoard().sendCardToGrave(attacker);
                self.getGameBoard().removeMonsterFromMonsterField(attacker);
                opponent.getGameBoard().sendCardToGrave(defender);
                opponent.getGameBoard().removeMonsterFromMonsterField(defender);
                System.out.println("both you and your opponent monster cards are destroyed and no one receives damage");
                return true;

            }
            if (defender.getAttackPower() > ((Monster) attacker).getAttackPower()) {
                int damage = defender.getAttackPower() - ((Monster) attacker).getAttackPower();
                self.getGameBoard().increaseLp(-damage);
                self.getGameBoard().sendCardToGrave(attacker);
                self.getGameBoard().removeMonsterFromMonsterField(attacker);
                System.out.println("Your monster card is destroyed and you received" + damage + "battle damage");
                return true;
            }
        }
        if (defender.getState() == Card.State.DO || defender.getState() == Card.State.DH) {
            if (defender.getDefencePower() > ((Monster) attacker).getAttackPower()) {
                int damage = defender.getDefencePower() - ((Monster) attacker).getAttackPower();
                self.getGameBoard().increaseLp(-damage);

                System.out.println("no card is destroyed and you received " + damage + " battle damage");
                return true;
            }
            if (defender.getDefencePower() == ((Monster) attacker).getDefencePower()) {
                System.out.println("no card is destroyed");
                return true;
            }
            if (defender.getDefencePower() < ((Monster) attacker).getAttackPower()) {
                opponent.getGameBoard().sendCardToGrave(defender);
                opponent.getGameBoard().removeMonsterFromMonsterField(defender);
                System.out.println("the defense position monster is destroyed");
                return true;
            }

        }

        return true;

    }

    public static void flipSummon(Card card, BattleWave battleWave) {
        card.setState(Card.State.OO);
        System.out.println("summon flip Successful");


    }


    public static void directAttack(Card attacker, Player self, Player opponent) {

        opponent.getGameBoard().increaseLp(((Monster) attacker).getAttackPower());
        System.out.println("you opponent receives " + ((Monster) attacker).getAttackPower() + " battale damage");
        return;


    }

    public static boolean defence(Card attacker, Player self, Player opponent, Card defence) {
        return false;

    }


    public static void battle(Card attacker, Player self, Player opponent, Card defence, int defnum, BattleWave battleWave) {

        if (defenceAction(attacker, defence, opponent, self))
            return;
        if (attackAction(attacker, opponent, self, defnum, battleWave)) ;

    }

    public static void spellEffect(BattleWave battleWave) {
    }
}

class Command_Knight extends Action {
}

class Yomi_Ship extends Action {
}

class Suijin extends Action {
}

class Crab_Turtle extends Action {
}

class Skull_Guardian extends Action {
}

class ManEater_Bug extends Action {
}

class Gate_Guardian extends Action {
}

class Scanner extends Action {
}

class Marshmallon extends Action {
}

class Beast_King_Barbaros extends Action {
}

class Texchanger extends Action {
}

class The_Calculator extends Action {
}

class Mirage_Dragon extends Action {
}

class Herald_of_Creation extends Action {
}

class Exploder_Dragon extends Action {
}

class Terratiger_the_Empowered_Warrior extends Action {
}

class The_Tricky extends Action {
}

/////////////////////////////////////
class Monster_Reborn extends Action {
    public static void spellEffect(BattleWave battleWave) {
        Player self = battleWave.getSelf();
        Player opponent = battleWave.getOpponent();
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < self.getGameBoard().getGrave().size(); i++) {
            Card card = Card.getCardByName(self.getGameBoard().getGrave().get(i));
            if (card.getCardKind() == 1)
                cards.add(card);
        }
        for (int i = 0; i < opponent.getGameBoard().getGrave().size(); i++) {
            Card card = Card.getCardByName(self.getGameBoard().getGrave().get(i));
            if (card.getCardKind() == 1)
                cards.add(card);
        }
        Card card = cards.get(getNumberOfChoose(cards));
        self.getGameBoard().putMonsterInMonsterField(card);
    }

    public static int getNumberOfChoose(ArrayList<Card> cards) {

        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i + " : " + cards.get(i).getCardName());
        }
        while (true) {
            System.out.println("choose number");
            int num = Integer.parseInt(Controller.scanner.nextLine());
            if (num < cards.size() && num >= 0) {
                return num;
            }
        }
    }

}


class Terraforming extends Action {


}

class Pot_Of_Greed extends Action {

    public static void spellEffect(BattleWave battleWave) {
        Player self = battleWave.getSelf();
        for (int i = 0; i < 2; i++) {
            if (self.getGameBoard().getInHandCard().size() < 5) {
                self.getGameBoard().putCardInHand(
                        self.getGameBoard().takeCardFromShuffleAndRemove()
                );
            }
        }
    }
}

class Raigeki extends Action {
    public static void spellEffect(BattleWave battleWave) {
        Player self = battleWave.getSelf();
        Player opponent = battleWave.getOpponent();
        opponent.getGameBoard().setMonsterField(new HashMap<>());
        opponent.getGameBoard().setSpellTrapField(new HashMap<>());
    }
}

class Change_Of_Heart extends Action {

}

class Harpies_Feather_Duster extends Action {
    public static void spellEffect(BattleWave battleWave) {
        Player opponent = battleWave.getOpponent();
        opponent.getGameBoard().setSpellTrapField(new HashMap<>());
    }
}

class Swords_Of_Revealing_Light extends Action {

}

class Dark_Hole extends Action {

    public static void spellEffect(BattleWave battleWave) {
        Player self = battleWave.getSelf();
        Player opponent = battleWave.getOpponent();
        self.getGameBoard().setMonsterField(new HashMap<>());
        opponent.getGameBoard().setMonsterField(new HashMap<>());
    }
}

class Supply_Squad extends Action {

}

class Spell_Absorption extends Action {

}

class Messenger_of_Peace extends Action {

}

class Twin_Twisters extends Action {

}

class Mystical_space_typhoon extends Action {

}

class Ring_of_Defense extends Action {

}

class Yami extends Action {

}

class Forest extends Action {

}

class Closed_forest extends Action {

}

class UMIIRUKA extends Action {

}

class Sword_of_Dark_Destruction extends Action {

}

class Black_Pendant extends Action {

}

class United_We_Stand extends Action {

}

class Magnum_Shield extends Action {

}





