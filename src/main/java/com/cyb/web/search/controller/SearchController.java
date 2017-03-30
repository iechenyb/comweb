package com.cyb.web.search.controller;

import java.io.StringReader;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.cyb.web.search.utils.LuceneMemIndexIK;
import com.cyb.web.search.utils.LuceneMemIndexStandard;
@Controller
@RequestMapping("search")
public class SearchController {
	static Log log = LogFactory.getLog(SearchController.class);
	@ResponseBody
	@RequestMapping("news")
	public JSONArray search(String key){
		try {
			if(StringUtils.isEmpty(key)){
				LuceneMemIndexIK.getAllNews();
				return JSONArray.fromObject(LuceneMemIndexIK.all);
			}else{
				log.info("索引路径："+LuceneMemIndexIK.IndexPath);
				if(LuceneMemIndexIK.start("name",key)>0){
					return JSONArray.fromObject(LuceneMemIndexIK.res);
				}else{
					LuceneMemIndexStandard.start("name",key);
					return JSONArray.fromObject(LuceneMemIndexStandard.res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JSONArray.fromObject("[]");
		}
  }
	@SuppressWarnings("resource")
	@ResponseBody
	@RequestMapping("words")
	public  String analyWords(String key) throws Exception{
        StringReader sr=new StringReader(key);  
        IKSegmenter ik=new IKSegmenter(sr, true); //ik分词 
        Lexeme lex=null;  
        String str1="";
        String str2="";
        while((lex=ik.next())!=null){  
            str1+=lex.getLexemeText()+",";  
        }  
    	@SuppressWarnings("deprecation")
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
    	 StringReader reader=new StringReader(key);  
         //分词  
         TokenStream ts=analyzer.tokenStream("", reader);  
         CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
         ts.reset();
         //遍历分词数据  
         while(ts.incrementToken()){  
        	 str2+=term.toString()+",";  
         }  
         reader.close();  
        if(LuceneMemIndexIK.start("name",key)>0){
			return str1;
		}else{
			LuceneMemIndexStandard.start("name",key);
			return str2;
		}
	}
}
