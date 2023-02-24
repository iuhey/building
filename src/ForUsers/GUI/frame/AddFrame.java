package ForUsers.GUI.frame;

import ForUsers.GUI.others.BackGroundPanel;
import ForUsers.GUI.others.Path;
import ForUsers.GUI.others.ScreenUtils;
import ForAdmin.DateClass.Building;
import ForUsers.services.BuildServicelmpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @program: HomeWork
 * @description: 添加信息界面
 * @author: 廖建权
 * @create: 2021-12-12 10:57
 **/

public class AddFrame extends JDialog implements ActionListener {
    // 添加label

    JLabel houseLa;
    JLabel buildingLa;
    JLabel floorLa;
    JLabel areaLa;
    JLabel unameLa;
    JLabel telLa;

    // 添加文本框
    JTextField houseTf;
    JTextField buildingTf;
    JTextField floorTf;
    JTextField areaTf;
    JTextField unameTf;
    JTextField telTf;

    // 添加按钮
    JButton addBt;

    BackGroundPanel bg;

    static final int WIDTH = 400, HEIGHT = 500;     //窗口宽高

    public AddFrame() {
        setModal(true);     // 模态
        this.setTitle("添加楼宇信息");
        this.setResizable(false);

        // 设置窗口位置
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置点击关闭按钮是隐藏窗口
        this.setSize(400, 440);     // 设置窗口大小
//        this.setLayout(null);   // 绝对布局

//        try {
//            this.setIconImage(ImageIO.read(new File(Path.getRealPath("add.png"))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        init();

        this.setVisible(true);
    }

    public void init() {
        try {
            bg = new BackGroundPanel(ImageIO.read(new File(Path.getRealPath("5.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 组装相关组件
        Box vBox = Box.createVerticalBox();

        // 组装房号
        Box hBox = Box.createHorizontalBox();
        houseLa = new JLabel("门牌:");
        houseTf = new JTextField(15);
        hBox.add(houseLa);
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(houseTf);

        for(int i=0;i<houseLa.getText().length();i++){

        }

        // 组装楼号
        Box bBox = Box.createHorizontalBox();
        buildingLa = new JLabel("楼栋:");
        buildingTf = new JTextField(15);
        bBox.add(buildingLa);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(buildingTf);

        // 组装层号
        Box fBox = Box.createHorizontalBox();
        floorLa = new JLabel("楼层:");
        floorTf = new JTextField(15);
        fBox.add(floorLa);
        fBox.add(Box.createHorizontalStrut(20));
        fBox.add(floorTf);

        // 组装面积
        Box aBox = Box.createHorizontalBox();
        areaLa = new JLabel("面积:");
        areaTf = new JTextField(15);
        aBox.add(areaLa);
        aBox.add(Box.createHorizontalStrut(20));
        aBox.add(areaTf);

        // 组装姓名
        Box uBox = Box.createHorizontalBox();
        unameLa = new JLabel("户主:");
        unameTf = new JTextField(15);
        uBox.add(unameLa);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(unameTf);

        // 组装电话
        Box tBox = Box.createHorizontalBox();
        telLa = new JLabel("电话:");
        telTf = new JTextField(15);
        tBox.add(telLa);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(telTf);

        // 组装按钮
        Box btBox = Box.createHorizontalBox();
        addBt = new JButton("确认");
        addBt.addActionListener(this);
        btBox.add(addBt);

        vBox.add(Box.createVerticalStrut(30));
        vBox.add(hBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(fBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(aBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(btBox);

        bg.add(vBox);
        this.add(bg);
    }

    BuildServicelmpl dsl = new BuildServicelmpl();

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bt = (JButton) e.getSource();       // 获得发生的时间，强制转化为按钮事件类
        if (bt.getText().equals("确认")) {
            if (houseTf.getText().trim().equals("")
                    || buildingTf.getText().trim().equals("") || floorTf.getText().trim().equals("")
                    || areaTf.getText().trim().equals("") || unameTf.getText().trim().equals("")
                    || telTf.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "信息填写不完全！");
            } 
            else if(telTf.getText().trim().length() != 11) {
                JOptionPane.showMessageDialog(this, "电话信息填写不正确！");
            }
            else if(!(houseTf.getText().trim().charAt(0) >= 'A' && houseTf.getText().trim().charAt(0) <= 'Z')
                || houseTf.getText().trim().replaceAll("[0-9]", "").length() != 1
                || houseTf.getText().trim().length() != 4) {
                JOptionPane.showMessageDialog(this, "门牌号填写错误");
            }
            else if(!houseTf.getText().trim().substring(0, 1).equals(buildingTf.getText().trim())
                || !houseTf.getText().trim().substring(1, 2).equals(floorTf.getText().trim())) {
                JOptionPane.showMessageDialog(this, "楼栋号或楼层号与门牌号不匹配！");
            }
            else {
                if (dsl.findByHouseNumber(houseTf.getText().trim()) != null) {
                    JOptionPane.showMessageDialog(this, "所输入的门牌号已存在！请重新输入");
                } else {
                    if (floorTf.getText().trim().replaceAll("[0-9]", "").length() != 0
                            || areaTf.getText().trim().replaceAll("[0-9]", "").length() != 0) {
                        JOptionPane.showMessageDialog(this, "请输入正确的格式！");
                    } else {
                        String houseNumber = houseTf.getText().trim();
                        String buildingNumber = buildingTf.getText().trim();
                        int floorNumber = Integer.parseInt(floorTf.getText().trim());
                        int areaNumber = Integer.parseInt(areaTf.getText().trim());
                        String name = unameTf.getText().trim();
                        String tel = telTf.getText().trim();

                        // 封装
                        Building bd = new Building(0, houseNumber, buildingNumber,
                                floorNumber, areaNumber, name, tel);

                        // 添加新的
                        dsl.newInsert(bd);
                        // 更新table

                        JOptionPane.showMessageDialog(this, "添加成功！");

                        dispose();
                    }

                }
            }
        }
    }
}

