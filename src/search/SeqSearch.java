package search;

import java.util.ArrayList;
import java.util.List;

public class SeqSearch {

	public static void main(String[] args) {
		int arr[] = {1,3,11,-1,34,89,-1,-1,11,11,11};
		int index = seqSearch(arr,11);
		if (index == -1) {
			System.out.println("没有找到");
		} else {
			System.out.println("找到，下标为：" + index);
		}

		List<Integer> list = seqSearch2(arr, 11);
		if (list == null){
			System.out.println("没有找到");
		}else{
			System.out.println("找到了，下标为："+ list.toString());
		}


	}


	/**
	 * 找到第一个满足条件的值，就返回
	 * @param arr
	 * @param value
	 * @return 找到就返回小标，要不然就返回-1
	 */
	public static int seqSearch(int[] arr, int value){
		//线性查找是逐一对比，发现有相同值，就返回下标
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value){
				return i;
			}
		}
		return -1;
	}

	/**
	 * 返回所有满足条件的下标
	 * @param arr
	 * @param value
	 * @return
	 */
	public static List<Integer> seqSearch2(int[] arr, int value){
		ArrayList<Integer> list = new ArrayList<>();
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value){
				list.add(i);
				flag = true;
			}
		}
		if (flag){
			return list;
		}
		return null;
	}
}
