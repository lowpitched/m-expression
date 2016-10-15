package com.mlh.expression.engine;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.mlh.expression.calcoper.OperatorType;
import com.mlh.expression.parse.ExpressionParse;
import com.mlh.expression.varpool.IVarSource;


/**
 * 简单四则运算的表达式计算引擎
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午5:41:03
 */

public class SimpleCalcEngine extends NoBracketsCalcEngine{
	
	public BigDecimal calc(String expression,IVarSource varSource){
		ExpressionParse parse = ExpressionParse.initExpression(expression, varSource);
		String[] words = parse.parse2Raw();
		LinkedList<String> stack = new LinkedList<String>();
		for(int i=0;i<words.length;i++){
			String word = words[i];
			stack.add(word);
			int operatorType = OperatorType.whatOperator(word);
			if(OperatorType.OPERATOR_BRACKETS_RIGHT==operatorType){
				fold(stack);
			}
		}
		if(stack.size()>1){
			finalCalc(stack);
		}
		return new BigDecimal(stack.get(0));
	}
	
	/**
	 * 折叠计算完成之后，完成最后的计算
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @param stack
	 */
	private void finalCalc(LinkedList<String> stack) {
		String[] words = stack.toArray(new String[stack.size()]);
		stack.clear();
		stack.add(super.simpleCalc2(words).toString());
	}
	/**
	 * 折叠计算（将括号包含的表达式出栈，计算，然后压入栈顶）
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @param stack
	 */
	private void fold(LinkedList<String> stack) {
		//右括号出栈
		stack.pollLast();
		LinkedList<String> sub = new LinkedList<String>();
		while(true){
			String word = stack.pollLast();
			int operatorType = OperatorType.whatOperator(word);
			if(OperatorType.OPERATOR_BRACKETS_LEFT==operatorType){
				BigDecimal foldFactor = super.simpleCalc2(sub.toArray(new String[sub.size()]));
				stack.add(foldFactor.toString());
				break;
			}
			sub.addFirst(word);
		}
	}
	
}
