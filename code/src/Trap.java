

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Trap extends Card {


    Trap(String name, String description, int kind,int price,String type) {
        super(name, description, kind,price,type);
    }

    @Override
    public void addThisCardFile() {
        File file = new File(System.getProperty("user.dir") + "\\src\\cards\\" + super.getCardName() + ".json");

        try (FileWriter fileWriter = new FileWriter(file);) {
            JSONObject obj = new JSONObject();
            obj.put("name", super.getCardName());
            obj.put("description", super.getCardDescription());
            obj.put("kind", super.getCardKind() + "");
            fileWriter.write(obj.toJSONString());
        } catch (Exception e) {
        }

    }
    public static Trap getCardByName(String name){
        File file = new File(System.getProperty("user.dir")+ "\\src\\cards\\" + name + ".json");
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader(file);){
            JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
            String description = (String) obj.get("description");
            int price = Integer.parseInt((String) obj.get("price"));
            String type = (String) obj.get("type");
            int kind = 3;
            return new Trap(name,description,kind,price,type);
        }catch (Exception e){}
        return new Trap("","",0,0,"");
    }
}