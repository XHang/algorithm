package com.cxh.simplealgorithm;

/**
 * 查找相关的工具类
 */
public class SearchUtil {

    /**
     * 本方法将使用二分法查找目标数字在数组的哪个索引上
     *
     * @param arr    要查找的数组，必须有序
     * @param target 要查找的数据
     * @return 索引，如果不存在，返回-1
     */
    public static int BinarySearch(int[] arr, int target) {
        int max = arr.length - 1;
        int min = 0;
        //先假设目标数组是升序排列
        while (max >= min) {
            int mid = (max+min) / 2;
            if (arr[mid] > target) {
                max = mid;
            } else if (arr[mid] < target) {
                min = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
