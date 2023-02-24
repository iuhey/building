package ForUsers.GUI.frame;

import ForUsers.GUI.others.BackGroundPanel;
import ForUsers.GUI.others.Path;
import ForAdmin.DateClass.Register;
import ForAdmin.Interface.RegisterDeal;
import ForAdmin.Interface.RegisterDealImpl;
import ForUsers.GUI.others.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author 廖建权
 * @title: registFrame
 * @projectName Log
 * @description: 注册
 * @date 2022/1/110:49
 */

public class RegisterFrame extends JDialog implements ActionListener {

    // 声明基本组件
    JLabel uLabel;
    JLabel pLabel;
    JLabel pcLabel;
    JTextField uTf;
    JPasswordField pTf;
    JPasswordField pcTf;

    JButton registBt;
    JLabel img;
    ImageIcon imageIcon;

    BackGroundPanel bg;

    static final int WIDTH = 400, HEIGHT = 300;     //设置窗口宽高

    public RegisterFrame() {
        setModal(true);
        // 设置窗口相关属性
        this.setTitle("注册页面");
        this.setResizable(false);   // 设置窗口大小不可变
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH)/2,
                (ScreenUtils.getScreenHeight() - HEIGHT)/2, WIDTH, HEIGHT); // 设置窗口在屏幕上的位置
        // 设置框架标题图标
//        try {
//            this.setIconImage(ImageIO.read(new File(Path.getRealPath("登录.jpg"))));
//        } catch(IOException e){
//            e.printStackTrace();
//        }

        init(); // 组装

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置点击关闭按钮是隐藏窗口，并停止程序
        this.setVisible(true);  // 设置窗口可见
    }

    public void init(){
        // 设置窗口内容
        // 设置背景
        try {
            bg = new BackGroundPanel(ImageIO.read(new File(Path.getRealPath("7.jpg"))));
        }catch (IOException e) {
            e.printStackTrace();
        }

/*
        // 设置北部图案
        try {
            imageIcon = new ImageIcon(new ImageIcon("D:\\lc\\src\\ForUsers.Picture\\动.gif")
                    .getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT));
            img = new JLabel(imageIcon);
//            img.setHorizontalAlignment(SwingConstants.NORTH);

//            img = new JLabel(new ImageIcon("F:\\IdeaProjects\\images\\1.gif"));

        }catch(Exception e) {
            e.printStackTrace();
        }
*/

        // 组装登录相关的元素
        Box vBox = Box.createVerticalBox();
        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        uLabel = new JLabel("用  户  名:");
        uTf = new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uTf);
        // 组装密码
        Box pBox = Box.createHorizontalBox();
        pLabel = new JLabel("密        码:");
        pTf = new JPasswordField(15);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pTf);
        // 组装密码确认
        Box pcBox = Box.createHorizontalBox();
        pcLabel = new JLabel("确认密码:");
        pcTf = new JPasswordField(15);
        pcBox.add(pcLabel);
        pcBox.add(Box.createHorizontalStrut(20));
        pcBox.add(pcTf);
        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        registBt = new JButton("确认");
        registBt.addActionListener(this);
        bBox.add(registBt);

        vBox.add(Box.createVerticalStrut(40));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(pcBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(bBox);

        bg.add(vBox);

        //this.add(img, BorderLayout.NORTH);  // 将图案放在框架北部
        this.add(bg, BorderLayout.CENTER);  // .....中央
    }

    RegisterDeal rd = new RegisterDealImpl();
    Register r = new Register();

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton Bt=(JButton)e.getSource();
        if(Bt.getText().equals("确认")) {
            if(uTf.getText().trim().equals("") || (pTf.getText().trim().equals(""))
            || pcTf.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "用户名或密码不能为空");
            }
            else {
                r.setUsers(uTf.getText());
                r.setPassword(pTf.getText());
                r.setConfirmpassword(pcTf.getText());

                int res = rd.addUsers(r);

                if(res == 1) {
                    JOptionPane.showMessageDialog(this, "注册成功!");
                    this.dispose();
                }
                else if(res == 2){
                    JOptionPane.showMessageDialog(this, "用户已存在!");
                }
                else if(res == 3) {
                    JOptionPane.showMessageDialog(this, "密码和确认密码不一致!");
                }
                else {
                    JOptionPane.showMessageDialog(this, "注册失败!");
                }
            }
        }
    }
}
 