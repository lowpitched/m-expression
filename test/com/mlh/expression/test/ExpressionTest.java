package com.mlh.expression.test;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import com.mlh.expression.engine.ICalcEngine;
import com.mlh.expression.engine.NoBracketsCalcEngine;
import com.mlh.expression.engine.SimpleCalcEngine;
import com.mlh.expression.parse.ExpressionParse;
import com.mlh.expression.parse.IExpressionParse;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午8:17:46
 * @update 修改时间：2016年10月13日下午8:17:46
 */

public class ExpressionTest {
	@Test
	public void testNoBracketsCalc(){
		ICalcEngine noBracketsCalcEngine = new NoBracketsCalcEngine(){
			@SuppressWarnings("deprecation")
			public BigDecimal calc(String expression) {
				return simpleCalc(expression);
			}
		};
		BigDecimal result = noBracketsCalcEngine.calc("1*2+4*4/2-1+1*3-1");
		System.out.println(result);
	}
	
	@Test
	public void testExpressionParse(){
		IExpressionParse parse = new ExpressionParse();
		String[] ss = parse.parse("3.145926*12+(-4.4)/5.111-(12.01+1)+1");
		System.out.println(Arrays.asList(ss).toString());
	}
	
	@Test
	public void testSimpleCalc(){
		SimpleCalcEngine engine = new SimpleCalcEngine();
		String[] es = getExpression();
		for(String e:es){
			BigDecimal result = engine.calc(e);
			System.err.println(result);
		}
	}
	
	private String[] getExpression(){
		String a1 = "1.231+123.234/12-2123.412";
		String a2 = "(9.54+12)*0.73-16/12-11+5";
		String a3 = "((442.98*23-12)*7+6)-11.5*6";
		String a4 = "(11+11)+11+11-11*(-19)/5";
		return new String[]{a1,a2,a3,a4};
	}
}
