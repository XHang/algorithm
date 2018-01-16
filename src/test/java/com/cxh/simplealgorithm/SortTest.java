package com.cxh.simplealgorithm;


import org.junit.Test;

public class SortTest {
	
	
	@Test
	public void bubbleSortTest(){
		System.out.println("now create Out of Order Int Array");
		int[] arr = SortUitl.getOutOfOrderArray(10,100);
		SortUitl.traverse(arr);
		SortUitl.bubbleSort(arr);
		System.out.println("already bubbleSort");
		System.out.println("now traverse Array");
		SortUitl.traverse(arr);
	}
	
	
	
	@Test
	public void selectSortTest(){
		System.out.println("now ready selectSort");
		System.out.println("now create Out of Order Int Array");
		int[] arr = SortUitl.getOutOfOrderArray(40,1000);
		SortUitl.selectSort(arr);
		System.out.println("now traverse Array");
		SortUitl.traverse(arr);
	}
	
}
