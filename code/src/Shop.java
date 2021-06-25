import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shop {
    public static void shopMenuController(String input) {
        Pattern menuExitRegex = Pattern.compile("menu exit");
        Matcher matcher = menuExitRegex.matcher(input);
        if(matcher.find()){
            menuExit();
            return;
        }
        Pattern showMenuRegex = Pattern.compile("menu show-current");
        matcher = showMenuRegex.matcher(input);
        if(matcher.find()){
            showCurrentMenu();
            return;
        }
        Pattern buyCardRegex = Pattern.compile("shop buy (?<cardName>.*)");
        matcher = buyCardRegex.matcher(input);
        if(matcher.find()){
            buyCard(matcher.group("cardName"));
            return;
        }
        Pattern showAllCardsRegex = Pattern.compile("shop show --all");
        matcher = showAllCardsRegex.matcher(input);
        if(matcher.find()){
            showAllCards();
            return;
        }
        System.out.println("invalid command");
    }

    private static void showAllCards() {
        HashMap<String, String > allCards = new HashMap<>();
        File file = new File(System.getProperty("user.dir") + "/src/cards/");
        file.mkdir();
        String[] cards = file.list();
        for (String card : cards) {
            JSONParser jsonParser = new JSONParser();
            File cardFile = new File(System.getProperty("user.dir")+"/src/cards/"+ card);
            try (FileReader fileReader = new FileReader(cardFile)) {
                JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
                String description = (String) obj.get("description");
                String name = (String) obj.get("name");
                allCards.put(name, description);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String card : allCards.keySet()) {
            System.out.println(card + ":" + allCards.get(card));
        }
    }

    private static void buyCard(String cardName) {
        Card cardToBuy = Card.getCardByName(cardName + ".json");
        if (!cardToBuy.getCardName().equals(cardName)) {
            System.out.println("there is no card with this name");
            return;
        } else if (cardToBuy.getCardPrice() > MainMenu.player.getMoney()) {
            System.out.println("not enough money");
            return;
        } else {
            MainMenu.player.increaseMoney(-cardToBuy.getCardPrice());
            MainMenu.player.addCard(cardName,1);
        }

    }

    private static void showCurrentMenu() {
        System.out.println("Shop");
    }

    private static void menuExit() {
        Controller.menuNumber = 2;
    }
}
