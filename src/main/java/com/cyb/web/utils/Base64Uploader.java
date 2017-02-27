package com.cyb.web.utils;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
public class Base64Uploader {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月17日下午1:33:49</br>
	 */
	  public static State save(String content, Map<String, Object> conf)
	  {
	    byte[] data = decode(content);

	    long maxSize = ((Long)conf.get("maxSize")).longValue();

	    if (!validSize(data, maxSize)) {
	      return new BaseState(false, 1);
	    }

	    String suffix = FileType.getSuffix("JPG");

	    String savePath = (String)conf.get("savePath")+(String)conf.get("filename")+UUID.randomUUID().toString().replace("-", "");
	    savePath = savePath + suffix;
	    String physicalPath = (String)conf.get("rootPath") + savePath;
	    State storageState = StorageManager.saveBinaryFile(data, physicalPath);//保存到本地
	    File  localFile = new File(physicalPath) ;
        FtpClient.uploadFile((String)conf.get("savePath"), new File(physicalPath)); 
        boolean keepLocalFile = "false".equals(conf.get("keepLocalFile")) ? false : true;
        if(!keepLocalFile){
        	localFile.delete();//本地不保存
        }
	    if (storageState.isSuccess()) {
	      storageState.putInfo("url", PathFormat.format(savePath));
	      storageState.putInfo("type", suffix);
	      storageState.putInfo("original", "");
	    }
	    return storageState;
	  }

	  private static byte[] decode(String content) {
	    return Base64.decodeBase64(content);
	  }

	  private static boolean validSize(byte[] data, long length) {
	    return data.length <= length;
	  }
}
