package stack;

/**
 * 定义一个ArrayStack表示栈
 */
public class ArrayStack {
	public int maxSize;//栈的大小
	private int[] stack;//利用数组模拟栈，数据就存放在该数组中
	private int top = -1;//top表示栈顶，初始化为-1

	public ArrayStack(int maxSize) {
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
}
