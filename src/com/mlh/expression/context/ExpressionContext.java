package com.mlh.expression.context;

import java.math.BigDecimal;

import com.mlh.expression.config.ConfigModel;
import com.mlh.expression.config.ExpressionConfig;
import com.mlh.expression.engine.SimpleCalcEngine;
import com.mlh.expression.resource.FileExpression;
import com.mlh.expression.varpool.IVarSource;


/**
 * 调用主类(入口)
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午9:28:11
 */
public class ExpressionContext {

	private SimpleCalcEngine engine;
	
	private ConfigModel config;
	
	private FileExpression expressions;
	
	public ExpressionContext(ExpressionConfig config,FileExpression expressions){
		engine = new SimpleCalcEngine();
		this.config = config.getModel();
		this.expressions = expressions;
	}
	
	public BigDecimal calcByExpressionId(String expressionId,IVarSource varSource){
		int rs = config.getPersonalResultScale(expressionId);
		int tt = config.getPersonalTailType(expressionId);
		int round;
		if(0==tt){
			round = BigDecimal.ROUND_HALF_UP;
		}else if(1==tt){
			round = BigDecimal.ROUND_UP;
		}else if(2==tt){
			round = BigDecimal.ROUND_DOWN;
		}else{
			throw new RuntimeException("不支持的精度处理方式");
		}
		String expression = expressions.getExpression(expressionId);
		BigDecimal result = engine.calc(expression, varSource).setScale(rs,round);
		return result;
	}
	
}
