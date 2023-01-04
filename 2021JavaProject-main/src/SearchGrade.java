import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SearchGrade {
    static class GradeComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if((float)o1>(float)o2){
                return 1;
            }else if((float)o1<(float)o2){
                return -1;
            }else {
                return 0;
            }

        }
    }

    public static void searchgrade(JFrame relativeWindow){
        String title = "成绩查询--admin";
        if(LoginWindow.Status==1){
            title = "成绩查询--"+LoginWindow.Username;
        }else if(LoginWindow.Status==2){
            title = "教师成绩查询--"+LoginWindow.Username;
        }
        JFrame SG = new JFrame(title);
        SG.setSize(1000, 500);

        SG.setLocationRelativeTo(relativeWindow);
        SG.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

        DefaultTableModel model = new DefaultTableModel();
        Vector row = new Vector();
        Vector<Vector> data = new Vector();
        Vector names = new Vector();
        model.setDataVector(data, names);

        //----------menu--------------

        JMenuBar menuBar = new JMenuBar();

        JMenu micon = new JMenu(" ");
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu viewMenu = new JMenu("视图");
        JMenu helpMenu = new JMenu("帮助");
        JMenu aboutMenu = new JMenu("关于");

        menuBar.add(micon);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);

        //文件 子菜单
        JMenuItem file_open = new JMenuItem("打开");
        JMenuItem file_new = new JMenuItem("新建");
        JMenuItem file_quit = new JMenuItem("退出");
        fileMenu.add(file_new);
        fileMenu.add(file_open);
        fileMenu.addSeparator();
        fileMenu.add(file_quit);

        file_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(
                        SG,
                        "确认退出？",
                        "提示",
                        JOptionPane.YES_NO_OPTION
                );

                if(result==0){
                    SG.dispose();
                    System.exit(0);
                }


            }
        });


        //编辑 子菜单
        //视图 子菜单
        //帮助 子菜单
        //关于 子菜单

        SG.setJMenuBar(menuBar);


        //database connect
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        names.add("学号");
        names.add("总加权");
        names.add("公共基础课加权");
        names.add("专业课加权");
        names.add("学分通过率");
        names.add("获奖情况");
        names.add("加分");
        names.add("备注");



        try {
            long start = System.currentTimeMillis();

            conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/sssql01",
                    "SSSQL01", "Yyq132456");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

            stmt = conn.createStatement();

            if(LoginWindow.Status!=1){
                rs = stmt.executeQuery("select * from `StudentGrades`");
                //System.out.println("id\tname\tage\tsex");

                while (rs.next()){
                    row = new Vector();
                    row.add(rs.getString(1));
                    row.add(rs.getString(2));
                    row.add(rs.getString(3));
                    row.add(rs.getString(4));
                    row.add(rs.getString(5));
                    row.add(rs.getString(6));
                    row.add(rs.getString(7));
                    row.add(rs.getString(8));

                    data.add(row);

                }
            }else {
                rs = stmt.executeQuery("SELECT * FROM StudentGrades WHERE ID ="+LoginWindow.Username);
                rs.next();
                row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));

                data.add(row);
            }

        }catch (SQLException d) {
            d.printStackTrace();
        }


        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton SG_01 = new JButton("按总加权排序");
        SG_01.setFont(new Font(null,Font.ITALIC,15));

        JButton SG_02 = new JButton("按基础课加权排序");
        SG_02.setFont(new Font(null,Font.ITALIC,15));

        JButton SG_03 = new JButton("按专业课加权排序");
        SG_03.setFont(new Font(null,Font.ITALIC,15));

        JButton SG_04 = new JButton("按学分通过率排序");
        SG_04.setFont(new Font(null,Font.ITALIC,15));

        JButton SG_05 = new JButton("按默认学号排序");
        SG_05.setFont(new Font(null,Font.ITALIC,15));


        SG_01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(Float.parseFloat((String) data.get(j).get(1))<Float.parseFloat((String) data.get(j+1).get(1))){
                            Vector tmp = new Vector();
                            tmp.add(data.get(j+1));
                            data.set(j+1,data.get(j));
                            data.set(j, (Vector) tmp.firstElement());

                            flag = false;
                        }
                    }
                }


                table.updateUI();
                scrollPane.updateUI();
            }
        });


        SG_02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(Float.parseFloat((String) data.get(j).get(2))<Float.parseFloat((String) data.get(j+1).get(2))){
                            Vector tmp = new Vector();
                            tmp.add(data.get(j+1));
                            data.set(j+1,data.get(j));
                            data.set(j, (Vector) tmp.firstElement());

                            flag = false;
                        }
                    }
                }


                table.updateUI();
                scrollPane.updateUI();
            }
        });

        SG_03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(Float.parseFloat((String) data.get(j).get(3))<Float.parseFloat((String) data.get(j+1).get(3))){
                            Vector tmp = new Vector();
                            tmp.add(data.get(j+1));
                            data.set(j+1,data.get(j));
                            data.set(j, (Vector) tmp.firstElement());

                            flag = false;
                        }
                    }
                }


                table.updateUI();
                scrollPane.updateUI();
            }
        });

        SG_04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(Float.parseFloat((String) data.get(j).get(4))>Float.parseFloat((String) data.get(j+1).get(4))){
                            Vector tmp = new Vector();
                            tmp.add(data.get(j+1));
                            data.set(j+1,data.get(j));
                            data.set(j, (Vector) tmp.firstElement());

                            flag = false;
                        }
                    }
                }


                table.updateUI();
                scrollPane.updateUI();
            }
        });

        SG_05.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(Float.parseFloat((String) data.get(j).get(0))>Float.parseFloat((String) data.get(j+1).get(0))){
                            Vector tmp = new Vector();
                            tmp.add(data.get(j+1));
                            data.set(j+1,data.get(j));
                            data.set(j, (Vector) tmp.firstElement());

                            flag = false;
                        }
                    }
                }


                table.updateUI();
                scrollPane.updateUI();
            }
        });




        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);

        table.setRowHeight(30);

        table.setPreferredScrollableViewportSize(new Dimension(900,300));


        //panel.add(table.getTableHeader(), BorderLayout.NORTH);
        //panel.add(table, BorderLayout.CENTER);

        panel.add(SG_01);
        panel.add(SG_02);
        panel.add(SG_03);
        panel.add(SG_04);
        panel.add(SG_05);
        panel.add(scrollPane);

        SG.setContentPane(panel);
        SG.setVisible(true);


    }
}
