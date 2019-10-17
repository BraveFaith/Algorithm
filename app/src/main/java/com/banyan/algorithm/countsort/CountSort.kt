package com.banyan.algorithm.countsort

import com.banyan.algorithm.CalculateInterface

/**
 * 计数排序
 * 根据待排序的数组中最大和最小的元素，统计数组中每个值为i的元素出现的次数，存入数组C的第i项，对所有的计数累加，然后反向填充目标数组。
 */
object CountSort : CalculateInterface {

    override fun calculate(arr: IntArray) {
        countSort(arr)
    }

    private fun countSort(arr: IntArray) {
        var max = Integer.MIN_VALUE
        var min = Integer.MAX_VALUE
        for (i in arr.indices) {
            max = Math.max(max, arr[i])
            min = Math.min(min, arr[i])
        }

        val b = IntArray(arr.size) // 存储数组
        val count = IntArray(max - min + 1) // 计数数组

        for (num in min..max) {
            // 初始化各元素值为0，数组下标从0开始因此减min
            count[num - min] = 0
        }

        for (i in arr.indices) {
            val num = arr[i]
            count[num - min]++ // 每出现一个值，计数数组对应元素的值+1
            // 此时count[i]表示数值等于i的元素的个数
        }

        for (i in min + 1..max) {
            count[i - min] += count[i - min - 1]
            // 此时count[i]表示数值<=i的元素的个数
        }

        for (i in arr.indices) {
            val num = arr[i] // 原数组第i位的值
            val index = count[num - min] - 1 //加总数组中对应元素的下标
            b[index] = num // 将该值存入存储数组对应下标中
            count[num - min]-- // 加总数组中，该值的总和减少1。
        }

        // 将存储数组的值替换给原数组
        for (i in arr.indices) {
            arr[i] = b[i]
        }
    }
}