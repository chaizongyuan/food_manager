package com.res.utils;

import java.util.List;

public class Pager {
    
    /**
     * 下一页算法：
     *  this.curPage == totalPage ? this.curPage : this.curPage + 1
     */
    private Integer nextPage;
    
    /**
     * 前一页算法：
     *  prePage = curPage == 1 ? 1 : curPage - 1
     */
    private Integer prePage;
    
    //当前页
    private Integer curPage;
    
    //总记录数量
    private Integer totalCount;
    
    /**
     * 总页数的算法：
     *  totalPage = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize + 1
     */
    private Integer totalPage;
    
    //每页条数
    private Integer pageSize;
    
    /**
     * limit起点算法：
     *  curPage * size - size + 1
     */
    private Integer startIndex;
    
    /**
     * limit终点算法：
     *  curPage * size
     */
    private Integer endIndex;
    
    //分页数据容器
    @SuppressWarnings("rawtypes")
    private List data;

    /**
     * 构造方法
     * @param curPage 当前页
     * @param totalCount 总记录数
     * @param pageSize 每页大小
     */
    public Pager(Integer curPage, Integer totalCount, Integer pageSize) {
        this.curPage = curPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize == null ? 10 : pageSize;  //每页条数
        //总页数
        this.totalPage = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : (totalCount / this.pageSize) + 1;
        this.nextPage = this.curPage == totalPage ? this.curPage : this.curPage + 1;//下一页
        this.prePage = curPage == 1 ? 1 : curPage - 1;//前一页
        this.startIndex = (curPage - 1) * this.pageSize + 1;  //limit起点
        this.endIndex = curPage * this.pageSize; //limit终点
        
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    @SuppressWarnings("rawtypes")
    public List getData() {
        return data;
    }

    @SuppressWarnings("rawtypes")
    public void setData(List data) {
        this.data = data;
    }
    
    public static void main(String[] args) {
        Integer curPage = 0;
        Integer totalCount = 20;
        Integer pageSize = 5;
        
        Pager pager = new Pager(curPage , totalCount , pageSize);
        
        System.out.println("分页起点: " + pager.getStartIndex());
        System.out.println("分页终点: " + pager.getEndIndex());
        System.out.println("上一页: " + pager.getPrePage());
        System.out.println("下一页: " + pager.getNextPage());
    }
}
