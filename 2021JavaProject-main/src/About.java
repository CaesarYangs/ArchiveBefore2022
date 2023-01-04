import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class About {
    public static void About(JFrame relativeWindow){
        JFrame about = new JFrame("关于");
        about.setSize(300, 200);

        about.setLocationRelativeTo(relativeWindow);
        about.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //GridLayout layout = new GridLayout(3, 1);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,10));


        JLabel l0 = new JLabel();
        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();

        l0.setText("毕业设计文档管理系统");
        l1.setText("杨业卿 19080215  设计制作");
        l2.setText("2021.4");


        panel.add(l0);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        about.setContentPane(panel);
        about.setVisible(true);




    }

}
