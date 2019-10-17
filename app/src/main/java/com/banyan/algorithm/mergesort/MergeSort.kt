package com.banyan.algorithm.mergesort

import com.banyan.algorithm.CalculateInterface
import com.banyan.algorithm.utils.LogUtil
import com.banyan.algorithm.utils.print

/**
 * 归并排序
 * 分解待排序的数组成两个各具 n/2 个元素的子数组，递归调用归并排序两个子数组，合并两个已排序的子数组成一个已排序的数组。
 */
object MergeSort : CalculateInterface {

    private val TAG = "MergeSort"

    override fun calculate(arr: IntArray) {
        val temp = IntArray(arr.size)
        internalMergeSort(arr, temp, 0, arr.size - 1)
    }

    private fun internalMergeSort(arr: IntArray, temp: IntArray, left: Int, right: Int) {
        // 当left == right时，不需要再划分
        LogUtil.i(TAG, "\ninternalMergeSort\narr:${arr.print()}\ntemp:${temp.print()}\nleft:$left\nright:$right")
        if (left < right) {
            val mid = (left + right) / 2
            internalMergeSort(arr, temp, left, mid)
            internalMergeSort(arr, temp, mid + 1, right)
            mergeSortedArray(arr, temp, left, mid, right)
        }
    }

    // 合并两个有序子序列
    private fun mergeSortedArray(arr: IntArray, temp: IntArray, left: Int, mid: Int, right: Int) {
        LogUtil.i(TAG, "\nmergeSortedArray\narr:${arr.print()}\ntemp:${temp.print()}\nleft:$left\nright:$right\nmid:$mid")
        var i = left
        var j = mid + 1
        var k = 0
        while (i <= mid && j <= right) {
            temp[k++] = if (arr[i] < arr[j]) arr[i++] else arr[j++]
        }
        while (i <= mid) {
            temp[k++] = arr[i++]
        }
        while (j <= right) {
            temp[k++] = arr[j++]
        }
        // 把temp数据复制回原数组
        i = 0
        while (i < k) {
            arr[left + i] = temp[i]
            i++
        }
    }
}