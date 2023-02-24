package ForAdmin.Interface;
import ForAdmin.DateClass.Building;

import java.util.ArrayList;
import java.util.List;

//数据操作接口
public interface BuildDeal {
    // 获取数据表中的行数
    public int LineNumber();

    //插入数据
    public void newInsert(Building b);

    //根据门牌号删除信息
    public void deleteBilByHouseNumber(String HouseNumber);

    //更新数据
    public void updateBil(Building b);

    //根据门牌号查找
    public Building findByHouseNumber(String HouseNumber);

    //根据户主信息查找
    public ArrayList<Building> findByName(String Name);

    //根据楼号查找
    public ArrayList<Building> findByBuildingNumber(String BuildingNumber);

    //根据面积范围查找
    public ArrayList<Building> findBetweenArea(int minArea,int maxArea);

    //查找所有信息
    public ArrayList<Building> findAllB();

    //多个属性模糊  查找方法
    public List<Object> search(String sql, List<Object> sqlParameter);
}
