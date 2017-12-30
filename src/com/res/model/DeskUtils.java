package com.res.model;

import java.util.List;
import java.util.Map;

import com.res.utils.JdbcUtlis;
import com.res.utils.Pager;

public class DeskUtils {
    /**
     * 获取总行数
     * 
     * @throws Exception
     * @throws NumberFormatException
     */
    public Integer getTotalCount(String deskName) throws NumberFormatException, Exception {
        String sql = "select count(rowid) c from desk where dname like '%" + deskName + "%'";
        return Integer.valueOf(JdbcUtlis.getDataBySql(sql).get(0).get("C"));
    }

    /**
     * 通过餐桌名获取餐桌列表
     * @param deskName 餐桌名
     * @param curPage 当前页
     * @return
     * @throws Exception
     */
    public Pager getPager(String deskName, Integer curPage) throws Exception {
        if (deskName == null) {
            deskName = "";
        }

        Integer totalCount = getTotalCount(deskName);
        Pager pager = new Pager(curPage, totalCount, null);

        List<Map<String, String>> datas = JdbcUtlis
                .getDataBySql("select * from (select d.* , rownum rn from desk d where d.dname like '%" + deskName + "%' order by d.deskid) where rn >= " + pager.getStartIndex() + " and rn <= " + pager.getEndIndex());
        pager.setData(datas);
        return pager;
    }
    
    /**
     * 添加餐桌
     * @param deskName Controller传递过来的餐桌名
     * @return 受影响的行数
     * @throws Exception
     */
    public void addDesk(String deskName) throws Exception{
        String sql = "insert into desk values((select nvl(max(DESKID) , 0) + 1 from desk) , '" + deskName + "' , 0 , '')";
        JdbcUtlis.execute(sql);
    }
    
    /**
     * 根据餐桌ID删除餐桌 
     * @param deskID 餐桌ID
     * @throws Exception
     */
    public void delDesk(String deskID) throws Exception{
        String sql = "delete from desk where DESKID = " + deskID;
        JdbcUtlis.execute(sql);
    }
    
    /**
     * 订桌
     * @param deskID
     * @throws Exception 
     */
    public void subDesk(String deskID) throws Exception{
        String sql = "update desk set DTIME = sysdate , DSTATE = 1 where DESKID = " + deskID;
        JdbcUtlis.execute(sql);
    }
    
    /**
     * 退订
     * @param deskID
     * @throws Exception
     */
    public void unSubDesk(String deskID) throws Exception{
        String sql = "update desk set DTIME = '' , DSTATE = 0 where DESKID = " + deskID;
        JdbcUtlis.execute(sql);
    }
}
