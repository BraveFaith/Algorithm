package com.banyan.algorithm.treenodesort

import com.banyan.algorithm.utils.LogUtil
import java.sql.Array
import java.util.*
import kotlin.collections.ArrayList

/**
 * 二叉树
 */
object TreeNodeSort {
    private val TAG = "TreeNodeSort"

    /**---------------------顺序遍历------------------------------*/
    // 先序遍历: 根->左->右
    fun preTraverse(root: TreeNode?, stringBuffer: StringBuffer) {
        if (root != null) {
            LogUtil.i(TAG, "preTraverse:${root.value}")
            stringBuffer.append(root.value)
            stringBuffer.append(",")
            preTraverse(root.left, stringBuffer)
            preTraverse(root.right, stringBuffer)
        }

    }

    // 中序遍历: 左->根->右
    fun inTraverse(root: TreeNode?, stringBuffer: StringBuffer) {
        if (root != null) {
            inTraverse(root.left, stringBuffer)
            LogUtil.i(TAG, "preTraverse:${root.value}")
            stringBuffer.append(root.value)
            stringBuffer.append(",")
            inTraverse(root.right, stringBuffer)
        }
    }

    // 后序遍历: 左->右->根
    fun postTraverse(root: TreeNode?, stringBuffer: StringBuffer) {
        if (root != null) {
            postTraverse(root.left, stringBuffer)
            postTraverse(root.right, stringBuffer)
            LogUtil.i(TAG, "preTraverse:${root.value}")
            stringBuffer.append(root.value)
            stringBuffer.append(",")
        }
    }

    /**---------------------DFS层次遍历------------------------------*/
    // 层次遍历(DFS)
    fun levelOrder(root: TreeNode?): MutableList<MutableList<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        if (root == null) {
            return res
        }

        dfs(root, res, 0)
        return res
    }

    private fun dfs(root: TreeNode?, res: MutableList<MutableList<Int>>, level: Int) {
        if (root == null) {
            return
        }
        if (level == res.size) {
            res.add(ArrayList())
        }
        res[level].add(root.value)

        dfs(root.left, res, level + 1)
        dfs(root.right, res, level + 1)
    }


    /**---------------------BFS层次遍历------------------------------*/
    // 层次遍历(BFS)
    fun levelOrder2(root: TreeNode?): MutableList<MutableList<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        if (root == null) {
            return result
        }

        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        while (!queue.isEmpty()) {
            val level = mutableListOf<Int>()
            val size = queue.size
            for (i in 0 until size) {
                val head = queue.poll()
                level.add(head.value)
                if (head.left != null) {
                    queue.offer(head.left)
                }
                if (head.right != null) {
                    queue.offer(head.right)
                }
            }
            result.add(level)
        }

        return result
    }

    /**---------------------"Z"字遍历------------------------------*/
    // "Z"字遍历
    fun zigzagLevelOrder(root: TreeNode?): MutableList<MutableList<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        if (root == null) {
            return result
        }

        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        var isFromLeft = false
        while (!queue.isEmpty()) {
            val size = queue.size
            isFromLeft = !isFromLeft
            val list = mutableListOf<Int>()
            for (i in 0 until size) {
                val node: TreeNode
                if (isFromLeft) {
                    node = queue.pollFirst()
                } else {
                    node = queue.pollLast()
                }
                list.add(node.value)

                if (isFromLeft) {
                    if (node.left != null) {
                        queue.offerLast(node.left)
                    }
                    if (node.right != null) {
                        queue.offerLast(node.right)
                    }
                } else {
                    if (node.right != null) {
                        queue.offerFirst(node.right)
                    }
                    if (node.left != null) {
                        queue.offerFirst(node.left)
                    }
                }
            }
            result.add(list)
        }

        return result
    }

    /**---------------------中序遍历线索化------------------------------*/
    private var preTreeNode: TreeNode? = null
    fun inTraverseThreading(current: TreeNode?) {
        preTreeNode = null
        innerInTraverseThreading(current)
    }

    private fun innerInTraverseThreading(current: TreeNode?) {
        if (current != null) {
            innerInTraverseThreading(current.left)
            LogUtil.i(TAG, "current:${current.value},preTreeNode:${preTreeNode?.value}")
            if (current.left == null) {
                current.leftTag = TreeNodeTag.Thread
                current.left = preTreeNode
            }
            if (preTreeNode?.right == null) {
                preTreeNode?.rightTag = TreeNodeTag.Thread
                preTreeNode?.right = current
            }
            preTreeNode = current
            innerInTraverseThreading(current.right)
        }
    }

    /**---------------------左右翻转------------------------------*/
    fun invert(root: TreeNode?) {
        if (root == null) {
            return
        }
        val temp = root.left
        root.left = root.right
        root.right = temp

        invert(root.left)
        invert(root.right)
    }

    /**---------------------最大值------------------------------*/
    fun getMax(root: TreeNode?): Int {
        if (root == null) {
            return Integer.MIN_VALUE
        } else {
            val left = getMax(root.left)
            val right = getMax(root.right)
            return Math.max(Math.max(left, right), root.value)
        }
    }

    /**---------------------最大深度------------------------------*/
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val left = maxDepth(root.left)
        val right = maxDepth(root.right)
        return Math.max(left, right) + 1
    }

    /**---------------------最小深度------------------------------*/
    fun minDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val left = minDepth(root.left)
        val right = minDepth(root.right)

        return if (left == 0) {
            right + 1
        } else if (right == 0) {
            left + 1
        } else {
            Math.min(left, right) + 1
        }
    }

    /**---------------------平衡二叉树------------------------------*/
    fun isBalanced(root: TreeNode?): Boolean {
        return maxDepth2(root) !== -1
    }

    private fun maxDepth2(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val left = maxDepth(root.left)
        val right = maxDepth(root.right)
        return if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            -1
        } else Math.max(left, right) + 1
    }

    /** 二叉排序树*/
    /**
     * 查找
     * @param target 目标
     * @param current 当前的节点
     */
    fun searchBST(target: TreeNode, current: TreeNode): TreeNode {
        if (target.value == current.value) {
            return current
        }
        return if (target.value > current.value) {
            if (current.right != null) {
                searchBST(target, current.right!!)
            } else {
                current
            }
        } else {
            if (current.left != null) {
                searchBST(target, current.left!!)
            } else {
                current
            }
        }
    }

    /**
     * 插入
     * @param target 目标
     * @param current 当前节点
     */
    fun insertBST(target: TreeNode, current: TreeNode){
        if (target.value > current.value) {
            if (current.right != null) {
                searchBST(target, current.right!!)
            } else {
                current.right = target
            }
        } else {
            if (current.left != null) {
                searchBST(target, current.left!!)
            } else {
                current.left = target
            }
        }
    }

    fun deleteBST(target: TreeNode,current: TreeNode){
        val temp = searchBST(target, current)
        if (temp.value != target.value){
            return
        }
        if (temp.right == null && temp.left == null){

        }
    }
}