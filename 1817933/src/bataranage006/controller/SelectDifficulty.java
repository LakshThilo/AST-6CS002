package bataranage006.controller;

import bataranage006.model.Domino;

import java.util.LinkedList;
import java.util.List;

public class SelectDifficulty {


    private static final int MAX_DOMINOES_VAL = 9;
    private static int SET_OF_DOMINOES = 28;
    public List<Domino> dominoes;
    private static final int NUMBER_COL = 8;
    private static final int NUMBER_ROW = 7;
    public int[][] grid = new int[NUMBER_ROW][NUMBER_COL];


    public  SelectDifficulty(List<Domino> dominoes) {

        this.dominoes = dominoes;
        generateDominoes();
        shuffleDominoesOrder();
        placeDominoes();
        collateGrid();
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



    private void invertSomeDominoes() {
        for (Domino d : dominoes) {
            if (Math.random() > 0.5) {
                d.invert();
            }
        }
    }

    public int[][] getGrid() {

        return grid;
    }
}