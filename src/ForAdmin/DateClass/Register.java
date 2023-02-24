package ForAdmin.DateClass;
/*
     注册记录类:存储数据库中的列名信息，并为其实例化对象的属性赋值
 */
public class Register {
    /**
     *  users  用户名
     *  password  密码
     *  confirmpassword 确认密码
     *
     */

    //成员变量
    private String users,password,confirmpassword;

    /**
     *  构造方法
     */

    //无参构造
    public  Register(){

    }

    //有参构造
    public Register(String users, String password, String confirmpassword) {
        this.users = users;
        this.password=password;
        this.confirmpassword=confirmpassword;
    }
    /**
     *    Getter和Setter方法
     */

    //获取用户名
    public String getUsers() {
        return users;
    }
    //设置用户名
    public void setUsers(String users) {
        this.users = users;
    }
    //获取密码
    public String getPassword() {
        return password;
    }
    //设置密码
    public void setPassword(String password) {
        this.password = password;
    }
    //获取确认密码
    public String getConfirmpassword() {
        return confirmpassword;
    }
    //设置确认密码
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
