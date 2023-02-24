package ForUsers.services;

import ForAdmin.DateClass.Login;
import ForAdmin.Interface.LoginDealImpl;

/*
     前端登录接口实现类
 */
public class LoginServiceImpl implements LoginService {
    //创建一个登录实现类对象
    LoginDealImpl lg=new LoginDealImpl();
    @Override
    public int JudgeLogin(Login login) {
        //返回调用登陆方法的结果
       return lg.JudgeLogin(login);
    }
}
