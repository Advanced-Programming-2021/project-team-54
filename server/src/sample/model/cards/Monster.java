package sample.model.cards;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.model.Card;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Monster extends Card {

    private int attackPower;
    private int defencePower;
    private int level;


    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }


    Monster(String name, String description, int cardKind, int attackPower, int defencePower, int level) {
        super(name, description, cardKind);
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.level = level;
    }




    public int getLevel() {
        return level;
    }

//    public static void main(String[] args) {
//        Game.Card monster = new Game.Monster("Tkakakak","pool",1,3000,600,7);
//        monster.addThisCardFile();
//   }
}