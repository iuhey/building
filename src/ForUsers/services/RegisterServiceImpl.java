package ForUsers.services;

import ForAdmin.DateClass.Register;
import ForAdmin.Interface.RegisterDeal;
import ForAdmin.Interface.RegisterDealImpl;

public class RegisterServiceImpl implements RegisterService {

    @Override
    public int addUsers(Register r) {
        //创建一个注册实现类的对象
        RegisterDeal rd=new RegisterDealImpl();
        //返回调用结果
        return rd.addUsers(r);
    }
}
