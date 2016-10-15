package com.mlh.expression.engine;

import java.math.BigDecimal;

import com.mlh.expression.varpool.IVarSource;


/**
 * 计算引擎接口
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午4:57:11
 */
public interface ICalcEngine {

	BigDecimal calc(String expression,IVarSource varSource);
	
}
