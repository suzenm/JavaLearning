package com.example;

import java.util.Arrays;

public class SortAlgorithm {
    private int[] sourceArray;

    public SortAlgorithm() {
    }

    public SortAlgorithm(int[] sourceArray) {
        this.sourceArray = sourceArray;
    }

    /**
     * 冒泡排序
     * @return 已排序数组
     */
    public int[] bubbleSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 1; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];

                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * @return 已排序数组
     */
    public int[] selectionSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[i] ^= arr[minIndex];
                arr[minIndex] ^= arr[i];
                arr[i] ^= arr[minIndex];
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * @return 已排序数组
     */
    public int[] insertSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * 希尔排序
     * @return 已排序数组
     */
    public int[] shellSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && tmp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3.0);
        }
        return arr;
    }
}
