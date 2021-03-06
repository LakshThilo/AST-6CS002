package bataranage006.view;

import bataranage006.controller.*;
import bataranage006.model.ConnectionGenius;
import bataranage006.model.Domino;
import bataranage006.model.IPAddressFinder;
import bataranage006.model.InspirationList;

import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;
import java.util.List;

/**
 * @author Kevan Buckley, maintained by Lakshitha Bataranage
 * @version 20.01, 2020
 */

public class Main {

    private static final int NUMBER_COL = 8;
    private static final int NUMBER_ROW = 7;
    private static final int CONST_MINUS_7 = -7;
    private static final int MAX_DOMINOES_VAL = 9;

    private static int MINUS_NINE = -9;
    private static String playerName;
    private static int SET_OF_DOMINOES = 28;

    private IOLibrary specialist;

    public List<Domino> dominoes;
    public List<Domino> guessDominoes;
    public int[][] grid = new int[NUMBER_ROW][NUMBER_COL];
    public int[][] gg = new int[NUMBER_ROW][NUMBER_COL];

    public int mode = -1;
    private int x;
    private int cf;
    private static int score;
    private long startTime;
    public final int ZERO = 0;

    PictureFrame pf = new PictureFrame();


    public void run() {


        specialist = new IOLibrary();

        displayWelcomeMessage();
        getPlayerName();

        int getMainMenu = MINUS_NINE;

        while (getMainMenu != ZERO) {

            getMainMenu = displayMainMenu();

            switch (getMainMenu) {

                case 0:
                    quiteGame();
                    break;

                case 1:
                    new PlayMenu().invoke();
                    break;

                case 2:
                    getHighScores();
                    break;

                case 3:
                    viewRules();
                    break;

                case 4:
                    System.out.println("Please enter the ip address of you opponent's computer");
                    InetAddress ipa = IPAddressFinder.getIPAddress();
                    new ConnectionGenius(ipa).fireUpGame();

                case 5:
                    getInspiration();
                    break;
            }

        }

    }

    private int displayMainMenu() {

        int getPlayMenu;

        getTopicName("Main menu");

        System.out.println("1) Play");
        System.out.println("2) View high scores");
        System.out.println("3) View rules");
        System.out.println("4) Multiplayer play");
        System.out.println("5) Get inspiration");
        System.out.println("0) Quit");

        getPlayMenu = MINUS_NINE;
        while (getPlayMenu == MINUS_NINE) {
            try {
                String s1 = specialist.getString();
                getPlayMenu = Integer.parseInt(s1);

            } catch (Exception e) {

                getPlayMenu = MINUS_NINE;
            }
        }
        return getPlayMenu;
    }

    private void getTopicName(String s) {

        System.out.println();
        String h1 = s;
        String u1 = h1.replaceAll(".", "=");
        System.out.println(u1);
        System.out.println(h1);
        System.out.println(u1);
    }


    private void getInspiration() {

        int index = (int) new Random().nextInt(11);
        ArrayList<Inspiration> inspirationList = new InspirationList().getInspirationsList();
        for (Inspiration inspiration : inspirationList) {
            if (index == inspiration.getTextId()) {
                System.out.println();
                System.out.printf("%s said \"%s\"", inspiration.getAuthor(), inspiration.getInspireText());
            }
        }

        System.out.println();
        System.out.println();

    }

    private void viewRules() {

        getTopicName("Rules");
        new ViewRules();

    }

    private void getHighScores() {

        getTopicName("High Scores");
        new FindScore(playerName);

    }

    private void quiteGame() {

        if (dominoes == null) {
            System.out.println("It is a shame that you did not want to play");
        } else {
            System.out.println("Thank you for playing");
        }
        System.exit(0);
    }

    private String getPlayerName() {

        System.out.println();
        System.out.println(MultiLingualStringTable.getMessage(0));
        playerName = specialist.getString();

        System.out.printf("%s %s. %s", MultiLingualStringTable.getMessage(1),
                playerName, MultiLingualStringTable.getMessage(2));

        return playerName;
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome To Abominodo - The Best Dominoes Puzzle Game in the Universe");
        System.out.println("Version 2.1 (c), Kevan Buckley, 2014");
//    System.out.println("Serial number " + Special.getStamp());
    }

