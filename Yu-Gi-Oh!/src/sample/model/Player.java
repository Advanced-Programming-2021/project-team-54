package sample.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Player {
    private String username;
    private String password;
    private int score;
    private String nickname;
    private int money;
    private HashMap<String, Integer> listOfFreeCards = new HashMap<>();
    private ArrayList<String> listOfDeck = new ArrayList<>();
    private GameBoard gameboard ;

    public Player(String name, String password, int score, String nickname, int money) {
        this.username = name;
        this.password = password;
        this.score = score;
        this.nickname = nickname;
        this.money = money;

    }

    public void setGameBoard(GameBoard gameboard){
        this.gameboard = gameboard;
    }
    public GameBoard getGameBoard(){
        return this.gameboard;
    }
    public void updateInJsonFile() {
        createPlayerJsonFile(this);
    }

    public static boolean createPlayerJsonFile(Player player) {
        JSONObject playerDetails = new JSONObject();
        playerDetails.put("username", player.username);
        playerDetails.put("password", player.password);
        playerDetails.put("nickname", player.nickname);
        playerDetails.put("score", player.score + "");
        playerDetails.put("money", player.money + "");
        JSONArray cardArray = new JSONArray();

        for (String cardName : player.listOfFreeCards.keySet()) {
            JSONObject cardObj = new JSONObject();
            cardObj.put("cardname", cardName);
            cardObj.put("number", player.listOfFreeCards.get(cardName) + "");
            cardArray.add(cardObj);
        }
        playerDetails.put("cards", cardArray);
        JSONArray deckArray = new JSONArray();
        for (String deckName : player.listOfDeck) {
            JSONObject deckObj = new JSONObject();
            deckObj.put("deckname", deckName);
            deckArray.add(deckObj);
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

    public Deck getActiveDeck(){
        for (String name:
                listOfDeck) {
            Deck deck = Deck.getDeckByName(name);
            if(deck.getIsActive())
                return deck;
        }
        return new Deck("null",new HashMap<String, Integer>(),new HashMap<String, Integer>());
    }

    public boolean checkActivationOfDeck(){
        for(String deckName : listOfDeck){
            Deck deck =Deck.getDeckByName(deckName);
            if(deck.getIsActive())
                return true;
        }
        return false;
    }

    public void deleteDeck(String name){
        int i = 0 ;
        for(;i<listOfDeck.size();i++){
            if( name.contentEquals(listOfDeck.get(i))){
                listOfDeck.remove(i);
                return;
            }

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
        String[] pathNames;
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        pathNames = file.list();

        for (String string : pathNames) {

            JSONParser jsonParser = new JSONParser();
            File userFile = new File(System.getProperty("user.dir") + "\\src\\users\\" + string);
            try (FileReader fileReader = new FileReader(userFile)) {

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

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getNickName() {
        return this.nickname;
    }

    public int getScore() {
        return this.score;
    }

    public int getMoney() {
        return this.money;
    }

    public void addCard(String name, int number) {
        if(listOfFreeCards.containsKey(name)) {
            this.listOfFreeCards.replace(name,listOfFreeCards.get(name)+number);
        }
        else{
            listOfFreeCards.put(name,number);
        }
    }

    public void removeCard(String name , int number){
        if(listOfFreeCards.get(name) == 1 ){
            listOfFreeCards.remove(name);
        }else {
            listOfFreeCards.replace(name,listOfFreeCards.get(name)-number);
        }
    }

    public void addDeck(String name) {
        this.listOfDeck.add(name);
    }

    public  String[] getDecksOfThisPlayer(){
        String list[] = new String[listOfDeck.size()];
        list = listOfDeck.toArray(list);
        return list;
    }

    public boolean doesHaveThisCard(String name){
        return listOfFreeCards.containsKey(name);
    }

    public boolean doesHaveThisDeck(String deckName){
        for (String name : listOfDeck){
            if(deckName.contentEquals(name))
                return   true;
        }
        return false;
    }

    public String[] AllCardList(){
        ArrayList<String> list = new ArrayList<>();
        for (String name:
                listOfFreeCards.keySet()) {
            list.add(name);
        }
        String[] decks = getDecksOfThisPlayer();
        for (int i = 0; i < decks.length ; i++) {
            Deck deck = Deck.getDeckByName(decks[i]);
            HashMap<String, Integer> mainDeck = deck.getMainDeck();
            HashMap<String, Integer> sideDeck = deck.getSideDeck();
            for (String name:
                    mainDeck.keySet()) {
                if(!list.contains(name)){
                    list.add(name);
                }
            }
            for (String name:
                    sideDeck.keySet()) {
                if(!list.contains(name)){
                    list.add(name);
                }
            }
        }
        String []array = new String[list.size()];
        array = list.toArray(array);
        Arrays.sort(array,String.CASE_INSENSITIVE_ORDER);
        return array;

    }

    public static Player[] getListOFPlayers() {
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        String[] playersFileName = file.list();
        ArrayList<Player> players = new ArrayList<>();
        for (String s : playersFileName) {
            Player player = Player.getPlayerByUsername(s.substring(0, s.length() - 5));
            players.add(player);
        }
        Player[] listOfPlayer = new Player[players.size()];
        listOfPlayer = players.toArray(listOfPlayer);
        return listOfPlayer;
    }

    public static void main(String[] args) {
        Player player = new Player("mobin", "12", 0, "mobin", 0);
        createPlayerJsonFile(player);
        //System.out.println(isThereThisNickname("jafar"));
        // player = getPlayerByUsername("amir");
        //System.out.println(player.listOfFreeCards.get("zzz"));
    }

}
