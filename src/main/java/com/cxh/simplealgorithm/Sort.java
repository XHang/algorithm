package com.cxh.simplealgorithm;

/**
 * 再熟悉下排序
 */
public class Sort {

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr.length-(i+1) ; j++) {
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void selectSort(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j <arr.length ; j++) {
                if (arr[i]>arr[j]){
                    int temp = arr[j];
                    arr[j]=arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}
