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

    /**
     * 归并排序
     * @return 已排序数组
     */
    public int[] mergeSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return merge(arr);
    }

    /**
     * 归并算法，将待排数组一分为二，递归传入两个数组
     * @param arr 待排数组
     * @return 已排序数组
     */
    protected int[] merge(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int[] sortedArr = new int[arr.length];
        int middle = (int) Math.floor(arr.length / 2.0);
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        left = merge(left);
        right = merge(right);
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] < right[0]) {
                sortedArr[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                sortedArr[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            sortedArr[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }
        while (right.length > 0) {
            sortedArr[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return sortedArr;
    }

    /**
     * 快速排序
     * @return 已排序数组
     */
    public int[] quickSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return partition(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序分区
     * @param arr 待排数组
     * @param left 分区左边界
     * @param rigtht 分区右边界
     * @return 已排序数组
     */
    protected int[] partition(int[] arr, int left, int rigtht) {
        if (left < rigtht) {
            int i = left;
            int j = rigtht;
            while (i < j) {
                while (arr[j] >= arr[left] && i < j) {
                    j--;
                }
                while (arr[i] <= arr[left] && i < j) {
                    i++;
                }
                swap(arr, i, j);
            }
            swap(arr, left, i);
            partition(arr, left, i - 1);
            partition(arr, i + 1, rigtht);
        }
        return arr;
    }

    /**
     * 交换数组中索引为i和j的位置
     * @param arr 待交换数组
     * @param i i的索引值
     * @param j j的索引值
     */
    protected void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 堆排序
     * @return 已排序数组
     */
    public int[] heapSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;
        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    /**
     * 堆化
     * @param arr 待堆化数组
     * @param i 根节点
     * @param len 堆长度
     */
    private void heapify(int[] arr, int i , int len) {
        int left = 2 * i + 1;
        int rigth = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (rigth < len && arr[rigth] > arr[largest]) {
            largest = rigth;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    /**
     * 创建一个最大堆
     * @param arr 待堆化数组
     * @param len 堆长度
     */
    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2.0); i >= 0 ; i--) {
            heapify(arr, i, len);
        }
    }

    /**
     * 计数排序
     * @return 已排序数组
     */
    public int[] countingSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int max = getMaxValue(arr);
        int min = getMinValue(arr);
        int[] bucket = new int[max - min + 1];
        for (int value :
                arr) {
            bucket[value - min]++;
        }
        int sortedIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                arr[sortedIndex++] = i + min;
                bucket[i]--;
            }
        }
        return arr;
    }

    /**
     * 找到数组中的最大值
     * @param arr 待查找数组
     * @return 数组中的最大值
     */
    private int getMaxValue(int[] arr) {
        int max = arr[0];
        for (int value :
                arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * 找到数组中的最小值
     * @param arr 待查找数组
     * @return 数组中的最小值
     */
    private int getMinValue(int[] arr) {
        int min = arr[0];
        for (int value :
                arr) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    /**
     * 桶排序
     * @return 已排序数组
     */
    public int[] bucketSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        
        int min = getMinValue(arr);
        int max = getMaxValue(arr);
        
        int bucketCount = (int) Math.floor((max - min) / 5.0) + 1;
        int[][] buckets = new int[bucketCount][0];

        for (int value :
                arr) {
            int bucketIndex = (int) Math.floor((value - min) / 5.0);
            buckets[bucketIndex] = arrAppend(buckets[bucketIndex], value);
        }
        int arrIndex = 0;
        for (int[] bucket :
                buckets) {
            bucket = new SortAlgorithm(bucket).insertSort();
            for (int value :
                    bucket) {
                arr[arrIndex++] = value;
            }
        }
        return arr;
    }

    /**
     * 数组扩容，并保存数据
     * @param arr 带扩容数组
     * @param value 待插入值
     * @return 扩容后数组
     */
    private int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * 基数排序
     * @return 已排序数组
     */
    public int[] radixSort() {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int tmp = getMaxValue(arr);
        int maxRadix = 0;
        while (tmp != 0) {
            tmp /= 10;
            maxRadix++;
        }
        int mod = 10;
        for (int i = 0; i < maxRadix; i++, mod *= 10) {
            int[][] buckets = new int[10][0];
            for (int j = 0; j < arr.length; j++) {
                int bucketIndex = (arr[j] - (arr[j] / mod * mod)) / (mod / 10);
                buckets[bucketIndex] = arrAppend(buckets[bucketIndex], arr[j]);
            }

            int pos = 0;
            for (int[] bucket :
                    buckets) {
                for (int value :
                        bucket) {
                    arr[pos++] = value;
                }
            }
        }
        return arr;
    }
}
