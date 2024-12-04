package com.wxm.DataStructuresAndAlgorithms.Algorithms;

/**
 * 归并排序（Merge Sort）是一种有效的、基于比较的排序算法，它采用分治法（Divide and Conquer）的策略。归并排序将数组分成两半，递归地排序这两半，然后再将结果合并成一个有序的数组。由于归并排序是稳定的（相等的元素保持原有的顺序），并且在所有情况下都能保证O(n log n)的时间复杂度，因此在处理大数据量时非常有效。
 *
 * 下面是归并排序的基本步骤：
 *
 * 分解：如果数组的长度大于1，则将数组分为两个相等（或几乎相等）长度的子数组。
 * 递归排序：递归地对两个子数组进行排序。
 * 合并：将两个排序好的子数组合并成一个有序的数组。
 * 以下是一个使用Java实现的归并排序示例：
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 17:13:42
 */

/**
 * 归并排序是建立在归并操作上的一种有效，稳定的排序算法，该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 *
 *
 */
public class MergeSort {
    /**
     * 归并排序的主方法，用于启动排序过程。
     * @param array 待排序的数组。
     */
    public static void mergeSort(int[] array) {
        if (array.length > 1) {
            // 调用递归排序方法
            mergeSortRecursive(array, 0, array.length - 1);
        }
    }

    /**
     * 递归排序方法，用于分割和排序数组。
     * @param array 待排序的数组。
     * @param left 左边界索引。
     * @param right 右边界索引。
     */
    private static void mergeSortRecursive(int[] array, int left, int right) {
        if (left < right) {
            // 计算中间索引
            int middle = (left + right) / 2;
            // 递归排序左半部分
            mergeSortRecursive(array, left, middle);
            // 递归排序右半部分
            mergeSortRecursive(array, middle + 1, right);
            // 合并两个已排序的半部分
            merge(array, left, middle, right);
        }
    }

    /**
     * 合并方法，用于将两个已排序的子数组合并成一个有序数组。
     * @param array 待排序的数组。
     * @param left 左子数组的起始索引。
     * @param middle 中间索引，左子数组的结束索引。
     * @param right 右子数组的结束索引。
     */
    private static void merge(int[] array, int left, int middle, int right) {
        // 左子数组的长度
        int n1 = middle - left + 1;
        // 右子数组的长度
        int n2 = right - middle;

        // 创建临时数组
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 复制数据到临时数组L和R
        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[middle + 1 + j];

        // 合并临时数组L和R到原数组
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        // 复制L[]中的剩余元素
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        // 复制R[]中的剩余元素
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    // 测试函数
    public static void main(String[] args) {
        // 初始化待排序数组
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("原始数组:");
        printArray(arr);
        // 调用归并排序方法
        mergeSort(arr);
        System.out.println("排序后的数组:");
        // 打印排序后的数组
        printArray(arr);
    }

    /**
     * 打印数组的辅助方法。
     * @param arr 要打印的数组。
     */
    private static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}
