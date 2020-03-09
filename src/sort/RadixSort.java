package sort;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
//		int arr[] = {53, 3, 542, 748, 14, 214};
//		radixSort(arr);
//		System.out.println("排序后为 " + Arrays.toString(arr));

		int[] arr2 = Utils.createArr();
		long before = Utils.before();
		radixSort(arr2);
		long after = Utils.after();

		System.out.println("排序后的顺序为 " + Arrays.toString(arr2));
		System.out.println(before + " ========= " + after);
		System.out.println("花费的时间为：" + (after-before));
	}

	/*
	基数排序方法
	 */
	public static void radixSort(int[] arr){

		//得到数组中最大的数的位数
		int max = arr[0];//假设第一数就是最大数
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max){
				max = arr[i];
			}
		}
		//得到最大数是几位数
		int maxLength = (max+"").length();

		/*
		定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		1. 二维数组包含10个一维数组
		2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
		3. 必须明确，基数排序是使用空间换时间的经典算法
		 */
		int[][] bucket = new int[10][arr.length];

		/*
		为了记录每个桶中，实际存放了多少个数据，我们定义了一个一位数组来记录各个桶的每次放入的数据个数
		比如：counts[0]记录的就是bucket[0]桶的放入数据个数
		 */

		int[] counts = new int[10];

		for (int i = 0, n=1; i < maxLength; i++, n*=10) {
			//针对每个元素的对应位进行排序处理，第一位是个位，第二个是十位，第三位是百位
			for (int j = 0; j < arr.length; j++) {
				//取出每个元素的对应位的值
				int element = arr[j] / n % 10;
				//放入到对应的桶中
				bucket[element][counts[element]] = arr[j];
				counts[element]++;

			}

			//按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
			int index = 0;
			for (int k = 0; k < counts.length; k++) {
				//如果桶中有数据，将数据放入到原数组
				if (counts[k] != 0){
					//循环该桶(即第i个一维数组)，放入
					for (int l = 0; l < counts[k]; l++) {
						arr[index++] = bucket[k][l];
					}
				}
				//数据放完之后，需要将每个counts[k]=0!!!
				counts[k] = 0;
			}
			System.out.println("第 " +(i+1) +" 轮，对个位的排序处理 arr = " + Arrays.toString(arr));
		}
	}
}
