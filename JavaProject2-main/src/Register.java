import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends MainWindow{

    public static void Register(JFrame relativeWindow){
        JFrame reg = new JFrame("注册");
        reg.setSize(1000, 500);

        reg.setLocationRelativeTo(relativeWindow);
        reg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());


        JLabel l1 = new JLabel();
        l1.setText("用户名");
        JLabel l2 = new JLabel();
        l2.setText("密码");
        JLabel l3 = new JLabel();
        l3.setText("确认密码");
        JButton b1 = new JButton();
        b1.setText("注册");

        final JTextField i0 = new JTextField(10);
        i0.setFont(new Font(null, Font.PLAIN, 20));
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

                        conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                                "taskssql", "Yyq132456");
                        long end = System.currentTimeMillis();
                        System.out.println(conn);
                        System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");


                        stmt = conn.createStatement();

                        rs = stmt.executeUpdate("INSERT INTO `taskssql`.`Users` (`Username`, `Password`) VALUES ('"+i0.getText()+"','"+u2+"')");
                        JOptionPane.showMessageDialog(
                                reg,
                                "注册成功 请登录",
                                "提示",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        reg.dispose();

                    }catch (SQLException d) {
                        d.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(
                            reg,
                            "两次输入密码不一致",
                            "提示",
                            JOptionPane.WARNING_MESSAGE
                    );
                }



            }
        });











        panel.add(l1);
        panel.add(i0);
        panel.add(l2);
        panel.add(i1);
        panel.add(l3);
        panel.add(i2);
        panel.add(b1);



        reg.setContentPane(panel);
        reg.setVisible(true);


    }

}
