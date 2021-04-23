import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

public class loginMenu {
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
            logingin(input);
            return;
        }
        System.out.println("invalid command");
    }
    public static void main(String[] args) {
        InputStream src;
        Scanner scanner = new Scanner(System.in);
        String u = scanner.nextLine();
        loginMenuRun(u);
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
        Player player = new Player(username, password, 0, nickname, 0);
        Player.createPlayerJsonFile(player);
        System.out.println("user created successfully!");
    }

    public static void patternmaker(String[] Components, String staticstr, boolean isUsed[],
            ArrayList<String> patterns) {
        for (int i = 0; i < isUsed.length; i++) {
            if (!isUsed[i]) {
                staticstr += Components[i];
                isUsed[i] = true;
                patternmaker(Components, staticstr, isUsed, patterns);
                isUsed[i] = false;
                staticstr = staticstr.substring(0, staticstr.length() - Components[i].length());
            }
        }
        if (check(isUsed))
            patterns.add(staticstr);

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
        String[] capturgroups = new String[3];
        String staticstr = "user create";
        capturgroups[0] = " --username (?<name>[\\S]+)";
        capturgroups[1] = " --nickname (?<nick>[\\S]+)";
        capturgroups[2] = " --password (?<pass>[\\S]+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternmaker(capturgroups, staticstr, b, patterns);
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
        String[] capturgroups = new String[2];
        String staticstr = "user login";
        capturgroups[0] = " --username (?<name>[\\S]+)";
        capturgroups[1] = " --password (?<pass>[\\S]+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternmaker(capturgroups, staticstr, b, patterns);
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

    public static void logingin(String input) {

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
        Mainmenu.player = player;
        Controller.menuNumber = 2;
    }
}
