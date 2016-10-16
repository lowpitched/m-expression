package com.mlh.expression.engine;

import java.math.BigDecimal;

import com.mlh.expression.model.CalcUnit;
import com.mlh.expression.model.CalcUnitChain;
import com.mlh.expression.model.Operator;


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
	
}
