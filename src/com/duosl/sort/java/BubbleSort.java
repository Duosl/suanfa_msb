package com.duosl.sort.java;

import com.duosl.LogUtils;
import com.duosl.sort.SortUtils;

/**
 * 冒泡排序
 * 1. 取第一个数字和第二个数字进行比较，如果第一个大于第二数字，则进行换位
 * 2. 然后用上一次比较的较大值与第三个值进行比较，如果大于第三个值，则换位，以此类推，知道找出最大值，放到数组的最后一个位置上
 * 3. 然后重复进行1，2操作，找出数组中的次大值，放在数组的倒数第二个位置，以此类推，知道数组排序成功
 * <p>
 * 时间复杂度：O(n²)
 */
public class BubbleSort implements SortUtils.ISort, SortUtils.ISort2 {

    public static void main(String[] args) {
        mytest();

        // 测试算法时间
        // testO();

        // 算法正确性校验
        // SortUtils.sortChecker(new BubbleSort());
    }

    private static void mytest() {
        int[] arr = new int[]{10, 13, 18, 9, 7, 16, 11, 8, 4, 0};
        LogUtils.printArr("待排序数组为：", arr);
        BubbleSort bubble = new BubbleSort();
        // bubble.sort(arr);
        bubble.sort2(arr);
        LogUtils.printArr("排序完成：", arr);
    }

    private int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtils.swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    private int[] bubbleSort2(int[] arr) {
        /**
         * 优化点：
         * 每次比较大小时，不进行swap，记录最大值的下标，到改轮比较结束后，把最大值放在待比较数组的最后一个位置
         */
        for (int i = 0; i < arr.length - 1; i++) {
            // 最大值的下标
            int tempMaxValueIndex = 0;
            // 该轮循环，需要比对的的数组长度(= 数组总长度 - 已排好序的数组长度)
            int maxLength = arr.length - i;
            for (int j = 0; j <= maxLength - 1; j++) {
                if (arr[j] > arr[tempMaxValueIndex]) {
                    tempMaxValueIndex = j;
                }
            }
            SortUtils.swap(arr, tempMaxValueIndex, maxLength - 1);
        }
        return arr;
    }

    private static void testO() {
        BubbleSort bubbleSort = new BubbleSort();
        SortUtils.sortTestO(bubbleSort, bubbleSort);
    }

    @Override
    public int[] sort(int[] arr) {
        return bubbleSort(arr);
    }

    @Override
    public int[] sort2(int[] arr) {
        return bubbleSort2(arr);
    }
}
