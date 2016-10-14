package com.mlh.expression.parse;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mlh.expression.calcoper.OperatorType;



/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月13日下午5:36:10
 */

public class ExpressionParse implements IExpressionParse{

	public String[] parse(String expression){
		//处理以负号开头的表达式
		expression = startWithMinus(expression);
		
		List<String> result = new ArrayList<String>();
		char[] charArray = expression.toCharArray();
		CharArrayReader reader = new CharArrayReader(charArray);
		try{
			int c;
			int i=0;
			StringBuilder word=new StringBuilder();
			while((c=reader.read())!=-1){
				i++;
				Character r = (char) c;
				int operatorType = OperatorType.whatOperator(r.toString());
				if(OperatorType.OPERATOR_NONE==operatorType){
					word.append(r);
					if(i==expression.length()&&word.length()>0){
						result.add(word.toString().trim());
					}
					continue;
				}else{
					if(word.length()>0){
						result.add(word.toString().trim());
					}
					word.delete(0, word.length());
					result.add(r.toString());
				}
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(null!=reader)
				reader.close();
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * 如果表达式以负号开头(例：-4+1)则在负号前加0
	 * @description 方法描述：
	 * @crater      创建者：MENGLIHAO 
	 * @create      创建时间：2016年10月14日
	 * @param expression
	 * @return
	 */
	private String startWithMinus(String expression) {
		expression = expression.trim().replaceAll("\\(-", "(0-");
		if(expression.trim().startsWith("-")){
			return "0"+expression;
		}
		return expression;
	}
}
