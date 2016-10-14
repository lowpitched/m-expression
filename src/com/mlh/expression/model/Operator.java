package com.mlh.expression.model;

import com.mlh.expression.calcoper.OperatorType;


/**
 * 运算符
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月14日下午8:41:18
 */

public class Operator {
	/**运算符类型*/
	private int operatorType;
	/**运算符符号*/
	private String operatorRaw;
	/**运算符优先级*/
	private float priority;
	
	public Operator(String operatorRaw){
		this.operatorRaw=operatorRaw;
		setOperatorType();
		setPriority();
	}
	
	public int getOperatorType() {
		return operatorType;
	}
	private void setOperatorType() {
		this.operatorType = OperatorType.whatOperator(operatorRaw);
	}
	public String getOperatorRaw() {
		return operatorRaw;
	}
	public void setOperatorRaw(String operatorRaw) {
		this.operatorRaw = operatorRaw;
	}
	public float getPriority() {
		return priority;
	}
	private void setPriority() {
		this.priority = OperatorType.getOperatorPriority(this.operatorType);
	}
}
