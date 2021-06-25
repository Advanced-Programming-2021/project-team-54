

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
    public static  void profileControl(Player player , String input){
        Pattern changeNicknameRegex = Pattern.compile("profile change --nickname ([\\S]+)");
        Matcher matcher = changeNicknameRegex.matcher(input);
        if(matcher.find()){
            changeNickname(matcher,player);
            return;
        }
        if(haveThisStrChangePassPattern(input)){
            changePass(input,player);
            return;
        }
        Pattern enterMenuRegex = Pattern.compile("menu enter (Login|Main|Duel|Deck|Scoreboard|Shop)");
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
    public static void changeNickname(Matcher matcher,Player player){
        String newNickname = matcher.group(1);
        if(isNicknameAlreadyExist(newNickname)){
            System.out.println("user with nickname "+newNickname+" already exists");
            return;
        }
        player.setNickname(newNickname);
        player.updateInJsonFile();
        System.out.println("nickname changed successfully!");

    }
    public static boolean isNicknameAlreadyExist(String nickname){
        Player[] players = Scoreboard.getListOFPlayers();
        for(Player player:players){
            if(nickname.contentEquals(player.getNickName()))
                return true;
        }
        return  false;
    }
    public  static  String[] changePasswordPatterns(){
        String[] components = {" --password"," --current (?<currentpass>[\\S]+)"," --new (?<newpass>[\\S]+)"};
        String staticStr = "profile change";
        boolean[]b = new boolean[3];
        ArrayList<String > patterns = new ArrayList<>();
        LoginMenu.patternMaker(components,staticStr,b,patterns);
        String[] patternsArray = new String[patterns.size()];
        patternsArray = patterns.toArray(patternsArray);
        return patternsArray;
    }
    public static void main(String[] args) {
        Player player = Player.getPlayerByUsername("amir");
        profileControl(player,"profile change --password --current 1234 --new ss");

    }
    public  static boolean haveThisStrChangePassPattern(String input){
        String[] patterns = changePasswordPatterns();
        for (String patter:
             patterns) {
            Pattern p = Pattern.compile(patter);
            Matcher matcher = p.matcher(input);
            if(matcher.find())
                return true;
        }
        return false;
    }
    public static void changePass(String input , Player player){
        String []patterns = changePasswordPatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String p:
             patterns) {
            Pattern pattern = Pattern.compile(p);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String currentPass = matcher.group("currentpass");
        String newPass = matcher.group("newpass");
        if(!currentPass.contentEquals(player.getPassword())){
            System.out.println("current password is invalid");
            return;
        }
        if(currentPass.contentEquals(newPass)){
            System.out.println("please enter a new password");
            return;
        }
        player.setPassword(newPass);
        player.updateInJsonFile();
        System.out.println("password changed successfully!");
    }

    public static void enterMenu(){
        System.out.println("menu navigation is not possible");
    }
    public static void exit(){
        Controller.menuNumber = 2;
    }
    public static void showCurrentMenu(){
        System.out.println("Profile");
    }
}
