package com.duosl.sort;

import com.duosl.LogUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 排序算法工具类
 */
public class SortUtils {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     * 排序算法检测器，自动生成随机数组
     * 将自定义排序算法结果与系统排序算法结果对比，检查自定义算法的准确性
     *
     * @param iSort 自定义算法
     */
    public static void sortChecker(ISort iSort) {
        // LogUtils.setDebug(false);
        int[][] arr = randomArr(10000, 10);
        boolean success = true;
        for (int i = 0; i < arr.length; i++) {
            int[] originArr = Arrays.copyOf(arr[i], arr[i].length);
            int[] result = iSort.sort(Arrays.copyOf(arr[i], arr[i].length));
            boolean b = sortChecker(arr[i], result);
            if (!b) {
                success = false;
                LogUtils.error("==========> 您的算法出错了~ ============");
                LogUtils.printErrorArr("原数组为：", originArr);
                LogUtils.printErrorArr("您的排序：", result);
                LogUtils.error("=======================================");
                break;
            } else {
                // LogUtils.log("【TRUE】" + Arrays.toString(result));
            }
        }
        if (success) {
            LogUtils.log("True");
        }
    }

    /**
     * 测试两个算法的时间
     */
    public static void sortTestO(ISort iSort, ISort2 iSort2) {
        // LogUtils.setDebug(false);
        int[][] arr = randomArr(1000, 1000);
        Callable<Boolean> task1 = () -> {
            LogUtils.log("task1 start...");
            long start1 = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                iSort.sort(Arrays.copyOf(arr[i], arr[i].length));
            }
            LogUtils.log("task1: %s", calcTime(System.currentTimeMillis() - start1));
            return true;
        };
        Callable<Boolean> task2 = () -> {
            LogUtils.log("task2 start...");
            long start2 = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                iSort2.sort2(Arrays.copyOf(arr[i], arr[i].length));
            }
            LogUtils.log("task2: %s", calcTime(System.currentTimeMillis() - start2));
            return true;
        };
        Future<Boolean> submit1 = executor.submit(task1);
        Future<Boolean> submit2 = executor.submit(task2);
        try {
            submit1.get();
            submit2.get();
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成一个长度为 @param arrSize 的数组
     *
     * @param arrSize 数组长度
     * @return 生成的数组
     */
    public static int[] randomArr(int arrSize) {
        return randomArr(1, arrSize)[0];
    }

    public static int[][] randomArr(int arrCount, int arrSize) {
        int[][] arr = new int[arrCount][arrSize];
        for (int i = 0; i < arrCount; i++) {
            Random random = new Random();
            for (int j = 0; j < arrSize - 1; j++) {
                arr[i][j] = random.nextInt(arrSize * 2);
            }
        }
        return arr;
    }

    /**
     * 数组特定下标两个元素进行位置交换
     */
    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * 检查排序算法是否正确
     */
    private static boolean sortChecker(int[] originArr, int[] resultArr) {
        Arrays.sort(originArr);
        for (int i = 0; i < originArr.length; i++) {
            if (resultArr[i] != originArr[i]) {
                return false;
            }
        }
        return true;
    }

    private static String calcTime(long time) {
        if (time > 10_000L && time <= 999_999) {
            return time / 1000.f + "s";
        } else if (time > 999_999) {
            return time / 1000.f / 1000.f + "min";
        } else {
            return time + "ms";
        }
    }

    /**
     * 排序算法接口，要调用工具类方法时，可以需要当参数传进来，具体详见方法实现
     */
    public interface ISort {
        int[] sort(int[] arr);
    }

    public interface ISort2 {
        int[] sort2(int[] arr);
    }
}
