package com.banyan.algorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.banyan.algorithm.bubblesort.BubbleSort
import com.banyan.algorithm.bucketsort.BucketSort
import com.banyan.algorithm.countsort.CountSort
import com.banyan.algorithm.mergesort.MergeSort
import com.banyan.algorithm.quicksort.QuickSort
import com.banyan.algorithm.treenodesort.TreeNode
import com.banyan.algorithm.treenodesort.TreeNodeSort
import com.banyan.algorithm.utils.LogUtil
import com.banyan.algorithm.utils.print
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random
import kotlin.random.nextUInt

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private var mArrayData1: MutableList<Int> = mutableListOf()

    private var mTreeNode: TreeNode? = null

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

        mTreeNode = TreeNode(15)
        for (i in 0..10) {
            createTreeNode(mTreeNode)
        }
    }


    private fun createTreeNode(root: TreeNode?) {
        val random = Random.nextInt(30)
        LogUtil.i(TAG, "random:$random")

        val treeNode = TreeNode(random)
        if ((random and 1) == 0) {
            root?.right = treeNode
        } else {
            root?.left = treeNode
        }
        if (random == 15)
            return
        createTreeNode(treeNode)
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

        btn_6.setOnClickListener {
            val stringBuffer = StringBuffer()
            TreeNodeSort.preTraverse(mTreeNode, stringBuffer)
            tv_3.text = "先序遍历: 根->左->右 : $stringBuffer"
        }

        btn_7.setOnClickListener {
            val stringBuffer = StringBuffer()
            TreeNodeSort.inTraverse(mTreeNode, stringBuffer)
            tv_3.text = "中序遍历: 左->根->右 : $stringBuffer"
        }

        btn_8.setOnClickListener {
            val stringBuffer = StringBuffer()
            TreeNodeSort.postTraverse(mTreeNode, stringBuffer)
            tv_3.text = "后序遍历: 左->右->根 : $stringBuffer"
        }

        btn_9.setOnClickListener {
            val result = TreeNodeSort.levelOrder(mTreeNode)
            val stringBuffer = StringBuffer()
            result.forEach {
                it.forEach {
                    stringBuffer.append(it)
                    stringBuffer.append(",")
                }
            }
            tv_3.text = "层次遍历(DFS):$stringBuffer"

        }

        btn_10.setOnClickListener {
            val result = TreeNodeSort.levelOrder2(mTreeNode)
            val stringBuffer = StringBuffer()
            result.forEach {
                it.forEach {
                    stringBuffer.append(it)
                    stringBuffer.append(",")
                }
            }
            tv_3.text = "层次遍历(BFS):$stringBuffer"

        }

        btn_11.setOnClickListener {
            val result = TreeNodeSort.zigzagLevelOrder(mTreeNode)
            val stringBuffer = StringBuffer()
            result.forEach {
                it.forEach {
                    stringBuffer.append(it)
                    stringBuffer.append(",")
                }
            }
            tv_3.text = "\"Z\"字遍历:$stringBuffer"

        }

        btn_12.setOnClickListener {
            val result = TreeNodeSort.levelOrder(mTreeNode)
            val stringBuffer = StringBuffer()
            result.forEach {
                it.forEach {
                    stringBuffer.append(it)
                    stringBuffer.append(",")
                }
            }

            TreeNodeSort.invert(mTreeNode)

            val result2 = TreeNodeSort.levelOrder(mTreeNode)
            val stringBuffer2 = StringBuffer()
            result2.forEach {
                it.forEach {
                    stringBuffer2.append(it)
                    stringBuffer2.append(",")
                }
            }
            tv_3.text = "翻转前:$stringBuffer\n翻转后:$stringBuffer2"

        }

        btn_13.setOnClickListener {
            val max = TreeNodeSort.getMax(mTreeNode)

            tv_3.text = "最大值:$max"

        }

        btn_14.setOnClickListener {
            val maxDeep = TreeNodeSort.maxDepth(mTreeNode)

            tv_3.text = "最大深度:$maxDeep"

        }

        btn_15.setOnClickListener {
            val minDeep = TreeNodeSort.minDepth(mTreeNode)

            tv_3.text = "最小深度:$minDeep"

        }

        btn_16.setOnClickListener {
            val balanced = TreeNodeSort.isBalanced(mTreeNode)

            tv_3.text = "平衡二叉树:$balanced"

        }
    }
}
