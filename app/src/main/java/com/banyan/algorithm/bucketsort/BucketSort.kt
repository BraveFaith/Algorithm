package com.banyan.algorithm.bucketsort

import com.banyan.algorithm.CalculateInterface
import java.util.*

/**
 * 桶排序
 * 找出待排序数组中的最大值max、最小值min，数组ArrayList作为桶，桶里放的元素用ArrayList存储。计算每个元素 arr[i] 放的桶，每个桶各自排序，遍历桶数组，把排序好的元素放进输出数组。
 */
object BucketSort : CalculateInterface {
    override fun calculate(arr: IntArray) {
        bucketSort(arr)
    }

    fun bucketSort(arr: IntArray) {
        var max = Integer.MIN_VALUE
        var min = Integer.MAX_VALUE
        for (i in arr.indices) {
            max = Math.max(max, arr[i])
            min = Math.min(min, arr[i])
        }
        // 桶数
        val bucketNum = (max - min) / arr.size + 1
        val bucketArr = ArrayList<ArrayList<Int>>(bucketNum)
        for (i in 0 until bucketNum) {
            bucketArr.add(ArrayList())
        }
        // 将每个元素放入桶
        for (i in arr.indices) {
            val num = (arr[i] - min) / arr.size
            bucketArr.get(num).add(arr[i])
        }
        // 对每个桶进行排序
        for (i in 0 until bucketArr.size) {
            Collections.sort(bucketArr.get(i))
            for (j in 0 until bucketArr.get(i).size) {
                arr[j] = bucketArr.get(i).get(j)
            }
        }
    }
}