package search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {
		int[] arr = {1,8,10,89,1000,1234};
		int i = fibSearch(arr, 1234);
		System.out.println(i);

	}

	/**
	 * 使用非递归的方式编写斐波那契查找算法
	 * @param a 数组
	 * @param key 要查找的关键值
	 * @return 返回对应的下标，没有返回-1
	 */
	public static int fibSearch(int[] a, int key){
		int low = 0;
		int high = a.length-1;
		int k = 0;//表示斐波那契分割数值的下标
		int mid = 0;//存放mid值
		int[] f = fib();//获取斐波那契数列

		//获取斐波那契分割数值的下标
		while (high > f[k]-1){
			k++;
		}

		/*
		因为f[k]的值可能会大于a的值，因此需要构建一个新的数组，指向temp[]
		不足的地方用0填充
		 */
		int[] temp = Arrays.copyOf(a,f[k]);
//		System.out.println(Arrays.toString(temp));
		/*
		实际上需要使用a数组最后的数填充 temp
		temp = {1,8,10,89,1000,1234,0,0} --> {1,8,10,89,1000,1234,1234,1234}
		 */
		for (int i = high+1; i < temp.length; i++) {
			temp[i] = a[high];
		}

		//使用while来循环处理，找到我们要找的数key
		while (low <= high) {//只要满足这个条件，就可以找
			mid = low + f[k - 1] - 1;

			if (key < temp[mid]) {//数组左边找
				high = mid - 1;
				k--;
			} else if (key > temp[mid]) {//数组右边找
				low = mid + 1;
				k -= 2;
			} else {//找到
				if (mid <= high) {
					return mid;
				} else {
					return high;

				}
			}
		}
		return -1;

	}

	/**
	 * 非递归方法获取一个斐波那契数列
	 * @return
	 */
	public static int[] fib(){
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
}
