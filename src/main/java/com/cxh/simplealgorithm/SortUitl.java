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
}