package bataranage006.view;

import javax.swing.*;
import java.awt.*;

public class ViewRules extends JFrame{


    public  ViewRules(){

       // JFrame f = new JFrame("Rules by Lakshitha Bataranage");
        super("Rules by Lakshitha Bataranage");
        setSize(new Dimension(500, 500));
        JEditorPane w;
        try {
            w = new JEditorPane("http://www.scit.wlv.ac.uk/~in6659/abominodo/");

        } catch (Exception e) {
            w = new JEditorPane("text/plain",
                    "Problems retrieving the rules from the Internet");
        }
        setContentPane(new JScrollPane(w));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
