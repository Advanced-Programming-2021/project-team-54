import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayGame {
    public static Player opponent;
    public static boolean duelInProccess;

    public static void controller(String input) {
        if (duelInProccess) {

        }


        if (doeshaveCreateGamePatters(input)) {
            makeGame(input);
            return;

        }


        System.out.println("invalide command");


    }


    public static Matcher patternMatcher(String[] patterns, String input) {
        Matcher matcher = Pattern.compile("akjk").matcher("");
        for (String pattern :
                patterns) {
            Pattern p = Pattern.compile(pattern);
            matcher = p.matcher(input);
            if (matcher.find())
                break;
        }
        return matcher;
    }

    public static String[] createGameRegexes() {
        boolean[] b = new boolean[2];
        String[] cotents = {" --second-player (?<name>[\\S]+)", " --rounds (?<number>[\\d]+)"};
        String staticstr = "duel new";
        ArrayList<String> list = new ArrayList<>();
        LoginMenu.patternmaker(cotents, staticstr, b, list);
        String[] pat = new String[2];
        pat = list.toArray(pat);
        return pat;
    }

    public static boolean doeshaveCreateGamePatters(String input) {
        String[] patterns = createGameRegexes();
        for (String pattern :
                patterns) {
            Matcher matcher = Pattern.compile(pattern).matcher(input);
            if (matcher.find())
                return true;
        }
        return false;
    }

    public static void showGameBoard(Player self, Player oppont) {

        topShow(oppont);
        System.out.println("----------------------------------------");
        downShow(self);

    }

    public static void topShow(Player player) {
        GameBoard gameboard = player.getGameBoard();
        System.out.println(player.getNikName() + " : " + gameboard.getLp());
        System.out.print("    ");
        for (int i = 0; i < gameboard.getNumberOfInHandCard(); i++) {
            System.out.print("c   ");
        }
        System.out.println("\n" + gameboard.getNumberOfMaindeck());
        System.out.print("    ");
        HashMap<Integer, Card> list = new HashMap<>(gameboard.getSpelltrapField());
        if (list.containsKey(4)) {
            Card card = list.get(4);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");
        if (list.containsKey(2)) {
            Card card = list.get(2);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");
        if (list.containsKey(1)) {
            Card card = list.get(1);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");
        if (list.containsKey(3)) {
            Card card = list.get(3);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");
        if (list.containsKey(5)) {
            Card card = list.get(5);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("    " + "\n" + "    ");
        list = new HashMap<>(gameboard.getMounsterField());
        if (list.containsKey(4)) {
            Card card = list.get(4);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(2)) {
            Card card = list.get(2);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(1)) {
            Card card = list.get(1);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(3)) {
            Card card = list.get(3);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(5)) {
            Card card = list.get(5);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");
        System.out.print("\n" + gameboard.getNumberOfGrave() + "                      " + gameboard.getNumberOfFieldZone() + "\n");
    }

    public static void downShow(Player player) {
        GameBoard gameboard = player.getGameBoard();

        System.out.print(gameboard.getNumberOfGrave() + "                      " + gameboard.getNumberOfFieldZone() + "\n    ");
        HashMap<Integer, Card> list = new HashMap<>(gameboard.getMounsterField());
        if (list.containsKey(5)) {
            Card card = list.get(5);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(3)) {
            Card card = list.get(3);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(1)) {
            Card card = list.get(1);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(2)) {
            Card card = list.get(2);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");

        if (list.containsKey(4)) {
            Card card = list.get(4);
            if (card.getState() == Card.State.OO) System.out.print("OO  ");
            if (card.getState() == Card.State.DO) System.out.print("DO  ");
            if (card.getState() == Card.State.DH) System.out.print("DH  ");
        } else System.out.print("E   ");
        System.out.print("\n" + "    ");
        list = new HashMap<>(gameboard.getSpelltrapField());

        if (list.containsKey(5)) {
            Card card = list.get(5);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");


        if (list.containsKey(3)) {
            Card card = list.get(3);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");

        if (list.containsKey(1)) {
            Card card = list.get(1);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");

        if (list.containsKey(2)) {
            Card card = list.get(2);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   ");

        if (list.containsKey(4)) {
            Card card = list.get(4);
            if (card.getState() == Card.State.O) System.out.print("O");
            else System.out.print("H");
        } else System.out.print("E");
        System.out.print("   \n                       " + gameboard.getNumberOfMaindeck() + "\n");
        for (int i = 0; i < gameboard.getNumberOfInHandCard(); i++) {
            System.out.print("c   ");
        }
        System.out.println(player.getNikName() + " : " + gameboard.getLp());

    }

    public static void makeGame(String input) {
        Matcher matcher = patternMatcher(createGameRegexes(), input);
        String opponentName = matcher.group("name");
        int round = Integer.parseInt(matcher.group("number"));
        if (!Player.isThereThisUsername(opponentName)) {
            System.out.println("there is no player with this username");
            return;
        }
        opponent = Player.getPlayerByUsername(opponentName);
        Player self = MainMenu.player;
        if (!self.checkActivationOfDeck()) {
            System.out.println(self.getUsername() + " has no active deck");
            return;
        }
        if (!opponent.checkActivationOfDeck()) {
            System.out.println(opponentName + " has no active deck");
            return;
        }

        if (!self.getActiveDeck().isDeckValid()) {
            System.out.println(self.getUsername() + "’s deck is invalid");
            return;
        }
        if (!opponent.getActiveDeck().isDeckValid()) {
            System.out.println(opponent.getUsername() + "’s deck is invalid");
            return;
        }
        if (round != 1 || round != 3) {
            System.out.println("number of rounds is not supported");
            return;
        }
        self.setGameBoard(new GameBoard(self.getActiveDeck(), 1000));
        opponent.setGameBoard(new GameBoard(opponent.getActiveDeck(), 1000));
        duelInProccess = true;

    }

//    public static Card cardSelection(Player self, Player opponent, String input) {
//        re
//    }

    public static Matcher givePatternTakeMatcher(String input, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        matcher.find();
        return matcher;
    }

    public static int finalCardSelection(String input, Player self, Player opponent, BattleWave battleWave) {
        Card card;
        String[] patterns = selectionPatterns();
        int part = doesHaveSelectionPatterns(input);
        switch (part) {
            case 0:
                Matcher matcher = givePatternTakeMatcher(input, patterns[0]);
                int number = Integer.parseInt(matcher.group(1));
                if (number > 5 || number < 1)
                    return 1;
                if (self.getGameBoard().getMounsterField().containsKey(number)) {
                    card = self.getGameBoard().getMounsterField().get(number);
                    return 2;
                } else return 3;
            case 1:
                matcher = givePatternTakeMatcher(input, patterns[1]);
                number = Integer.parseInt(matcher.group(1));
                if (number > 5 || number < 1)
                    return 1;
                if (opponent.getGameBoard().getMounsterField().containsKey(number)) {
                    card = opponent.getGameBoard().getMounsterField().get(number);
                    battleWave.selecting(card);
                    return 2;
                } else return 3;
            case 2:
                matcher = givePatternTakeMatcher(input, patterns[2]);
                number = Integer.parseInt(matcher.group(1));
                if (number > 5 || number < 1)
                    return 1;
                if (opponent.getGameBoard().getMounsterField().containsKey(number)) {
                    card = opponent.getGameBoard().getMounsterField().get(number);
                    battleWave.selecting(card);
                    return 2;
                } else return 3;
            case 3:
                matcher = givePatternTakeMatcher(input, patterns[3]);
                number = Integer.parseInt(matcher.group(1));
                if (number > 5 || number < 1)
                    return 1;
                if (self.getGameBoard().getSpelltrapField().containsKey(number)) {
                    battleWave.selecting(self.getGameBoard().getMounsterField().get(number));
                    return 2;
                } else return 3;
            case 4:
                matcher = givePatternTakeMatcher(input, patterns[4]);
                number = Integer.parseInt(matcher.group(1));
                if (number > 5 || number < 1)
                    return 1;
                if (opponent.getGameBoard().getSpelltrapField().containsKey(number)) {
                    battleWave.selecting(opponent.getGameBoard().getMounsterField().get(number));
                    return 2;
                } else return 3;
            case 5:
                matcher = givePatternTakeMatcher(input, patterns[5]);
                number = Integer.parseInt(matcher.group(1));
                if (number > 5 || number < 1)
                    return 1;
                if (opponent.getGameBoard().getSpelltrapField().containsKey(number)) {
                    battleWave.selecting(opponent.getGameBoard().getMounsterField().get(number));
                    return 2;
                } else return 3;
            case 6:
                if (self.getGameBoard().getFieldZoneCard().size() == 0)
                    return 3;
                battleWave.selecting(self.getGameBoard().getFieldZoneCard().get(0));
                return 2;
            case 7:
            case 8:
                if (opponent.getGameBoard().getFieldZoneCard().size() == 0)
                    return 3;
                battleWave.selecting(opponent.getGameBoard().getFieldZoneCard().get(0));
                return 2;
            case 9:
                matcher = givePatternTakeMatcher(input, patterns[9]);
                number = Integer.parseInt(matcher.group(1));
                if (number < 1 || number > self.getGameBoard().getNumberOfInHandCard())
                    return 1;
                battleWave.selecting(self.getGameBoard().getInHandCard().get(number));
                return 2;
            default:
                return 0;
        }


    }

    public static int doesHaveSelectionPatterns(String input) {
        String[] patterns = selectionPatterns();
        for (int i = 0; i < patterns.length; i++) {
            Matcher matcher = Pattern.compile(patterns[i]).matcher(input);
            if (matcher.find())
                return i;
        }
        return -1;
    }

    public static String[] selectionPatterns() {
        String p1 = "^select --monster ([\\d]+)$";
        String p2 = "^select --monster --opponent ([\\d]+)$";
        String p3 = "^select --opponent --monster ([\\d]+)$";
        String p4 = "^select --spell ([\\d]+)$";
        String p5 = "^select --spell --opponent ([\\d]+)$";
        String p6 = "^select --opponent --spell ([\\d]+)$";
        String p7 = "^select --field$";
        String p8 = "^select --field --opponent$";
        String p9 = "^select --opponent --field$";
        String p10 = "^select --hand ([\\d]+)$";
        String[] patterns = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
        return patterns;
    }


    public static void main(String[] args) {
        Player player = new Player("amir", "as", 0, "m", 0);
        Deck deck = Deck.getDeckByName("amir");
        System.out.println(deck.getName());
        deck.addCardToMainDeck("div");
        deck.addCardToMainDeck("div");
        deck.addCardToMainDeck("sam");
        deck.updateFile();
        // Gameboard gameboard = new Gameboard("amir",100);
        // player.setGameboard(gameboard);
        showGameBoard(player, player);
    }
}
