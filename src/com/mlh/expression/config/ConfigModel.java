package com.mlh.expression.config;

import java.util.HashMap;
import java.util.Map;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午7:51:36
 */
public class ConfigModel {

	public static final String EXPRESSION_MLH_LOG="expression.mlh.log";
	public static final String EXPRESSION_MLH_VARSOURCE_CLASS="expression.mlh.varsource.class";
	public static final String EXPRESSION_MLH_GLOBAL_SCALE_RESULT="expression.mlh.global.scale.result";
	public static final String EXPRESSION_MLH_GLOBAL_SCALE_PROCESS="expression.mlh.global.scale.process";
	public static final String EXPRESSION_MLH_GLOBAL_TAIL="expression.mlh.global.tail";
	public static final String EXPRESSION_MLH_PERSONAL_SCALE_RESULT="expression.mlh.personal.scale.result";
	public static final String EXPRESSION_MLH_PERSONAL_SCALE_PROCESS="";
	public static final String EXPRESSION_MLH_PERSONAL_TAIL="expression.mlh.personal.tail";
	
	/**是否输出计算跟踪日志 0 不输出 1 输出*/
	public int printLog = 0;
	/**变量池数据源CLASS*/
	public String varSourceClass;
	/**全局结果精度*/
	public int resultScale = 2;
	/**全局计算过程精度*/
	public int processScale = 16; 
	/**精度处理方式 0 四舍五入 1只入不舍 2只舍不入 */
	public int tailType = 0;
	/**个性化表达式结果精度*/
	Map<String,Integer> personalResultScale=new HashMap<String,Integer>();
	/**个性化表达式过程计算精度*/
	Map<String,Integer> personalProcessScale=new HashMap<String,Integer>();;
	/**个性化精度处理方式*/
	Map<String,Integer> personalTailType=new HashMap<String,Integer>();;
	
	public int getPersonalResultScale(String expressionId){
		Integer r = personalResultScale.get(expressionId);
		return r==null?this.resultScale:r;
	}
	
	public int getPersonalProcessScale(String expressionId){
		Integer r = personalProcessScale.get(expressionId);
		return r==null?this.processScale:r;
	}
	
	public int getPersonalTailType(String expressionId){
		Integer r = personalTailType.get(expressionId);
		return r==null?this.tailType:r;
	}
}
