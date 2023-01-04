import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckConnection {

    public static void Check(JFrame relativeWindow){
        JFrame changeitem = new JFrame("连接检查");
        changeitem.setSize(500, 200);

        changeitem.setLocationRelativeTo(relativeWindow);
        changeitem.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //GridLayout layout = new GridLayout(3, 1);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,10));


        JLabel l0 = new JLabel();
        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();

        Connection conn = null;
        Statement stmt = null;
        int rs;

        try {
            long start = System.currentTimeMillis();

            conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/sssql01",
                    "SSSQL01", "Yyq132456");
            long end = System.currentTimeMillis();
            //System.out.println(conn);
            //System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");
            l0.setText("连接位置：杨业卿的云端服务器 地址：mysql://118.31.60.105:3306/sssql01");
            l1.setText(String.valueOf(conn));
            l2.setText("建立连接耗时： " + (end - start) + "ms 毫秒");
            l3.setText("连接成功");


            stmt = conn.createStatement();

        }catch (SQLException d) {
            l1.setText("连接失败");
            d.printStackTrace();
        }


        panel.add(l0);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        changeitem.setContentPane(panel);
        changeitem.setVisible(true);


    }
}
