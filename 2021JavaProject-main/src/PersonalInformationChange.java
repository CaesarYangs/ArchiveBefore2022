import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class PersonalInformationChange {

    public static void PIChangeWindow(JFrame relativeWindow){
        JFrame PICW = new JFrame("修改登录密码");
        PICW.setSize(1000, 500);

        PICW.setLocationRelativeTo(relativeWindow);
        PICW.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());


        JLabel l1 = new JLabel();
        l1.setText("新密码");
        JLabel l2 = new JLabel();
        l2.setText("确认密码");
        JButton b1 = new JButton();
        b1.setText("修改");

        final JTextField i1 = new JTextField(10);
        i1.setFont(new Font(null, Font.PLAIN, 20));
        final JTextField i2 = new JTextField(10);
        i2.setFont(new Font(null, Font.PLAIN, 20));

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                Statement stmt = null;
                int rs;

                String u1 = i1.getText();
                String u2 = i2.getText();

                if(u2.equals(u1)){
                    try {
                        long start = System.currentTimeMillis();

                        conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/sssql01",
                                "SSSQL01", "Yyq132456");
                        long end = System.currentTimeMillis();
                        System.out.println(conn);
                        System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");


                        stmt = conn.createStatement();

                        rs = stmt.executeUpdate("UPDATE `sssql01`.`Users` SET `Password` = "+"'"+u1+"'"+" WHERE `Username` = "+"'"+LoginWindow.Username+"'");
                        JOptionPane.showMessageDialog(
                                PICW,
                                "修改成功",
                                "提示",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        PICW.dispose();

                    }catch (SQLException d) {
                        d.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(
                            PICW,
                            "两次输入密码不一致",
                            "提示",
                            JOptionPane.WARNING_MESSAGE
                    );
                }



            }
        });





        panel.add(l1);
        panel.add(i1);
        panel.add(l2);
        panel.add(i2);
        panel.add(b1);



        PICW.setContentPane(panel);
        PICW.setVisible(true);


    }
}
