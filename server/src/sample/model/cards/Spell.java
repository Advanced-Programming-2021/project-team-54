package sample.model.cards;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.model.Card;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Spell extends Card {
    Spell(String name, String description, int kind) {
        super(name, description, kind);
    }


}