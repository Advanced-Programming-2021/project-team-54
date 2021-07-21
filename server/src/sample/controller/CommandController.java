package sample.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandController {


    public static boolean haveCommandPattern(String input, ArrayList<String> patterns) {
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
        captureGroups[0] = " --username (?<name>.+)";
        captureGroups[1] = " --nickname (?<nick>.+)";
        captureGroups[2] = " --password (?<pass>.+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> logoutPatterns() {
        boolean b[] = new boolean[1];
        String[] captureGroups = new String[1];
        String staticStr = "user logout";
        captureGroups[0] = " --authToken (?<authToken>\\S+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> getProfilePicturePatterns() {
        boolean b[] = new boolean[1];
        String[] captureGroups = new String[1];
        String staticStr = "user get picture";
        captureGroups[0] = " --authToken (?<authToken>\\S+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> getChangeProfilePicturePatterns() {
        boolean b[] = new boolean[1];
        String[] captureGroups = new String[1];
        String staticStr = "user change picture";
        captureGroups[0] = " --authToken (?<authToken>\\S+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> getProfileInfoPatterns() {
        boolean b[] = new boolean[1];
        String[] captureGroups = new String[1];
        String staticStr = "user get info";
        captureGroups[0] = " --authToken (?<authToken>\\S+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> getScoreboardPatterns() {
        boolean b[] = new boolean[1];
        String[] captureGroups = new String[1];
        String staticStr = "user get scoreboard";
        captureGroups[0] = " --authToken (?<authToken>\\S+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> changePasswordPatterns() {
        boolean b[] = new boolean[3];
        String[] captureGroups = new String[3];
        String staticStr = "user changePassword";
        captureGroups[0] = " --authToken (?<authToken>.+)";
        captureGroups[1] = " --currentPass (?<oldPass>.+)";
        captureGroups[2] = " --newPassword (?<newPass>.+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static ArrayList<String> changeNicknamePatterns() {
        boolean b[] = new boolean[2];
        String[] captureGroups = new String[2];
        String staticStr = "user changeNickname";
        captureGroups[0] = " --authToken (?<authToken>.+)";
        captureGroups[1] = " --nickname (?<nickname>.+)";
        ArrayList<String> patterns = new ArrayList<>();
        patternMaker(captureGroups, staticStr, b, patterns);
        return patterns;
    }

    public static boolean check(boolean X[]) {
        for (boolean b : X) {
            if (b == false)
                return false;
        }
        return true;
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

    public static ArrayList<String> loginPatters() {
        boolean[] b = new boolean[2];
        String[] captureGroups = new String[2];
        String staticStr = "user login";
        captureGroups[0] = " --username (?<name>.+)";
        captureGroups[1] = " --password (?<pass>.+)";
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
}
