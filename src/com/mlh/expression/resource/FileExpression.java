package com.mlh.expression.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 文件模式输入（支持变量）
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午12:59:29
 */

public class FileExpression {
	
	Map<String,String> expressions = new HashMap<String,String>(); 
	
	public FileExpression(File file){
		try {
			Properties ppt = new Properties();
			ppt.load(new FileInputStream(file));
			Enumeration<Object> keys = ppt.keys();
			while(keys.hasMoreElements()){
				String expressionId = (String) keys.nextElement();
				String expression = ppt.getProperty(expressionId);
				expressions.put(expressionId, expression);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getExpression(String expressionId){
		return expressions.get(expressionId);
	}
	
}
