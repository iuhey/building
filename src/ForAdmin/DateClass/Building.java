package ForAdmin.DateClass;

//楼宇
public class Building {
    public Integer Number,Area,Floor;
    public String HouseNumber,BuildingNumber ,NAME,Tel ;

    /*
        编号     Number
        门牌号   HouseNumber
        楼栋号   BuildingNumber
        楼层号    Floor
        房屋面积   Area
        户主姓名   NAME
        户主电话   Tel
    */

    @Override
    public String toString() {
        return "建筑物{" +
                "Number=" + Number +
                ", Area=" + Area +
                ", Floor=" + Floor +
                ", HouseNumber='" + HouseNumber + '\'' +
                ", BuildingNumber='" + BuildingNumber + '\'' +
                ", NAME='" + NAME + '\'' +
                ", Tel='" + Tel + '\'' +
                '}';
    }

     public  Building(){

     }

    public Building(Integer Number, String HouseNumber, String BuildingNumber,Integer Floor, Integer Area, String Name, String Tel)
    {

        this.Number=Number;
        this.HouseNumber=HouseNumber;
        this.BuildingNumber=BuildingNumber;
        this.Floor=Floor;
        this.Area=Area;
        this.NAME=Name;
        this.Tel=Tel;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public Integer getArea() {
        return Area;
    }

    public void setArea(Integer area) {
        Area = area;
    }

    public Integer getFloor() {
        return Floor;
    }

    public void setFloor(Integer floor) {
        Floor = floor;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getBuildingNumber() {
        return BuildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        BuildingNumber = buildingNumber;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
