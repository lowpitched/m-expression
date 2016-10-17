package com.mlh.expression.varpool.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.mlh.expression.annotation.Var;
import com.mlh.expression.varpool.IVarSource;


/**
 * 将POJO对象转换成变量池
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午4:38:15
 */

public class PojoField2VarSource<T> implements IVarSource{
	/**变量池*/
	private Map<String,String> vars;
	/**pojo对象*/
	private T pojo;
	
	public PojoField2VarSource(T pojo){
		vars = new HashMap<String,String>();
		this.pojo = pojo;
		init();
	}
	
	private void init() {
		try {
			Class<? extends Object> clazz = pojo.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				Var varAnnotation = field.getAnnotation(Var.class);
				if(null==varAnnotation){
					continue;
				}
				String varName = varAnnotation.name();
				if("".equals(varName)){
					varName = field.getName();
				}
					vars.put(varName, field.get(pojo).toString());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String getVarValue(String var) {
		return vars.get(var);
	}

	@Override
	public Map<String, String> getSource() {
		return vars;
	}

}
