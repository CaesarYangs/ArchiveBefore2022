import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Comparator;
import java.util.Vector;

public class DocManage {
    static class FileComparator implements Comparator {

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

    public static void docManage(JFrame relativeWindow){
        JFrame docManage = new JFrame("毕业设计文档管理");
        docManage.setSize(1000, 500);

        docManage.setLocationRelativeTo(relativeWindow);
        docManage.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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
                        docManage,
                        "确认退出？",
                        "提示",
                        JOptionPane.YES_NO_OPTION
                );

                if(result==0){
                    docManage.dispose();
                    System.exit(0);
                }


            }
        });


        //编辑 子菜单
        //视图 子菜单
        //帮助 子菜单
        //关于 子菜单

        docManage.setJMenuBar(menuBar);


        //database connect
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        names.add("学号");
        names.add("姓名");
        names.add("毕业设计");
        names.add("老师评价");
        names.add("得分");



        try {
            long start = System.currentTimeMillis();

            conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/sssql01",
                    "SSSQL01", "Yyq132456");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

            stmt = conn.createStatement();

            if(LoginWindow.Status!=1){
                rs = stmt.executeQuery("select * from `StudentDocs`");
                //System.out.println("id\tname\tage\tsex");

                while (rs.next()){
                    row = new Vector();
                    row.add(rs.getString(1));
                    row.add(rs.getString(2));
                    row.add(rs.getString(3));
                    row.add(rs.getString(4));
                    row.add(rs.getString(5));

                    data.add(row);

                }
            }else {
                rs = stmt.executeQuery("SELECT * FROM StudentDocs WHERE ID ="+LoginWindow.Username);
                rs.next();
                row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));

                data.add(row);
            }

        }catch (SQLException d) {
            d.printStackTrace();
        }


        JTable table = new JTable(model);
        //JScrollPane scrollPane = new JScrollPane(table);

        //TableColumn tableColumn = table.getColumnModel().getColumn(0);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        //tableColumn.sizeWidthToFit();
        //tableColumn.setPreferredWidth(6);


        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);

        table.setRowHeight(30);

        table.setPreferredScrollableViewportSize(new Dimension(900,300));


        //panel.add(table.getTableHeader(), BorderLayout.NORTH);
        //panel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton DM_01 = new JButton("分数排序");
        DM_01.setFont(new Font(null,Font.ITALIC,15));
        DM_01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(Float.parseFloat((String) data.get(j).get(4))<Float.parseFloat((String) data.get(j+1).get(4))){
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

        JButton DM_02 = new JButton("修改毕设题目");
        DM_02.setFont(new Font(null,Font.ITALIC,15));

        DM_02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               DocChange.Docchange(docManage);
            }
        });

        JButton DM_03 = new JButton("刷新");
        DM_03.setFont(new Font(null,Font.ITALIC,15));
        DM_03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                docManage.dispose();
                DocManage.docManage(docManage);
            }
        });

        panel.add(DM_01);
        panel.add(DM_02);
        panel.add(DM_03);
        panel.add(scrollPane);


        docManage.setContentPane(panel);
        docManage.setVisible(true);


    }
}
