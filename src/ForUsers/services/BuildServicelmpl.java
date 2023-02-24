package ForUsers.services;
import ForAdmin.DateClass.Building;
import ForAdmin.Interface.BuildDealImpl;

import java.util.ArrayList;
import java.util.List;
public class BuildServicelmpl implements BuildService {
    BuildDealImpl bd=new BuildDealImpl();
    public static List<Object> collum= new ArrayList<Object>();
    static {
        collum.add("编号");
        collum.add("门牌号");
        collum.add("楼栋");
        collum.add("楼层");
        collum.add("住宅面积");
        collum.add("户主姓名");
        collum.add("电话");
    }

    public int LineNumber() {
        return  bd.LineNumber();
    }

    @Override
    /**
     *
     */
    public void newInsert(Building b) {
          bd.newInsert(b);
    }

    @Override
    public void deleteBilByHouseNumber(String houseNumber) {
        bd.deleteBilByHouseNumber(houseNumber);
    }

    @Override
    public void updateBil(Building b) {
        bd.updateBil(b);
    }

    @Override
    public Building findByHouseNumber(String HouseNumber) {
        return bd.findByHouseNumber(HouseNumber);
    }

    @Override
    public List<Building> findByName(String Name) {
        return bd.findByName(Name);
    }

    @Override
    public List<Building> findByBuildingNumber(String buildingNumber) {
      return   bd.findByBuildingNumber(buildingNumber);
    }

    @Override
    public List<Building> findBetweenArea(int minArea, int maxArea) {

        return bd.findBetweenArea(minArea,maxArea);
    }

    @Override
    public List<Building> findAllB() {
        return bd.findAllB();
    }

    @Override
    /**
     *
     * @param sqlParameter 待模糊匹配的参数
     * @param colParameter 被模糊匹配的列明
     * @return
     */
    public List<Object> searchVague(List<Object> sqlParameter)
    {
        return bd.searchBuildingVague(sqlParameter,collum);
    }
}
