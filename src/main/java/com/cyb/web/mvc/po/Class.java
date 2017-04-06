package com.cyb.freemarker.mvc.po;

public class Class {
	private String packagename;  
    private String classname;  
    private String[] imports;  
   
    public Class() {  
    }  
   
    public Class(String classname, String[] imports,String packagename) {  
        super();  
        this.classname = classname;  
        this.imports = imports;  
        this.packagename=packagename;  
    }  
   
    public String getClassname() {  
        return classname;  
    }  
   
    public void setClassname(String classname) {  
        this.classname = classname;  
    }  
   
    public String[] getImports() {  
        return imports;  
    }  
   
    public void setImports(String[] imports) {  
        this.imports = imports;  
    }  
   
    public String getPackagename() {  
        return packagename;  
    }  
   
    public void setPackagename(String packagename) {  
        this.packagename = packagename;  
    }  
}
