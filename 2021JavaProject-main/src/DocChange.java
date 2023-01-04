import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class DocChange extends DocManage{
    public static void Docchange(JFrame relativeWindow){
        JFrame docchange = new JFrame("修改毕业设计题目");
        docchange.setSize(1000, 300);

        docchange.setLocationRelativeTo(relativeWindow);
        docchange.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

        JLabel l1 = new JLabel();
        l1.setText("新题目");
        final JTextField textField = new JTextField(30);
        textField.setFont(new Font(null, Font.PLAIN, 20));


        JButton b1 = new JButton();
        b1.setText("提交");





        try {
            ResultSet rcs = null;
            Connection conn = null;
            Statement stmt = null;
            long start = System.currentTimeMillis();

            conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/sssql01",
                    "SSSQL01", "Yyq132456");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

            stmt = conn.createStatement();
            rcs = stmt.executeQuery("SELECT * FROM `sssql01`.`StudentDocs` WHERE id ="+LoginWindow.Username);
            rcs.next();
            textField.setText(rcs.getString(3));


        }catch (SQLException d) {
            d.printStackTrace();
        }




        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                Statement stmt = null;
                int rs;

                try {
                    long start = System.currentTimeMillis();

                    conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/sssql01",
                            "SSSQL01", "Yyq132456");
                    long end = System.currentTimeMillis();
                    System.out.println(conn);
                    System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");


                    stmt = conn.createStatement();

                    String up = textField.getText();
                    rs = stmt.executeUpdate("UPDATE `sssql01`.`StudentDocs` SET `Docs` = "+"'"+up+"'"+" WHERE `id` = "+LoginWindow.Username);


                    docchange.dispose();

                }catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        });




        panel.add(l1);
        panel.add(textField);
        panel.add(b1);


        docchange.setContentPane(panel);
        docchange.setVisible(true);


    }
}
