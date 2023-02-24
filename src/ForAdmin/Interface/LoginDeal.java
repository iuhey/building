package ForAdmin.Interface;

import ForAdmin.DateClass.Login;

/*
    底层数据操作接口
 */

public interface LoginDeal {
     // 返回1，说明数据表中存在对应的账号和密码，则登录成功
     // 返回0，登录失败
     int JudgeLogin(Login login);
}
