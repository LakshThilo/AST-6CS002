package bataranage006;
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

  public static InetAddress getIPAddress() {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    do {
      try {
        String[] chunks = r.readLine().split("\\.");
        byte[] data = { Byte.parseByte(chunks[0]),Byte.parseByte(chunks[1]),Byte.parseByte(chunks[2]),Byte.parseByte(chunks[3])};
        return Inet4Address.getByAddress(data);
      } catch (Exception e) {
      }
    } while (true);
  }

}
