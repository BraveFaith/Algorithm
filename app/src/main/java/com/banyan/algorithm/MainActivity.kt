package com.banyan.algorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.banyan.algorithm.bubblesort.BubbleSort
import com.banyan.algorithm.bucketsort.BucketSort
import com.banyan.algorithm.countsort.CountSort
import com.banyan.algorithm.mergesort.MergeSort
import com.banyan.algorithm.quicksort.QuickSort
import com.banyan.algorithm.utils.print
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mArrayData1: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        setListener()

    }

    private fun initData() {
        val data = tv_1.text.toString().split(":")[1]
        data.split(",").forEach {
            mArrayData1.add(it.toInt())
        }
    }

    private fun setListener() {
        btn_1.setOnClickListener {
            var sortData = mArrayData1.toIntArray()
            BubbleSort.calculate(sortData)
            tv_2.text = "冒泡排序结果:${sortData.print()}"
        }

        btn_2.setOnClickListener {
            var sortData = mArrayData1.toIntArray()
            MergeSort.calculate(sortData)
            tv_2.text = "归并排序结果:${sortData.print()}"
        }

        btn_3.setOnClickListener {
            var sortData = mArrayData1.toIntArray()
            QuickSort.calculate(sortData)
            tv_2.text = "快速排序结果:${sortData.print()}"
        }

        btn_4.setOnClickListener {
            var sortData = mArrayData1.toIntArray()
            CountSort.calculate(sortData)
            tv_2.text = "计数排序结果:${sortData.print()}"
        }

        btn_5.setOnClickListener {
            var sortData = mArrayData1.toIntArray()
            BucketSort.calculate(sortData)
            tv_2.text = "桶排序结果:${sortData.print()}"
        }
    }
}
