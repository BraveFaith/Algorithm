package com.banyan.algorithm.hanoi

import android.util.Log
import java.util.*

/**
 *递归实现汉诺塔的函数
 */
object HanoiSort {
    fun calculate(A:Stack<String>,B:Stack<String>,C:Stack<String>){
        move(A.size,A,B,C)
    }

    fun move(count:Int,a:Stack<String>,b:Stack<String>,c:Stack<String>){
        if (count == 1) {
            Log.d("banyan","1 count = $count,a = ${a},b = ${b},c = ${c}")
            c.push(a.pop())
        } else {
            move(count - 1, a, c, b)
            Log.d("banyan","2 count = $count,a = ${a},b = ${b},c = ${c}")
            c.push(a.pop())
            move(count - 1, b, a, c)
        }
    }
}