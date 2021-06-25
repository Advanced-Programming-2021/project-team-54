

import java.util.ArrayList;

public class Duel {

   private Player self;
   private Player opponent;

   private boolean turn = true;
   public Duel(Player self , Player opponent , int lp){
      self.setGameboard(lp);
      opponent.setGameboard(lp);
      self.getGameBoard().makeGameBoardReady();
      opponent.getGameBoard().makeGameBoardReady();
      this.self = self;
      this.opponent = opponent;

   }
   public int DuelController(){


      int number = 0 ;

      sk:
      while(true){
         BattleWave battleWave ;
         if(turn){
            battleWave = new BattleWave(opponent,self);
            while (battleWave.getPhase()<5){
               number = battleWave.battleWaveController(Controller.scanner.nextLine());
               PlayGame.showGameBoard(self,opponent);
               if(number == 1 || number == 2)
                  break sk;
            }
            turn = false;
         }else{
            battleWave = new BattleWave(self,opponent);
            while (battleWave.getPhase()<5){
               battleWave.battleWaveController(Controller.scanner.nextLine());
               PlayGame.showGameBoard(opponent,self);
               if(number == 1 || number == 2)
                  break sk;
            }
            turn = true;
         }
      }
      return number;


   }


   public static int oneDuelMaker(Player self , Player opponent){
      Duel duel = new Duel(self,opponent,8000);
      return duel.DuelController();
   }
   public static void DuelMaker(int rand , Player self , Player opponent){
      if(rand == 1){
         int num = oneDuelMaker(self, opponent);
         switch (num){
            case 1:
               System.out.println(self.getUsername() +" : win ");
               self.increaseScore(3);
               self.updateInJsonFile();
               break;
            case 2:
               System.out.println(opponent.getUsername()+" : win");
               opponent.increaseScore(3);
               opponent.updateInJsonFile();
               break;
         }
      }else{
         int selfScore = 0 ;
         int opponentScore = 0;
         while (true) {
            if (selfScore == 2) {
               System.out.println(self.getUsername() + " : win ");
               self.increaseScore(3);
               self.updateInJsonFile();
               return;
            }
            if (opponentScore == 2) {
               System.out.println(opponent.getUsername() + " : win");
               opponent.increaseScore(3);
               opponent.updateInJsonFile();
               return;
            }
            int num = oneDuelMaker(self, opponent);

            switch (num){
               case 1:
                  System.out.println(self.getUsername() +" : win in this rond ");
                  selfScore++;
                  break;
               case 2:
                  System.out.println(opponent.getUsername()+" : win in  this rond");
                  opponentScore++;
                  break;
            }
         }













      }
   }

   public static void main(String[] args) {
      Player self = Player.getPlayerByUsername("c");
      Player opponent = Player.getPlayerByUsername("b");
      //self.setGameboard( 1000);
    //  opponent.setGameboard(1000);
      //PlayGame.showGameBoard(self,opponent);
      Duel duel= new Duel(self,opponent,1000);
      duel.DuelController();

   }


}
