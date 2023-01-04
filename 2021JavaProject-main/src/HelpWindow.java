import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class HelpWindow {
    public static void Help(JFrame relativeWindow){
        JFrame help = new JFrame("帮助");
        help.setSize(1000, 500);

        help.setLocationRelativeTo(relativeWindow);
        help.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());


        help.setContentPane(panel);
        help.setVisible(true);


    }
}
