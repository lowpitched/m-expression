package com.mlh.expression.engine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;

import com.mlh.expression.calcoper.OperatorType;
import com.mlh.expression.model.CalcUnit;
import com.mlh.expression.model.CalcUnitChain;
import com.mlh.expression.model.Operator;
import com.mlh.expression.parse.ExpressionParse;
import com.mlh.expression.parse.IExpressionParse;


/**
 * 没有括号的简单四则运算
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午4:56:54
 */

public abstract class NoBracketsCalcEngine implements ICalcEngine{

	/**
	 * 将表达式分词包装成计算单位链
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @param words
	 * @return
	 */
	private CalcUnitChain arr2CalcUnitChain(String[] words){
		CalcUnitChain chain = new CalcUnitChain();
		CalcUnit previousUnit = null;
		System.out.println(Arrays.asList(words));
		for(int i=1;i<=words.length;i++){
			if(i%2==0&&i!=0){
				//运算符
				String operatorRaw = words[i-1];
				//第一个计算因子
				String firstFactor = words[i-2];
				//第二个计算因子
				String secordFactor = words[i];
				
				CalcUnit unit = new CalcUnit();
				unit.setOperator(new Operator(operatorRaw));
				unit.setPrevious(firstFactor);
				unit.setNext(secordFactor);
				unit.setPreviousUnit(previousUnit);
				if(null!=previousUnit){
					previousUnit.setNextUnit(unit);
				}
				previousUnit = unit;
				chain.add(unit);
			}
		}
		return chain;
	}
	/**
	 * 没有括号的四则运算
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @param words
	 * @return
	 */
	protected BigDecimal simpleCalc2(String[] words){
		CalcUnitChain chain = arr2CalcUnitChain(words);
		chain.sort();
		while(chain.hasNext()){
			CalcUnit unit = chain.next();
			String result = unit.result(chain);
			if(!"$".equals(result))
				return new BigDecimal(result);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @description 方法描述：没有括号的简单四则运算
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @param expression
	 * @return
	 */
	@Deprecated
	protected BigDecimal simpleCalc(String[] words){
		LinkedList<String> queue = new LinkedList<String>();
		//运算符当前优先级
		float lastPriority = -1f;
		boolean tailOperatorIsMorePriority = false;
		for(int i=0;i<words.length;i++){
			String word = words[i];
			int operatorType = OperatorType.whatOperator(word);
			if(OperatorType.OPERATOR_NONE!=operatorType){
				float currPriority = OperatorType.getOperatorPriority(operatorType);
				//当前运算符优先级小于上一个运算符优先级
				if(currPriority<lastPriority){
					String newWord = popLastThreeAndCalc(queue);
					queue.add(newWord);
				}else if(currPriority>lastPriority&&i==words.length-2){
					/*倒数第二个word之后没有运算符无法和上一个比较
					  因此如果本运算符优先级别大于上一个则从后开始计算*/
					tailOperatorIsMorePriority=true;
				}
				lastPriority = currPriority;
			}
			queue.add(word);
			if(i==words.length-1){
				if(tailOperatorIsMorePriority){
					String newWord = popLastThreeAndCalc(queue);
					queue.add(newWord);
				}
				while(true){
					//只剩下相同优先等级的运算符，应从左向右算
					if(queue.size()==1){
						break;
					}
					String newWord = popFisrtThreeAndCalc(queue);
					queue.addFirst(newWord);
				}
			}
		}
		return new BigDecimal(queue.getFirst());
	}
	@Deprecated
	private String popFisrtThreeAndCalc(LinkedList<String> queue) {
		String firstFactor = queue.pollFirst();
		String operator = queue.pollFirst();
		String secordFactor = queue.pollFirst();
		return doOperator(firstFactor,operator,secordFactor);
	}

	@Deprecated
	protected BigDecimal simpleCalc(String expression) {
		IExpressionParse parse =  new ExpressionParse();
		String[] words = parse.parse(expression);
		return simpleCalc(words);
	}
	
	/**
	 * 将栈顶的三个元素POP并计算
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @param queue
	 * @return
	 */
	@Deprecated
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
	@Deprecated
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
