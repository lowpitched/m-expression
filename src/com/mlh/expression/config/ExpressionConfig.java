package com.mlh.expression.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午1:02:15
 * @update 修改时间：2016年10月15日下午1:02:15
 */

public class ExpressionConfig {
	
	public static final String FILENAME="m-expression-config.txt";
	
	public static ExpressionConfig config = new ExpressionConfig();
	
	private Properties properties;
	
	private ConfigModel model;
	
	public static ExpressionConfig instance(){
		return config;
	}
	
	public static ExpressionConfig instance(String path){
		return config.config(path);
	}
	
	public ExpressionConfig(){
		properties = new Properties();
	}
	
	public ExpressionConfig config(String path){
		FileInputStream fi = null;
		try {
			ConfigModel model = new ConfigModel();
			fi = new FileInputStream(new File(path+File.separator+FILENAME));
			properties.load(fi);
			String printLog = properties.getProperty(ConfigModel.EXPRESSION_MLH_LOG);
			if(null==printLog&&"".equals(printLog)){
				model.printLog=Integer.parseInt(printLog);
			}
			String varSource = properties.getProperty(ConfigModel.EXPRESSION_MLH_VARSOURCE_CLASS);
			if(null==varSource&&"".equals(varSource)){
				model.varSourceClass=varSource;
			}
			String resultScale = properties.getProperty(ConfigModel.EXPRESSION_MLH_GLOBAL_SCALE_RESULT);
			if(null==resultScale&&"".equals(resultScale)){
				model.resultScale=Integer.parseInt(resultScale);
			}
			String processScale = properties.getProperty(ConfigModel.EXPRESSION_MLH_GLOBAL_SCALE_PROCESS);
			if(null==processScale&&"".equals(processScale)){
				model.processScale=Integer.parseInt(processScale);
			}
			String tailType = properties.getProperty(ConfigModel.EXPRESSION_MLH_GLOBAL_TAIL);
			if(null==tailType&&"".equals(tailType)){
				model.tailType=Integer.parseInt(tailType);
			}
			String personalResultScale = properties.getProperty(ConfigModel.EXPRESSION_MLH_PERSONAL_SCALE_RESULT);
			if(null==personalResultScale&&"".equals(personalResultScale)){
				model.personalResultScale=initPersonalScale(personalResultScale);
			}
			String personalProcessScale = properties.getProperty(ConfigModel.EXPRESSION_MLH_PERSONAL_SCALE_PROCESS);
			if(null==personalProcessScale&&"".equals(personalProcessScale)){
				model.personalProcessScale=initPersonalScale(personalProcessScale);
			}
			String personalTailType = properties.getProperty(ConfigModel.EXPRESSION_MLH_PERSONAL_TAIL);
			if(null==personalTailType&&"".equals(personalTailType)){
				model.personalTailType=initPersonalScale(personalTailType);
			}
			this.model = model;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("配置文件不存在",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("加载配置文件失败",e);
		}finally{
			if(null!=fi){
				try {
					fi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	private  Map<String,Integer> initPersonalScale(String personal) {
		Map<String,Integer> result = new HashMap<String,Integer>();
		String[] units = personal.split(",");
		for(String unit:units){
			String[] kv = unit.split(":");
			if(kv.length<2){
				throw new RuntimeException("配置文件Personal部分格式错误");
			}
			result.put(kv[0], Integer.parseInt(kv[1]));
			
		}
		return result;
	}
	
	public ConfigModel getModel(){
		return this.model;
	}
}
