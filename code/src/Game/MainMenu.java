package Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
public static Player player;
public  static void mainMenuController(String input){
    Pattern enterMenuRegex = Pattern.compile("menu enter (Duel|Deck|Scoreboard|Profile|Shop)");
    Matcher matcher = enterMenuRegex.matcher(input);
    if (matcher.find()){
        enterMenu(matcher);
        return;
    }
    Pattern exitMenuRegex = Pattern.compile("menu exit");
    matcher = exitMenuRegex.matcher(input);
    if(matcher.find()){
        exitMenu();
        return;
    }
    Pattern showCurrentMenuRegex = Pattern.compile("menu show-current");
    matcher = showCurrentMenuRegex.matcher(input);
    if(matcher.find()){
        showCurrentMenu();
        return;
    }
    System.out.println("invalid command");
}
public static void enterMenu(Matcher matcher){
    String menu = matcher.group(1);
    if(menu.contentEquals("Game.Duel"))
        Controller.menuNumber = 3 ;
    else if(menu.contentEquals("Game.Deck"))
        Controller.menuNumber = 4;
    else  if(menu.contentEquals("Game.Scoreboard"))
        Controller.menuNumber = 5;
    else if (menu.contentEquals("Game.Profile"))
        Controller.menuNumber = 6;
    else if (menu.contentEquals("Shop"))
        Controller.menuNumber=7;
}
public static void exitMenu(){
    Controller.menuNumber =1;
}
public static void showCurrentMenu(){
    System.out.println("Game.MainMenu");
}
}
