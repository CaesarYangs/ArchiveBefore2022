import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Comparator;
import java.util.Vector;

public class MainWindow extends Thread {
    static class GradeComparator implements Comparator {    //重写排序接口方法 以供后期重用

        @Override
        public int compare(Object o1, Object o2) {  //比较
            if((float)o1>(float)o2){
                return 1;
            }else if((float)o1<(float)o2){
                return -1;
            }else {
                return 0;
            }

        }
    }


    public static int selectedRow;
    public static String selectedId;
    public static JFrame Mmenu = new JFrame("任务清单");


    public static void MainWindow(JFrame relativeWindow){


        Mmenu.setSize(1000, 500);

        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        Mmenu.setLocationRelativeTo(relativeWindow);

        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        Mmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

        //----------menu--------------

        JMenuBar menuBar = new JMenuBar();  //标题栏设计与构造

        JMenu micon = new JMenu(" ");
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu viewMenu = new JMenu("账户");
        JMenu checkMenu = new JMenu("远程连接检查");
        JMenu aboutMenu = new JMenu("关于");

        menuBar.add(micon);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(checkMenu);
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
                        Mmenu,
                        "确认退出？",
                        "提示",
                        JOptionPane.YES_NO_OPTION
                );

                if(result==0){
                    Mmenu.dispose();
                    System.exit(0);
                }


            }
        });

        JMenuItem about = new JMenuItem("关于程序");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About.About(Mmenu);
            }
        });

        aboutMenu.add(about);

        JMenuItem checksql = new JMenuItem("在线服务器连接检查");
        checksql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckConnection.Check(Mmenu);
            }
        });
        checkMenu.add(checksql);


        //账户 子菜单
        JMenuItem now_login = new JMenuItem("现在登录账户："+LoginWindow.Username);
        JMenuItem logout = new JMenuItem("退出登录");

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Mmenu,
                        "确认登出？",
                        "提示",
                        JOptionPane.YES_NO_OPTION

                );

                if(result==0){
                    Mmenu.dispose();
                    LoginWindow.LoginW(Mmenu);
                }
            }
        });

        viewMenu.add(now_login);
        viewMenu.addSeparator();
        viewMenu.add(logout);

        //视图 子菜单
        //帮助 子菜单
        //关于 子菜单

        Mmenu.setJMenuBar(menuBar);


        DefaultTableModel model = new DefaultTableModel();
        Vector row = new Vector();  //构造JList显示函数
        Vector<Vector> data = new Vector();
        Vector names = new Vector();
        model.setDataVector(data, names);

        //database connect
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        names.add("任务id");  //构造JList显示表头
        names.add("任务名称");
        names.add("完成？");
        names.add("截止时间");
        names.add("备注");

        try {
            long start = System.currentTimeMillis();

            conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                    "taskssql", "Yyq132456");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

            stmt = conn.createStatement();



            rs = stmt.executeQuery("SELECT * FROM Tasks WHERE Username ="+"'"+LoginWindow.Username+"'");


            while (rs.next()){
                row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));

                data.add(row);
            }

        }catch (SQLException d) {
            d.printStackTrace();
        }


        //主要功能按钮设计
        JButton addt = new JButton("添加任务");
        addt.setFont(new Font(null,Font.ITALIC,15));

        JButton done = new JButton("改变完成情况");
        done.setFont(new Font(null,Font.ITALIC,15));

        JButton delete = new JButton("删除任务");
        delete.setFont(new Font(null,Font.ITALIC,15));

        JButton sortdate = new JButton("按截止日期排序");
        delete.setFont(new Font(null,Font.ITALIC,15));

        JButton sortdone = new JButton("按完成排序");
        delete.setFont(new Font(null,Font.ITALIC,15));

        JButton edit = new JButton("编辑任务");
        delete.setFont(new Font(null,Font.ITALIC,15));




        JButton DM_03 = new JButton("刷新");
        DM_03.setFont(new Font(null,Font.ITALIC,15));
        DM_03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mmenu.dispose();
                MainWindow.MainWindow(Mmenu);
            }
        });


        addt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTasks.Add(Mmenu);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedId!=null){
                    Edit.Edit(Mmenu);
                }else {
                    JOptionPane.showMessageDialog(
                            Mmenu,
                            "请选择要修改的项目",
                            "消息标题",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Mmenu,
                        "确认删除？",
                        "提示",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                System.out.println("选择结果: " + result);

                if(result==0){
                    Connection conn = null;
                    Statement stmt = null;
                    boolean rs;
                    try {
                        long start = System.currentTimeMillis();

                        conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                                "taskssql", "Yyq132456");
                        long end = System.currentTimeMillis();
                        System.out.println(conn);
                        System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

                        stmt = conn.createStatement();

                        rs = stmt.execute("DELETE FROM `taskssql`.`Tasks` WHERE `id` = "+"'"+selectedId+"'");


                    }catch (SQLException d) {
                        d.printStackTrace();
                    }


                    JOptionPane.showMessageDialog(
                            Mmenu,
                            "删除成功 请刷新列表",
                            "消息标题",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    MainWindow.Mmenu.dispose();
                    MainWindow.MainWindow(Mmenu);

                }

            }
        });


        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取选中的第一行
                selectedRow = table.getSelectedRow();
                selectedId = (String) data.get(selectedRow).get(0);
            }
        });


        sortdate.addActionListener(new ActionListener() {
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

        sortdone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<data.size();i++){
                    boolean flag = true;
                    for(int j=0;j<data.size()-i-1;j++){
                        if(data.get(j).get(2).equals("yes")){
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

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                Statement stmt = null;
                boolean rs;
                try {
                    long start = System.currentTimeMillis();

                    conn = DriverManager.getConnection("jdbc:mysql://118.31.60.105:3306/taskssql",
                            "taskssql", "Yyq132456");
                    long end = System.currentTimeMillis();
                    System.out.println(conn);
                    System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");

                    stmt = conn.createStatement();

                    if(selectedId.equals("")){
                        JOptionPane.showMessageDialog(
                                Mmenu,
                                "请选择要改变的任务",
                                "消息标题",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                    if(data.get(selectedRow).get(2).equals("no")){
                        rs = stmt.execute("UPDATE `taskssql`.`Tasks` SET `Check` = 'yes' WHERE `id` = "+"'"+selectedId+"'");
                    }else {
                        rs = stmt.execute("UPDATE `taskssql`.`Tasks` SET `Check` = 'no' WHERE `id` = "+"'"+selectedId+"'");
                    }



                }catch (SQLException d) {
                    d.printStackTrace();
                }



                JOptionPane.showMessageDialog(
                        Mmenu,
                        "更改成功",
                        "消息",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);

        table.setRowHeight(30);

        table.setPreferredScrollableViewportSize(new Dimension(900,300));

        panel.add(scrollPane);
        panel.add(addt);
        panel.add(done);
        panel.add(edit);
        panel.add(delete);
        panel.add(sortdate);
        panel.add(sortdone);
        panel.add(DM_03);
        Mmenu.setContentPane(panel);
        Mmenu.setVisible(true);        // PS: 最后再设置为可显示(绘制), 所有添加的组件才会显示
    }
}
