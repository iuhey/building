package ForAdmin.Interface;
import ForAdmin.Datebase.Condb;
import ForAdmin.DateClass.Register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDealImpl implements RegisterDeal {
    //创建数据库连接对象（通过Condb类）
    private Condb conn = new Condb();
    private static PreparedStatement ps; //创建PreparedStatement对象，用于执行编译sql语
    /*
        判断注册的编号和用户名是否存在
     */
    public boolean RegisterExamine(Register r)  {
        int ans = 0;
        try{
            conn.getconn(); //创建链接
            String sql="select * from Log where 用户名=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,r.getUsers());
            ResultSet rs = ps.executeQuery();

            if(rs.next())  //如果rs中有结果，则匹配成功
                ans = 1;

        }
        catch(SQLException ex){

        }finally {
            conn.closeconn(); //关闭数据库链接
        }

        if(ans == 1) return true;
        else return false;
    }

        //注册方法
    public int addUsers(Register r)  {
        //用户已存在，注册失败
         if(RegisterExamine(r)){
             //System.out.println("用户名已存在");
              return 2;
          }
         //密码和确认密码不一致，注册失败
         else if(!(r.getPassword().equals(r.getConfirmpassword()))){
             //System.out.println("密码和确认密码不一致");
             return 3;
         }
         else {
                String sql="insert into Log(用户名,密码)  values (?,?) ";
                try {
                    conn.getconn();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,r.getUsers());
                    ps.setString(2,r.getPassword());
                    ps.executeUpdate();
                    //System.out.println("注册成功");
                    return 1;
                }catch(SQLException ex) {
                     //System.out.println("注册失败");
                    return 0;
                }finally {
                    conn.closeconn();
                }
            }
    }
}



