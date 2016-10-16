package com.mlh.expression.model;

import java.math.BigDecimal;

import com.mlh.expression.calcoper.OperatorType;
import com.mlh.expression.config.ExpressionConfig;


/**
 * 计算最小单元
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月14日下午8:40:09
 */
public class CalcUnit implements Comparable<CalcUnit>{
	
	private String expressionId;
	/**前一个计算因子*/
	private String previous;
	/**运算符*/
	private Operator operator; 
	/**后一个计算因子*/
	private String next;
	/**前一个计算单元*/
	private CalcUnit previousUnit;
	/**后一个计算单元*/
	private CalcUnit nextUnit;
	/**
	 * 计算结果
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @return
	 */
	private BigDecimal resultBigDeimal(){
		BigDecimal first = new BigDecimal(previous);
		BigDecimal secord = new BigDecimal(next);
		int operatorType = operator.getOperatorType();
		if(OperatorType.OPERATOR_ADD==operatorType){
			return first.add(secord);
		}else if(OperatorType.OPERATOR_MINUS==operatorType){
			return first.subtract(secord);
		}else if(OperatorType.OPERATOR_MULTIPLY==operatorType){
			return first.multiply(secord);
		}else if(OperatorType.OPERATOR_DIVIDE==operatorType){
			ExpressionConfig config = ExpressionConfig.instance();
			int scale = config.getModel().getPersonalProcessScale(expressionId);
			return first.divide(secord,scale,BigDecimal.ROUND_HALF_UP);
		}else{
			throw new RuntimeException("不支持的运算符号");
		}

	}
	
	/**
	 * 计算结果
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @return
	 */
	public String result(CalcUnitChain chain){
		BigDecimal result = resultBigDeimal();
		if(chain.size()==1){
			return result.toString();
		}
		if(null!=this.previousUnit){
			this.previousUnit.next=result.toString();
			this.previousUnit.nextUnit=this.nextUnit;
		}
		if(null!=this.nextUnit){
			this.nextUnit.previous=result.toString();
			this.nextUnit.previousUnit=this.previousUnit;
		}
		chain.remove(this);
		return "$";
	}
	
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public CalcUnit getPreviousUnit() {
		return previousUnit;
	}
	public void setPreviousUnit(CalcUnit previousUnit) {
		this.previousUnit = previousUnit;
	}
	public CalcUnit getNextUnit() {
		return nextUnit;
	}
	public void setNextUnit(CalcUnit nextUnit) {
		this.nextUnit = nextUnit;
	}

	/**
	 * 比较运算符优先级
	 */
	public int compareTo(CalcUnit o) {
		 float crs = this.operator.getPriority()
				 		-o.getOperator().getPriority();
		 if(crs>0)
			 return -1;
		 else if(crs<0)
			 return 1;
		 else 
			 return 0;
	}
}
