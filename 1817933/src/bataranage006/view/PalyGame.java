package bataranage006.view;

import bataranage006.controller.*;
import bataranage006.model.Domino;

import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PalyGame {
    int mode = -1;

    private static int CONST_VAL;
    private IOLibrary specialist;
    private static int MINUS_NINE = -9;
    private static int SET_OF_DOMINOES = 28;

    private static final int NUMBER_COL = 8;
    private static final int NUMBER_ROW = 7;
    private static final int CONST_MINUS_7 = -7;
    private static final int MAX_DOMINOES_VAL = 9;

    private int x;
    private static String playerName;
    public List<Domino> dominoes;
    public List<Domino> guessDominoes;
    public int[][] grid = new int[NUMBER_ROW][NUMBER_COL];
    public int[][] gg = new int[NUMBER_ROW][NUMBER_COL];
    int cf;
    int score;
    long startTime;


    PictureFrame pf = new PictureFrame();

    public final int ZERO = 0;
    private Main main;

    public PalyGame(Main main,String playerName){

        playerName = playerName;
        //System.out.println(playerName);
        main = main;


    selectDifficulty();

        int c2 = CONST_MINUS_7;
        while (!(c2 == 1 || c2 == 2 || c2 == 3)) {
            try {
                String s2 = specialist.getString();
                c2 = Integer.parseInt(s2);
            } catch (Exception e) {
                c2 = CONST_MINUS_7;
            }
        }
        grid =  new SelectDifficulty(dominoes).getGrid();

        printGrid();
        generateGuesses();
        collateGuessGrid();
        mode = 1;
        cf = 0;
        score = 0;
        startTime = System.currentTimeMillis();
       // pf.PictureFrame(this); ///////////////////////////// remove to comment
        pf.dp.repaint();
        int c3 = CONST_MINUS_7;
        while (c3 != ZERO) {

            displayPlayMenu();

            c3 = MAX_DOMINOES_VAL;
            // make sure the user enters something valid
            while (!((c3 == 1 || c3 == 2 || c3 == 3)) && (c3 != 4)
                    && (c3 != ZERO) && (c3 != 5) && (c3 != 6) && (c3 != 7)) {
                try {
                    String s3 = specialist.getString();
                    c3 = Integer.parseInt(s3);
                } catch (Exception e) {
                    c3 = gecko(55);
                }
            }

            switch (c3) {
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
                            lotion = new Location(x, y, Location.DIRECTION.HORIZONTAL);
                            System.out.println("Direction to place is " + lotion.d);
                            horiz = true;
                            x2 = x + 1;
                            y2 = y;
                            break;
                        }
                        if (s3 != null && s3.toUpperCase().startsWith("V")) {
                            horiz = false;
                            lotion = new Location(x, y, Location.DIRECTION.VERTICAL);
                            System.out.println("Direction to place is " + lotion.d);
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
                            break;
                        }
                        // check if the domino has not already been placed
                        if (d.placed) {
                            System.out.println("That domino has already been placed :");
                            System.out.println(d);
                            break;
                        }
                        // check guessgrid to make sure the space is vacant
                        if (gg[y][x] != MAX_DOMINOES_VAL || gg[y2][x2] != MAX_DOMINOES_VAL) {
                            System.out.println("Those coordinates are not vacant");
                            break;
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
                    break;
                case 5:
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
                    break;
                case 7:
                    System.out.printf("%s your score is %d\n", playerName, score);
                    break;
                case 6:
                    System.out.println();
                    String h8 = "So you want to cheat, huh?";
                    String u8 = h8.replaceAll(".", "=");
                    System.out.println(u8);
                    System.out.println(h8);
                    System.out.println(u8);
                    System.out.println("1) Find a particular Domino (costs you 500)");
                    System.out.println("2) Which domino is at ... (costs you 500)");
                    System.out.println("3) Find all certainties (costs you 2000)");
                    System.out.println("4) Find all possibilities (costs you 10000)");
                    System.out.println("0) You have changed your mind about cheating");
                    System.out.println("What do you want to do?");
                    int yy = MINUS_NINE;
                    while (yy < 0 || yy > 4) {
                        try {
                            String s3 = specialist.getString();
                            yy = Integer.parseInt(s3);
                        } catch (Exception e) {
                            yy = CONST_MINUS_7;
                        }
                    }
                    switch (yy) {
                        case 0:
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
                                    System.out.println("debugg#2"+playerName);
                                    cf++;
                                    break;
                                default:
                                    System.out.println("Some people just don't learn");
                                    playerName = playerName.replace("scoundrel",
                                            "pathetic scoundrel");
                                    System.out.println("debugg#3"+playerName);
                                    for (int i = 0; i < 10000; i++) {
                                        score--;
                                    }
                            }
                            break;
                        case 1:
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

                            break;
                        case 2:
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
                            break;
                        case 3: {
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
                            break;
                        }

                        case 4: {
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
                            break;
                        }
                    }
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
    private void printDominoes() {
        for (Domino d : dominoes) {
            System.out.println(d);
        }
    }

    private void recordTheScore() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
            String n = playerName.replaceAll(",", "_");
            pw.print(n);
            pw.print(",");
            pw.print(score);
            pw.print(",");
            pw.println(System.currentTimeMillis());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong saving scores");
        }
    }


    private void printGuesses() {
        for (Domino d : guessDominoes) {
            System.out.println(d);
        }
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
    private Domino findGuessByLH(int x, int y) {
        for (Domino d : guessDominoes) {
            if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
                return d;
            }
        }
        return null;
    }


    private Domino findDominoAt(int x, int y) {
        for (Domino d : dominoes) {
            if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
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
    private Domino findGuessAt(int x, int y) {
        for (Domino d : guessDominoes) {
            if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
                return d;
            }
        }
        return null;
    }

    public static int gecko(int p) {
        if (p == (32 & 16)) {
            return CONST_MINUS_7;
        } else {
            if (p < 0) {
                return gecko(p + 1| 0);
            } else {
                return gecko(p - 1 | 0);
            }
        }
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
    private void displayPlayMenu() {
        System.out.println();
        String h5 = "Play menu";
        String u5 = h5.replaceAll(".", "=");
        System.out.println(u5);
        System.out.println(h5);
        System.out.println(u5);
        System.out.println("1) Print the grid");
        System.out.println("2) Print the box");
        System.out.println("3) Print the dominos");
        System.out.println("4) Place a domino");
        System.out.println("5) Unplace a domino");
        System.out.println("6) Get some assistance");
        System.out.println("7) Check your score");
        System.out.println("0) Given up");
        System.out.println("What do you want to do " + playerName + "?");
    }
    private void selectDifficulty() {

        System.out.println();
        String h4 = "Select difficulty";
        String u4 = h4.replaceAll(".", "=");
        System.out.println(u4);
        System.out.println(h4);
        System.out.println(u4);
        System.out.println("1) Simples");
        System.out.println("2) Not-so-simples");
        System.out.println("3) Super-duper-shuffled");

    }


    public void drawDominoes(Graphics g) {
        for (Domino d : dominoes) {
            pf.dp.drawDomino(g, d);
        }
    }


    public void drawGuesses(Graphics g) {
        for (Domino d : guessDominoes) {
            pf.dp.drawDomino(g, d);
        }
    }

}
