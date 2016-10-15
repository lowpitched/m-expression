package com.mlh.expression.model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * 计算单元链
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月14日下午9:02:09
 */
public class CalcUnitChain {

	private ArrayList<CalcUnit> chain = new ArrayList<CalcUnit>();
	/*
	public void add(CalcUnit unit){
		chain.add(unit);
	}
	
	public List<CalcUnit> getChain(){
		return chain;
	}
	*/
	int currPoint=-1;
	
	public void add(CalcUnit unit){
		chain.add(unit);
	}
	
	public boolean remove(CalcUnit unit){
		currPoint--;
		return chain.remove(unit);
	}
	
	public void sort(){
		Collections.sort(chain);
	}
	
	public boolean hasNext(){
		return currPoint==chain.size()?false:true;
	}
	
	public CalcUnit next(){
		currPoint++;
		CalcUnit unit = chain.get(currPoint);
		return unit;
	}
	
	public int size(){
		return chain.size();
	}
	
}
