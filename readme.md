####项目名称：m-expression(表达式计算引擎)
####项目版本：v1.0.00-release
####项目功能：输入字符串表达式输出计算结果（内部用BigDecimal实现，保证精度）
####项目特色：支持变量表达式。调用时需传入变量池(变量-值映射)，用以替换表达式中的变量进行计算。
####项目局限：目前只支持加减乘除四则运算
####项目使用:
1. 控制台模式
	* 1) 运行calculator.bat
	* 2) 输入运算式，如：((442.98*23-12)*7+6)-11.5*6
	* 3) 回车，输出计算结果
2. API模式
	* 1)配置配置文件m-expression-config.txt(文件名称不可变)，配置文件中有各项配置说明
	* 2)编写表达式配置文件，可参考m-expression.txt
	* 3)入口类ExpressionContext，构造需要两个参数ExpressionConfig和FileExpression
	* 4)配置文件解析类ExpressionConfig 解析m-expression-config.txt(配置文件名称不可变)
	* 5)表达式配置文件FileExpression 解析示例中的 m-expression.txt(配置文件名称自定义)
	* 6)调用ExpressionContext 方法calcByExpressionId，需传入expressionId(表达式ID)和IVarSource(变量池数据源)
	* 7)需要将POJO对象的字段转换为变量池时，请使用PojoFiled2VarSource 传入的POJO参数，需要加入变量池的请使用@Var注解
<br/>

####代码示例：
<pre>
<code>
//需要将Zoo对象的字段值放入变量池用@Var注解修饰。 name为变量名，缺省时变量名为字段名
public class Zoo {
	@Var(name="dogtest")
	private int dog = 2;
	@Var
	private int cat = 4;
	@Var
	private int elephant = 5;
	@Var
	private int monkey = 8;
	//不加Var注解不会将此字段放入变量池
	private int tigger=10;
	
	...get、Set方法请自行补充
	
}
</code>
</pre>
<pre>
<code>
public class ExpressionStart {
	public static void main(String[] args){
		ExpressionContext context = new ExpressionContext(new ExpressionConfig().config("./bin"),new FileExpression(new File("./bin/m-expression.txt")));
		BigDecimal result = context.calcByExpressionId("num", new PojoField2VarSource(new Zoo()));
		System.err.println(result);
	}
}
</code>
</pre>
