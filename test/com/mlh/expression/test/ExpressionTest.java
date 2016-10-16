package com.mlh.expression.test;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import com.mlh.expression.config.ExpressionConfig;
import com.mlh.expression.context.ExpressionContext;
import com.mlh.expression.engine.SimpleCalcEngine;
import com.mlh.expression.resource.FileExpression;
import com.mlh.expression.varpool.impl.PojoField2VarSource;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午8:17:46
 * @update 修改时间：2016年10月13日下午8:17:46
 */

public class ExpressionTest {
	
	@Test
	public void testSimpleCalc(){
		SimpleCalcEngine engine = new SimpleCalcEngine();
		String[] es = getExpression();
		for(String e:es){
			BigDecimal result = engine.calc(e, null);
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
	
	@Test
	public void testVarCalc(){
		SimpleCalcEngine engine = new SimpleCalcEngine();
		BigDecimal result = engine.calc("(dogtest+cat)*cat+elephant-11*(-monkey)/5", new PojoField2VarSource<Zoo>(new Zoo()));
		System.err.println(result);
	}
	@Test
	public  void testContext(){
		ExpressionContext context = new ExpressionContext(new ExpressionConfig().config("./bin"),new FileExpression(new File("./bin/m-expression.txt")));
		BigDecimal result = context.calcByExpressionId("num", new PojoField2VarSource<Zoo>(new Zoo()));
		System.err.println(result);
	}
	@Test
	public void testPath(){
		System.out.println(new File("./").getAbsolutePath());
	}
}
