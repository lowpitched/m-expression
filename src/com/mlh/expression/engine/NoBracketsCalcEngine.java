package com.mlh.expression.engine;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.mlh.expression.calcoper.OperatorType;
import com.mlh.expression.parse.ExpressionParse;
import com.mlh.expression.parse.IExpressionParse;


/**
 * 没有括号的简单四则运算
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午4:56:54
 * @update 修改时间：2016年10月13日下午4:56:54
 */

public abstract class NoBracketsCalcEngine implements ICalcEngine{

	/**
	 * 
	 * @description 方法描述：没有括号的简单四则运算
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @param expression
	 * @return
	 */
	public BigDecimal simpleCalc(String expression) {
		IExpressionParse parse =  new ExpressionParse();
		String[] words = parse.parse(expression);
		LinkedList<String> queue = new LinkedList<String>();
		//运算符当前优先级
		float lastPriority = -1f;
		for(int i=0;i<words.length;i++){
			String word = words[i];
			int operatorType = OperatorType.whatOperator(word);
			if(OperatorType.OPERATOR_NONE!=operatorType){
				float currPriority = OperatorType.getOperatorPriority(operatorType);
				//当前运算符优先级高于上一个运算符优先级
				if(currPriority<=lastPriority){
					String newWord = popLastThreeAndCalc(queue);
					queue.add(newWord);
				}
				lastPriority = currPriority;
			}
			queue.add(word);
			if(i==words.length-1){
				while(true){
					if(queue.size()==1){
						break;
					}
					String newWord = popLastThreeAndCalc(queue);
					queue.add(newWord);
				}
			}
		}
		return new BigDecimal(queue.getFirst());
	}
	
	/**
	 * 将栈顶的三个元素POP并计算
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @param queue
	 * @return
	 */
	private String popLastThreeAndCalc(LinkedList<String> queue){
		String secordFactor = queue.pollLast();
		String operator = queue.pollLast();
		String firstFactor = queue.pollLast();
		return doOperator(firstFactor,operator,secordFactor);
	}
	
	/**
	 * 加减乘除
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @param firstFactor
	 * @param operator
	 * @param secordFactor
	 * @return
	 */
	private String doOperator(String firstFactor, String operator,String secordFactor) {
		BigDecimal first = new BigDecimal(firstFactor);
		BigDecimal secord = new BigDecimal(secordFactor);
		int operatorType = OperatorType.whatOperator(operator);
		if(OperatorType.OPERATOR_ADD==operatorType){
			return first.add(secord).toString();
		}else if(OperatorType.OPERATOR_MINUS==operatorType){
			return first.subtract(secord).toString();
		}else if(OperatorType.OPERATOR_MULTIPLY==operatorType){
			return first.multiply(secord).toString();
		}else if(OperatorType.OPERATOR_DIVIDE==operatorType){
			return first.divide(secord,16,BigDecimal.ROUND_HALF_UP).toString();
		}else{
			throw new RuntimeException("不支持的运算符号");
		}
	}
	
}
