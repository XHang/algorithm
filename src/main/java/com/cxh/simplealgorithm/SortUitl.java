package com.cxh.simplealgorithm;

public class SortUitl {
	
	
	/**
	 * 冒泡排序
	 * 关键在于内部循环里面依次交换相邻的值
	 * 大者排后
	 * @param arr
	 */
	public static void bubbleSort(int [] arr){
		int length = arr.length;
		for(int i=0;i<length;i++){
			//内部循环不用循环每一个元素，因为是两两比较的
			for(int j=0;j<length-(i+1);j++){
				if(arr[j]>arr[j+1]){
					swapByAdd(arr,j,j+1);
				}
			}
		}
	}


	/**
	 * 交换数组两个角标的值
	 * 通过加法交换
	 * A=3  B=2
	 * A=5 B=2
	 * B=A-B=5-2=3
	 * A=A-B=5-3=2
	 * @param arr
	 * @param index1
	 * @param index2
	 */
	public static void swapByAdd(int [] arr,int index1,int index2){
		arr[index1] = arr[index1]+arr[index2];
		arr[index2] =  arr[index1]-arr[index2];
		arr[index1] =arr[index1]-arr[index2];
	}
	
	/**
	 * 选择排序
	 * 其实就是内部每次循环循环后取出最大，或者最小的值放到开头或者末尾。
	 * 外部循环后，得到的数组就是排序好的了
	 * 这个方法采取的是，将最大的值放到开头，也就是倒序
	 * 和冒泡排序的区别？
	 * 冒泡排序 每次都要交换，交换次数多，选择排序只要在每次内部循环后交换最大的值和开头的值就行
	 * @param arr
	 */
	public static  void selectSort(int [] arr){
		for(int i=0;i<arr.length;i++){
			int maxIndex = i;
			//j=i是因为每次内部循环后，最大的值都放到开头了，那些值不需要再比较一遍了
			//至于i+1是因为选定的值不需要和自身进行比较
			for(int j=i+1;j<arr.length;j++){
				if(arr[maxIndex]<arr[j]){
					maxIndex = j;
				}
			}
			swapByAdd(arr,maxIndex,i);
		}
	}
	
	/**
	 * 获取一个乱序的数组
	 * @param size  需要的数组大小
	 * @param rate 倍率，随机数的倍率 如100既是2位随机数
	 * @return
	 */
	public static int[] getOutOfOrderArray(int size,int rate){
		int [] arr = new int[size];
		for(int i=0;i<arr.length;i++){
			arr[i] = (int) (Math.random()*rate);
		}
		return arr;
	}
	
	public static void traverse(int arr[]){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",");
		}
		System.out.println();
	}
}