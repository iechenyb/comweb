package com.cyb.web.xzzx.controller;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.constant.Contants;
import com.cyb.web.utils.Configuration;
import com.cyb.web.xzzx.service.XzzxService;
import com.cyb.web.xzzx.vo.QHFileVo;
/**
 * 
 * 功能描述：下载中心管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:53:55
 */
@Controller
@RequestMapping("xzzx")
public class XzzxController extends BaseController{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月8日下午2:20:21</br>
	 */
	@Resource(name = "xzzxService")
	XzzxService service;
    /**
     * 
     * 作者:iechenyb</br>
     * 功能描述： 新增文件类型</br>
     * 创建时间：2017年1月3日上午10:54:05</br>
       @param fileType
       @return
     */
	@ResponseBody
	@RequestMapping("upload")
	public Map<String, Object> uploadFile(QHFileVo file) {
		try {} catch (Exception e) {
			e.printStackTrace();
			setMsgMap(FAILURE, "文件信息上传失败！");
		}
		return msgMap;
	}

	/**
	 * 更新注意事项 ：更新文件时，不更文件名，只更新内容
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月10日上午9:59:30</br>
	 */	
	@ResponseBody
	@RequestMapping("updFile")
	public Map<String, Object> updFile(QHFileVo file) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			setMsgMap(FAILURE, "文件信息更新失败！");
		}
		return msgMap;
	}
	
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 下载文件</br>
	 * 创建时间：2017年1月3日上午10:56:09</br>
	   @param response
	   @param id
	 */
	@RequestMapping("download")
	public  void downLoadFile(HttpServletResponse response,String id) {
		OutputStream out = null;
		try {
			String path = Contants.WEBPATH+Configuration.get("xzzxtmppath");
			File file = new File( path);
			if(!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
			}
			String fileName = "";
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"),"iso-8859-1"));
			out = response.getOutputStream();
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
