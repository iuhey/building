package ForAdmin.DateClass;
/*
     登录记录类:存储数据库中的列名信息，并为其实例化对象的属性赋值
 */
public class Login {

    /**
     *  users  用户名
     *  password  密码
     */

    //成员变量
    private String users,password;


    /**
     *  构造方法
     */

    //无参构造
    public  Login(){

    }
    //有参构造
    public Login(String users, String password) {
        this.users=users;
        this.password = password;
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


}
