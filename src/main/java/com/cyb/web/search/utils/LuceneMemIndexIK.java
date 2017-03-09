package com.cyb.web.search.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.cyb.web.constant.Contants;
  
public class LuceneMemIndexIK {  
	static Log log = LogFactory.getLog(LuceneMemIndexIK.class);
	public static String DataPath=Contants.WEBPATH+"\\lucene\\example";
	public static String IndexPath=Contants.WEBPATH+"\\lucene\\index";
	private final static Directory indexDir = new RAMDirectory();  
	public static List<Map<String,Object>> res = null;
	public static List<Map<String,Object>> all = null;
    /**  
     * ��������  
     * @param analyzer  
     * @throws Exception  
     */  
    @SuppressWarnings("deprecation")
	public static void createIndex() throws Exception{  
        IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_46, analyzer);  
        IndexWriter iw=new IndexWriter(indexDir, iwc);  
        LuceneMemIndexIK.addDoc(iw);  
        iw.close();  
    }  
      
    /**  
     * ��̬���Document  
     * @param iw  
     * @throws Exception  
     */  
    public static void addDoc(IndexWriter iw)  throws Exception{  
        File[] files=new File(DataPath).listFiles();  
        res = new ArrayList<Map<String,Object>>();
        Map<String,Object> tmp;
        for (File file : files) {  
            Document doc=new Document();  
            String content=LuceneMemIndexIK.getContent(file);  
            String name=file.getName();  
            String path=file.getAbsolutePath();  
            tmp = new HashMap<String, Object>();
            tmp.put("url",path);
            tmp.put("title",name);
            doc.add(new TextField("content", content, Store.YES));  
            doc.add(new TextField("name", name, Store.YES));  
            doc.add(new TextField("path", path,Store.YES));  
            log.info("创建索引文件："+path);   
            iw.addDocument(doc);  
            iw.commit();  
        }  
    }
    public static void getAllNews()  throws Exception{  
        File[] files=new File(DataPath).listFiles();  
        all = new ArrayList<Map<String,Object>>();
        Map<String,Object> tmp;
        for (File file : files) {  
            String content=LuceneMemIndexIK.getContent(file);  
            String name=file.getName();  
            String path=file.getAbsolutePath();  
            tmp = new HashMap<String, Object>();
            tmp.put("url",path);
            tmp.put("title",name);
            tmp.put("content",content);
            all.add(tmp);
        }  
    } 
      
    /**  
     * ��ȡ�ı�����  
     * @param file  
     * @return  
     * @throws Exception  
     */  
    @SuppressWarnings("resource")  
    public static String getContent(File file) throws Exception{  
        FileInputStream fis=new FileInputStream(file);  
        InputStreamReader isr=new InputStreamReader(fis,"UTF-8");  
        BufferedReader br=new BufferedReader(isr);  
        StringBuffer sb=new StringBuffer();  
        String line=br.readLine();  
        while(line!=null){  
            sb.append(line+"\n");  
            line=null;  
        }  
        return sb.toString();  
    }  
      
    /**  
     * ����  
     * @param query  
     * @throws Exception  
     */  
    private static int search(Query query,String words) throws Exception {  
        IndexReader ir=DirectoryReader.open(indexDir);  
        IndexSearcher is=new IndexSearcher(ir);  
        TopDocs td=is.search(query, 1000);  
        res = new ArrayList<Map<String,Object>>();
        log.info("搜索句子:["+words+"],查询结果"+td.totalHits+"");   
        ScoreDoc[] sds =td.scoreDocs;  
        Map<String,Object> tmp;
        for (ScoreDoc sd : sds) {   
            Document d = is.doc(sd.doc);  
            tmp = new HashMap<String, Object>();
            tmp.put("url",d.get("path"));
            tmp.put("title",d.get("name"));
            res.add(tmp);
        }
        return td.totalHits;
    }  
      
    public static  void deleteIndex(){
    	File file = new File(IndexPath);
    	for(File f:file.listFiles()){
    		f.delete();
    	}
    }  
    static Analyzer analyzer=new IKAnalyzer();  
    @SuppressWarnings("deprecation")
	public static int start(String col,String words) throws Exception{
        QueryParser parser = new QueryParser(Version.LUCENE_46, col, analyzer);   
        Query query = parser.parse(words);  
        return LuceneMemIndexIK.search(query,words);
    }
    public static void main(String[] args) throws Exception, Exception {  
    	//deleteIndex();
    	DataPath="d:\\lucene\\example";
    	IndexPath="d:\\lucene\\index";
    	LuceneMemIndexIK.createIndex();
    	String words = "开";
    	String col ="name";
    	start(col,words);
    	/*System.out.println("----------------");
    	start(col,"我们的总理要开户");
    	System.out.println("----------------");
    	start(col,"我先注册，后边在开户");
    	System.out.println("----------------");
    	start(col,"我去，密码丢了，咋办？");
    	System.out.println("----------------");*/
    }  
}  