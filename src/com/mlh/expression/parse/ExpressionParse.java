package com.mlh.expression.parse;



/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午5:36:10
 * @update 修改时间：2016年10月13日下午5:36:10
 */

public class ExpressionParse implements IExpressionParse{

	public String[] parse(String expression){
		// TODO
		//String[] expressionArr = {"(","(","1","+","2",")","*","3","-","4",")","/","2","+","5"};
		String[] expressionArr = toStringArr(expression.toCharArray());
		return expressionArr;
	}
	
	private String[] toStringArr(char[] charArr){
		String[] result = new String[charArr.length];
		for(int i=0;i<charArr.length;i++){
			result[i]=charArr[i]+"";
		}
		return result;
	}
	
}
