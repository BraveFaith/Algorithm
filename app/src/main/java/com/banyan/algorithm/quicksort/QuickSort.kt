package com.banyan.algorithm.quicksort

import com.banyan.algorithm.CalculateInterface
import com.banyan.algorithm.utils.LogUtil
import com.banyan.algorithm.utils.print

/**
 * 快速排序
 * 在待排序的数组选取一个元素作为基准，将待排序的元素进行分区，比基准元素大的元素放在一边，比其小的放另一边，递归调用快速排序对两边的元素排序。选取基准元素并分区的过程采用双指针左右交换。
 */
object QuickSort: CalculateInterface {

    private val TAG = "QuickSort"

    override fun calculate(arr: IntArray) {
        quickSort(arr, 0, arr.size - 1)
    }


    private fun quickSort(arr: IntArray, low: Int, high: Int) {
        LogUtil.i(TAG,"\nquickSort\narr:${arr.print()}\nlow:$low\nhigh:$high")
        if (low >= high)
            return
        val pivot =
            partition(arr, low, high)        //将数组分为两部分
        quickSort(
            arr,
            low,
            pivot - 1
        )                   //递归排序左子数组
        quickSort(
            arr,
            pivot + 1,
            high
        )                  //递归排序右子数组
    }

    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        LogUtil.i(TAG,"\npartition\narr:${arr.print()}\nlow:$low\nhigh:$high")
        var low = low
        var high = high
        val pivot = arr[low]     //基准
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--
            }
            arr[low] = arr[high]             //交换比基准大的记录到左端
            while (low < high && arr[low] <= pivot) {
                low++
            }
            arr[high] = arr[low]           //交换比基准小的记录到右端
        }
        //扫描完成，基准到位
        arr[low] = pivot
        //返回的是基准的位置
        return low
    }
}