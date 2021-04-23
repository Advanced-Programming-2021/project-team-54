import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Player {
    private String username;
    private String password;
    private int score;
    private String nikname;
    private int money;
    private HashMap<String, Integer> listOfFreeCards = new HashMap<>();
    private ArrayList<String> listOfDeck = new ArrayList<>();

    public Player(String name, String password, int score, String nikname, int money) {
        this.username = name;
        this.password = password;
        this.score = score;
        this.nikname = nikname;
        this.money = money;

    }

    public void updateInjsonFile() {
        createPlayerJsonFile(this);
    }

    public static boolean createPlayerJsonFile(Player player) {
        JSONObject playerDetails = new JSONObject();
        playerDetails.put("username", player.username);
        playerDetails.put("password", player.password);
        playerDetails.put("nickname", player.nikname);
        playerDetails.put("score", player.score + "");
        playerDetails.put("money", player.money + "");
        JSONArray cardArray = new JSONArray();

        for (String cardName : player.listOfFreeCards.keySet()) {
            JSONObject cardobj = new JSONObject();
            cardobj.put("cardname", cardName);
            cardobj.put("number", player.listOfFreeCards.get(cardName) + "");
            cardArray.add(cardobj);
        }
        playerDetails.put("cards", cardArray);
        JSONArray deckArray = new JSONArray();
        for (String deckName : player.listOfDeck) {
            JSONObject deckobj = new JSONObject();
            deckobj.put("deckname", deckName);
            deckArray.add(deckobj);
        }
        playerDetails.put("decks", deckArray);

        String currentState = System.getProperty("user.dir");
        File usersFile = new File(currentState + "\\src\\users\\" + player.username + ".json");
        try (FileWriter fileWriter = new FileWriter(usersFile)) {
            fileWriter.write(playerDetails.toJSONString());
            fileWriter.flush();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static Player getPlayerByUsername(String username) {
        String currentState = System.getProperty("user.dir");
        File usersFile = new File(currentState + "\\src\\users\\" + username + ".json");
        JSONParser jsonParser = new JSONParser();
        try (FileReader fileReader = new FileReader(usersFile)) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            String name = (String) jsonObject.get("username");

            String nikname = (String) jsonObject.get("nickname");
            String password = (String) jsonObject.get("password");

            int score = Integer.parseInt((String) jsonObject.get("score"));
            int money = Integer.parseInt((String) jsonObject.get("money"));
            Player player = new Player(name, password, score, nikname, money);
            JSONArray listOfCardsJson = (JSONArray) jsonObject.get("cards");
            for (Object object : listOfCardsJson) {
                JSONObject obj = (JSONObject) object;
                player.listOfFreeCards.put((String) obj.get("cardname"), Integer.parseInt((String) obj.get("number")));
            }
            JSONArray listOfDeck = (JSONArray)jsonObject.get("decks");
            for (Object object : listOfDeck) {
                JSONObject obj = (JSONObject) object;
                player.listOfDeck.add((String) obj.get("deckname"));
            }
            return player;

        } catch (Exception e) {
            return new Player("", "", -1, "", 12);
        }
    }

    public static boolean isThereThisUsername(String username) {
        String currentState = System.getProperty("user.dir");
        File usersFile = new File(currentState + "\\src\\users\\" + username + ".json");
        return usersFile.exists();
    }

    public static boolean isThereThisNickname(String nickname) {
        String[] pathnames;
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        pathnames = file.list();

        for (String string : pathnames) {

            JSONParser jsonParser = new JSONParser();
            File userfile = new File(System.getProperty("user.dir") + "\\src\\users\\" + string);
            try (FileReader fileReader = new FileReader(userfile)) {

                JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
                if (nickname.contentEquals((String) jsonObject.get("nickname")))
                    return true;
            } catch (Exception e) {

            }
        }
        return false;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void increaseMoney(int amount) {
        this.money += amount;
    }

    public void setNikname(String nikname) {
        this.nikname = nikname;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNikname() {
        return this.nikname;
    }

    public int getScore() {
        return this.score;
    }

    public int getMoney() {
        return this.money;
    }

    public void addCard(String name, int number) {
        this.listOfFreeCards.put(name, number);
    }

    public void addDeck(String name, int deckNumber) {
        this.listOfDeck.add(name);
    }

    public static void main(String[] args) {
        Player player = new Player("mobin", "12", 0, "mobin", 0);
         createPlayerJsonFile(player);
        //System.out.println(isThereThisNickname("jafar"));
       // player = getPlayerByUsername("amir");
        //System.out.println(player.listOfFreeCards.get("zzz"));
    }
}
