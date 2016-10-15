package com.mlh.expression.varpool.impl;

import java.util.HashMap;
import java.util.Map;

import com.mlh.expression.varpool.IVarSource;


/**
 * Map类型的变量池
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午7:24:37
 * @update 修改时间：2016年10月15日下午7:24:37
 */

public class MapVarSource implements IVarSource{

	private Map<String,String> source = new HashMap<String,String>();
	
	public void putVar(String var,String value){
		source.put(var, value);
	}
	
	public void putAll(Map<String,String> map){
		source.putAll(map);
	}
	
	public void addObject(Object o){
		source.putAll(new PojoField2VarSource<Object>(o).getSource());
	}
	
	public String remove(String var){
		return source.remove(var);
	}
	
	public int size(){
		return source.size();
	}
	
	public Map<String, String> getSource() {
		return source;
	}

	public String getVarValue(String var) {
		return source.get(var);
	}
	
}
