package sample.model;

import sample.controller.CommandController;
import sample.controller.PlayerController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        while (true) {
                            String input = dataInputStream.readUTF();
                            String result = process(input);
                            System.out.println(result);
                            if (result.equals("")) break;
                            dataOutputStream.writeUTF(result);
                            dataOutputStream.flush();
                        }
                        dataInputStream.close();
                        socket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String process(String input) {
        if(CommandController.haveCommandPattern(input, CommandController.signupPatterns()))
            return signup(input);
        if (CommandController.haveLoginPattern(input, CommandController.loginPatters()))
            return login(input);
        if (CommandController.haveCommandPattern(input, CommandController.logoutPatterns()))
            return logout(input);
        if (CommandController.haveCommandPattern(input, CommandController.changePasswordPatterns()))
            return changePassword(input);
        if (CommandController.haveCommandPattern(input, CommandController.getProfileInfoPatterns()))
            return getProfileInfo(input);
        if (CommandController.haveCommandPattern(input, CommandController.getProfilePicturePatterns()))
            return getProfilePicture(input);
        if (CommandController.haveCommandPattern(input, CommandController.getChangeProfilePicturePatterns()))
            return changeProfilePicture(input);
        if (CommandController.haveCommandPattern(input, CommandController.changeNicknamePatterns()))
            return changeNickname(input);
        if (CommandController.haveCommandPattern(input, CommandController.getScoreboardPatterns()))
            return getScoreboard(input);
        return "error";
    }

    private String changeNickname(String input) {
        ArrayList<String> patterns = CommandController.changeNicknamePatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        String nickname = matcher.group("nickname");
        return PlayerController.changeNickname(OnlinePlayer.getPlayerByAuthToken(authToken), nickname).toString();
    }

    private String changeProfilePicture(String input) {
        ArrayList<String> patterns = CommandController.getChangeProfilePicturePatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        if (OnlinePlayer.getPlayerByAuthToken(authToken) != null)
            OnlinePlayer.getPlayerByAuthToken(authToken).changeProfilePicture();
        return "0";
    }

    private String getProfileInfo(String input) {
        ArrayList<String> patterns = CommandController.getProfileInfoPatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        if (OnlinePlayer.getPlayerByAuthToken(authToken) != null)
            return OnlinePlayer.getPlayerByAuthToken(authToken).getProfileInfo();
        return "0";
    }

    private String getScoreboard(String input) {
        ArrayList<String> patterns = CommandController.getScoreboardPatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        if (OnlinePlayer.getPlayerByAuthToken(authToken) != null) {
            ArrayList<String> scoreboard = PlayerController.getScoreBoard(OnlinePlayer.getPlayerByAuthToken(authToken));
            String result = "";
            for (String part : scoreboard)
                result += part + "partioned";
            return result;
        }
        return "0";
    }

    private String getProfilePicture(String input) {
        ArrayList<String> patterns = CommandController.getProfilePicturePatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        if (OnlinePlayer.getPlayerByAuthToken(authToken) != null)
            return String.valueOf(OnlinePlayer.getPlayerByAuthToken(authToken).getProfilePictureID());
        return "0";
    }

    private String logout(String input) {
        ArrayList<String> patterns = CommandController.logoutPatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        if (OnlinePlayer.getPlayerByAuthToken(authToken) != null)
            OnlinePlayer.getOnlinePlayerByAuthToken(authToken).logout();
        return "success";
    }

    private String changePassword(String input) {
        ArrayList<String> patterns = CommandController.changePasswordPatterns();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String authToken = matcher.group("authToken");
        String oldPass = matcher.group("oldPass");
        String newPass = matcher.group("newPass");
        return PlayerController.changePassword(OnlinePlayer.getPlayerByAuthToken(authToken) ,oldPass, newPass).toString();
    }

    private String login(String input) {
        ArrayList<String> patterns = CommandController.loginPatters();
        Matcher matcher = Pattern.compile("").matcher("");
        for (String string : patterns) {
            Pattern pattern  = Pattern.compile(string);
            matcher = pattern.matcher(input);
            if(matcher.find())
                break;
        }
        String username = matcher.group("name");
        String password = matcher.group("pass");
        return PlayerController.login(username, password);
    }

    private String signup(String input) {
        ArrayList<String> patterns = CommandController.signupPatterns();
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
        return PlayerController.register(username, nickname, password).toString();
    }

}
