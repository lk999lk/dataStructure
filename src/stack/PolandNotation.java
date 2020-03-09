package stack;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
	public static void main(String[] args) {
		/**
		 * 先定义给逆波兰表达式
		 * (30+4)*5-6 => (((30+4)*5)-6) => (((30 4)+5)*6)- => 30 4 + 5 * 6 - => 164
		 * 4*5-8+60+8/2 =76 => 4 5 * 8 - 60 + 8 2 / +
		 */

		//将suffixExpression放入ArrayList中
//		String suffixExpression = "30 4 + 5 * 6 -";
		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList="+list);

//		String[] split2 = suffixExpression.split(" ");
//		for (String ele :split2)
//		System.out.println(ele);

		int res = calculate(list);
		System.out.println("计算的结果是：" + res);

		//中缀表达式转换为对应的List
		//中缀表达式对应的List => 后缀表达式对应的List
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("中缀表达式对应的List: " + infixExpressionList);
		List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
		System.out.println("后缀表达式对应的List: " + suffixExpressionList);
		//计算
		System.out.println(calculate(suffixExpressionList));


	}

	/**
	 * 将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
	 * @param suffixExpression
	 * @return
	 */
	public static List<String> getListString(String suffixExpression){
		//将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		ArrayList<String> list = new ArrayList<>();
		for (String ele : split){
			list.add(ele);
		}
		return list;
	}

	/**
	 * 完成对逆波兰表达式的运算
	 *
	 * @param ls
	 * @return
	 */
	public static int calculate(List<String> ls){
		//创建给栈，只需要一个栈即可
		Stack<String> stack = new Stack<>();
		//遍历ls
		for (String item : ls){
			//使用这则表达式来取出数
			if (item.matches("\\d+")){//匹配的是多位数
				//入栈
				stack.push(item);
			} else {
				//pop出两个数，并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")){
					res = num1 + num2;
				} else if (item.equals("-")){
					res = num1 - num2;
				} else if (item.equals("*")){
					res = num1 * num2;
				} else if (item.equals("/")){
					res = num1 / num2;
				} else{
					throw new RuntimeException("运算符有误");
				}
				//把res入栈
				stack.push(""+res);
			}
		}
		//最后留在stack中的数据是运算结果
		return Integer.parseInt(stack.pop());
	}

	/**
	 * 将得到的中缀表达式对应的List => 后缀表达式对应的List
	 * @param ls
	 * @return
	 */
	public static List<String> parseSuffixExpressionList(List<String> ls){
		//定义两个栈
		Stack<String> s1 = new Stack<>();//符号栈
		//说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
		//因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String>
//		Stack<String> s2 = new Stack<>();
		ArrayList<String> s2 = new ArrayList<>();//储存中间结果的List2

		//遍历ls
		for (String item : ls){
			//如果是一个数，加入s2
			if (item.matches("\\d+")){
				s2.add(item);
			} else if (item.equals("(")){
				s1.push(item);
			} else if (item.equals(")")){
				//如果是右括号，则依次弹出是s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
				while (!s1.peek().equals("(")){
					s2.add(s1.pop());
				}
				s1.pop();//将"("弹出s1栈，消除小括号
			} else{
				/*当item的优先级小于等于s1栈顶运算符，将s1栈顶运算符弹出并加入到s2中
				再次与s1中新的栈顶运算符作比较
				 */
				while (s1.size() != 0 && Operarion.getValue(s1.peek()) >= Operarion.getValue(item)){
					s2.add(s1.pop());
				}
				//还需要将 item 压入栈
				s1.push(item);
			}
		}

		//将s1中剩余的运算符依次弹出并加入s2
		while (s1.size() != 0){
			s2.add(s1.pop());
		}
		return s2;
	}

	//将中缀表达式转换为对应的List
	public static List<String> toInfixExpressionList(String s){
		//定义一个List，存放中缀表达式，对应的内容
		ArrayList<String> ls = new ArrayList<>();
		int i = 0;//这时是一个指针，用于遍历中缀表达式字符串
		String str;//对多位数的拼接
		char c;//每遍历一个字符，就放入到c
		do {
			//如果c是一个非数字，就需要加入到ls
			if ((c=s.charAt(i))<48 || (c=s.charAt(i))>57){
				ls.add(""+c);
				i++;//i需要后移
			} else{//如果是一个数，需要考虑多位数
				str = "";//先将str置成""
				while (i<s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
					str += c;//拼接
					i++;
				}
				ls.add(str);
			}

		} while (i<s.length());
		return ls;
	}
}

/**
 * 编写一个Operation 可以返回一个运算符对应的优先级
 */
class Operarion{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	//写一个方法，返回对应的优先级数字
	public static int getValue(String operation){
		int result = 0;
		switch(operation){
			case "+":
				result = ADD;
				break;
			case "-":
				result = SUB;
				break;
			case "*":
				result = MUL;
				break;
			case "/":
				result = DIV;
				break;
			default:
				System.out.println("不存在该运算符：()");
				break;
		}
		return result;
	}
}
