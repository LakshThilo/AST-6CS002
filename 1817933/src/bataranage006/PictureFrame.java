package bataranage006;
import java.awt.*;

import javax.swing.*;
/**
 * @author Dr Kevan Buckley, maintained by Lakshitha Bataranage
 * @version 20.01, 2020
 */

public class PictureFrame {

  //public int[] reroll = null;
  public Main master = null;
  public DominoPanel dp;

  public void PictureFrame(Main sf) {
    master = sf;
    if (dp == null) {
      JFrame f = new JFrame("Abominodo");
      dp = new DominoPanel();
      f.setContentPane(dp);
      f.pack();
      f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      f.setVisible(true);
    }
  }

  public void reset() {
    // TODO Auto-generated method stub

  }

  class DominoPanel extends JPanel {

    private static final long serialVersionUID = 4190229282411119364L;
    private Coordinates coordinates,coordinates1;
    private static final int MAX_ROW = 7;
    private static final int MAX_COLUMN = 8;
    private static final int CONST_30 = 30;
    private static final int CONST_20 = 20;
    private static final int CONST_10 = 10;
    private static final int APP_WINDOW_WIDTH = 202;
    private static final int APP_WINDOW_HEIGHT = 182;


    public void drawGrid(Graphics g) {
      for (int are = 0; are < MAX_ROW; are++) {
        for (int see = 0; see < MAX_COLUMN; see++) {
          coordinates = new Coordinates();
          coordinates.setX_cordinate(CONST_30 + see * CONST_20);
          coordinates.setY_cordinate(CONST_30 + are * CONST_20);
          coordinates.setDiameter(CONST_20);
          coordinates.setNum(master.grid[are][see]);
          drawDigitGivenCentre(g, coordinates, Color.BLACK);
        }
      }
    }

    public void drawHeadings(Graphics g) {


      for (int are = 0; are < MAX_ROW; are++) {
        coordinates = new Coordinates();
        coordinates.setX_cordinate(CONST_10);
        coordinates.setY_cordinate(CONST_30 + are * CONST_20);
        coordinates.setDiameter(CONST_20);
        coordinates.setNum(are+1);
        //fillDigitGivenCentre(g, 10, 30 + are * 20, 20, are+1);
        fillDigitGivenCentre(g, coordinates);
      }

      for (int see = 0; see < MAX_COLUMN; see++) {
        coordinates1 = new Coordinates();
        coordinates1.setX_cordinate(CONST_30 + see * CONST_20);
        coordinates1.setY_cordinate(CONST_10);
        coordinates1.setDiameter(CONST_20);
        coordinates1.setNum(see+1);
        //fillDigitGivenCentre(g, 30 + see * 20, 10, 20, see+1);
        fillDigitGivenCentre(g, coordinates1);
      }
    }

    public void drawDomino(Graphics g, Domino d) {

      //Coordinates coordinates1,coordinates2;

      if (d.placed) {
        int y = Math.min(d.ly, d.hy);
        int x = Math.min(d.lx, d.hx);
        int w = Math.abs(d.lx - d.hx) + 1;
        int h = Math.abs(d.ly - d.hy) + 1;
        g.setColor(Color.WHITE);
        g.fillRect(CONST_20 + x * CONST_20, CONST_20 + y * CONST_20, w * CONST_20, h * CONST_20);
        g.setColor(Color.RED);
        g.drawRect(CONST_20 + x * CONST_20, CONST_20 + y * CONST_20, w * CONST_20, h * CONST_20);

        coordinates = new Coordinates();
        coordinates.setX_cordinate(CONST_30 + d.hx * CONST_20);
        coordinates.setY_cordinate(CONST_30 + d.hy * CONST_20);
        coordinates.setDiameter( CONST_20);
        coordinates.setNum(d.high);
        //refactoredDrawDigitCenter(g,coordinates1);

        coordinates1 = new Coordinates();
        coordinates1.setX_cordinate(CONST_30 + d.lx * CONST_20);
        coordinates1.setY_cordinate(CONST_30 + d.ly * CONST_20);
        coordinates1.setDiameter( CONST_20);
        coordinates1.setNum(d.low);
       // refactoredDrawDigitCenter(g,coordinates2);

        drawDigitGivenCentre(g,coordinates ,Color.BLUE);
        drawDigitGivenCentre(g, coordinates1, Color.BLUE);
      }
    }

    void drawDigitGivenCentre(Graphics g, Coordinates coordinates, Color c) {
      g.setColor(c);
      refactoredDrawDigitCenter(g,coordinates);
      //g.setColor(c);

    }

    private void refactoredDrawDigitCenter(Graphics g, Coordinates c) {
      int xCoordinate = c.getX_cordinate();
      int yCoordinate = c.getY_cordinate();
      int diameter = c.getDiameter();
      int num = c.getNum();

      int radius = diameter / 2;
      //g.setColor(Color.BLUE);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(num);
      g.drawString(txt, xCoordinate - fm.stringWidth(txt) / 2, yCoordinate + fm.getMaxAscent() / 2);

    }


  /*  void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      int radius = diameter / 2;
      g.setColor(Color.BLACK);
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      // convert the string to an integer
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }
  void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n, Color c) {
      int radius = diameter / 2;
      g.setColor(c);
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }*/

    void fillDigitGivenCentre(Graphics g, Coordinates c) {
      int xCoordinate = c.getX_cordinate();
      int yCoordinate = c.getY_cordinate();
      int diameter = c.getDiameter();
      int num = c.getNum();

      int radius = diameter / 2;
      g.setColor(Color.GREEN);
      g.fillOval(xCoordinate - radius, yCoordinate - radius, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(xCoordinate - radius, yCoordinate - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(num);
      g.drawString(txt, xCoordinate - fm.stringWidth(txt) / 2, yCoordinate + fm.getMaxAscent() / 2);

    }

    protected void paintComponent(Graphics g) {
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, getWidth(), getHeight());

    /*  // numbaz(g);
      //
      // if (master!=null && master.orig != null) {
      // drawRoll(g, master.orig);
      // }
      // if (reroll != null) {
      // drawReroll(g, reroll);
      // }
      //
      // drawGrid(g);*/
      Location l = new Location(1,2);

      if (master.mode == 1) {
        l.drawGridLines(g);
        drawHeadings(g);
        drawGrid(g);
        master.drawGuesses(g);
      }
      if (master.mode == 0) {
        l.drawGridLines(g);
        drawHeadings(g);
        drawGrid(g);
        master.drawDominoes(g);
      }
    }

    public Dimension getPreferredSize() {
      // the application window always prefers to be 202x182
      return new Dimension(APP_WINDOW_WIDTH, APP_WINDOW_HEIGHT);
    }

  }


}
