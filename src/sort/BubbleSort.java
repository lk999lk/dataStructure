package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
	public static void main(String[] args) {
//		int arr[] = {3,9,-1,10,20};
//		System.out.println("排序前");
//		System.out.println(Arrays.toString(arr));
//
//		bubbleSort(arr);

		//测试冒泡排序的时间
		int[] arr2 = Utils.createArr();

		long before = Utils.before();

		bubbleSort(arr2);

		long after = Utils.after();

		System.out.println("排序后的顺序为 ");
		System.out.println(Arrays.toString(arr2));
		System.out.println("花费的时间为：" + (after-before));

	}


	public static void bubbleSort(int[] arr){
		int temp = 0;//临时变量
		boolean flag = false;//标识变量，标识是否进行过交换
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if (arr[j]>arr[j+1]){
					flag = true;
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}

//			System.out.println("第" + (i+1) + "趟排序后的数组");
//			System.out.println(Arrays.toString(arr));

			if (!flag){//在一趟排序中，一次交换都没有发生过
				break;
			} else{
				flag = false;//重置flag,进行下次判断
			}
		}
	}

}
