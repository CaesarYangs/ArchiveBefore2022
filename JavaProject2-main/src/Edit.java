import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Edit {

    public static void Edit(JFrame relativeWindow){
        JFrame edit = new JFrame("编辑任务");
        edit.setSize(500, 300);

        edit.setLocationRelativeTo(relativeWindow);
        edit.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,10));


        JLabel l1 = new JLabel("任务名:");
        JLabel l2 = new JLabel("截止时间:");
        JLabel l3 = new JLabel("备注:");

        JTextField t1 = new JTextField(10);
        t1.setFont(new Font(null, Font.PLAIN, 20));
        JTextField t2 = new JTextField(10);
        t2.setFont(new Font(null, Font.PLAIN, 20));
        JTextField t3 = new JTextField(10);
        t3.setFont(new Font(null, Font.PLAIN, 20));

        JButton b1 = new JButton();
        b1.setText("修改");

        try {
            ResultSet rcs = null;
            Connection conn = null;
            Statement stmt = null;
            long start = System.currentTimeMillis();

            conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                    "taskssql", "Yyq132456");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

            stmt = conn.createStatement();
            rcs = stmt.executeQuery("SELECT * FROM `taskssql`.`Tasks` WHERE id ="+"'"+MainWindow.selectedId+"'");
            rcs.next();
            t1.setText(rcs.getString(3));
            t2.setText(rcs.getString(5));
            t3.setText(rcs.getString(6));


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

                    conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                            "taskssql", "Yyq132456");
                    long end = System.currentTimeMillis();
                    System.out.println(conn);
                    System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");


                    stmt = conn.createStatement();


                    rs = stmt.executeUpdate("UPDATE `taskssql`.`Tasks` SET `TaskName` = "+"'"+t1.getText()+"'"+" WHERE `id` = "+MainWindow.selectedId);
                    rs = stmt.executeUpdate("UPDATE `taskssql`.`Tasks` SET `DueDate` = "+"'"+t2.getText()+"'"+" WHERE `id` = "+MainWindow.selectedId);
                    rs = stmt.executeUpdate("UPDATE `taskssql`.`Tasks` SET `Notes` = "+"'"+t3.getText()+"'"+" WHERE `id` = "+MainWindow.selectedId);



                    edit.dispose();
                    MainWindow.Mmenu.dispose();
                    MainWindow.MainWindow(edit);

                }catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        });

        panel.add(l1);
        panel.add(t1);
        panel.add(l2);
        panel.add(t2);
        panel.add(l3);
        panel.add(t3);
        panel.add(b1);




        edit.setContentPane(panel);
        edit.setVisible(true);


    }
}