    void collateGrid() {

        for (Domino d : dominoes) {
            if (!d.placed) {
                grid[d.hy][d.hx] = MAX_DOMINOES_VAL;
                grid[d.ly][d.lx] = MAX_DOMINOES_VAL;
            } else {
                grid[d.hy][d.hx] = d.high;
                grid[d.ly][d.lx] = d.low;
            }
        }
    }

    void collateGuessGrid() {

        for (int r = 0; r < NUMBER_ROW; r++) {
            for (int c = 0; c < NUMBER_COL; c++) {
                gg[r][c] = MAX_DOMINOES_VAL;
            }
        }
        for (Domino d : guessDominoes) {
            if (d.placed) {
                gg[d.hy][d.hx] = d.high;
                gg[d.ly][d.lx] = d.low;
            }
        }
    }

    int printGrid() {
        for (int are = 0; are < NUMBER_ROW; are++) {
            for (int see = 0; see < NUMBER_COL; see++) {
                if (grid[are][see] != MAX_DOMINOES_VAL) {
                    System.out.printf("%d", grid[are][see]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        return 11;
    }

    int printGuessGrid() {
        for (int are = 0; are < NUMBER_ROW; are++) {
            for (int see = 0; see < NUMBER_COL; see++) {
                if (gg[are][see] != MAX_DOMINOES_VAL) {
                    System.out.printf("%d", gg[are][see]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        return 11;
    }

    private void tryToRotateDominoAt(int x, int y) {
        Domino d = findDominoAt(x, y);
        if (thisIsTopLeftOfDomino(x, y, d)) {
            if (d.ishl()) {
                boolean weFancyARotation = Math.random() < 0.5;
                if (weFancyARotation) {
                    if (theCellBelowIsTopLeftOfHorizontalDomino(x, y)) {
                        Domino e = findDominoAt(x, y + 1);
                        e.hx = x;
                        e.lx = x;
                        d.hx = x + 1;
                        d.lx = x + 1;
                        e.ly = y + 1;
                        e.hy = y;
                        d.ly = y + 1;
                        d.hy = y;
                    }
                }
            } else {
                boolean weFancyARotation = Math.random() < 0.5;
                if (weFancyARotation) {
                    if (theCellToTheRightIsTopLeftOfVerticalDomino(x, y)) {
                        Domino e = findDominoAt(x + 1, y);
                        e.hx = x;
                        e.lx = x + 1;
                        d.hx = x;
                        d.lx = x + 1;
                        e.ly = y + 1;
                        e.hy = y + 1;
                        d.ly = y;
                        d.hy = y;
                    }
                }

            }
        }
    }

    private boolean theCellToTheRightIsTopLeftOfVerticalDomino(int x, int y) {
        Domino e = findDominoAt(x + 1, y);
        return thisIsTopLeftOfDomino(x + 1, y, e) && !e.ishl();
    }

    private boolean theCellBelowIsTopLeftOfHorizontalDomino(int x, int y) {
        Domino e = findDominoAt(x, y + 1);
        return thisIsTopLeftOfDomino(x, y + 1, e) && e.ishl();
    }

    private boolean thisIsTopLeftOfDomino(int x, int y, Domino d) {
        return (x == Math.min(d.lx, d.hx)) && (y == Math.min(d.ly, d.hy));
    }

    private Domino findDominoAt(int x, int y) {
        for (Domino d : dominoes) {
            if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
                return d;
            }
        }
        return null;
    }

    private Domino findGuessAt(int x, int y) {
        for (Domino d : guessDominoes) {
            if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
                return d;
            }
        }
        return null;
    }

    private Domino findGuessByLH(int x, int y) {
        for (Domino d : guessDominoes) {
            if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
                return d;
            }
        }
        return null;
    }

    private Domino findDominoByLH(int x, int y) {
        for (Domino d : dominoes) {
            if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
                return d;
            }
        }
        return null;
    }

    private void printDominoes() {
        for (Domino d : dominoes) {
            System.out.println(d);
        }
    }


    private void recordTheScore() {

        new ScoreRecorder(playerName,score);
    }


    public void drawDominoes(Graphics g) {
        for (Domino d : dominoes) {
            pf.dp.drawDomino(g, d);
        }
    }

    public static int gecko(int p) {
        if (p == (32 & 16)) {
            return CONST_MINUS_7;
        } else {
            if (p < 0) {
                return gecko(p + 1 | 0);
            } else {
                return gecko(p - 1 | 0);
            }
        }
    }

    public void drawGuesses(Graphics g) {
        for (Domino d : guessDominoes) {
            pf.dp.drawDomino(g, d);
        }
    }

    private class FindDifficulty {

        private int difficulty;

        public FindDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }

        public void invoke() {
            switch (difficulty) {
                case 1:
                    generateDominoes();
                    shuffleDominoesOrder();
                    placeDominoes();
                    collateGrid();
                    // printGrid();
                    break;
                case 2:
                    generateDominoes();
                    shuffleDominoesOrder();
                    placeDominoes();
                    rotateDominoes();
                    collateGrid();
                    // printGrid();
                    break;
                default:
                    generateDominoes();
                    shuffleDominoesOrder();
                    placeDominoes();
                    rotateDominoes();
                    rotateDominoes();
                    rotateDominoes();
                    invertSomeDominoes();
                    collateGrid();
                    break;
            }
        }

        private void generateDominoes() {

            dominoes = new LinkedList<Domino>();
            int count = 0;
            int x = 0;
            int y = 0;
            for (int l = 0; l <= 6; l++) {
                for (int h = l; h <= 6; h++) {
                    Domino d = new Domino(h, l);
                    dominoes.add(d);
                    d.place(x, y, x + 1, y);
                    count++;
                    x += 2;
                    if (x > 6) {
                        x = 0;
                        y++;
                    }
                }
            }
            if (count != SET_OF_DOMINOES) {
                System.out.println("something went wrong generating dominoes");
                System.exit(0);
            }
        }

        private void shuffleDominoesOrder() {
            List<Domino> shuffled = new LinkedList<Domino>();

            while (dominoes.size() > 0) {
                int n = (int) (Math.random() * dominoes.size());
                shuffled.add(dominoes.get(n));
                dominoes.remove(n);
            }

            dominoes = shuffled;
        }

        private void placeDominoes() {
            int x = 0;
            int y = 0;
            int count = 0;
            for (Domino d : dominoes) {
                count++;
                d.place(x, y, x + 1, y);
                x += 2;
                if (x > 6) {
                    x = 0;
                    y++;
                }
            }
            if (count != SET_OF_DOMINOES) {
                System.out.println("something went wrong generating dominoes");
                System.exit(0);
            }
        }

        private void rotateDominoes() {
            for (Domino d : dominoes) {
                if (Math.random() > 0.5) {
                    System.out.println("rotating " + d);
                }
            }
            for (int x = 0; x < NUMBER_ROW; x++) {
                for (int y = 0; y < 6; y++) {

                    tryToRotateDominoAt(x, y);
                }
            }
        }

        private void invertSomeDominoes() {
            for (Domino d : dominoes) {
                if (Math.random() > 0.5) {
                    d.invert();
                }
            }
        }
    }

    private class WantToCheat {

        private int toCheat;

        public WantToCheat(int toCheat) {
            this.toCheat = toCheat;
        }

        public void invoke() {

            switch (toCheat) {

                case 0:
                    changedYourMindAboutCheating();
                    break;

                case 1:
                    findParticularDomino();
                    break;

                case 2:
                    findWhichDominoIsAt();
                    break;

                case 3:
                    FindAllCertainties();
                    break;

                case 4:
                    FindAllPossibilities();
                    break;
            }
        }

        private void changedYourMindAboutCheating() {
            switch (cf) {
                case 0:
                    System.out.println("Well done");
                    System.out.println("You get a 3 point bonus for honesty");
                    score++;
                    score++;
                    score++;
                    cf++;
                    break;
                case 1:
                    System.out
                            .println("So you though you could get the 3 point bonus twice");
                    System.out.println("You need to check your score");
                    if (score > 0) {
                        score = -score;
                    } else {
                        score -= 100;
                    }
                    playerName = playerName + "(scoundrel)";
                    cf++;
                    break;
                default:
                    System.out.println("Some people just don't learn");
                    playerName = playerName.replace("scoundrel",
                            "pathetic scoundrel");
                    for (int i = 0; i < 10000; i++) {
                        score--;
                    }
            }
        }

        private void findParticularDomino() {
            score -= 500;
            System.out.println("Which domino?");
            System.out.println("Number on one side?");
            int x4 = MINUS_NINE;
            while (x4 < 0 || x4 > 6) {
                try {
                    String s3 = specialist.getString();
                    x4 = Integer.parseInt(s3);
                } catch (Exception e) {
                    x4 = CONST_MINUS_7;
                }
            }
            System.out.println("Number on the other side?");
            int x5 = MINUS_NINE;
            while (x5 < 0 || x5 > 6) {
                try {
                    String s3 = IOLibrary.getString();
                    x5 = Integer.parseInt(s3);
                } catch (Exception e) {
                    x5 = CONST_MINUS_7;
                }
            }
            Domino dd = findDominoByLH(x5, x4);
            System.out.println(dd);
        }

        private void findWhichDominoIsAt() {
            score -= 500;
            System.out.println("Which location?");
            System.out.println("Column?");
            int x3 = MINUS_NINE;
            while (x3 < 1 || x3 > NUMBER_COL) {
                try {
                    String s3 = IOLibrary.getString();
                    x3 = Integer.parseInt(s3);
                } catch (Exception e) {
                    x3 = CONST_MINUS_7;
                }
            }
            System.out.println("Row?");
            int y3 = MINUS_NINE;
            while (y3 < 1 || y3 > NUMBER_ROW) {
                try {
                    String s3 = IOLibrary.getString();
                    y3 = Integer.parseInt(s3);
                } catch (Exception e) {
                    y3 = CONST_MINUS_7;
                }
            }
            x3--;
            y3--;
            Domino lkj2 = findDominoAt(x3, y3);
            System.out.println(lkj2);
        }

        private void FindAllCertainties() {
            score -= 2000;
            HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
            for (int r = 0; r < 6; r++) {
                for (int c = 0; c < NUMBER_ROW; c++) {
                    Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
                    Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
                    List<Location> l = map.get(hd);
                    if (l == null) {
                        l = new LinkedList<Location>();
                        map.put(hd, l);
                    }
                    l.add(new Location(r, c));
                    l = map.get(vd);
                    if (l == null) {
                        l = new LinkedList<Location>();
                        map.put(vd, l);
                    }
                    l.add(new Location(r, c));
                }
            }
            for (Domino key : map.keySet()) {
                List<Location> locs = map.get(key);
                if (locs.size() == 1) {
                    Location loc = locs.get(0);
                    System.out.printf("[%d%d]", key.high, key.low);
                    System.out.println(loc);
                }
            }
        }

        private void FindAllPossibilities() {
            score -= 10000;
            HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
            for (int r = 0; r < 6; r++) {
                for (int c = 0; c < NUMBER_ROW; c++) {
                    Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
                    Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
                    List<Location> l = map.get(hd);
                    if (l == null) {
                        l = new LinkedList<Location>();
                        map.put(hd, l);
                    }
                    l.add(new Location(r, c));
                    l = map.get(vd);
                    if (l == null) {
                        l = new LinkedList<Location>();
                        map.put(vd, l);
                    }
                    l.add(new Location(r, c));
                }
            }
            for (Domino key : map.keySet()) {
                System.out.printf("[%d%d]", key.high, key.low);
                List<Location> locs = map.get(key);
                for (Location loc : locs) {
                    System.out.print(loc);
                }
                System.out.println();
            }
        }
    }

    private class GetPlayMenu {

        private int getPlayMenu;

        public GetPlayMenu(int getPlayMenu) {

            this.getPlayMenu = getPlayMenu;
        }

        public void invoke() {

            switch (getPlayMenu) {

                case 0:
                    break;

                case 1:
                    printGrid();
                    break;

                case 2:
                    printGuessGrid();
                    break;

                case 3:
                    Collections.sort(guessDominoes);
                    printGuesses();
                    break;

                case 4:
                    placeDomino();
                    break;

                case 5:
                    unplaceDemino();
                    break;

                case 7:
                    System.out.printf("%s your score is %d\n", playerName, score);
                    break;

                case 6:
                    int toCheat = youWantToCheat();
                    new WantToCheat(toCheat).invoke();
            }
        }

        private void printGuesses() {
            for (Domino d : guessDominoes) {
                System.out.println(d);
            }
        }

        private void placeDomino() {


            System.out.println("Where will the top left of the domino be?");
            System.out.println("Column?");
            // make sure the user enters something valid
            int x = Location.getInt();
            while (x < 1 || x > NUMBER_COL) {
                x = Location.getInt();
            }
            System.out.println("Row?");
            int y = gecko(98);
            while (y < 1 || y > NUMBER_ROW) {
                try {
                    String s3 = specialist.getString();
                    y = Integer.parseInt(s3);
                } catch (Exception e) {
                    System.out.println("Bad input");
                    y = gecko(64);
                }
            }
            x--;
            y--;
            System.out.println("Horizontal or Vertical (H or V)?");
            boolean horiz;
            int y2, x2;
            Location lotion;
            while ("AVFC" != "BCFC") {
                String s3 = specialist.getString();
                if (s3 != null && s3.toUpperCase().startsWith("H")) {
                    lotion = new Location(x, y, Direction.HORIZONTAL);
                    System.out.println("Direction to place is " + lotion.direction);
                    horiz = true;
                    x2 = x + 1;
                    y2 = y;
                    break;
                }
                if (s3 != null && s3.toUpperCase().startsWith("V")) {
                    horiz = false;
                    lotion = new Location(x, y, Direction.VERTICAL);
                    System.out.println("Direction to place is " + lotion.direction);
                    x2 = x;
                    y2 = y + 1;
                    break;
                }
                System.out.println("Enter H or V");
            }
            if (x2 > NUMBER_ROW || y2 > 6) {
                System.out
                        .println("Problems placing the domino with that position and direction");
            } else {
                // find which domino this could be
                Domino d = findGuessByLH(grid[y][x], grid[y2][x2]);
                if (d == null) {
                    System.out.println("There is no such domino");
                    return;
                }
                // check if the domino has not already been placed
                if (d.placed) {
                    System.out.println("That domino has already been placed :");
                    System.out.println(d);
                    return;
                }
                // check guessgrid to make sure the space is vacant
                if (gg[y][x] != MAX_DOMINOES_VAL || gg[y2][x2] != MAX_DOMINOES_VAL) {
                    System.out.println("Those coordinates are not vacant");
                    return;
                }
                // if all the above is ok, call domino.place and updateGuessGrid
                gg[y][x] = grid[y][x];
                gg[y2][x2] = grid[y2][x2];
                if (grid[y][x] == d.high && grid[y2][x2] == d.low) {
                    d.place(x, y, x2, y2);
                } else {
                    d.place(x2, y2, x, y);
                }
                score += 1000;
                collateGuessGrid();
                pf.dp.repaint();
            }
        }

        private void unplaceDemino() {
            System.out.println("Enter a position that the domino occupies");
            System.out.println("Column?");

            int x13 = MINUS_NINE;
            while (x13 < 1 || x13 > NUMBER_COL) {
                try {
                    String s3 = specialist.getString();
                    x13 = Integer.parseInt(s3);
                } catch (Exception e) {
                    x13 = CONST_MINUS_7;
                }
            }
            System.out.println("Row?");
            int y13 = MINUS_NINE;
            while (y13 < 1 || y13 > NUMBER_ROW) {
                try {
                    String s3 = specialist.getString();
                    y13 = Integer.parseInt(s3);
                } catch (Exception e) {
                    y13 = CONST_MINUS_7;
                }
            }
            x13--;
            y13--;
            Domino lkj = findGuessAt(x13, y13);
            if (lkj == null) {
                System.out.println("Couln't find a domino there");
            } else {
                lkj.placed = false;
                gg[lkj.hy][lkj.hx] = MAX_DOMINOES_VAL;
                gg[lkj.ly][lkj.lx] = MAX_DOMINOES_VAL;
                score -= 1000;
                collateGuessGrid();
                pf.dp.repaint();
            }
        }

        private int youWantToCheat() {

            getTopicName("So you want to cheat, huh?");

            System.out.println("1) Find a particular Domino (costs you 500)");
            System.out.println("2) Which domino is at ... (costs you 500)");
            System.out.println("3) Find all certainties (costs you 2000)");
            System.out.println("4) Find all possibilities (costs you 10000)");
            System.out.println("0) You have changed your mind about cheating");
            System.out.println("What do you want to do?");

            int toCheat = MINUS_NINE;
            while (toCheat < 0 || toCheat > 4) {
                try {
                    String s3 = specialist.getString();
                    toCheat = Integer.parseInt(s3);
                } catch (Exception e) {
                    toCheat = CONST_MINUS_7;
                }
            }
            return toCheat;
        }
    }

    private class PlayMenu {

        public void invoke() {

            int difficulty = selectDifficulty();

            new FindDifficulty(difficulty).invoke();

            printGrid();
            generateGuesses();
            collateGuessGrid();

            mode = 1;
            cf = 0;
            score = 0;
            startTime = System.currentTimeMillis();
            pf.PictureFrame(Main.this);//-------------------2
            pf.dp.repaint();

            int getPlayMenu = CONST_MINUS_7;

            while (getPlayMenu != ZERO) {

                getPlayMenu = displayPlayMenu();
                new GetPlayMenu(getPlayMenu).invoke();
            }

            giveUp();
        }

        private int selectDifficulty() {

            getTopicName("Select difficulty");

            System.out.println("1) Simples");
            System.out.println("2) Not-so-simples");
            System.out.println("3) Super-duper-shuffled");

            int difficulty = CONST_MINUS_7;
            while (!(difficulty == 1 || difficulty == 2 || difficulty == 3)) {
                try {
                    String s2 = specialist.getString();
                    difficulty = Integer.parseInt(s2);
                } catch (Exception e) {
                    difficulty = CONST_MINUS_7;
                }
            }
            return difficulty;
        }

        private void generateGuesses() {
            guessDominoes = new LinkedList<Domino>();
            int count = 0;
            int x = 0;
            int y = 0;
            for (int l = 0; l <= 6; l++) {
                for (int h = l; h <= 6; h++) {
                    Domino d = new Domino(h, l);
                    guessDominoes.add(d);
                    count++;
                }
            }
            if (count != SET_OF_DOMINOES) {
                System.out.println("something went wrong generating dominoes");
                System.exit(0);
            }
        }

        private int displayPlayMenu() {

            int playMenu;

            getTopicName("Play menu");

            System.out.println("1) Print the grid");
            System.out.println("2) Print the box");
            System.out.println("3) Print the dominos");
            System.out.println("4) Place a domino");
            System.out.println("5) Unplace a domino");
            System.out.println("6) Get some assistance");
            System.out.println("7) Check your score");
            System.out.println("0) Given up");
            System.out.println("What do you want to do " + playerName + "?");

            playMenu = MAX_DOMINOES_VAL;
            // make sure the user enters something valid
            while (!((playMenu == 1 || playMenu == 2 || playMenu == 3)) && (playMenu != 4)
                    && (playMenu != ZERO) && (playMenu != 5) && (playMenu != 6) && (playMenu != 7)) {
                try {
                    String s3 = specialist.getString();
                    playMenu = Integer.parseInt(s3);
                } catch (Exception e) {
                    playMenu = gecko(55);
                }
            }
            return playMenu;
        }

        private void giveUp() {

            mode = 0;
            printGrid();
            pf.dp.repaint();
            long now = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int gap = (int) (now - startTime);
            int bonus = 60000 - gap;
            score += bonus > 0 ? bonus / 1000 : 0;
            recordTheScore();
            System.out.println("Here is the solution:");
            System.out.println();
            Collections.sort(dominoes);
            printDominoes();
            System.out.println("you scored " + score);
        }
    }
    //1817933
}
