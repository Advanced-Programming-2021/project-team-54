import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Card {
    
    private String cardName;
    private String cardDescription;
    public Card(String name,String cardDescription){
        this.cardName = name;
        this.cardDescription = cardDescription;
    }
    public static Card getCardByName(String name){
        String currentState = System.getProperty("user.dir");
        File usersFile = new File(currentState + "\\users\\" + name + ".json");
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(usersFile)) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            String username = (String)jsonObject.get("name");
            String description = (String)jsonObject.get("description");
            return new Card(username  , description);

            

        } catch (Exception e) {
            return new Card("null"  , "null");
        }

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
}
