package ForUsers.GUI.frame;

import ForUsers.GUI.others.BackGroundPanel;
import ForUsers.GUI.others.Path;
import ForUsers.GUI.others.ScreenUtils;
import ForAdmin.DateClass.Login;
import ForUsers.services.LoginService;
import ForUsers.services.LoginServiceImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author 廖建权
 * @title: LoginFrame2
 * @projectName Log
 * @description: 登录
 * @date 2021/12/3117:02
 */

public class LoginFrame extends JFrame implements ActionListener {

    // 声明基本组件
    JLabel uLabel;
    JLabel pLabel;

    JTextField uTf;
    JPasswordField pTf;

    JButton loginBt;
    JButton registBt;
    JLabel img;
    ImageIcon imageIcon;

    BackGroundPanel bg;

    static final int WIDTH = 380, HEIGHT = 270;     //设置窗口宽高

    public LoginFrame() {
        // 设置窗口相关属性
        this.setTitle("登录");
        this.setResizable(false);   // 设置窗口大小不可变
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH)/2,
                (ScreenUtils.getScreenHeight() - HEIGHT)/2, WIDTH, HEIGHT); // 设置窗口在屏幕上的位置

        // 设置框架标题图标
//        try {
//            this.setIconImage(ImageIO.read(new File(Path.getRealPath("设计.png"))));
//        } catch(IOException e){
//            e.printStackTrace();
//        }

        init(); // 组装

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置点击关闭按钮是隐藏窗口，并停止程序
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


         //设置北部图案
//        try {
//            imageIcon = new ImageIcon(new ImageIcon("D:\\lc\\src\\ForUsers.Picture\\123.gif")
//                    .getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT));
//            img = new JLabel(imageIcon);
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }


        // 组装登录相关的元素
        Box vBox = Box.createVerticalBox();
        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        uLabel = new JLabel("用户名:");
        uTf = new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uTf);
        // 组装密码
        Box pBox = Box.createHorizontalBox();
        pLabel = new JLabel("密    码:");
        pTf = new JPasswordField(15);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pTf);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        loginBt = new JButton("登录");
        loginBt.addActionListener(this);
        registBt = new JButton("注册");
        registBt.addActionListener(this);
        bBox.add(loginBt);
        bBox.add(Box.createHorizontalStrut(30));
        bBox.add(registBt);

        vBox.add(Box.createVerticalStrut(40));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(pBox);

        vBox.add(Box.createVerticalStrut(40));
        vBox.add(bBox);

        bg.add(vBox);

       //this.add(img, BorderLayout.NORTH);  // 将图案放在框架北部
       this.add(bg, BorderLayout.CENTER);  // .....中央
    }

    LoginService loginService = new LoginServiceImpl();
    Login lg;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton Bt=(JButton)e.getSource();
        if(Bt.getText().equals("登录"))
        {
            if(uTf.getText().trim().equals("") || (pTf.getText().trim().equals("")))
            {
                JOptionPane.showMessageDialog(this, "用户名或密码不能为空");
            }
            else {
                lg = new Login(uTf.getText().trim(), pTf.getText().trim());
                if(loginService.JudgeLogin(lg) == 1)
                {
                    JOptionPane.showMessageDialog(this, "登录成功");
                    //SchoolFrame f=new SchoolFrame();
                    new MainFrame("主页面");
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "用户名或密码不正确");
                }
            }
        }
        else if(Bt.getText().equals("注册")) {
            new RegisterFrame();
        }
    }
}

 