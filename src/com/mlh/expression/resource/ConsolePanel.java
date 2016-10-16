package com.mlh.expression.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import com.mlh.expression.engine.SimpleCalcEngine;


/**
 * 命令行模式输入输出(不支持变量)
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午12:58:51
 */

public class ConsolePanel {

	public static void main(String[] args){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			SimpleCalcEngine engine = new SimpleCalcEngine();
			System.out.println("<<请输入公式");
			while(true){
				System.out.print(">>");
				String expression = reader.readLine();
				if("exit".equals(expression.trim())){
					break;
				}
				BigDecimal result = engine.calc(expression, null).setScale(8, BigDecimal.ROUND_HALF_UP);
				System.out.println("<<"+result);
			}
		} catch (IOException e) {
			System.out.println(">>ioexception");
			e.printStackTrace();
		}
	}
	
}
