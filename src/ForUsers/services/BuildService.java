package ForUsers.services;

import ForAdmin.DateClass.Building;

import java.util.List;

public interface BuildService {
    //查询统计个数
    int LineNumber();

    //插入数据
    void newInsert(Building b);

    //根据门牌号删除信息
    void deleteBilByHouseNumber(String houseNumber);

    //更新数据
    void updateBil(Building b);

    //根据门牌号查找
    Building findByHouseNumber(String HouseNumber);

    //根据户主信息查找（模糊查找）
    List<Building> findByName(String Name);

    //根据楼号查找
    List<Building> findByBuildingNumber(String buildingNumber);

    //根据面积范围查找
    List<Building> findBetweenArea(int minArea, int maxArea);

    //查找所有信息
    List<Building> findAllB();

    //多个属性模糊查询
    List<Object> searchVague(List<Object> sqlParameter);
}
