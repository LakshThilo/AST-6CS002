package bataranage006.controller;
import java.net.InetAddress;
/**
 * @author Kevan Buckley, maintained by Lakshitha Bataranage
 * @version 20.01, 2020
 */

public class ConnectionGenius {

  InetAddress ipa;
  
  public ConnectionGenius(InetAddress ipa) {
    this.ipa = ipa;
  }

  public void fireUpGame() {
    downloadWebVersion();
    connectToWebService();
    awayWeGo();
  }
  
  public void downloadWebVersion(){
    System.out.println("Getting specialised web version.");
    System.out.println("Wait a couple of moments");  
  }
  
  public void connectToWebService() {
    System.out.println("Connecting");
  }
  
  public void awayWeGo(){
    System.out.println("Ready to play");
  }

}
