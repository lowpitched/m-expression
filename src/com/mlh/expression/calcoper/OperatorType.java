package com.mlh.expression.calcoper;


/**
 * 支持的操作符类型
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午4:59:01
 * @update 修改时间：2016年10月13日下午4:59:01
 */

public class OperatorType {
	//不是运算符
	public static final int OPERATOR_NONE=-1;
	//运算符+
	public static final int OPERATOR_ADD=0;
	//运算符-
	public static final int OPERATOR_MINUS=1;
	//运算符*
	public static final int OPERATOR_MULTIPLY=2;
	//运算符/
	public static final int OPERATOR_DIVIDE=3;
	//左括號
	public static final int OPERATOR_BRACKETS_LEFT=4;
	//右括號
	public static final int OPERATOR_BRACKETS_RIGHT=5;
	
	private static final String ADD="+";
	private static final String MINUS="-";
	private static final String MULTIPLY="*";
	private static final String DIVIDE="/";
	private static final String BRACKETS_LEFT="(";
	private static final String BRACKETS_RIGHT=")";
	
	/**运算符优先级，数值越大优先级越高*/
	public static final float OPERATOR_ADD_PRIORITY=0f;
	public static final float OPERATOR_MINUS_PRIORITY=0f;
	public static final float OPERATOR_MULTIPLY_PRIORITY=1f;
	public static final float OPERATOR_DIVIDE_PRIORITY=1f;
	public static final float OPERATOR_BRACKETS_LEFT_PRIORITY=9f;
	public static final float OOPERATOR_BRACKETS_RIGHT_PRIORITY=10f;
	/**
	 * 判断是哪个运算符
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @updater     修改者：MENGLIHAO 
	 * @create      创建时间：2016年10月13日
	 * @update      修改时间：2016年10月13日
	 * @param operator
	 * @return
	 */
	public static int whatOperator(String operator){
		if(ADD.equals(operator)){
			return OPERATOR_ADD;
		}else if(MINUS.equals(operator)){
			return OPERATOR_MINUS;
		}else if(MULTIPLY.equals(operator)){
			return OPERATOR_MULTIPLY;
		}else if(DIVIDE.equals(operator)){
			return OPERATOR_DIVIDE;
		}else if(BRACKETS_LEFT.equals(operator)){
			return OPERATOR_BRACKETS_LEFT;
		}else if(BRACKETS_RIGHT.equals(operator)){
			return OPERATOR_BRACKETS_RIGHT;
		}else{
			return OPERATOR_NONE;
		}
	}
	
	public static float getOperatorPriority(int OperatorType){
		if(OPERATOR_ADD==OperatorType){
			return OPERATOR_ADD_PRIORITY;
		}else if(OPERATOR_MINUS==OperatorType){
			return OPERATOR_MINUS_PRIORITY;
		}else if(OPERATOR_MULTIPLY==OperatorType){
			return OPERATOR_MULTIPLY_PRIORITY;
		}else if(OPERATOR_DIVIDE==OperatorType){
			return OPERATOR_DIVIDE_PRIORITY;
		}else if(OPERATOR_BRACKETS_LEFT==OperatorType){
			return OPERATOR_BRACKETS_LEFT_PRIORITY;
		}else if(OPERATOR_BRACKETS_RIGHT==OperatorType){
			return OOPERATOR_BRACKETS_RIGHT_PRIORITY;
		}else{
			return -1f;
		}
	}
	
}
