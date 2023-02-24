package ForAdmin.Interface;

import ForAdmin.Datebase.Condb;
import ForAdmin.DateClass.Building;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildDealImpl implements BuildDeal {

    private static PreparedStatement ps;
    private static Statement stmt;
    //创建数据库连接对象
    Condb conn = new Condb();

    //无参构造方法
    public BuildDealImpl() {
    }

    public int LineNumber() {
        conn.getconn();//获取链接
        try {
            ps = conn.prepareStatement("select count(*) from Building");//聚合函数查询
            ResultSet resultSet = ps.executeQuery();//获取结果
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                return anInt;//返回聚合函数查询
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.closeconn();
        }
        return 0;
    }

    //新增数据方法
    public void newInsert(Building b) {
        try {
            conn.getconn(); //获取连接
            //执行sql语句
            ps = conn.prepareStatement("insert into building (门牌号,楼栋,楼层,住宅面积,户主姓名,电话) values(?,?,?,?,?,?)");
            ps.setString(1, b.HouseNumber); //设置门牌号
            ps.setString(2, b.BuildingNumber);//设置楼栋
            ps.setInt(3, b.Floor);//设置楼层
            ps.setInt(4, b.Area);//设置住宅面积
            ps.setString(5, b.NAME);//设置户主姓名
            ps.setString(6, b.Tel);//设置户主电话

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.closeconn(); //关闭链接
        }
    }

    //根据门牌号删除数据
    public void deleteBilByHouseNumber(String HouseNumber) {
        try {
            conn.getconn();//获取数据库链接
            //执行sql语句
            ps = conn.prepareStatement("delete from Building where 门牌号 =?");
            ps.setString(1, HouseNumber); //设置门牌号
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            conn.closeconn(); //关闭数据库链接
        }
    }

    //根据门牌号修改信息
    public void updateBil(Building b) {
        try {
            conn.getconn();//获取链接
            //执行sql语句
            ps = conn.prepareStatement("update Building set 住宅面积=?,户主姓名=?,电话=?where 门牌号=?");

            ps.setInt(1, b.Area);//设置主住宅面积
            ps.setString(2, b.NAME);//设置户主姓名
            ps.setString(3, b.Tel); //设置户主电话
            ps.setString(4, b.HouseNumber); //设置门牌号
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            conn.closeconn();//关闭数据库链接
        }
    }

    //根据门牌号查找信息
    public Building findByHouseNumber(String HouseNumber) {   //创建楼宇对象
        Building b = null;
        try {
            conn.getconn();//获取数据库链接
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Building where 门牌号='" + HouseNumber + "'");
            if (rs.next())//如果当前语句不是最后一条，则进入循环
            {
                b = new Building(rs.getInt(1), HouseNumber, rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException ex) {

        } finally {
            conn.closeconn();
        }
        return b;
    }

    //根据户主姓名查找信息（模糊查询）
    public ArrayList<Building> findByName(String Name) {   //创建结果集
        ArrayList<Building> result = new ArrayList<Building>();
        try {
            conn.getconn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Building where 户主姓名 like '%" + Name + "%'");
            while (rs.next()) {
                Building b = new Building(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                result.add(b);
            }
        } catch (SQLException ex) {

        } finally {
            conn.closeconn();
        }
        return result;//返回结果
    }

    //根据楼栋查找信息
    public ArrayList<Building> findByBuildingNumber(String BuildingNumber) {
        ArrayList<Building> result = new ArrayList<Building>();
        try {
            conn.getconn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Building where 楼栋='" + BuildingNumber + "'");
            while (rs.next()) {
                Building b = new Building(rs.getInt(1), rs.getString(2), BuildingNumber, rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                result.add(b);
            }
        } catch (SQLException ex) {

        } finally {
            conn.closeconn();
        }
        return result;
    }

    //根据住宅面积区间查找信息
    public ArrayList<Building> findBetweenArea(int minArea, int maxArea) {
        ArrayList<Building> result = new ArrayList<Building>();
        try {
            conn.getconn();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Building where 住宅面积 between " + minArea + " and " + maxArea + "");
            while (rs.next()) {
                Building b = new Building(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                result.add(b);
            }
        } catch (SQLException ex) {

        } finally {
            conn.closeconn();
        }
        return result;
    }

    //查询所有信息
    public ArrayList<Building> findAllB() {
        ArrayList<Building> result = new ArrayList<Building>();
        try {
            conn.getconn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Building order by 编号");
            while (rs.next()) {
                Building b = new Building(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                result.add(b);
            }
        } catch (SQLException ex) {

        } finally {
            conn.closeconn();
        }
        return result;
    }

    /**
     * 生成待模糊的变量 形如'% xxxx %'
     */
    private static List<Object> sqlP = new ArrayList<Object>();

    public List<Object> getSqlP() {
        return sqlP;
    }

    /**
     * @param sqlParameter 参数
     * @param colParameter 列名
     * @return
     */
    public String SqlCreate(List<Object> sqlParameter, List<Object> colParameter) {
        //初始化sql语句，让其可以查询所有信息
        String sql = "SELECT * FROM Building where 1=1";
        //
        sqlP.clear();
        int j = 0;
        if (sqlParameter.size() != 0 && colParameter.size() != 0) {
            for (int i = 0; i < sqlParameter.size(); i++) {
                //遍历参数
                //拼接
                if (sqlParameter.get(i) != null) {
                    sql += " AND " + colParameter.get(i) + " like ?";
                    //保留参数
                    sqlP.add(j, "%" + sqlParameter.get(i) + "%");
                    j++;
                }

            }
        }
        //遍历参数，将不为空的参数对应的属性查询语句加入sql

        //返回sql语句
        return sql;
    }

    //执行查询操作
    public List<Object> search(String sql, List<Object> sqlParameter) {
//        System.out.println(sqlParameter);
        List<Object> result = new ArrayList<>();
        ResultSet rs;
        result.clear();
        try {
            //创建链接Condb conn=new Condb();
            conn.getconn();
            //获取statement对象
            PreparedStatement prestmt = conn.prepareStatement(sql);
            //替换占位符
            if (sqlParameter.size() != 0) {
                for (int i = 1; i <= sqlParameter.size(); i++) {
                    prestmt.setObject(i, sqlParameter.get(i - 1));
                }

                rs = prestmt.executeQuery();

                while (rs.next()) {
                    Building b = new Building(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                    result.add(b);
                }

            } else if (sqlParameter.size() == 0) {
                rs = prestmt.executeQuery();
                while (rs.next()) {
                    Building b = new Building(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                    result.add(b);
                }
            }
        } catch (Exception ex) {

        } finally {
            //关闭数据库连接
            conn.closeconn();
        }
        return result;
    }

    /**
     * @param sqlParameter 待模糊匹配的参数
     * @param colParameter 被模糊匹配的列明
     * @return
     */
    public List<Object> searchBuildingVague(List<Object> sqlParameter, List<Object> colParameter) {
        String sql = SqlCreate(sqlParameter, colParameter);
        return search(sql, sqlP);
    }
}
