
public class Action {
    public static void summon(Card card, Player self,BattleWave battleWave) {
        GameBoard gameBoard = self.getGameBoard();
        Monster monster = (Monster) battleWave.getSelectedCard();
        if(monster.getLevel()<=4){
            gameBoard.putMonsterInMonsterField(monster);
            gameBoard.removeCardFromHand(monster);
            battleWave.setSetOrSummon(true);
            monster.setState(Card.State.OO);
            battleWave.addCardToListThatSummonOrSetInThisBattleWave(monster);
            System.out.println("summoned successfully");
            return;


        }
        if(monster.getLevel() ==5 ||monster.getLevel()==6){
            if(gameBoard.getNumberOfMonsterField() < 1){
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
        if(monster.getLevel() == 7 || monster.getLevel()== 8){

            if(gameBoard.getNumberOfMonsterField() < 2){
                System.out.println("there are not enough cards for tribute");
                return;
            }

            while(true){
            System.out.println("please write the number of monsters that you want to tribute");
            int num1 = Controller.scanner.nextInt();
            int num2 = Controller.scanner.nextInt();
            if (!gameBoard.getMonsterField().containsKey(num1)||!gameBoard.getMonsterField().containsKey(num2)) {
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

    public static void summonAction(Card card, Player player,BattleWave battleWave) {
        switch (card.getCardName()) {
            case "battle_Ox":
               // Battle_Ox.summon(card, player);
                break;
            case "Axe_Raider":
             //   Axe_Raider.summon(card, player);
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
               // return Battle_Ox.attack(card, aPlayer, dPlayer, numberOfCardThatBeenAttacked);
            case "Axe_Raider":
               // return Axe_Raider.attack(card, aPlayer, dPlayer, numberOfCardThatBeenAttacked);
            case "Yomi_Ship":
               // return Yomi_Ship.attack(card, aPlayer, dPlayer, numberOfCardThatBeenAttacked);
        }
        return true;
    }

    public static boolean defenceAction(Card attackCard, Card defenderCard, Player dPlayer, Player aPlayer) {
        switch (defenderCard.getCardName()) {
            case "Battle_Ox":
               // return Battle_Ox.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Axe_Raider":
              //  return Axe_Raider.defence(attackCard, aPlayer, dPlayer, defenderCard);
            case "Yomi_Ship":
               // return Yomi_Ship.defence(attackCard, aPlayer, dPlayer, defenderCard);
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

