package com.mlh.expression.parse;

import com.mlh.expression.varpool.IVarSource;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午6:00:18
 */
class Expression {
	/**表达式ID*/
	private String expressionId;
	/**带变量的表达式*/
	private String expressionWithVar;
	/**替换变量后的分词*/
	private String[] words;
	/**变量池*/
	private IVarSource varSource;
	
	public Expression(String expressionWithVar,IVarSource varSource){
		if(expressionWithVar.contains("=")){
			String[] split = expressionWithVar.split("=");
			this.expressionId = split[0];
			this.expressionWithVar = split[1];
		}else{
			this.expressionWithVar = expressionWithVar;
		}
		this.varSource = varSource;
		init();
	}
	
	/**
	 * 以实际值替换变量
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月15日
	 */
	private void init() {
		String[] varWords = ExpressionParse.parse(expressionWithVar);
		if(null==varSource){
			words = varWords;
			return;
		}
		words = new String[varWords.length];
		for(int i=0;i<varWords.length;i++){
			String word = varSource.getVarValue(varWords[i]);
			if(null!=word){
				words[i]=word;
			}else{
				words[i]=varWords[i];
			}
		}
	}
	
	public String[] getParseWord(){
		return words;
	}
	
	public String getId(){
		return this.expressionId;
	}
	
	
	
}
