

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    public int getDefencePower(){
        return  defencePower;
    }

    Monster(String name, String description, int cardKind, int attackPower, int defencePower, int level , int price , String type) {
        super(name, description, cardKind,price,type);
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.level = level;
    }

    @Override
    public void addThisCardFile() {
        File file = new File(System.getProperty("user.dir") + "\\src\\cards\\" + super.getCardName() + ".json");

        try( FileWriter fileWriter = new FileWriter(file);) {


            JSONObject obj = new JSONObject();
            obj.put("name", super.getCardName());

            obj.put("description", super.getCardDescription());
            obj.put("attack", this.attackPower + "");
            obj.put("defence", this.defencePower + "");
            obj.put("kind", super.getCardKind() + "");
            obj.put("level", this.level + "");

            fileWriter.write(obj.toJSONString());
            System.out.println("1");
        } catch (Exception e) {
        }
    }


    public static Monster getCardByName(String name) {
        File file = new File(System.getProperty("user.dir")+"\\src\\cards\\"+name+".json");
        JSONParser jsonParser = new JSONParser();
        try (FileReader fileReader = new FileReader(file);) {
            JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
            String description = (String) obj.get("description");
            int kind   = Integer.parseInt((String) obj.get("kind"));
            int attack = Integer.parseInt((String) obj.get("attack"));
            int defence = Integer.parseInt((String) obj.get("defence"));
            int level = Integer.parseInt((String) obj.get("level"));
            int price = Integer.parseInt((String) obj.get("price"));
            String type = (String) obj.get("type");
            return  new Monster(name,description,kind,attack,defence,level,price,type);
        } catch (Exception e) {
        }
        return new Monster("","",0,0,0,0,0,"");
    }

    public int getLevel(){
        return level;
    }

}
