

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
            while (battleWave.getPhase()<6){
               battleWave.battleWaveController(Controller.scanner.nextLine());
            }
            turn = false;
         }else{
            battleWave = new BattleWave(self,opponent);
            while (battleWave.getPhase()<6){
               battleWave.battleWaveController(Controller.scanner.nextLine());
            }
            turn = true;
         }
      }


   }


}
