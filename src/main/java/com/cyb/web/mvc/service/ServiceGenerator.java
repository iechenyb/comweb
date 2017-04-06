package com.cyb.web.mvc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyb.date.DateUtil;
import com.cyb.web.utils.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class ServiceGenerator {

    public static void main(String[] args) {

        try {
            new ServiceGenerator().gen();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void gen() throws IOException, TemplateException{
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File( FileUtils.getAbsolutePathAtMavenClass(ServiceGenerator.class)));   
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate("service.ftl");  // load E:/Work/Freemarker/templates/person.ftl
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", "com.cyb.web.mvc.service");
        root.put("modelName", "Test");
        root.put("varModelName", "test");
        root.put("author", "iechenyb");
        root.put("date", DateUtil.timeToSec(new Date()).toString());
        File dir = new File( FileUtils.getAbsolutePathAtMavenClass(ServiceGenerator.class));
        if(!dir.exists()){
            dir.mkdirs();
        }
        OutputStream fos = new  FileOutputStream( new File(dir, root.get("modelName")+"Service.java")); //java文件的生成目录   
        Writer out = new OutputStreamWriter(fos);
        temp.process(root, out);
        fos.flush();  
        fos.close();
        System.out.println("gen "+root.get("packageName")+"."+root.get("modelName")+"Service.java"+" code success!");
    }
}