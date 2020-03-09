package sort;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
//		int[] arr = {101,34,119,1,-1,90,123};
//		System.out.println(Arrays.toString(arr));
//		insertSort(arr);

		int[] arr2 = Utils.createArr();

		Utils.before();

		insertSort(arr2);

		Utils.after();

		System.out.println("排序后的顺序为 ");
		System.out.println(Arrays.toString(arr2));
	}

	public static void insertSort(int[] arr){
		int insertVal = 0;
		int insertIndex = 0;

		//使用for循环来把代码简化
		for (int i = 1; i < arr.length; i++) {
			//定义待插入的数
			insertVal = arr[i];
			//即arr[1]的前面这个数的下标
			for (insertIndex = i - 1; insertIndex>=0 && insertVal<arr[insertIndex]; insertIndex--){
				arr[insertIndex+1] = arr[insertIndex];

			}
			if (insertIndex+1 != i){
				arr[insertIndex+1] = insertVal;
			}

//			System.out.println("第 " +i+ " 轮插入");
//			System.out.println(Arrays.toString(arr));

		}
	}
}
