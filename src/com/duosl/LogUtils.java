package com.duosl;

import java.util.Arrays;

public class LogUtils {

    private static boolean mDebug = false;

    public static void setDebug(boolean debug) {
        mDebug = debug;
    }

    public static void printArr(String prefix, int[] arr) {
        if (mDebug) {
            System.out.print(prefix);
//            for (int i = 0; i < arr.length; i++) {
//                System.out.print(arr[i] + " ");
//            }
            System.out.print(Arrays.toString(arr));
            System.out.println();
        }
    }

    public static void printArr(int[] arr) {
        LogUtils.printArr("", arr);
    }

    public static void log(String log, Object...objects){
        System.out.println(String.format(log, objects));
    }

    public static void error(String log, Object...objects){
        System.err.println(String.format(log, objects));
    }
}
