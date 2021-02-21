package com.duosl;

import java.util.Arrays;

public class LogUtils {

    private static boolean mDebug = true;

    public static void setDebug(boolean debug) {
        mDebug = debug;
    }

    public static void printArr(String prefixStr, int[] arr) {
        if (mDebug) {
            System.out.print(prefixStr + Arrays.toString(arr) + "\n");
            //            for (int i = 0; i < arr.length; i++) {
            //                System.out.print(arr[i] + " ");
            //            }
        }
    }

    public static void printArr(int[] arr) {
        LogUtils.printArr("", arr);
    }

    public static void printErrorArr(String prefixStr, int[] arr) {
        if (mDebug) {
            System.err.print(prefixStr + Arrays.toString(arr) + "\n");
            //            for (int i = 0; i < arr.length; i++) {
            //                System.out.print(arr[i] + " ");
            //            }
        }
    }

    public static void printErrorArr(int[] arr) {
        LogUtils.printArr("", arr);
    }

    public static void log(String log, Object... objects) {
        if (mDebug) {
            System.out.println(String.format(log, objects));
        }
    }

    public static void error(String log, Object... objects) {
        if (mDebug) {
            System.err.println(String.format(log, objects));
        }
    }
}
