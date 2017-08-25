package com.cyb.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.date.DateUtil;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月7日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);
	public static void main(String[] args) throws SQLException {
		String sql="INSERT INTO `test`.`tb_model`"
				+ " (`id`, `czsj`, `czyid`, `czymc`, `zt`)"
				+ " VALUES (?, ?, ?, ?, '0')";
		
	    Connection conn = DriverManager.
	    		getConnection("jdbc:mysql://localhost:3306/test","root","111111");
	    System.out.println("new Connection:"+conn);
	    System.out.println("is valid :"+conn.isValid(2));
	    System.out.println("is close :"+conn.isClosed());
	    conn.close();
	    System.out.println("is valid :"+conn.isValid(2));
	    System.out.println("is close :"+conn.isClosed());
	    System.out.println("after close:"+conn);
	    /* PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, DateUtil.date2long14());
	    ps.setString(2, "czyid");
	    ps.setString(3, "iechenybname");
	    ps.setString(4, "four ");
	    ps.addBatch();
	    ps.executeBatch();*/
	 }
	
}
