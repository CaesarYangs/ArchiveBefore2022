import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainMenu extends LoginWindow{
    public static void MainWindow(JFrame relativeWindow){
        JFrame Mmenu = new JFrame("主菜单");

        Mmenu.setSize(1000, 500);

        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        Mmenu.setLocationRelativeTo(relativeWindow);

        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        Mmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

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


        //编辑 子菜单
        //视图 子菜单
        //帮助 子菜单
        //关于 子菜单

        Mmenu.setJMenuBar(menuBar);


        //主界面视图
        GroupLayout levelhead = new GroupLayout(panel);
        panel.setLayout(levelhead);
        //levelhead.setAutoCreateGaps(true);
        //levelhead.setAutoCreateContainerGaps(true);

        JLabel H1label = new JLabel();
            H1label.setText("毕业设计文档管理系统");
            H1label.setFont(new Font(null,Font.BOLD,35));
        JButton Mbt01 = new JButton("关于");
            Mbt01.setFont(new Font(null,Font.ITALIC,15));

        JLabel cardCompH2label = new JLabel();
            cardCompH2label.setText(LoginWindow.Username);
            cardCompH2label.setFont(new Font(null,Font.CENTER_BASELINE,15));

        JLabel cardCompH3label = new JLabel();
            cardCompH3label.setText("欢迎进入系统");

        //头像图片处理
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/Users/yyq/2021javaProjectResources/headicon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);

        JLabel headimg = new JLabel();
        headimg.setIcon(imageIcon);
        //
        //简介框设计
        Box cardComp01 = Box.createVerticalBox();
        cardComp01.add(cardCompH2label);
        //cardComp01.add(Box.createVerticalStrut(1));
        cardComp01.add(cardCompH3label);
        Box cardComp = Box.createHorizontalBox();
        //cardComp.add(Box.createHorizontalStrut(1));
        cardComp.add(headimg);
        //cardComp.add(Box.createHorizontalStrut(1));
        cardComp.add(cardComp01);
        //cardComp.add(Box.createHorizontalStrut(1));
        //


        //声明按钮
        JButton Mbt10 = new JButton("登录信息修改");//
        JButton Mbt11 = new JButton("  退出登陆 ");//
        JButton Mbt12 = new JButton("  功能查询 ");
        JButton Mbt13 = new JButton("    关于   ");

        JButton Mbt14 = new JButton("个人信息查询");//
        JButton Mbt15 = new JButton("  文档管理 ");//
        JButton Mbt16 = new JButton("  分析报告 ");
        JButton Mbt17 = new JButton("检查数据连接");//
        JButton Mbt18 = new JButton("教师文档录入");//
        JButton Mbt19 = new JButton("  成绩查询 ");//
        JButton Mbt20 = new JButton("一键导出成绩");
        JButton Mbt21 = new JButton("版权所有:杨业卿");

        Mbt20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportGrade.searchgrade(Mmenu);
            }
        });

        Mbt19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchGrade.searchgrade(Mmenu);
            }
        });


        Mbt18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginWindow.Status!=1){
                    LogDoc.logdoc(Mmenu);
                }else {
                    JOptionPane.showMessageDialog(
                            Mmenu,
                            "无权限访问",
                            "消息标题",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }


            }
        });



        Mbt17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckConnection.Check(Mmenu);
            }
        });

        Mbt11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Mmenu,
                        "确认登出？",
                        "登出",
                        JOptionPane.YES_NO_OPTION
                );
                if(result == 0){
                    LoginWindow.LoginW(Mmenu);
                    Mmenu.dispose();
                }else {

                }
            }
        });

        Mbt10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonalInformationChange.PIChangeWindow(Mmenu);
            }
        });

        Mbt14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonalInformationSearch.PISearchWindow(Mmenu);
            }
        });

        Mbt12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow.Help(Mmenu);
            }
        });

        Mbt13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About.About(Mmenu);
            }
        });

        Mbt15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocManage.docManage(Mmenu);
            }
        });

        Mbt16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnalyticsWIndow.docManage(Mmenu);
            }
        });






        //水平连续组
        GroupLayout.SequentialGroup hGroup = levelhead.createSequentialGroup();
        //hGroup.addGap(5);
        hGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(H1label).addComponent(cardComp).addComponent(Mbt10).addComponent(Mbt11).addComponent(Mbt12).addComponent(Mbt13));
        hGroup.addGap(100);
        hGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(Mbt14).addComponent(Mbt15).addComponent(Mbt16).addComponent(Mbt17));
        hGroup.addGap(10);
        hGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(Mbt18).addComponent(Mbt19).addComponent(Mbt20).addComponent(Mbt21));
        levelhead.setHorizontalGroup(hGroup);

        //垂直连续组
        GroupLayout.SequentialGroup vGroup = levelhead.createSequentialGroup();
        vGroup.addGroup(levelhead.createParallelGroup().addComponent(H1label));
        vGroup.addGap(50);
        vGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(cardComp));
        vGroup.addGap(5);
        vGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(Mbt10).addComponent(Mbt14).addComponent(Mbt18));
        vGroup.addGap(5);
        vGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(Mbt11).addComponent(Mbt15).addComponent(Mbt19));
        vGroup.addGap(5);
        vGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(Mbt12).addComponent(Mbt16).addComponent(Mbt20));
        vGroup.addGap(5);
        vGroup.addGroup(levelhead.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(Mbt13).addComponent(Mbt17).addComponent(Mbt21));
        vGroup.addGap(10);
        levelhead.setVerticalGroup(vGroup);


        Mmenu.setContentPane(panel);
        Mmenu.setVisible(true);        // PS: 最后再设置为可显示(绘制), 所有添加的组件才会显示


    }
}
