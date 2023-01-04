import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

public class AddTasks extends MainWindow{
    public void c(){
        this.run();
        this.checkAccess();
    }

    public static void Add(JFrame relativeWindow){
        JFrame add = new JFrame("添加任务");
        add.setSize(500, 300);

        add.setLocationRelativeTo(relativeWindow);
        add.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //GridLayout layout = new GridLayout(3, 1);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,10));

        JLabel l0 = new JLabel();
        l0.setText("任务名");
        JLabel l1 = new JLabel();
        l1.setText("截止日期");
        JLabel l2 = new JLabel();
        l2.setText("描述");
        JLabel l3 = new JLabel();

        final JTextField textField01 = new JTextField(20);
        textField01.setFont(new Font(null, Font.PLAIN, 20));

        final JTextField textField02 = new JTextField(20);
        textField02.setFont(new Font(null, Font.PLAIN, 20));

        final JTextField textField03 = new JTextField(20);
        textField03.setFont(new Font(null, Font.PLAIN, 20));


        JButton b1 = new JButton();
        b1.setText("提交");



        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection conn = null;
                Statement stmt = null;
                int rs = 0;

                try {
                    long start = System.currentTimeMillis();

                    conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                            "taskssql", "Yyq132456");
                    long end = System.currentTimeMillis();
                    System.out.println(conn);
                    System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

                    stmt = conn.createStatement();
                    System.out.println(textField01.getText());
                    System.out.println(textField02.getText());
                    System.out.println(textField03.getText());


                    //rs = stmt.executeUpdate("INSERT INTO `taskssql`.`Tasks` (`Username`) VALUES ('yyq')");

                    if(textField02.getText().equals("")){
                        Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        ++month;
                        int day = c.get(Calendar.DATE);
                        textField02.setText(year+"-"+month+"-"+day);
                    }

                    rs = stmt.executeUpdate("INSERT INTO `taskssql`.`Tasks` (`Username`, `TaskName`, `Check`, `DueDate`, `Notes`) VALUES ('"+LoginWindow.Username+"', '"+textField01.getText()+"', 'no', '"+textField02.getText()+"','"+textField03.getText()+"')");

                    JOptionPane.showMessageDialog(
                            add,
                            "添加成功 请刷新",
                            "消息",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    add.dispose();
                    MainWindow.Mmenu.dispose();
                    MainWindow.MainWindow(add);


                }catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        });










        /*ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取选中的第一行
                selectedRow = table.getSelectedRow();
                selectedId = (String) data.get(selectedRow).get(0);
            }
        });*/





        panel.add(l0);
        panel.add(textField01);

        panel.add(l1);
        panel.add(textField02);

        panel.add(l2);
        panel.add(textField03);

        panel.add(b1);

        add.setContentPane(panel);
        add.setVisible(true);

    }
}
