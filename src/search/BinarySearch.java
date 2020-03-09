package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用二分查找的前提是 该数组是有序的
 */
public class BinarySearch {
	public static void main(String[] args) {
		int arr[] = {1,8,10,89,1000,1000,1000,1234};
//		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//		System.out.println("resIndex = "+resIndex);

		List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 100);
		if (list == null || list.size()==0){
			System.out.println("没有找到");
		}else{
			System.out.println("结果是"+list);
		}
	}

	/**
	 * 二分查找算法：返回找到的第一个数
	 * @param arr 数组
	 * @param left 左边的索引
	 * @param right 右边的索引
	 * @param findVal 要查找的值
	 * @return 如果找到就返回下标，如果没有找到，就返回-1
	 */
	public static int binarySearch(int[] arr, int left, int right, int findVal){
		//当left>right时，说明递归这个数组，但是没有找到
		if (left > right){
			return -1;
		}

		int mid = (left + right)/2;
		int midVal = arr[mid];

		if (findVal > midVal){
			return binarySearch(arr,mid+1,right,findVal);
		} else if (findVal < midVal){
			return binarySearch(arr,left,mid-1,findVal);
		} else {
			return mid;
		}
	}

	/**
	 * 有多个相同的数值时，将所有的数值都查到
	 * @param arr
	 * @param left
	 * @param right
	 * @param findVal
	 * @return
	 */
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
		//当left>right时，说明递归这个数组，但是没有找到
		if (left > right){
			return new ArrayList<Integer>();
		}

		int mid = (left + right)/2;
		int midVal = arr[mid];

		if (findVal > midVal){
			return binarySearch2(arr,mid+1,right,findVal);
		} else if (findVal < midVal){
			return binarySearch2(arr,left,mid-1,findVal);
		} else {
			/**
			 * 找到mid索引后，不要马上返回
			 * 向mid索引值的左边扫描，将所有满足1000的元素的下标加入到集合ArrayList
			 * 向mid索引值的右边扫描，将所有满足1000的元素的下标加入到集合ArrayList
			 * 将ArrayList返回
			 */
			List<Integer> resIndexList = new ArrayList<>();
			resIndexList.add(mid);

			//向mid索引值的左边扫描，将所有满足1000的元素的下标加入到集合ArrayList
			int temp = mid-1;
			while (true){
				if (temp<0 || arr[temp]!=findVal){//退出
					break;
				}
				//否则，就将temp放入到resIndexList
				resIndexList.add(temp);
				temp--;//temp左移
			}

			//向mid索引值的右边扫描，将所有满足1000的元素的下标加入到集合ArrayList
			temp = mid+1;
			while (true){
				if (temp>arr.length-1 || arr[temp]!=findVal){//退出
					break;
				}
				//否则，就将temp放入到resIndexList
				resIndexList.add(temp);
				temp++;//temp左移
			}
			return resIndexList;
		}
	}
}
