package sample.controller;

import sample.model.OnlinePlayer;
import sample.model.Player;
import sample.model.enums.ErrorMessages;

import java.util.*;

public class PlayerController {

    public static ErrorMessages register(String username, String nickname, String password) {
        if (username.equals("") || nickname.equals("") || password.equals(""))
            return ErrorMessages.FILL_IN_ALL_PARTS;
        if (Player.isThereThisUsername(username)) {
            System.out.println("user with username "+username+" already exists");
            return ErrorMessages.TAKEN_USERNAME;
        }
        if (Player.isThereThisNickname(nickname)) {
            System.out.println("user with nickname "+nickname+" already exists");
            return ErrorMessages.TAKEN_NICKNAME;
        }
        Random random = new Random();
        Player player = new Player(username, password, 0, nickname, 0, random.nextInt(38));
        Player.createPlayerJsonFile(player);
        System.out.println("user created successfully!");
        return ErrorMessages.SUCCESS;
    }

    public static String login(String username, String password) {
        if (username.equals("") || password.equals(""))
            return ErrorMessages.FILL_IN_ALL_PARTS.toString();
        if(!Player.isThereThisUsername(username)){
            System.out.println("Username and password didn't match!");
            return ErrorMessages.USER_NOT_IN_DATABASE.toString();
        }
        Player player = Player.getPlayerByUsername(username);
        if(!player.getPassword().contentEquals(password)){
            System.out.println("Username and password didn't match!");
            return ErrorMessages.WRONG_PASSWORD.toString();
        }
        System.out.println("user logged in successfully!");
        String authToken = givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect();
        new OnlinePlayer(player, authToken);
        String result = ErrorMessages.SUCCESS.toString() + " " + authToken;
        return result;
    }

    public static String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 32;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static ErrorMessages changePassword(Player player, String currentPass, String newPass) {
        if (player == null)
            return ErrorMessages.INVALID_TOKEN;
        if (currentPass.equals("") || newPass.equals(""))
            return ErrorMessages.FILL_IN_ALL_PARTS;
        if(!currentPass.contentEquals(player.getPassword())){
            System.out.println("current password is invalid");
            return ErrorMessages.OLD_PASS_WRONG;
        }
        if(currentPass.contentEquals(newPass)){
            System.out.println("please enter a new password");
            return ErrorMessages.NEW_PASS_IS_OLD_PASS;
        }
        player.setPassword(newPass);
        player.updateInJsonFile();
        System.out.println("password changed successfully!");
        return ErrorMessages.SUCCESS;
    }

    public static ErrorMessages changeNickname(Player player, String newNickname) {
        if (player == null)
            return ErrorMessages.INVALID_TOKEN;
        if (newNickname.equals(""))
            return ErrorMessages.FILL_IN_ALL_PARTS;
        if (player.getNickName().equals(newNickname))
            return ErrorMessages.NEW_NICK_IS_OLD_NICK;
        if (Player.isThereThisNickname(newNickname)){
            System.out.println("user with nickname "+newNickname+" already exists");
            return ErrorMessages.TAKEN_NICKNAME;
        }
        player.setNickname(newNickname);
        player.updateInJsonFile();
        System.out.println("nickname changed successfully!");
        return ErrorMessages.SUCCESS;
    }

    public static ArrayList<String> getScoreBoard(Player requester) {
        ArrayList<String> scoreboardList = new ArrayList<>();
        String scoreboard = "";
        int numberOfPlayersShown = 1;
        int last = 0;
        int rank = 1;
        HashMap<String,Integer> Username_HighScore = new HashMap<>();
        for (Player player : Player.getListOFPlayers())
            Username_HighScore.put(player.getNickName(), player.getScore());
        Map<String,Integer> map = sortByValue(Username_HighScore);
        for (Map.Entry<String , Integer> element : map.entrySet()) {
            if (numberOfPlayersShown > 20) {
                scoreboardList.add(scoreboard);
                return scoreboardList; //only show the top 10 players on the scoreboard
            }
            if (element.getValue() != last) {
                last = element.getValue();
                rank = numberOfPlayersShown; //update the new rank
            }
            if (requester.getNickName().equals(element.getKey())) {
                if (scoreboardList.size() == 0)
                    scoreboardList.add(scoreboard);
                scoreboard = "";
                scoreboardList.add(rank + " - " + element.getKey() + "\t" + element.getValue() + "\n");
            } else
                scoreboard = scoreboard + rank + " - " + element.getKey() + "\t" + element.getValue() + "\n";
            numberOfPlayersShown++;
        }
        scoreboardList.add(scoreboard);
        return scoreboardList;
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortedHashMap) {
        // Create a list from elements of HashMap to be able to sort the list
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(unsortedHashMap.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> player1, Map.Entry<String, Integer> player2) {
                return (player2.getValue()).compareTo(player1.getValue());
            }
        });
        // put data from sorted list in a new sorted hashmap
        HashMap<String, Integer> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> data : list) {
            sortedHashMap.put(data.getKey(), data.getValue());
        }
        //return sorted hashmap
        return sortedHashMap;
    }

}
