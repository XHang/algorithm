package com.cxh.simplealgorithm;


import org.junit.Test;

public class SortTest {
	
	
	@Test
	public void bubbleSortTest(){
		System.out.println("now create Out of Order Int Array");
		int [] arr = new int[10];
		for(int i=0;i<arr.length;i++){
			arr[i] = (int) (Math.random()*100);
		}
		traverse(arr);
		SortUitl.bubbleSort(arr);
		System.out.println("already bubbleSort");
		traverse(arr);
	}
	
	public void traverse(int arr[]){
		System.out.println("now traverse Array");
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",");
		}
		System.out.println();
	}
}
