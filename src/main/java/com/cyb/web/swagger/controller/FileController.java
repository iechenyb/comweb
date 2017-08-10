package com.cyb.web.swagger.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
@Api(value = "FileController", description = "文件管理模块")
@Controller
@RequestMapping("/file-test")
public class FileController {

    @ResponseBody
    @RequestMapping(value = "/upload1",method=RequestMethod.POST)  
    @ApiOperation(value = "文件上传方法具体描述",
    httpMethod = "POST", 
	produces = "application/json;charset=UTF-8",
	response = Object.class,
	notes = "文件上传方法1")
    public String upload1x(
    		@ApiParam(required = true, name = "file", value  = "上传文件参数")
    		@RequestParam(value = "file", required = false) MultipartFile file) {  
        String fileName = file.getOriginalFilename();  
        return file.getContentType()+","+fileName;  
    }
    
    @ResponseBody	
    @RequestMapping(value = "/upload2",method=RequestMethod.POST) 
    @ApiOperation(value = "文件上传方法2",
    httpMethod = "POST", 
	produces = MediaType.ALL_VALUE,
	response = Object.class,
	notes = "文件上传方法具体描述")
    public String upload2x(@ApiParam(required = true, name = "file", value  = "上传文件参数") MultipartFile file) {  
        String fileName = file.getOriginalFilename();  
        return file.getContentType()+","+fileName;  
    }  
}
