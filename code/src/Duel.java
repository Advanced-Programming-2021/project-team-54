

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
   public void DuelController(){
      while(true){
         BattleWave battleWave ;
         if(turn){
            battleWave = new BattleWave(opponent,self);
            while (battleWave.getPhase()<5){
               battleWave.battleWaveController(Controller.scanner.nextLine());
               PlayGame.showGameBoard(self,opponent);
            }
            turn = false;
         }else{
            battleWave = new BattleWave(self,opponent);
            while (battleWave.getPhase()<5){
               battleWave.battleWaveController(Controller.scanner.nextLine());
               PlayGame.showGameBoard(opponent,self);
            }
            turn = true;
         }
      }


   }

   public static void main(String[] args) {
      Player self = Player.getPlayerByUsername("a");
      Player opponent = Player.getPlayerByUsername("b");
      self.setGameboard( 1000);
      opponent.setGameboard(1000);
      //PlayGame.showGameBoard(self,opponent);
      Duel duel= new Duel(self,opponent,1000);
      duel.DuelController();

   }


}
