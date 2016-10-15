package com.mlh.expression.test;

import com.mlh.expression.annotation.Var;


/**
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午7:17:06
 * @update 修改时间：2016年10月15日下午7:17:06
 */

public class Zoo {
	@Var(name="dogtest")
	private int dog = 2;
	@Var
	private int cat = 4;
	@Var
	private int elephant = 5;
	@Var
	private int monkey = 8;
	
	public int getDog() {
		return dog;
	}
	public void setDog(int dog) {
		this.dog = dog;
	}
	public int getCat() {
		return cat;
	}
	public void setCat(int cat) {
		this.cat = cat;
	}
	public int getElephant() {
		return elephant;
	}
	public void setElephant(int elephant) {
		this.elephant = elephant;
	}
	public int getMonkey() {
		return monkey;
	}
	public void setMonkey(int monkey) {
		this.monkey = monkey;
	}
	
}
