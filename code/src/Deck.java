import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deck {
    private boolean isActive;
    private String name;
    private HashMap<String, Integer> mainDeck;
    private HashMap<String, Integer> sideDeck;

    public static void deckControl(String input, Player player) {

        Pattern createDeckRegex = Pattern.compile("deck create ([\\S]+)");
        Matcher matcher = createDeckRegex.matcher(input);
        if (matcher.find()) {
            createDeck(matcher, player);
            return;
        }
        Pattern deleteDeckRegex = Pattern.compile("deck delete ([\\S]+)");
        matcher = deleteDeckRegex.matcher(input);
        if (matcher.find()) {
            deleteDeck(matcher, player);
            return;
        }
        Pattern activeDeckRegex = Pattern.compile("deck set-activate ([\\S]+)");
        matcher = activeDeckRegex.matcher(input);
        if (matcher.find()) {
            activeDeck(matcher, player);
            return;
        }
        if (haveAddCardPattern(input)) {

            addCardToDeck(input, player);
            return;
        }
        if (haveRemovePattern(input)) {

            removeCardFromDeck(input, player);
            return;
        }
        Pattern showAllDeckRegex = Pattern.compile("deck show --all");
        matcher = showAllDeckRegex.matcher(input);
        if (matcher.find()) {

            showAllDeck(player);
            return;
        }
        if (haveShowDeckPattern(input)) {
            showDeck(input);
            return;
        }
        Pattern showAllCardsRegex = Pattern.compile("deck show --cards");
        matcher = showAllCardsRegex.matcher(input);
        if(matcher.find()){
            showAllCardOfPlayer(player);
            return;
        }
        Pattern enterMenuRegex = Pattern.compile("menu enter (Login|Main|Duel|Profile|Scoreboard|Shop)");
        matcher = enterMenuRegex.matcher(input);
        if(matcher.find()){
            enterMenu();
            return;
        }
        Pattern exitRegex = Pattern.compile("menu exit");
        matcher = exitRegex.matcher(input);
        if(matcher.find()){
            exit();
            return;
        }
        Pattern showCurrentMenuRegex = Pattern.compile("menu show-current");
        matcher = showCurrentMenuRegex.matcher(input);
        if (matcher.find()){
            showCurrentMenu();
            return;
        }
        System.out.println("invalid command");
    }
    public HashMap<String, Integer> getMainDeck(){
        return mainDeck;
    }
    public HashMap<String, Integer> getSideDeck(){
        return sideDeck;
    }

    public int numberOfMainDeckCard() {
        int counter = 0;
        for (String name : mainDeck.keySet()) {
            counter += mainDeck.get(name);
        }
        return counter;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String[] convertCardListToArray(int mainOrSide) {
        ArrayList<String> list = new ArrayList<>();
        if (mainOrSide == 1) {
            for (String name : mainDeck.keySet())
                list.add(name);
        } else {
            for (String name : sideDeck.keySet())
                list.add(name);
        }
        String[] array = new String[list.size()];
        array = list.toArray(array);
        Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
        return array;

    }

    public int numberOfSideDeckCard() {
        int counter = 0;
        for (String name : sideDeck.keySet()) {
            counter += sideDeck.get(name);
        }
        return counter;
    }

    public  String getName(){
        return this.name;
    }

    public int numberOfThisCardInDeck(String name) {
        int counter = 0;
        if (mainDeck.containsKey(name))
            counter += mainDeck.get(name);
        if (sideDeck.containsKey(name))
            counter += sideDeck.get(name);
        return counter;
    }

    public void removeCardFromMainDeck(String cardName) {
        if (mainDeck.get(cardName).intValue() == 1) {
            mainDeck.remove(cardName);
        } else {
            mainDeck.replace(cardName, mainDeck.get(cardName).intValue() - 1);
        }
    }

    public void removeCardFromSideDeck(String cardName) {
        if (sideDeck.get(cardName).intValue() == 1) {
            sideDeck.remove(cardName);
        } else {
            sideDeck.replace(cardName, sideDeck.get(cardName).intValue() - 1);
        }
    }

    public Deck(String name, HashMap<String, Integer> mainDeck, HashMap<String, Integer> sideDeck) {
        this.name = name;
        this.mainDeck = new HashMap<>(mainDeck);
        this.sideDeck = new HashMap<>(sideDeck);
    }

    public void addCardToMainDeck(String cardName) {
        if (mainDeck.containsKey(cardName)) {
            mainDeck.replace(cardName, getNumberOfThisCardInMainDeck(cardName) + 1);
        } else {
            mainDeck.put(cardName, 1);
        }
    }

    public void addCardToSideDeck(String cardName) {
        if (sideDeck.containsKey(cardName))
            sideDeck.replace(cardName, getNumberOfThisCardInSideDeck(cardName) + 1);
        else
            sideDeck.put(cardName, 1);
    }

    public int getNumberOfThisCardInMainDeck(String cardName) {
        if (mainDeck.containsKey(cardName))
            return mainDeck.get(cardName).intValue();
        return 0;
    }

    public int getNumberOfThisCardInSideDeck(String cardName) {
        if (sideDeck.containsKey(cardName))
            return sideDeck.get(cardName).intValue();
        return 0;
    }

    public void addDeckFile() {
        File file = new File(System.getProperty("user.dir") + "\\src\\decks\\" + name + ".json");
        JSONObject mainObj = new JSONObject();
        try (FileWriter fileWriter = new FileWriter(file);) {
            JSONArray mainCardList = new JSONArray();
            for (String cardName : mainDeck.keySet()) {
                JSONObject obj = new JSONObject();
                obj.put("name", cardName);
                obj.put("number", mainDeck.get(cardName) + "");
                mainCardList.add(obj);
            }
            JSONArray sideCardList = new JSONArray();
            for (String cardName : sideDeck.keySet()) {
                JSONObject obj = new JSONObject();
                obj.put("name", cardName);
                obj.put("number", mainDeck.get(cardName) + "");
                mainCardList.add(obj);
            }
            mainObj.put("active", isActive + "");
            mainObj.put("maindeck", mainCardList);
            mainObj.put("sidedeck", sideCardList);
            fileWriter.write(mainObj.toJSONString());
        } catch (Exception e) {
        }


    }

    public void updateFile() {
        addDeckFile();
    }

    public static Deck getDeckByName(String deckName) {
        File file = new File(System.getProperty("user.dir") + "\\src\\decks\\" + deckName + ".json");
        JSONParser jsonParser = new JSONParser();
        HashMap<String, Integer> mainDeck = new HashMap<>();
        HashMap<String, Integer> sideDeck = new HashMap<>();
        String name =""+ deckName;
        try (FileReader fileReader = new FileReader(file);) {
            JSONObject mainObj = (JSONObject) jsonParser.parse(fileReader);

            JSONArray mainList = (JSONArray) mainObj.get("maindeck");
            JSONArray sideList = (JSONArray) mainObj.get("sidedeck");
            for (Object obj : mainList) {
                JSONObject card = (JSONObject) obj;
                mainDeck.put((String) card.get("name"), Integer.parseInt((String) card.get("number")));
            }
            for (Object obj : sideList) {
                JSONObject card = (JSONObject) obj;
                sideDeck.put((String) card.get("name"), Integer.parseInt((String) card.get("number")));
            }
            return new Deck(name, mainDeck, sideDeck);


        } catch (Exception e) {
        }
        return new Deck(name, mainDeck, sideDeck);
    }

    public static boolean isThereThisDeck(String deckName) {

        String[] decks;
        File file = new File(System.getProperty("user.dir") + "\\src\\decks");
        decks = file.list();
        for (String name : decks) {
            if (deckName.contentEquals(name.substring(0, name.length() - 5)))
                return true;
        }
        return false;
    }

    public static void createDeck(Matcher matcher, Player player) {
        String name = matcher.group(1);
        if (isThereThisDeck(name)) {
            System.out.println("deck with name " + name + " already exists");
            return;

        }
        Deck deck = new Deck(name, new HashMap<>(), new HashMap<>());
        deck.addDeckFile();
        player.addDeck(name);
        player.updateInJsonFile();
        System.out.println("deck created successfully!");
    }

    public static void deleteDeck(Matcher matcher, Player player) {
        String deckName = matcher.group(1);
        if (!player.doesHaveThisDeck(deckName)) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }
        Deck deck = getDeckByName(deckName);
        for (String name : deck.mainDeck.keySet()) {
            player.addCard(name, deck.mainDeck.get(name));
        }
        for (String name : deck.sideDeck.keySet()) {
            player.addCard(name, deck.sideDeck.get(name));
        }
        player.deleteDeck(deckName);
        player.updateInJsonFile();
        File file = new File(System.getProperty("user.dir") + "\\src\\decks\\" + deckName + ".json");
        file.delete();
        System.out.println("deck deleted successfully");


    }

    public static void activeDeck(Matcher matcher, Player player) {
        String deckName = matcher.group(1);
        if (!player.doesHaveThisDeck(deckName)) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }
        String[] list = player.getDecksOfThisPlayer();
        for (String name : list) {
            Deck deck = Deck.getDeckByName(name);
            if (deckName.contentEquals(name))
                deck.isActive = true;
            else
                deck.isActive = false;
            deck.updateFile();
        }
        System.out.println("deck activated successfully");
    }

    public static String[] addCardToDeckPatterns() {

        boolean b[] = new boolean[2];
        String component[] = {" --card (?<cardname>[\\S]+)", " --deck (?<deckname>[\\S]+)"};
        String staticStr = "^deck add-card";
        ArrayList<String> p = new ArrayList<>();
        LoginMenu.patternMaker(component, staticStr, b, p);
        String[] patterns = new String[8];
        for (int i = 0; i < 2; i++) {
            patterns[i] = p.get(i) + "$";

        }
        b = new boolean[3];
        p = new ArrayList<>();
        String[] component2 = {" --card (?<cardname>[\\S]+)", " --deck (?<deckname>[\\S]+)", " --side"};
        LoginMenu.patternMaker(component2, staticStr, b, p);
        for (int i = 0; i < 6; i++) {
            patterns[i + 2] = p.get(i) + "$";

        }
        return patterns;
    }

    public static boolean haveAddCardPattern(String input) {
        String[] patterns = addCardToDeckPatterns();
        for (String pattern :
                patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(input);
            if (matcher.find())
                return true;
        }
        return false;
    }

    public static void addCardToDeck(String input, Player player) {
        Matcher matcher = Pattern.compile("kmk").matcher("");
        String patterns[] = addCardToDeckPatterns();
        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            matcher = p.matcher(input);
            if (matcher.find())
                break;

        }
        String deckName = matcher.group("deckname");
        String cardName = matcher.group("cardname");
        if (!player.doesHaveThisCard(cardName)) {
            System.out.println("card with name " + cardName + " does not exist");
            return;
        }
        if (!player.doesHaveThisDeck(deckName)) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }
        Deck deck = Deck.getDeckByName(deckName);
        if (!input.contains("--side")) {
            if (deck.numberOfMainDeckCard() == 60) {
                System.out.println("main deck is full");
                return;
            }
            if (deck.numberOfThisCardInDeck(cardName) == 3) {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
                return;
            }
            deck.addCardToMainDeck(cardName);
            player.removeCard(cardName, 1);

        } else {
            if (deck.numberOfSideDeckCard() == 15) {
                System.out.println("side deck is full");
                return;
            }
            if (deck.numberOfThisCardInDeck(cardName) == 3) {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
                return;
            }
            deck.addCardToSideDeck(cardName);
            player.removeCard(cardName, 1);

        }
        deck.updateFile();
        player.updateInJsonFile();
        System.out.println("card added to deck successfully");


    }

    public static void removeCardFromDeck(String input, Player player) {
        Matcher matcher = Pattern.compile("kmk").matcher("");
        String patterns[] = removeCardToDeckPatterns();
        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            matcher = p.matcher(input);
            if (matcher.find())
                break;

        }
        String deckName = matcher.group("deckname");
        String cardName = matcher.group("cardname");

        if (!player.doesHaveThisDeck(deckName)) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }
        Deck deck = getDeckByName(deckName);

        if (input.contains("--side")) {

            if (!deck.sideDeck.containsKey(cardName)) {
                System.out.println("card with name " + cardName + " does not exist in side deck");
                return;
            }
            deck.removeCardFromSideDeck(cardName);
            player.addCard(cardName,1);
        } else {
            if (!deck.mainDeck.containsKey(cardName)) {
                System.out.println("card with name " + cardName + " does not exist in main deck");
                return;
            }
            deck.removeCardFromMainDeck(cardName);
            player.addCard(cardName,1);
        }
        deck.updateFile();
        player.updateInJsonFile();
        System.out.println("card removed form deck successfully");


    }

    public static boolean haveRemovePattern(String input) {

        String[] patterns = removeCardToDeckPatterns();
        for (String pattern :
                patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(input);
            if (matcher.find())
                return true;
        }
        return false;
    }

    public static String[] removeCardToDeckPatterns() {

        boolean b[] = new boolean[2];
        String component[] = {" --card (?<cardname>[\\S]+)", " --deck (?<deckname>[\\S]+)"};
        String staticStr = "^deck rm-card";
        ArrayList<String> p = new ArrayList<>();
        LoginMenu.patternMaker(component, staticStr, b, p);
        String[] patterns = new String[8];
        for (int i = 0; i < 2; i++) {
            patterns[i] = p.get(i) + "$";

        }
        b = new boolean[3];
        p = new ArrayList<>();
        String[] component2 = {" --card (?<cardname>[\\S]+)", " --deck (?<deckname>[\\S]+)", " --side"};
        LoginMenu.patternMaker(component2, staticStr, b, p);
        for (int i = 0; i < 6; i++) {
            patterns[i + 2] = p.get(i) + "$";

        }
        return patterns;
    }

    public boolean isDeckValid() {
        if (numberOfMainDeckCard() >= 40)
            return true;
        return false;
    }

    public static void showAllDeck(Player player) {
        System.out.println("Decks:\n" +
                "Active deck:");
        String[] list = player.getDecksOfThisPlayer().clone();
        int activeDeckNumber = getActiveDeckNumberInArray(player);
        String activeDeckName = "";
        if (activeDeckNumber != -1) {
            Deck deck = getDeckByName(list[activeDeckNumber]);
            activeDeckName = list[activeDeckNumber];
            System.out.println(list[activeDeckNumber] + ": main deck " + deck.numberOfMainDeckCard() +
                    ", side deck " + deck.numberOfSideDeckCard() + ", " + (deck.isDeckValid() ? ("valid") : ("invalid")));
        }
        System.out.println("Other decks:");
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String name : list) {
            if (name.contentEquals(activeDeckName))
                continue;
            Deck deck = getDeckByName(name);
            System.out.println(list[activeDeckNumber] + ": main deck " + deck.numberOfMainDeckCard() +
                    ", side deck " + deck.numberOfSideDeckCard() + ", " + (deck.isDeckValid() ? ("valid") : ("invalid")));
        }

    }

    public static int getActiveDeckNumberInArray(Player player) {
        String decksNames[] = player.getDecksOfThisPlayer();
        for (int i = 0; i < decksNames.length; i++) {
            Deck deck = getDeckByName(decksNames[i]);
            if (deck.isActive)
                return i;
        }
        return -1;
    }

    public static String[] showDeckPatterns() {
        String[] patterns = {
                "^deck show --deck-name ([\\S]+) --side$",
                "^deck show --side --deck-name ([\\S]+)$",
                "^deck show --deck-name ([\\S]+)$"
        };
        return patterns;
    }

    public static boolean haveShowDeckPattern(String input) {
        String[] patterns = showDeckPatterns();
        for (String pattern :
                patterns) {
            Pattern pattern1 = Pattern.compile(pattern);
            Matcher matcher = pattern1.matcher(input);
            if (matcher.find())
                return true;
        }
        return false;
    }

    public static void showDeck(String input) {
        Matcher matcher = Pattern.compile("odjcij").matcher("");
        String[] patterns = showDeckPatterns();
        for (String name :
                patterns) {
            Pattern pattern = Pattern.compile(name);
            matcher = pattern.matcher(input);
            if (matcher.find())
                break;
        }
        String deckName = matcher.group(1);
        Deck deck = getDeckByName(deckName);
        System.out.println("Deck: " + deckName);
        String[] cardNames;
        if (input.contains(" --side")) {
            System.out.println("Side deck:");
            cardNames = deck.convertCardListToArray(2);
        } else {
            System.out.println("Main deck:");
            cardNames = deck.convertCardListToArray(1);
        }
        System.out.println("Monsters:");
        for (int i = 0; i < cardNames.length; i++) {
            Card card = Card.getCardByName(cardNames[i]);
            if (card.getCardKind() == 1) {
                System.out.println(card.getCardName() + ": " + card.getCardDescription());
            }

        }
        System.out.println("Spell and Traps:");
        for (int i = 0; i < cardNames.length; i++) {
            Card card = Card.getCardByName(cardNames[i]);
            if (card.getCardKind() > 1) {
                System.out.println(card.getCardName() + ": " + card.getCardDescription());
            }


        }
    }

    public static void showAllCardOfPlayer(Player player){
        String[] list = player.AllCardList();
        for (int i = 0 ; i < list.length ; i++) {
            Card card = Card.getCardByName(list[i]);
            System.out.println(card.getCardName()+": "+card.getCardDescription());
        }

    }
    public static void enterMenu(){
        System.out.println("menu navigation is not possible");
    }
    public static void exit(){
        Controller.menuNumber = 2;
    }
    public static void showCurrentMenu(){
        System.out.println("Deck");
    }

    public static void main(String[] args) {

        //System.out.println(addCardToDeckPatterns()[7]);
        ArrayList<String> list = new ArrayList<>();
        list.add("amir");
        list.add("mobin");
        list.add("arad");
        System.out.println(list.contains(""));
    }
}
