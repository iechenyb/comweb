package com.cyb.web.xzzx.controller;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.date.DateUtil;
import com.cyb.web.base.controller.BaseController;
import com.cyb.web.utils.Configuration;
import com.cyb.web.xzzx.po.SysFile;
import com.cyb.web.xzzx.service.XzzxService;
import com.cyb.web.xzzx.utils.ImageBase64;
import com.cyb.web.xzzx.vo.FileVo;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONArray;
/**
 * 
 * 功能描述：下载中心管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:53:55
 */
@Controller
@RequestMapping("xzzx")
public class XzzxController extends BaseController{
	Log log = LogFactory.getLog(XzzxController.class);
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月8日下午2:20:21</br>
	 */
	@Resource(name = "xzzxService")
	XzzxService service;
	@ResponseBody
	@RequestMapping("list")
	public JSONArray list(FileVo file) {
		return JSONArray.fromObject(service.list("SysFile"));
	}
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
	public Map<String, Object> uploadFile(FileVo file) {
		try {
			com.cyb.file.FileUtils.genFileDir(Configuration.get("uploadPath")+Configuration.get("tmp"));
			SysFile t = new SysFile();
			t.setContent(file.getDesc());
			t.setTitle(file.getTitle());
			t.setTime(DateUtil.date2long8(new Date()).toString());
			t.setFjname(file.getFile1().getOriginalFilename());
			t.setSize(file.getFile1().getInputStream().available());
			String savePath = Configuration.get("uploadPath")+Configuration.get("tmp")+file.getFile1().getOriginalFilename();
			String savePath2 = Configuration.get("uploadPath")+Configuration.get("tmp")+file.getFile2().getOriginalFilename();
			FileUtils.copyInputStreamToFile(file.getFile1().getInputStream(), new File(savePath));
			FileUtils.copyInputStreamToFile(file.getFile2().getInputStream(), new File(savePath2));
			log.info("上传的文件1："+savePath);
			log.info("上传的文件2："+savePath2);
			service.save(t);
			String picPath=Configuration.get("uploadPath")+Configuration.get("tmp")+"pic.jpg";
			ImageBase64.GenerateImage(file.getPicStr().split(",")[1], picPath);
			log.info("裁剪文件流存储位置："+picPath);
			setMsgMap(SUCCESS, "信息上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setMsgMap(FAILURE, "信息上传失败！");
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
	public Map<String, Object> updFile(FileVo file) {
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
	@RequestMapping("downloadold")
	public  void downLoad(HttpServletResponse response,String id) {
		OutputStream out = null;
		try {
			SysFile f=(SysFile) service.get(id);
			String path = Configuration.get("uploadPath")+Configuration.get("tmp");
			com.cyb.file.FileUtils.genFileDir(path);
			path=path+f.getFjname();
			String fileName = f.getFjname();
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(fileName.getBytes("utf-8"),"iso-8859-1")+"\"");
			out = response.getOutputStream();
			out.write(FileUtils.readFileToByteArray(new File(path)));
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
	@RequestMapping("download")
	public  void downLoadFile(HttpServletResponse response,String id) {
		OutputStream out = null;
		try {
			SysFile f=(SysFile) service.get(id);
			String filePath = Configuration.get("uploadPath")+Configuration.get("tmp");
			com.cyb.file.FileUtils.genFileDir(filePath);
			filePath=filePath+f.getFjname();
			String downName = f.getFjname();
			File file = new File(filePath);
			downloadBase(response,downName,file);
		} catch (Exception e) {
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
	
	@RequestMapping(value="downtest",method=RequestMethod.GET)
	public  void downLoadFileTest(HttpServletResponse response,@RequestParam(value = "fileName", required = true) @ApiParam(value = "下载的文件名") String fileName) {
		OutputStream out = null;
		try {
			String filePath = Configuration.get("uploadPath")+Configuration.get("tmp");
			com.cyb.file.FileUtils.genFileDir(filePath);
			filePath=filePath+fileName;
			log.info("文件下载路径:"+filePath);
			String downName = fileName;
			File file = new File(filePath);
			downloadBase(response,downName,file);
		} catch (Exception e) {
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
