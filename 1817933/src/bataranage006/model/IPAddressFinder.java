package bataranage006.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;

public class IPAddressFinder {


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
