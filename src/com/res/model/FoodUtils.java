package com.res.model;

import java.util.List;
import java.util.Map;

import com.res.utils.JdbcUtlis;
import com.res.utils.Pager;

public class FoodUtils {
    /**
     * 获取总行数
     * 
     * @throws Exception
     * @throws NumberFormatException
     */
    public Integer getTotalCount(String foodName) throws NumberFormatException, Exception {
        String sql = "select count(rowid) c from food where FOODNAME like '%" + foodName + "%'";
        return Integer.valueOf(JdbcUtlis.getDataBySql(sql).get(0).get("C"));
    }

    /**
     * 通过菜名获取总行数并初始化Pager
     * 
     * @param foodName
     *            菜品名字
     * @param curPage
     *            当前页
     * @return
     * @throws Exception
     */
    public Pager getPager(String foodName, Integer curPage) throws Exception {
        if (foodName == null) {
            foodName = "";
        }

        Integer totalCount = getTotalCount(foodName);
        Pager pager = new Pager(curPage, totalCount, null);

        List<Map<String, String>> datas = JdbcUtlis
                .getDataBySql("select * from (select f.* , rownum rn from food f where f.FOODNAME like '%" + foodName
                        + "%') where rn >= " + pager.getStartIndex() + " and rn <= " + pager.getEndIndex()
                        + " order by FOODID");
        pager.setData(datas);
        return pager;
    }

    /**
     * 添加新菜品
     * 
     * @param typeID
     *            所属菜系ID
     * @param foodName
     *            菜品名
     * @param price
     *            价格
     * @param desc
     *            简介
     * @param imgPath
     *            图片路径
     * @throws Exception
     */
    public void addFood(String typeID, String foodName, String price, String desc, String imgPath) throws Exception {
        String sql = "insert into food values((select nvl(max(FOODID) , 0) from food) + 1 , '" + typeID + "' , '" + foodName
                + "' , " + price + " , '" + imgPath + "' , '" + desc + "')";
        JdbcUtlis.execute(sql);
    }

    /**
     * 根据菜品ID删除菜品类别
     * 
     * @param foodID
     *            菜品ID
     * @throws Exception
     */
    public void delFood(String foodID) throws Exception {
        String sql = "delete from food where FOODID = " + foodID;
        JdbcUtlis.execute(sql);
    }

    /**
     * 根据菜品ID获取菜品信息
     * 
     * @param foodID
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> getFoodInfoById(String foodID) throws Exception {
        String sql = "select * from food where FOODID = " + foodID;
        List<Map<String, String>> list = JdbcUtlis.getDataBySql(sql);
        return list;
    }

    /**
     * 更新菜品信息 
     * @param typeID 所属类别ID
     * @param foodName 菜品名
     * @param price 价格
     * @param desc 简介
     * @param imgPath 图片路径
     * @param foodID 菜品ID
     * @return 是否成功更新
     * @throws Exception
     */
    public boolean updateFood(String typeID, String foodName, String price, String desc, String imgPath,
            String foodID) throws Exception {
        String sql = "update food set TYPEID = " + typeID + " , FOODNAME = '" + foodName + "' , PRICE = " + price
                + " , IMAGEPATH = '" + imgPath + "' , DESCS = '" + desc + "' where FOODID = " + foodID;
        
        boolean isUpdateSuccess = false;
        int line = JdbcUtlis.execute(sql);
        if (line == 1)
            isUpdateSuccess = true;
        
        return isUpdateSuccess;
    }
}
