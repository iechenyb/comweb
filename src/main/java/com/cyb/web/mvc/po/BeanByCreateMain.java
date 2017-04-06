package com.cyb.freemarker.mvc.po;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.cyb.file.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class BeanByCreateMain {
	private final static String TEMPLATE_NAME="po.ftl";  
    private Configuration cfg;  
    private Properties ploader;  
    /** 
     * 初始化工作 
     */  
    @SuppressWarnings("deprecation")
	public void init() throws Exception {  
        // 负责管理的实例创建+设置模板文件所在的目录  
        cfg = new Configuration();  
        // load资源的实例创建  
        ploader = new Properties();  
        cfg.setDirectoryForTemplateLoading(new File(  
                FileUtils.getAbsolutePathAtClass(BeanByCreateMain.class)));  
        String path = FileUtils.getAbsolutePathAtClass(BeanByCreateMain.class);
        ploader.load(new FileInputStream(path+"bean.properties"));  
        //ploader.load(new FileInputStream("E:\\Workspaces\\workspace\\javabean_complier\\src\\bean.properties"));  
    }  
   
      
    public void process() throws IOException, TemplateException {  
          
        //1.load信息  
        Class clazz=loadClass();  
        List<Attribute> attributes=loadAttr();  
        //2.向root中放入模版中所需信息  
        Map<String, Object> root = new HashMap<String, Object>();  
        root.put("clazz",clazz);  
        root.put("attributes",attributes);  
        //3.将模版进行指定文件的输出  
        write(root);  
          
    }  
    /* 
     * 执行入口 
     */  
    public static void main(String[] args) throws Exception {  
        BeanByCreateMain hfm = new BeanByCreateMain();  
        hfm.init();  
        hfm.process();  
    }  
      
    /* 
     * 加载类信息：包名，类名，import 
     */  
    public Class loadClass() {  
        String packagename=ploader.getProperty("packagename");  
        String classname=ploader.getProperty("classname");  
        String values=ploader.getProperty("imports");  
        String[] imports=values.split(",");  
        Class clazz = new Class(classname, imports, packagename);  
        return clazz;  
    }  
    /* 
     * 加载属性：属性名，属性类型 
     */  
    public List<Attribute> loadAttr() {  
        List<Attribute> attributes = new ArrayList<Attribute>();  
        String values=ploader.getProperty("attribute");  
        String[] strs=values.split(",");  
        for (String s : strs) {  
            String[] sp=s.split(":");  
            attributes.add(new Attribute(sp[0],sp[1]));  
        }  
        return attributes;  
    }  
    /* 
     * 将模版进行指定文件的输出 
     */  
    public void write(Map<String, Object> root) throws IOException, TemplateException{  
        Template t = cfg.getTemplate(TEMPLATE_NAME);  
        OutputStream out = new FileOutputStream(  
                new File(FileUtils.getAbsolutePathAtClass(BeanByCreateMain.class)+ploader.getProperty("classname")+".java"));  
        t.process(root, new OutputStreamWriter(out));  
    }  
}
