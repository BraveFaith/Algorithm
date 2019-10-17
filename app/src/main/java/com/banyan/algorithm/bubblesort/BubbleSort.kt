package com.banyan.algorithm.bubblesort

import com.banyan.algorithm.CalculateInterface

/**
 * 冒泡排序
 * 重复地走访过要排序的数列，每次比较相邻两个元素，如果它们的顺序错误就把它们交换过来，越大的元素会经由交换慢慢“浮”到数列的尾端。
 */
object BubbleSort : CalculateInterface {

    override fun calculate(arr: IntArray) {
        var temp = 0
        var swap: Boolean
        for (i in arr.size - 1 downTo 1) { // 每次需要排序的长度
            // 增加一个swap的标志，当前一轮没有进行交换时，说明数组已经有序
            swap = false
            for (j in 0 until i) { // 从第一个元素到第i个元素
                if (arr[j] > arr[j + 1]) {//通过修改这里的判断可以改变升降序
                    temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                    swap = true
                }
            }
            if (!swap) {
                break
            }
        }
    }
}