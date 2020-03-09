package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
//		int[] arr = {101,34,119,1,-1,90,123};
//		System.out.println(Arrays.toString(arr));
//		selectSort(arr);

		int[] arr2 = Utils.createArr();

		Utils.before();

		selectSort(arr2);

		Utils.after();

		System.out.println("排序后的顺序为 ");
		System.out.println(Arrays.toString(arr2));

	}

	//选择排序
	public static void selectSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			int minIndex = i;
			int min = arr[i];
			for (int j = i+1; j < arr.length; j++) {
				if (min > arr[j]){//说明假定的最小值并不是最小
					min = arr[j];//重置min
					minIndex = j;//重置minIndex
				}
			}
			//将最小值，放在arr[0]，交换
			if (minIndex != i){
				arr[minIndex] = arr[i];
				arr[i] = min;
			}

//			System.out.println("第 " + (i+1) +"轮后的结果为");
//			System.out.println(Arrays.toString(arr));
		}
	}
}
