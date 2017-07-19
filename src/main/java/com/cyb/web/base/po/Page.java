package com.cyb.web.base.po;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月19日
 */
public class Page<T> {
	Log log = LogFactory.getLog(Page.class);
	//当前页码  
    private int cunrrentPage;  
    //全部页码  
    private int totalPage;  
    //全部数据  
    private int totalCount;  
    //每页多少数据  
    private int pageSize;  
    //查询返回结果  
    private List<T> list=new ArrayList<T>();  
    //分页链接  
    private String url;  
      
    public String getUrl()  
    {  
        return url;  
    }  
  
    public void setUrl(String url)  
    {  
        this.url = url;  
    }  
  
    public int getCunrrentPage()  
    {  
        return cunrrentPage;  
    }  
  
    public void setCunrrentPage(int cunrrentPage)  
    {  
        if(cunrrentPage<0){  
            cunrrentPage=0;  
        }  
        this.cunrrentPage = cunrrentPage;  
    }  
  
    public int getTotalPage()  
    {  
        if(totalCount%pageSize==0){  
            totalPage=totalCount/pageSize;  
        }else{  
            totalPage=totalCount/pageSize+1;  
        }  
        return totalPage;  
    }  
  
    public void setTotalPage(int totalPage)  
    {  
        if(totalPage<0){  
            totalPage=0;  
        }  
        this.totalPage = totalPage;  
    }  
  
    public int getTotalCount()  
    {  
        return totalCount;  
    }  
  
    public void setTotalCount(int totalCount)  
    {  
        if(totalCount<0){  
            totalCount=0;  
        }  
        this.totalCount = totalCount;  
    }  
  
    public int getPageSize()  
    {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize)  
    {  
        if(pageSize<20){  
            pageSize=20;  
        }  
        this.pageSize = pageSize;  
    }  
  
    public List<T> getList()  
    {  
        return list;  
    }  
  
    public void setList(List<T> list)  
    {  
        this.list = list;  
    }  
}
