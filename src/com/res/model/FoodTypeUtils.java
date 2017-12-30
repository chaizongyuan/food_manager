package com.res.model;

import java.util.List;
import java.util.Map;

import com.res.utils.JdbcUtlis;
import com.res.utils.Pager;

public class FoodTypeUtils {

    /**
     * 获取总行数
     * 
     * @throws Exception
     * @throws NumberFormatException
     */
    public Integer getTotalCount(String typeName) throws NumberFormatException, Exception {
        String sql = "select count(rowid) c from foodtype where TYPENAME like '%" + typeName + "%'";
        return Integer.valueOf(JdbcUtlis.getDataBySql(sql).get(0).get("C"));
    }

    /**
     * 通过菜系名获取菜系列表
     * @param typeName 餐桌名
     * @param curPage 当前页
     * @return
     * @throws Exception
     */
    public Pager getPager(String typeName, Integer curPage) throws Exception {
        if (typeName == null) {
            typeName = "";
        }

        Integer totalCount = getTotalCount(typeName);
        Pager pager = new Pager(curPage, totalCount, null);

        List<Map<String, String>> datas = JdbcUtlis
                .getDataBySql("select * from (select f.* , rownum rn from foodtype f where f.TYPENAME like '%" + typeName + "%') where rn >= " + pager.getStartIndex() + " and rn <= " + pager.getEndIndex() + " order by TYPEID");
        pager.setData(datas);
        return pager;
    }
    
    /**
     * 添加新菜系
     * @param typeName
     * @throws Exception
     */
    public void addFoodType(String typeName) throws Exception{
        String sql = "insert into foodtype values((select nvl(max(TYPEID) , 0) + 1 from foodtype) , '" + typeName + "')";
        JdbcUtlis.execute(sql);
    }
    
    /**
     * 根据类别ID删除菜品类别 
     * @param typeID 菜品ID
     * @throws Exception
     */
    public void delFoodType(String typeID) throws Exception{
        String sql = "delete from foodtype where TYPEID = " + typeID;
        JdbcUtlis.execute(sql);
    }
    
       /**
        * 根据菜系ID更新菜系名
        * @param typeID 菜系ID
        * @param typeName 新菜系名
        * @throws Exception
        */
    public void updateFoodType(String typeID , String typeName) throws Exception{
        String sql = "update foodtype set TYPENAME = '" + typeName + "' where TYPEID = " + typeID;
        JdbcUtlis.execute(sql);
    }
    
    /**
     * 根据菜系ID获取菜系名
     * @param typeID
     * @return
     * @throws Exception 
     */
    public static String getTypeNameById(String typeID) throws Exception{
        String sql = "select TYPENAME from foodtype where TYPEID = " + typeID;
        List<Map<String, String>> list = JdbcUtlis.getDataBySql(sql);
        return list.get(0).get("TYPENAME");
    }
    
    /**
     * 获取所有菜系名以及ID
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> getAllType() throws Exception{
        String sql = "select TYPENAME , TYPEID from foodtype";
        List<Map<String, String>> list = JdbcUtlis.getDataBySql(sql);
        return list;
    }
}
