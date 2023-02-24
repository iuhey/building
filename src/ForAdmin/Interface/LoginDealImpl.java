package ForAdmin.Interface;
import ForAdmin.DateClass.Login;
import ForAdmin.Datebase.Condb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
     底层登录接口实现类
 */
public class LoginDealImpl  implements LoginDeal {

    //创建数据库连接对象（通过Condb类）
    Condb conn = new Condb();
    private static PreparedStatement ps; //创建PreparedStatement对象，用于执行编译sql语句
     int ans = 0;
    public boolean LoginExamine(Login login)  {
        try{
            conn.getconn(); //创建链接
            String sql="select * from Log where 用户名=? and 密码=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, login.getUsers());
            ps.setString(2, login.getPassword());
            ResultSet rs = ps.executeQuery();

            if(rs.next())  //如果rs中有结果，则匹配成功
                ans = 1;
        }
        catch(SQLException ex){

        }finally {
            conn.closeconn(); //关闭数据库链接
    }
        if(ans == 1)
            return true;
        else
            return false;
    }

    // 返回1，说明数据表中存在对应的账号和密码，则登录成功
     public int JudgeLogin(Login login) {
        try {
            if(LoginExamine(login)) {
                return 1;
            }else {
                return 0;
            }
        }catch(Exception e) {

        }
        return 0;
    }

}
