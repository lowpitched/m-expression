package com.mlh.expression.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.mlh.expression.engine.NoBracketsCalcEngine;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午8:17:46
 * @update 修改时间：2016年10月13日下午8:17:46
 */

public class NoBracketsCalcEngineTest {
	@Test
	public void testSimpleCalc(){
		NoBracketsCalcEngine noBracketsCalcEngine = new NoBracketsCalcEngine(){
			@Override
			public BigDecimal calc(String expression) {
				return null;
			}
		};
		BigDecimal result = noBracketsCalcEngine.simpleCalc("1*2+4*4/2-1+1*3-1");
		System.out.println(result);
	}
	
}
