package bataranage006.controller;
import bataranage006.model.SpacePlace;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Kevan Buckley, maintained by Lakshitha Bataranage
 * @version 20.01, 2020
 */

public class Location extends SpacePlace {

  private static final int CONST_20 = 20;
  private static final int MAX_WIDTH = 180;
  private static final int MAX_HEIGHT = 160;
  private static final int ROW = 7;
  private static final int COLUMN = 8;

  public int c;
  public int r;
  public DIRECTION d;
  public int tmp;
  public enum DIRECTION {VERTICAL, HORIZONTAL};
  
  public Location(int r, int c) {
    this.r = r;
    this.c = c;
  }

  public Location(int r, int c, DIRECTION d) {    
    this(r,c);
    this.d=d;
  }
  
  public String toString() {
    if(d==null){
      tmp = c + 1;
      return "(" + (tmp) + "," + (r+1) + ")";
    } else {
      tmp = c + 1;
      return "(" + (tmp) + "," + (r+1) + "," + d + ")";
    }
  }
  
  public void drawGridLines(Graphics g) {
    g.setColor(Color.LIGHT_GRAY);
    for (tmp = 0; tmp <= ROW; tmp++) {
      g.drawLine(CONST_20, CONST_20 + tmp * CONST_20, MAX_WIDTH, CONST_20 + tmp * CONST_20);
    }
    for (int see = 0; see <= COLUMN; see++) {
      g.drawLine(CONST_20 + see * CONST_20, CONST_20, CONST_20 + see * CONST_20, MAX_HEIGHT);
    }
  }
  
  public static int getInt() {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    do {
      try {
        return Integer.parseInt(r.readLine());
      } catch (Exception e) {
      }
    } while (true);
  }
}
