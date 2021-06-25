

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    public static void loginMenuRun(String input) {
        if(haveSignupPattern(input, signupPatterns())){
            signingUp(input);
            return;
        }
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
        Pattern enterMenuRegex = Pattern.compile("menu enter (Shop|Profile|Duel|Deck|Scoreboard)");
        matcher = enterMenuRegex.matcher(input);
        if(matcher.find()){
            enterMenu();
            return;
        }
        if(haveLoginPattern(input, loginPatters())){
            loggingIn(input);
            return;
        }
        System.out.println("invalid command");
    }
    public static void main(String[] args) {

    }

    public static void signingUp(String input) {
        ArrayList<String> patterns = signupPatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String username = matcher.group("name");
        String nickname = matcher.group("nick");
        String password = matcher.group("pass");
        if(Player.isThereThisUsername(username)){
            System.out.println("user with username "+username+" already exists");
            return;
        }
        if(Player.isThereThisNickname(nickname)){
            System.out.println("user with nickname "+nickname+" already exists");
            return;
        }
        Player player = new Player(username, password, 0, nickname, 100000);
        Player.createPlayerJsonFile(player);
        System.out.println("user created successfully!");
    }

    public static void patternMaker(String[] Components, String staticStr, boolean isUsed[],
                                    ArrayList<String> patterns) {
        for (int i = 0; i < isUsed.length; i++) {
            if (!isUsed[i]) {
                staticStr += Components[i];
                isUsed[i] = true;
                patternMaker(Components, staticStr, isUsed, patterns);
                isUsed[i] = false;
                staticStr = staticStr.substring(0, staticStr.length() - Components[i].length());
            }
        }
        if (check(isUsed))
            patterns.add(staticStr);

    }

    public static boolean check(boolean X[]) {
        for (boolean b : X) {
            if (b == false)
                return false;
        }
        return true;
    }

    public static boolean haveSignupPattern(String input, ArrayList<String> patterns) {
        for (String string : patterns) {
            Pattern pattern = Pattern.compile(string);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find())
                return true;
        }
        return false;

    }

    public static ArrayList<String> signupPatterns() {
        boolean b[] = new boolean[3];
        String[] captureGroups = new String[3];
        String staticStr = "user create";
        captureGroups[0] = " --username (?<name>[\\S]+)";
        captureGroups[1] = " --nickname (?<nick>[\\S]+)";
        captureGroups[2] = " --password (?<pass>[\\S]+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static void menuExit() {
        System.exit(0);
    }

    public static void showCurrentMenu() {
        System.out.println("Login Menu");
    }

    public static void enterMenu() {
        System.out.println("please login first");
    }

    public static ArrayList<String> loginPatters() {
        boolean[] b = new boolean[2];
        String[] captureGroups = new String[2];
        String staticStr = "user login";
        captureGroups[0] = " --username (?<name>[\\S]+)";
        captureGroups[1] = " --password (?<pass>[\\S]+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static boolean haveLoginPattern(String input, ArrayList<String> patterns) {
        for (String string : patterns) {
            Pattern pattern = Pattern.compile(string);
            Matcher matcher  = pattern.matcher(input);
            if(matcher.find())
                return true;
        }
        return false;

    }

    public static void loggingIn(String input) {

        ArrayList<String> patterns = loginPatters();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String username = matcher.group("name");
        String password = matcher.group("pass");
        if(!Player.isThereThisUsername(username)){
            System.out.println("Username and password didn’t match!");
            return;
        }
        Player player = Player.getPlayerByUsername(username);
        if(!player.getPassword().contentEquals(password)){
            System.out.println("Username and password didn’t match!");
            return ;
        }
        System.out.println("user logged in successfully!");
        MainMenu.player = player;
        Controller.menuNumber = 2;
    }
}
