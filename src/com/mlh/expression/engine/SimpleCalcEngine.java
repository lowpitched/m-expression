package com.mlh.expression.engine;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.mlh.expression.parse.ExpressionParse;
import com.mlh.expression.parse.IExpressionParse;


/**
 * 简单四则运算的表达式计算引擎
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午5:41:03
 */

public class SimpleCalcEngine implements ICalcEngine{
	
	public BigDecimal calc(String expression){
		IExpressionParse parse = new ExpressionParse();
		String[] words = parse.parse(expression);
		LinkedList<String> stack = new LinkedList<String>();
		for(int i=0;i<words.length;i++){
			stack.add(words[i]);
		}
		return null;
	}
	
	/**
	 * 判断是否需要冒泡
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @return
	 */
	public boolean isNeedPop(String word){
		return false;
	}
	
}
