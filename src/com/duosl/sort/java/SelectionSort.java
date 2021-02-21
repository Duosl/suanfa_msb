package com.duosl.sort.java;

import com.duosl.LogUtils;
import com.duosl.sort.SortUtils;

/**
 * 选择排序Java版
 * <p>
 * 基本原理：
 * 1. 假设第一个数字为最小值，记录最小值下标为0
 * 2. 与第二个数字（下标为1）比较，如果第二个数字大于第一个数字，则更新最小值下标，然后用新的最小值与第三个数字进行比较
 * 3. 依次类推，直到比较到数组的最后一个数字，将第一个数字与最小值下标的数字进行交互，可得数组下标为0为数组的最小值
 * 4. 然后假设数组的第二个数字为次小值，从该位置开始重复1，2，3步骤
 * 5. 重复4步骤，直到数组的最后一个，即可完成排序
 * <p>
 * 时间复杂度：O(n²)
 */
public class SelectionSort implements SortUtils.ISort, SortUtils.ISort2 {

    public static void main(String[] args) {
        // mytest();
        // 测试算法时间
        testO();

        // 算法正确性校验
        // SortUtils.sortChecker(new SelectionSort());
    }


    /**
     * 基础版本
     *
     * @param arr 待排序数组
     */
    private int[] sortBasic(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = minIndex + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SortUtils.swap(arr, minIndex, i);
            LogUtils.printArr(arr);
        }
        LogUtils.printArr(arr);
        return arr;
    }

    /**
     * 优化方案1 (适用于大量数据的优化)
     * 尝试一轮循环找出最大值和最小值，分别放在第一个位置和最后一个位置，外层循环次数减半
     * <p>
     * 经过验证，在10个长度10w的数组的情况下，时间上并没有得到优化，还不如{@link #sortBasic(int[])}速度快
     * 但在10_000个10_000条数据时，确实时间更快
     * <p>
     * TODO: 应该还可以优化
     */
    private int[] sortEnhance(int[] arr) {
        int middleIndex = (int) (arr.length / 2f + 0.5f);
        for (int i = 0; i < middleIndex; i++) {
            int minIndex = i;
            int maxIndex = arr.length - 1 - i;

            for (int j = minIndex + 1; j < arr.length - i; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SortUtils.swap(arr, minIndex, i);

            for (int k = maxIndex - 1; k >= i; k--) {
                if (arr[k] > arr[maxIndex]) {
                    maxIndex = k;
                }
            }
            SortUtils.swap(arr, maxIndex, arr.length - 1 - i);
            LogUtils.printArr("第" + (i + 1) + "次排序为：", arr);
        }
        return arr;
    }

    private static void mytest() {
        int[] arr = new int[]{10, 13, 18, 9, 7, 16, 11, 8, 4, 0};
        // LogUtils.setDebug(true);
        LogUtils.printArr("待排序数组为：", arr);
        SelectionSort selection = new SelectionSort();
        // selection.sortBasic(arr);
        selection.sortEnhance(arr);
        LogUtils.printArr("排序完成：", arr);
    }

    /**
     * 测试算法时间
     */
    private static void testO() {
        SelectionSort selectionSort = new SelectionSort();
        SortUtils.sortTestO(selectionSort, selectionSort);
    }

    @Override
    public int[] sort(int[] arr) {
        return sortBasic(arr);
    }

    @Override
    public int[] sort2(int[] arr) {
        return sortEnhance(arr);
    }

}
