

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Card {

    private String cardName;
    private String cardDescription;
    private int cardKind;
    public Card(String name,String cardDescription,int cardKind){
        this.cardName = name;
        this.cardDescription = cardDescription;
        this.cardKind = cardKind;
    }
    public enum State{
        OO,DO,DH,O,H;
    }
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public State getState(){
        return this.state;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public void setCardKind(int cardKind) {
        this.cardKind = cardKind;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardKind() {
        return cardKind;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getCardName() {
        return cardName;
    }


    public static Card getCardByName(String name){
        File file = new File(System.getProperty("user.dir")+"\\src\\cards\\"+name+".json");
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader(file);) {
            JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
            String description = (String) obj.get("description");
            int kind = Integer.parseInt((String) obj.get("kind"));
            if (kind == 1)
                return Monster.getCardByName(name);
            if(kind == 2)
                return Spell.getCardByName(name);
            if(kind == 3)
                return Trap.getCardByName(name);


        }
        catch (Exception e){}
        return new Card("","",0);
    }

    public static void exportCard(String name,String cardDescription) {
        String currentState = System.getProperty("user.dir");
        File file = new File(currentState+"\\cards\\"+name+".json");
        try (FileWriter fileWriter = new FileWriter(file)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("description",cardDescription);
            fileWriter.write(jsonObject.toJSONString());;
            
        } catch (Exception e) {
            
        }    
    }

    public  void addThisCardFile(){};
}
