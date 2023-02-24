package ForAdmin.Datebase;

import java.sql.*;

/*
  创建链接和关闭链接
 */
public class Condb {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lc?useSSL=false&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASS = "1234";
    Connection conn=null;
    Statement  stmt=null;
    PreparedStatement ps=null;
    //创建数据库链接
    public Connection getconn(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
      return  conn;
    }

    //关闭数据库链接
    public  void  closeconn(){
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            conn=null;
        }
    }

//创建stmt对象
    public Statement createStatement() {
        try {
              stmt= conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stmt;
    }

//获取preparestatement对象
    public PreparedStatement prepareStatement(String s) {
        try {
            ps = conn.prepareStatement(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  ps;
    }

    public static void main(String[] args) {
        new Condb().getconn();
    }
}
