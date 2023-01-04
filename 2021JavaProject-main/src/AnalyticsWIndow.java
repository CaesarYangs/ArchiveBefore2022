import javax.swing.*;
import java.awt.*;

public class AnalyticsWIndow {
    public static void docManage(JFrame relativeWindow){
        JFrame docManage = new JFrame("分析报告");
        docManage.setSize(1000, 500);

        docManage.setLocationRelativeTo(relativeWindow);
        docManage.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());


        docManage.setContentPane(panel);
        docManage.setVisible(true);


    }
}
