package recursion;

public class Queue8 {

	//定义一个max表示共有多少个皇后
	int max = 8;
	//定义数组array，保存皇后放置位置的结果，比如 arr={0,4,7,5,2,6,1,3}
	int[] array = new int[max];
	static int count = 0;
	static int judgeCount = 0;

	public static void main(String[] args){

		Queue8 queue8 = new Queue8();
		queue8.check(0);
		System.out.println("一共有 "+count+" 种解法");
		System.out.println("一共判断冲突的次数为 " + judgeCount);

	}

	private void check(int n){
		if (n == max){
			print();
			return;
		}

		for (int i = 0; i < max; i++) {
			//先把当前的这个皇后n，放到该行的第1列
			array[n] = i;
			//判断当前放置的第n个皇后到i列时，是否冲突
			if (judge(n)){//不冲突
				//接着放 n+1 个皇后，即开始递归
				check(n+1);
			}
			//如果冲突，就继续执行array[n]=i,即将第n个皇后，放置在本行的后移的一个位置
		}
	}

	/**
	 * 查看当我们放置在第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
	 * @param n
	 * @return
	 */
	private boolean judge(int n){
		judgeCount++;
		for (int i = 0; i < n; i++) {
			/*
			array[i] == array[n] 表示判断第n个皇后是否和第i个皇后在同一列
			Math.abs(n-i) == Math.abs(array[n]-array[i] 表示判断第n个皇后是否和第i个皇后在同一斜线上
			 */
			if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
				return false;
			}
		}
		return true;
	}

	/**
	 * 将皇后摆放的位置输出
	 */
	private void print(){
		count++;
		for (int i = 0; i < array.length; i++) {
			System.out.printf(array[i]+ " ");
		}
		System.out.println();
	}
}
