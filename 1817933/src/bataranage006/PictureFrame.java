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
    private Coordinates coordinates;
    public void drawGrid(Graphics g) {
      for (int are = 0; are < 7; are++) {
        for (int see = 0; see < 8; see++) {
          coordinates = new Coordinates();
          coordinates.setX_cordinate(30 + see * 20);
          coordinates.setY_cordinate(30 + are * 20);
          coordinates.setDiameter( 20);
          coordinates.setNum(master.grid[are][see]);
          drawDigitGivenCentre(g, coordinates, Color.BLACK);
        }
      }
    }

    public void drawHeadings(Graphics g) {
      for (int are = 0; are < 7; are++) {
        fillDigitGivenCentre(g, 10, 30 + are * 20, 20, are+1);
      }

      for (int see = 0; see < 8; see++) {
        fillDigitGivenCentre(g, 30 + see * 20, 10, 20, see+1);
      }
    }

    public void drawDomino(Graphics g, Domino d) {
      Coordinates coordinates1,coordinates2;
      if (d.placed) {
        int y = Math.min(d.ly, d.hy);
        int x = Math.min(d.lx, d.hx);
        int w = Math.abs(d.lx - d.hx) + 1;
        int h = Math.abs(d.ly - d.hy) + 1;
        g.setColor(Color.WHITE);
        g.fillRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
        g.setColor(Color.RED);
        g.drawRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);

        coordinates1 = new Coordinates();
        coordinates1.setX_cordinate(30 + d.hx * 20);
        coordinates1.setY_cordinate(30 + d.hy * 20);
        coordinates1.setDiameter( 20);
        coordinates1.setNum(d.high);
        //refactoredDrawDigitCenter(g,coordinates1);

        coordinates2 = new Coordinates();
        coordinates2.setX_cordinate(30 + d.lx * 20);
        coordinates2.setY_cordinate(30 + d.ly * 20);
        coordinates2.setDiameter( 20);
        coordinates2.setNum(d.low);
       // refactoredDrawDigitCenter(g,coordinates2);

        drawDigitGivenCentre(g,coordinates1 ,Color.BLUE);
        drawDigitGivenCentre(g, coordinates2, Color.BLUE);
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

    void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      int radius = diameter / 2;
      g.setColor(Color.GREEN);
      g.fillOval(x - radius, y - radius, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);

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
      return new Dimension(202, 182);
    }

  }


}
