package com.wxm.DataStructuresAndAlgorithms.Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速排序的时间复杂度分析取决于多种因素，主要包括枢轴的选择、数组的初始状态以及递归的平衡程度。快速排序的平均时间复杂度和最好情况下的时间复杂度都是O(n log n)，但其最坏情况下的时间复杂度为O(n^2)。
 *
 * 最好情况和平均情况时间复杂度：O(n log n)
 * 在最好情况和平均情况下，每次分区都能将数组大致平均分成两部分。这意味着每次递归调用处理的数组大小大约是原来的一半。这形成了一个深度为log₂n的递归树，每一层的比较和交换操作数量大致为n。因此，总的比较和交换次数为n * log₂n，即时间复杂度为O(n log n)。
 *
 * 最坏情况时间复杂度：O(n^2)
 * 最坏情况发生在每次分区都极不平衡，比如总是选择最大或最小的元素作为枢轴，导致每次递归调用只能减少一个元素。在这种情况下，递归树的深度为n，每一层的比较和交换操作数量为n，导致总的操作次数为n * n，即时间复杂度为O(n^2)。
 *
 * 实践中的优化
 * 为了减少出现最坏情况的概率，实际应用中通常会采取以下几种策略：
 *
 * 随机选择枢轴：随机选择数组中的一个元素作为枢轴，可以大大降低最坏情况发生的概率。
 * 三数取中法：选择数组的第一个元素、中间元素和最后一个元素的中位数作为枢轴，以提高分区的平衡性。
 * 尾递归优化：优化递归调用，只递归调用较小的子数组，较大的子数组用循环处理，以减少递归栈的深度。
 * 这些优化措施有助于保证快速排序在实际应用中的平均性能接近O(n log n)，并减少最坏情况的发生。因此，快速排序在实际中是一种非常高效和常用的排序算法。
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 17:05:22
 */
public class QuickSort {
    /**
     * 快速排序的主方法。
     * @param array 待排序的数组。
     * @param low 数组的起始索引。
     * @param high 数组的结束索引。
     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // 找到分区点
            int pi = partition(array, low, high);
            // 递归地对分区点左边的子数组进行快速排序
            quickSort(array, low, pi - 1);
            // 递归地对分区点右边的子数组进行快速排序
            quickSort(array, pi + 1, high);
        }
    }

    /**
     * 分区函数，确定分区点并调整数组。
     *
     * 这段代码是快速排序算法中的分区（partition）函数的实现，其目的是将数组的一部分重排，使得所有小于基准（pivot）的元素出现在基准的左边，所有大于基准的元素出现在基准的右边。具体步骤和解释如下：
     *
     * 选取基准：
     * Java
     * 浅色版本
     * int pivot = array[high];
     * 选择数组的最后一个元素作为基准元素。这是快速排序中常见的选择基准的方式之一。
     * 初始化小于基准元素的索引：
     * Java
     * 浅色版本
     * int i = low - 1;
     * i的初值设置为low - 1，意味着目前还没有找到小于等于基准的元素。i的索引将用来记录小于等于基准的元素的最新位置。
     * 遍历数组：
     * Java
     * 浅色版本
     * for (int j = low; j < high; j++) {
     *     ...
     * }
     * 从low到high - 1遍历数组，high位置的元素是基准元素，所以不参与比较。
     * 比较并交换：
     * Java
     * 浅色版本
     * if (array[j] <= pivot) {
     *     i++;
     *     int temp = array[i];
     *     array[i] = array[j];
     *     array[j] = temp;
     * }
     * 对于每个遍历到的元素array[j]，如果它小于等于基准pivot，则将i递增，并将array[j]与array[i]交换。这样做确保了小于等于基准的元素都聚集在数组的左半部分。
     * 定位基准元素：
     * Java
     * 浅色版本
     * int temp = array[i + 1];
     * array[i + 1] = array[high];
     * array[high] = temp;
     * 在遍历结束后，将基准元素array[high]与array[i + 1]交换，此时i + 1是基准元素的正确位置，即所有小于等于基准的元素都在它的左边，所有大于基准的元素都在它的右边。
     * 返回分区点索引：
     * Java
     * 浅色版本
     * return i + 1;
     * 返回基准元素的新位置i + 1，这将是下一次递归排序的分区点。
     * 综上所述，这段代码通过遍历和适当的元素交换，有效地将数组分成了两部分，一部分包含所有小于等于基准的元素，另一部分包含所有大于基准的元素，从而实现了快速排序算法中的分区操作。
     *
     *
     * @param array 待排序的数组。
     * @param low 分区的起始索引。
     * @param high 分区的结束索引。
     * @return 分区点的索引。
     */
    private static int partition(int[] array, int low, int high) {
        // 选取最后一个元素作为基准
        int pivot = array[high];
        // i是小于基准的元素的索引，初始化为low-1
        int i = low - 1;
        // 遍历数组，除了基准元素
        for (int j = low; j < high; j++) {
            // 如果当前元素小于等于基准
            if (array[j] <= pivot) {
                // 增加i的值，准备交换
                i++;
                // 交换array[i]和array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // 交换array[i+1]和array[high] (即基准元素)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        // 返回分区点的索引
        return i + 1;
    }

    // 测试函数，用于验证排序是否正确
    public static void main(String[] args) {
        // 初始化待排序数组
        int[] arr = {10, 7, 8, 9, 1, 5};
        // 调用快速排序方法
        quickSort(arr, 0, arr.length - 1);
        // 输出排序后的数组
        System.out.println("排序后的数组:");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");




        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(1);
        list.add(5);

        List<Integer> sortedArr = quickSort(list);
        System.out.println("Sorted array: " + sortedArr);
    }


    /**
     * 在Java中，如果想要使用额外的空间来简化快速排序的实现，使其更符合算法的直观描述，可以使用额外的数组来存储小于、等于和大于枢轴的元素。这种方法牺牲了空间复杂度，但使得代码更加清晰易懂。
     *
     *  这是一个python 实现：
     *  def quick_sort(arr):
     *     if len(arr) <= 1:
     *         return arr
     *     else:
     *         pivot = arr.pop()  # 选取最后一个元素作为枢轴
     *         less_than_pivot = []
     *         greater_than_pivot = []
     *
     *         for item in arr:
     *             if item <= pivot:
     *                 less_than_pivot.append(item)
     *             else:
     *                 greater_than_pivot.append(item)
     *
     *         return quick_sort(less_than_pivot) + [pivot] + quick_sort(greater_than_pivot)
     *
     * # 示例用法
     * arr = [10, 7, 8, 9, 1, 5]
     * sorted_arr = quick_sort(arr)
     * print("Sorted array:", sorted_arr)
     *
     *
     *
     * @param arr
     * @return
     */
    public static List<Integer> quickSort(List<Integer> arr) {
        if (arr.size() <= 1) {
            return arr;
        } else {
            int pivot = arr.get(0);
            List<Integer> less = new ArrayList<>();
            List<Integer> equal = new ArrayList<>();
            List<Integer> greater = new ArrayList<>();

            for (int num : arr) {
                if (num < pivot) {
                    less.add(num);
                } else if (num == pivot) {
                    equal.add(num);
                } else {
                    greater.add(num);
                }
            }

            List<Integer> sorted = new ArrayList<>();
            sorted.addAll(quickSort(less));
            sorted.addAll(equal);
            sorted.addAll(quickSort(greater));
            return sorted;
        }
    }
}
