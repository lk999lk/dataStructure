package stack;

public class ArrayStack2 {
	public int maxSize;//栈的大小
	private int[] stack;//利用数组模拟栈，数据就存放在该数组中
	private int top = -1;//top表示栈顶，初始化为-1

	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}

	//栈满
	public boolean isFull(){
		return top == maxSize - 1;
	}

	//栈空
	public boolean isEmpty(){
		return top == -1;
	}

	//入栈push
	public void push(int value){
		//先判断栈是否为满
		if (isFull()){
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}

	//出栈pop，将栈顶的数据返回
	public int pop(){

		//先判断栈是否为空
		if (isEmpty()){
			throw new RuntimeException("栈空，没有数据--");
		}
		int value = stack[top];
		top--;
		return value;
	}

	//显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
	public void list(){
		if (isEmpty()){
			System.out.println("栈空，没有数据--");
			return;
		}
		//需要从栈顶开始显示数据
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}

	}

	/*
	增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
	 */
	public int peek(){
		return stack[top];
	}

	public int priority(int oper){
		if (oper == '*' || oper == '/'){
			return 1;
		} else if (oper == '+' || oper == '-'){
			return 0;
		} else {
			return -1;//假设目前的表达式只有加减乘除
		}
	}

	//判断是不是一个运算符
	public boolean isOper(char val){
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	//计算方法
	public int cal(int num1,int num2,int oper){
		int res = 0;//res用于存放计算的结果
		switch (oper){
			case '+':
				res = num1 + num2;
				break;
			case '-':
				res = num2 -num1;
				break;
			case '*':
				res = num1 * num2;
				break;
			case '/':
				res = num2 / num1;
				break;
			default:
				break;
		}
		return res;

	}
}
