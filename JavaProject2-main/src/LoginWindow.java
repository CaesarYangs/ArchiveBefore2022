import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginWindow {
    public static String Username;
    public static String Password;

    public static int Status = new Integer(0);
    public static String id = new String("");




    LoginWindow(){
        Username = "admin";
        Password = "132456";
    }

    public static void LoginW(JFrame relativeWindow) {
        //database try


        Username = "admin";
        Password = "132456";

        JFrame newJFrame = new JFrame("登陆");

        newJFrame.setSize(700, 400);

        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setLocationRelativeTo(relativeWindow);

        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Box hBox01 = Box.createVerticalBox();
        Box user = Box.createHorizontalBox();
        Box pass = Box.createHorizontalBox();
        Box loginbuttons = Box.createHorizontalBox();

        JLabel biglable01 = new JLabel();
        biglable01.setText("系统登陆");

        JLabel label01 = new JLabel();
        label01.setText("用户名");
        //label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        final JTextField textField = new JTextField(8);


        JLabel label02 = new JLabel();
        label02.setText("密码 ");
        final JPasswordField passwordField = new JPasswordField(10);



        JButton login = new JButton("登陆");
        final JButton register = new JButton("注册");


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register.Register(newJFrame);
            }
        });




        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean canlogin = new Boolean(false);



                String u = textField.getText();
                Username = textField.getText();
                String p = new String(passwordField.getPassword());

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    long start = System.currentTimeMillis();

                    conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                            "taskssql", "Yyq132456");
                    long end = System.currentTimeMillis();
                    System.out.println(conn);
                    System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

                    stmt = conn.createStatement();

                    rs = stmt.executeQuery("select * from `Users`");
                    //System.out.println("id\tname\tage\tsex");

                    while (rs.next()){
                        //System.out.print(rs.getString(2)+" "+rs.getString(3));
                        if(rs.getString(1).equals(u) && rs.getString(2).equals(p)){
                            canlogin = true;
                            id = rs.getString(1);
                            break;
                        }
                    }


                }catch (SQLException d) {
                    d.printStackTrace();
                }
                if(canlogin==true) {
                    if(Status==1){
                        MainWindow.MainWindow(newJFrame);
                        newJFrame.dispose();
                    }else {
                        MainWindow.MainWindow(newJFrame);
                        newJFrame.dispose();
                    }


                }else {
                    passwordField.getCursor();
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(
                            newJFrame,
                            "密码错误",
                            "提示",
                            JOptionPane.WARNING_MESSAGE
                    );

                }

            }
        });



        Component hGlue = Box.createHorizontalStrut(20);
        Component vGlue = Box.createVerticalStrut(10);


        user.add(hGlue);
        user.add(label01);
        user.add(textField);
        user.add(hGlue);


        pass.add(hGlue);
        pass.add(label02);
        pass.add(passwordField);
        pass.add(hGlue);

        loginbuttons.add(login);
        loginbuttons.add(register);


        hBox01.add(vGlue);
        hBox01.add(user);
        hBox01.add(vGlue);
        hBox01.add(pass);
        hBox01.add(vGlue);
        hBox01.add(loginbuttons);

        newJFrame.setContentPane(hBox01);
        newJFrame.pack();
        newJFrame.setVisible(true);
    }
}
