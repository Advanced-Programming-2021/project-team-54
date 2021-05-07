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
        if(haveThisStrChangepassPattern(input)){
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
        player.setNikname(newNickname);
        player.updateInjsonFile();
        System.out.println("nickname changed successfully!");

    }
    public static boolean isNicknameAlreadyExist(String nickname){
        Player[] players = Scoreboard.getListOFPlayers();
        for(Player player:players){
            if(nickname.contentEquals(player.getNikname()))
                return true;
        }
        return  false;
    }
    public  static  String[] changePasswordPatterns(){
        String[] components = {" --password"," --current (?<currentpass>[\\S]+)"," --new (?<newpass>[\\S]+)"};
        String staticstr = "profile change";
        boolean[]b = new boolean[3];
        ArrayList<String > patterns = new ArrayList<>();
        loginMenu.patternmaker(components,staticstr,b,patterns);
        String[] patternsArray = new String[patterns.size()];
        patternsArray = patterns.toArray(patternsArray);
        return patternsArray;
    }
    public  static boolean haveThisStrChangepassPattern(String input){
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
        String currrentPass = matcher.group("currentpass");
        String newPass = matcher.group("newpass");
        if(!currrentPass.contentEquals(player.getPassword())){
            System.out.println("current password is invalid");
            return;
        }
        if(currrentPass.contentEquals(newPass)){
            System.out.println("please enter a new password");
            return;
        }
        player.setPassword(newPass);
        player.updateInjsonFile();
        System.out.println("password changed successfully!");
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
}
