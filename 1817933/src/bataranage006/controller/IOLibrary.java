package bataranage006.controller;
import java.io.*;
import java.net.*;
/**
 * This fantastic library contains all I/O code for the entire application.
 * 
 * @author Kevan Buckley, maintained by Lakshitha Bataranage
 * @version 20.01, 2020
 */

public final class IOLibrary {

  public static String getString() {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    do {
      try {
        return r.readLine();
      } catch (Exception e) {
      }
    } while (true);
  }
}
