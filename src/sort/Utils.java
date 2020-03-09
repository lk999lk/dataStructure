package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Utils {
	public static int[] createArr(){
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*800000);//生成一个 [0-800000)的随机数
		}
		System.out.println("排序前的顺序为 ");
		System.out.println(Arrays.toString(arr));
		return arr;
	}

	public static long before(){
		Date date1 = new Date();
		System.out.println(date1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = simpleDateFormat.format(date1);
		System.out.println("排序前的时间为 " + s);
		long time = System.currentTimeMillis();
		return time;
	}

	public static long after(){
		Date date2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s2 = simpleDateFormat2.format(date2);
		System.out.println("排序后的时间为 " + s2);
		long time = System.currentTimeMillis();
		return time;
	}


}
