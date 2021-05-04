import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mainmenu {
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
    Pattern showCurentMenuRegex = Pattern.compile("menu show-current");
    matcher = showCurentMenuRegex.matcher(input);
    if(matcher.find()){
        showCurrentMenu();
        return;
    }
    System.out.println("invalid command");
}
public static void enterMenu(Matcher matcher){
    String menu = matcher.group(1);
    if(menu.contentEquals("Duel"))
        Controller.menuNumber = 3 ;
    else if(menu.contentEquals("Deck"))
        Controller.menuNumber = 4;
    else  if(menu.contentEquals("Scoreboard"))
        Controller.menuNumber = 5;
    else if (menu.contentEquals("Profile"))
        Controller.menuNumber = 6;
    else if (menu.contentEquals("Shop"))
        Controller.menuNumber=7;
}
public static void exitMenu(){
    Controller.menuNumber =1;
}
public static void showCurrentMenu(){
    System.out.println("Mainmenu");
}
}
