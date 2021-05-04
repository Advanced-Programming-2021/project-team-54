import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Spell extends Card{
    Spell(String name , String description , int kind ){
        super(name , description , kind);
    }

    @Override
    public void addThisCardFile() {
        File file = new File(System.getProperty("user.dir")+"\\src\\cards\\"+super.getCardName()+".json");

        try(FileWriter fileWriter = new FileWriter(file);) {
            JSONObject obj = new JSONObject();
            obj.put("name",super.getCardName());
            obj.put("description",super.getCardDescription());
            obj.put("kind",super.getCardKind()+"");
            fileWriter.write(obj.toJSONString());
        }
        catch (Exception e){}
    }

    public static Spell getCardByName(String name){
        File file = new File (System.getProperty("user.dir")+"\\src\\cards\\"+name+".json");
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader(file);){
            JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
            String description  = (String) obj.get("description");
            int kind = 2 ;
            return new Spell(name,description,2);

        }catch (Exception e){

        }
        return new Spell("","",0);
    }

    public static void main(String[] args) {
        Spell spell = new Spell("sam" , "koshande" , 1);
        spell.addThisCardFile();

        //System.out.println(card.getCardDescription());
    }
}
