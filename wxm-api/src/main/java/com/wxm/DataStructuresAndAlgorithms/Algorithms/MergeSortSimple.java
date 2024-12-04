package com.wxm.DataStructuresAndAlgorithms.Algorithms;

/**
 * 归并排序（Merge Sort）是一种基于分治策略的高效排序算法。它将数组分成两半，递归地排序每一半，然后将两个有序的子数组合并成一个有序的整体。归并排序在最坏情况、平均情况和最好情况下的时间复杂度都是O(n log n)，并且它是一种稳定的排序算法，即相等的元素在排序前后保持原有的相对顺序不变。
 *
 * 归并排序的基本步骤：
 * 分解：如果数组的长度大于1，则将数组分成两个相等（或几乎相等）的子数组。
 * 递归排序：递归地对两个子数组进行归并排序。
 * 合并：将两个排序后的子数组合并成一个有序的数组。
 * 合并两个有序数组的过程：
 * 合并两个有序数组的关键是维护两个指针，分别指向两个数组的起始位置，比较两个指针所指向的元素，将较小的元素放入结果数组中，并将对应的指针向后移动一位。重复此过程，直到一个数组中的所有元素都被放入结果数组，然后将另一个数组中剩余的元素依次加入结果数组的末尾。
 *
 * 归并排序的时间复杂度：
 * 时间复杂度：归并排序的时间复杂度始终为O(n log n)，这是因为它将数组分成两半，然后在每一层递归中花费线性时间来合并两个有序数组。
 * 空间复杂度：归并排序的空间复杂度为O(n)，因为它需要额外的存储空间来存放合并后的数组。
 *
 *
 * Python  实现：
 * def merge_sort(arr):
 *     if len(arr) > 1:
 *         mid = len(arr) // 2
 *         left_half = arr[:mid]
 *         right_half = arr[mid:]
 *
 *         merge_sort(left_half)
 *         merge_sort(right_half)
 *
 *         i = j = k = 0
 *
 *         while i < len(left_half) and j < len(right_half):
 *             if left_half[i] < right_half[j]:
 *                 arr[k] = left_half[i]
 *                 i += 1
 *             else:
 *                 arr[k] = right_half[j]
 *                 j += 1
 *             k += 1
 *
 *         while i < len(left_half):
 *             arr[k] = left_half[i]
 *             i += 1
 *             k += 1
 *
 *         while j < len(right_half):
 *             arr[k] = right_half[j]
 *             j += 1
 *             k += 1
 *
 * # 测试代码
 * arr = [38, 27, 43, 3, 9, 82, 10]
 * merge_sort(arr)
 * print("Sorted array is:", arr)
 *
 *
 * Python 实例2 ，更好
 * def merge_sort(arr):
 *     if len(arr) <= 1:
 *         return arr
 *
 *     mid = len(arr) // 2
 *     left = arr[:mid]
 *     right = arr[mid:]
 *
 *     left = merge_sort(left)
 *     right = merge_sort(right)
 *
 *     return merge(left, right)
 *
 * def merge(left, right):
 *     result = []
 *     i = j = 0
 *
 *     while i < len(left) and j < len(right):
 *         if left[i] < right[j]:
 *             result.append(left[i])
 *             i += 1
 *         else:
 *             result.append(right[j])
 *             j += 1
 *
 *     result.extend(left[i:])
 *     result.extend(right[j:])
 *
 *     return result
 *
 * arr = [5, 3, 1, 4, 2]
 * print(merge_sort(arr))
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 17:18:00
 */
public class MergeSortSimple {
    public static void main(String[] args) {
        // 初始化一个整数数组
        int[] arr = {5, 3, 1, 4, 2};
        // 调用归并排序方法进行排序
        mergeSort(arr, 0, arr.length - 1);
        // 遍历打印出排序后的数组
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        // 如果左边界小于右边界，则继续进行递归排序
        if (left < right) {
            // 找到中间索引
            int mid = (left + right) / 2;
            // 递归的排序左半边
            mergeSort(arr, left, mid);
            // 递归的排序右半边
            mergeSort(arr, mid + 1, right);
            // 合并左右两个有序数组
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // 创建一个临时数组，用于存放合并后的结果
        int[] temp = new int[right - left + 1];
        // 初始化两个指针，分别指向左半边和右半边的头部
        int i = left, j = mid + 1, k = 0;

        // 当左半边和右半边都有元素时，进行合并操作
        while (i <= mid && j <= right) {
            // 如果左半边的当前元素小于右半边的当前元素
            if (arr[i] <= arr[j]) {
                // 把左半边的元素放入临时数组
                temp[k++] = arr[i++];  // temp[k++] 等同于：temp[k];  k++
            } else {
                // 否则把右半边的元素放入临时数组
                temp[k++] = arr[j++];
            }
        }

        // 如果左半边还有剩余元素，全部放入临时数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 如果右半边还有剩余元素，全部放入临时数组
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 把临时数组的元素全部拷贝到原数组中
        for (int l = 0; l < temp.length; l++) {
            arr[left + l] = temp[l];
        }
    }
}
