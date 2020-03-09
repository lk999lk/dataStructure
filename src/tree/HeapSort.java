package tree;

import sort.Utils;

import java.util.Arrays;

/**
 * 给定数组{4,6,8,5,9},要求使用堆排序法。将数组升序排序
 */
public class HeapSort {
	public static void main(String[] args) {
//		int[] arr = {4,6,8,5,9};
//		heapSort(arr);
//		System.out.println(Arrays.toString(arr));

		int[] arr = Utils.createArr();
		long before = Utils.before();
		heapSort(arr);
		long after = Utils.after();
		System.out.println(after-before);

	}

	/*
	将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
	重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换
	步骤，直到整个序列有序
	 */
	public static void heapSort(int[] arr){
		int temp = 0;
		System.out.println("===== 堆排序 =====");

//		adjustHeap(arr,1,arr.length);//4，9，8，5，6
//		adjustHeap(arr,0,arr.length);//9，6，8，5，4

		/*
		将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
		 */
		for (int i = arr.length/2-1; i >= 0; i--) {
			adjustHeap(arr,i,arr.length);
			System.out.println(Arrays.toString(arr));
		}

		for (int i = arr.length-1; i > 0; i--) {
			temp = arr[i];
			arr[i] = arr[0];
			arr[0] = temp;
			adjustHeap(arr,0,i);
		}

	}

	/**
	 * 对于一个数组(二叉树)，将以i对应的非叶子节点的树调整成大顶堆
	 *
	 * @param arr 待排序的数组
	 * @param i 表示非叶子节点在数组中的索引
	 * @param length 表示对多少个元素继续调整，
	 */
	public static void adjustHeap(int[] arr, int i, int length){
		int temp = arr[i];//先取出当前元素的值，保存在临时变量

		/*
		开始调整：
			k=i*2+1,k是i节点的左子节点
		 */
		for (int k=i*2+1; k<length; k=k*2+1){
			if (k+1<length && arr[k]<arr[k+1]){//说明左子节点的值小于右子节点的值
				k++;//k指向右子节点
			}
			if (arr[k]>temp){//如果子节点大于父节点
				arr[i] = arr[k];//把较大的值赋给当前节点
				i = k;//i指向k,继续循环比较
			} else {
				break;
			}
		}
		//当for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶
		arr[i] = temp;//将temp值放到调整后的位置

	}
}
