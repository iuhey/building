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
 * @author 廖建权
 * @title: ModFrame
 * @projectName HomeWork
 * @description: 修改数据
 * @date 2021/12/2810:29
 */

public class ModifyFrame extends JDialog implements ActionListener {

    JTable t;
    // 添加label
    JLabel idLa;
    JLabel houseLa;
    JLabel buildingLa;
    JLabel floorLa;
    JLabel areaLa;
    JLabel unameLa;
    JLabel telLa;

    // 添加文本框
    JTextField idTf;
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

    public ModifyFrame(JTable table) {
        setModal(true);     // 模态
        this.setTitle("修改楼宇信息");
        this.setResizable(false);
        // 设置窗口位置
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH)/2, (ScreenUtils.getScreenHeight() - HEIGHT)/2, WIDTH, HEIGHT);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置点击关闭按钮是隐藏窗口
        this.setSize(400, 440);     // 设置窗口大小
//        this.setLayout(null);   // 绝对布局

//        try {
//            this.setIconImage(ImageIO.read(new File(Path.getRealPath("D:\\lc\\src\\ForUsers.Picture\\设计.png"))));
//        } catch(IOException e){
//            e.printStackTrace();
//        }

        this.t = table;
        int [] selectedRow = table.getSelectedRows();  // 得到被选中的行号
        if(selectedRow.length > 1) {
            JOptionPane.showMessageDialog(this, "一次只能修改一项楼宇信息！");
            return;
        }
        else if(selectedRow.length == 0) {
            JOptionPane.showMessageDialog(this, "请选中要修改的楼宇信息！");
            return;
        }
        else {
           init(selectedRow[0]);
        }

        this.setVisible(true);
    }

    public void init(int row) {
        try {
            bg = new BackGroundPanel(ImageIO.read(new File(Path.getRealPath("6.png"))));
        }catch (IOException e) {
            e.printStackTrace();
        }

        // 组装相关组件
        Box vBox = Box.createVerticalBox();
        // 组装编号
        Box iBox = Box.createHorizontalBox();
        idLa = new JLabel("编号:");
        idTf = new JTextField(15);
        idTf.setText(t.getValueAt(row, 0).toString());
        iBox.add(idLa);
        iBox.add(Box.createHorizontalStrut(20));
        iBox.add(idTf);
        // 组装房号
        Box hBox = Box.createHorizontalBox();
        houseLa = new JLabel("门牌:");
        houseTf = new JTextField(15);
        houseTf.setText(t.getValueAt(row, 1).toString());
        houseTf.setEnabled(false);  // 反白，不可修改
        hBox.add(houseLa);
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(houseTf);
        // 组装楼号
        Box bBox = Box.createHorizontalBox();
        buildingLa = new JLabel("楼栋:");
        buildingTf = new JTextField(15);
        buildingTf.setText(t.getValueAt(row, 2).toString());

        buildingTf.setEnabled(false);  // 反白，不可修改

        bBox.add(buildingLa);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(buildingTf);
        // 组装层号
        Box fBox = Box.createHorizontalBox();
        floorLa = new JLabel("楼层:");
        floorTf = new JTextField(15);
        floorTf.setText(t.getValueAt(row, 3).toString());

        floorTf.setEnabled(false);  // 反白，不可修改

        fBox.add(floorLa);
        fBox.add(Box.createHorizontalStrut(20));
        fBox.add(floorTf);
        // 组装面积
        Box aBox = Box.createHorizontalBox();
        areaLa = new JLabel("面积:");
        areaTf = new JTextField(15);
        areaTf.setText(t.getValueAt(row, 4).toString());
        aBox.add(areaLa);
        aBox.add(Box.createHorizontalStrut(20));
        aBox.add(areaTf);
        // 组装姓名
        Box uBox = Box.createHorizontalBox();
        unameLa = new JLabel("户主:");
        unameTf = new JTextField(15);
        unameTf.setText(t.getValueAt(row, 5).toString());
        uBox.add(unameLa);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(unameTf);
        // 组装电话
        Box tBox = Box.createHorizontalBox();
        telLa = new JLabel("电话:");
        telTf = new JTextField(15);
        telTf.setText(t.getValueAt(row, 6).toString());
        tBox.add(telLa);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(telTf);
        // 组装按钮
        Box btBox = Box.createHorizontalBox();
        addBt = new JButton("确认");
        addBt.addActionListener(this);
        btBox.add(addBt);

        vBox.add(Box.createVerticalStrut(30));
//        vBox.add(iBox);
//        vBox.add(Box.createVerticalStrut(30));
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
        JButton bt = (JButton)e.getSource();
        if(bt.getText().trim().equals("确认")) {

            if(idTf.getText().trim().equals("") || houseTf.getText().trim().equals("")
                    || buildingTf.getText().trim().equals("") || floorTf.getText().trim().equals("")
                    || areaTf.getText().trim().equals("") || unameTf.getText().trim().equals("")
                    || telTf.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "信息填写不完全！");
            }
            else {
                if(dsl.findByHouseNumber(houseTf.getText().trim()) == null) {
                    JOptionPane.showMessageDialog(this, "楼宇信息不存在，无法更新！");
                }
                else {
                    if(idTf.getText().trim().replaceAll("[0-9]", "").length() != 0
                            || floorTf.getText().trim().replaceAll("[0-9]", "").length() != 0
                            || areaTf.getText().trim().replaceAll("[0-9]", "").length() != 0) {
                        JOptionPane.showMessageDialog(this, "请输入正确的格式！");
                    }
                    else if(telTf.getText().trim().length() != 11 || telTf.getText().trim().replaceAll("[0-9]", "").length() != 0){
                        JOptionPane.showMessageDialog(this, "电话信息输入错误！");
                    }
                    else if(areaTf.getText().trim().replaceAll("[0-9]", "").length() != 0){
                        JOptionPane.showMessageDialog(this, "面积输入错误！");
                    }
                    else {
                        int idNumber = Integer.parseInt(idTf.getText().trim());
                        String houseNumber = houseTf.getText().trim();
                        String buildingNumber = buildingTf.getText().trim();
                        int floorNumber = Integer.parseInt(floorTf.getText().trim());
                        int areaNumber = Integer.parseInt(areaTf.getText().trim());
                        String name = unameTf.getText().trim();
                        String tel = telTf.getText().trim();

                        // 封装
                        Building bd = new Building(idNumber, houseNumber, buildingNumber,
                                floorNumber, areaNumber, name, tel);

                        // 更新数据库
                        dsl.updateBil(bd);

                        JOptionPane.showMessageDialog(this, "修改成功！");
                        dispose();
                    }

                }
            }
        }
    }
}
