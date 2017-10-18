package com.cyb.web.mongodb.dao;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */
public class IdEntity implements Serializable {

    private static final long serialVersionUID = 33633625616087044L;
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}