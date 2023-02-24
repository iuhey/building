package ForUsers.GUI.frame;

import ForUsers.GUI.others.JTextFieldHintListener;
import ForUsers.GUI.others.ScreenUtils;
import ForAdmin.DateClass.Building;
import ForUsers.services.BuildService;
import ForUsers.services.BuildServicelmpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private BuildService buildService = new BuildServicelmpl();  // 多态，左边是父类，右边是子类

    //容器
    private Container container = getContentPane();

    // 四个按钮
    private JButton AddButton ;
    private JButton DeleteButton ;
    private JButton ChangeButton ;
    private JButton SearchButton ;

    // 输入框
    private JTextField InputTextField;

    // 下拉列表
    private  JComboBox <String> comboBoxox;

    // 表格
    private  JTable table;

    // 表格模型
    private DefaultTableModel defaultTableModel;

    public MainFrame(String title){
        super(title);

        container.setLayout(null);  // 选择绝对布局
        container.setBackground(Color.WHITE);  // 选择背景色

        setBounds((ScreenUtils.getScreenWidth() - 790)/2,(ScreenUtils.getScreenHeight() - 550)/2,790,550);  // 绝对布局
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 点 × 程序停止

        InitButton();  // 按钮布局
        InitText();   // 文本框布局
        InitTable();  // 表格布局

        setVisible(true);  // 主页面可视
        setResizable(false); // 主页面不可以改变大小
    }

    private void InitButton(){
        // 四个按钮：增加、删除、修改、查找
        AddButton =  new JButton("增加");
        DeleteButton =  new JButton("删除");
        ChangeButton =  new JButton("修改");
        SearchButton =  new JButton("查找");

        // 四个按钮的设置：绝对布局 与 背景色
        AddButton.setBounds(10,10,78,30);
        AddButton.setBackground(Color.WHITE);

        DeleteButton.setBounds(100,10,78,30);
        DeleteButton.setBackground(Color.WHITE);

        ChangeButton.setBounds(190,10,78,30);
        ChangeButton.setBackground(Color.WHITE);

        SearchButton.setBounds(500,10,78,30);
        SearchButton.setBackground(Color.WHITE);

        // 给按钮添加图标
        AddButton.setIcon(new ImageIcon("src/ForUsers/Picture/1.png"));
        DeleteButton.setIcon(new ImageIcon("src/ForUsers/Picture/2.png"));
        ChangeButton.setIcon(new ImageIcon("src/ForUsers/Picture/3.png"));
        SearchButton.setIcon(new ImageIcon("src/ForUsers/Picture/4.png"));

        // 放到容器中去
        container.add(AddButton);
        container.add(DeleteButton);
        container.add(ChangeButton);
        container.add(SearchButton);

        // 给按钮设置监听器
        AddButton.addActionListener(e -> Add());
        DeleteButton.addActionListener(e -> Delete());

        ChangeButton.addActionListener(e -> Modify());

        SearchButton.addActionListener(e -> Search(comboBoxox.getSelectedIndex()));

    }

    private void InitText() {
        String items[] = {"楼栋查询","面积查询","姓名查询","门牌号查询"};

        // 定义，以及添加下拉列表中的元素
        comboBoxox = new JComboBox<>(items);

        // 设置背景色
        comboBoxox.setBackground(Color.WHITE);

        // 绝对布局
        comboBoxox.setBounds(400,10,75,30);

        // 添加到容器中
        container.add(comboBoxox);

        // 定义，设置长度
        InputTextField = new JTextField(20);

        // 绝对布局
        InputTextField.setBounds(600,10,150,30);

        // 添加到容器中
        container.add(InputTextField);

        // 设置默认提示文字
        InputTextField.addFocusListener(new JTextFieldHintListener(InputTextField,"楼栋号"));

        String text [] = {"楼栋号","最小面积,最大面积","户主姓名","门牌号"};  // 提示文字

        // 设置监听器，不同的选中内容对应不同的提示文字
        comboBoxox.addItemListener(e -> InputTextField.addFocusListener(new JTextFieldHintListener(InputTextField,text[comboBoxox.getSelectedIndex()])));
    }

    private void InitTable(){
        // 列头
        String[] cNames = {"编号","门牌号","楼栋","楼层","住宅面积","户主姓名","户主电话"};

        // DealService dealService = new DealServicelmpl();

        int number = buildService.LineNumber();//行数
        String [][] tV = new String[number][cNames.length];

        //获取全部行数详情信息
        List<Building> allColum = buildService.findAllB();
        Building tempBuilding = null;

        for(int i=0;i<tV.length;i++){
            tempBuilding = allColum.get(i);
            tV[i][0] = String.valueOf(tempBuilding.Number);
            tV[i][1] = String.valueOf(tempBuilding.HouseNumber);
            tV[i][2] = String.valueOf(tempBuilding.BuildingNumber);
            tV[i][3] = String.valueOf(tempBuilding.Floor);
            tV[i][4] = String.valueOf(tempBuilding.Area);
            tV[i][5] = String.valueOf(tempBuilding.NAME);
            tV[i][6] = String.valueOf(tempBuilding.Tel);
        }

        //设置选中
        defaultTableModel = new DefaultTableModel(tV, cNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        table = new JTable(defaultTableModel);

/*
        // 选中的颜色
        table.setSelectionForeground(Color.RED);
        table.setSelectionBackground(Color.yellow);
*/

        // 定义表格滚动面板
        JScrollPane TableScrollPane = new JScrollPane(table);

        // 绝对布局
        TableScrollPane.setBounds(15,100,750,400);  // 400

        // 添加到容器中
        container.add(TableScrollPane);

        // 让表格中的内容居中
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);

        table.getTableHeader().setReorderingAllowed(false); //设置表格列不可拖拽
//        table.setEnabled(false); //设置表格为不可编辑
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );  // 多选
    }

    private void Add(){
        new AddFrame();
        Refresh(buildService.findAllB());
    }

    private void Modify(){
        new ModifyFrame(table);
        Refresh(buildService.findAllB());
    }

    private void Delete(){
        int [] selectedRow = table.getSelectedRows();  // 得到被选中的行号

        if(selectedRow.length==0){
            JOptionPane.showMessageDialog(this, "请选中要删除的楼宇信息！");
            return;
        }

        // 从表格、数据库中删除
        for(int i=selectedRow.length-1; i>=0; i--){
            buildService.deleteBilByHouseNumber(table.getValueAt(selectedRow[i], 1).toString());
            defaultTableModel.removeRow(selectedRow[i]);
        }
        JOptionPane.showMessageDialog(this, "删除成功！");
    }

    private void Search(int op){
        // 输入框中的内容为空，则打印出数据库中的全部内容
        if(InputTextField.getText().equals("楼栋号")|| InputTextField.getText().equals("最小面积,最大面积") || InputTextField.getText().equals("户主姓名")|| InputTextField.getText().equals("门牌号")) {  Refresh(buildService.findAllB()); }

        else {
            switch (op){

                // 按照楼栋号查询
                case 0:{
                    // 楼栋号只为大写的英文字母,且只为一位
                    if(InputTextField.getText().length()==1&&InputTextField.getText().charAt(0)>='A'&&InputTextField.getText().charAt(0)<='Z')
                        Refresh(buildService.findByBuildingNumber(InputTextField.getText()));
                    else JOptionPane.showMessageDialog(null,"请输入正确的查找格式！","格式错误",JOptionPane.ERROR_MESSAGE);
                    break;
                }

                // 按照面积查询
                case 1:{  // 加判错机制
                    String Text = InputTextField.getText();

                    // 只允许有一个特殊字符','或'，' 其余的均为数字字符

                    int special_cnt = 0,pos = 0;
                    for(int i=0;i<Text.length();i++)
                        if(!(Text.charAt(i)>='0'&&Text.charAt(i)<='9')) {
                         special_cnt++; pos=i;
                    }

                    if(special_cnt>1||special_cnt==0||(special_cnt==1&&Text.charAt(pos)!=','&&Text.charAt(pos)!='，')){
                        JOptionPane.showMessageDialog(null,"请输入正确的查找格式！","格式错误",JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    int minarea = 0, maxarea = 0;

                    for(int i=0;i<pos;i++) minarea=minarea*10+Text.charAt(i)-'0';
                    for(int i=pos+1;i<Text.length();i++) maxarea=maxarea*10+Text.charAt(i)-'0';

                    if(minarea>maxarea){
                        JOptionPane.showMessageDialog(null,"请输入合法的查询区间！","提示",JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    Refresh(buildService.findBetweenArea(minarea,maxarea));

                    break;
                }

                // 按照姓名查询,支持模糊查询
                case 2:{ // 无法加判断机制
                    Refresh(buildService.findByName(InputTextField.getText()));
                    break;
                }

                // 按照门牌号查询
                case 3:{
                    Building building = buildService.findByHouseNumber(InputTextField.getText());

                    // 如果没有找到，加.....
                    if(building == null )  {
                        JOptionPane.showMessageDialog(null,"查询结果为空，请重新查找！","查询结果",JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    List<Building> buildings = new ArrayList<>();
                    buildings.add(0,building);
                    Refresh(buildings);
                    break;
                }

            }
        }

    }

    private void Refresh(List<Building> allrows){  // allrows 中的内容同步到表格上面
        if(allrows.size()==0){
            System.out.println("yyy");
            JOptionPane.showMessageDialog(null,"查询结果为空，请重新查找！","查询结果",JOptionPane.ERROR_MESSAGE);
            return ;
        }

        defaultTableModel.setRowCount(0);  // 清空现有表格

        // 将 allrows 中的内容 即查询结果放到表格中
        for(int i=0;i<allrows.size();i++){
            Building tempBuilding = allrows.get(i);
            String [] s = new String[10];
            s[0]= String.valueOf(tempBuilding.Number);
            s[1] = String.valueOf(tempBuilding.HouseNumber);
            s[2] = String.valueOf(tempBuilding.BuildingNumber);
            s[3] = String.valueOf(tempBuilding.Floor);
            s[4] = String.valueOf(tempBuilding.Area);
            s[5] = String.valueOf(tempBuilding.NAME);
            s[6] = String.valueOf(tempBuilding.Tel);
            defaultTableModel.addRow(s);
        }
    }

}

