package com.banyan.algorithm.listnodesort

import com.banyan.algorithm.utils.LogUtil
import java.lang.NullPointerException

object ListNodeSort {

    /**-------------翻转链表-------------------*/
    fun reverse(head: ListNode?): ListNode? {
        var head: ListNode? = head
        //prev表示前继节点
        var prev: ListNode? = null
        while (head != null) {
            //temp记录下一个节点，head是当前节点
            val temp = head.next
            head.next = prev
            prev = head
            head = temp
        }
        return prev
    }

    /**-------------中间元素-------------------*/
    fun findMiddle(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        var slow: ListNode? = head
        var fast: ListNode? = head

        // fast.next = null 表示 fast 是链表的尾节点
        while (fast != null && fast.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        return slow
    }

    /**-------------判断是否为循环链表-------------------*/
    fun hasCycle(head: ListNode?): Boolean? {
        if (head == null || head.next == null) {
            return false
        }

        var slow: ListNode? = head
        var fast: ListNode? = head.next

        while (fast !== slow) {
            if (fast == null || fast.next == null) {
                return false
            }
            fast = fast.next?.next
            slow = slow?.next
        }
        return true
    }

    /**-------------合并两个已排序链表-------------------*/
    private fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1: ListNode? = l1
        var l2: ListNode? = l2
        val dummy = ListNode(0)
        var lastNode: ListNode? = dummy

        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                lastNode!!.next = l1
                l1 = l1.next
            } else {
                lastNode!!.next = l2
                l2 = l2.next
            }
            lastNode = lastNode.next
        }

        if (l1 != null) {
            lastNode!!.next = l1
        } else {
            lastNode!!.next = l2
        }

        return dummy.next
    }

    /**-------------归并排序-------------------*/
    fun sortMergeList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) {
            return head
        }

        val mid: ListNode? = findMiddle(head)

        val right: ListNode? = sortQuickList(mid?.next)
        mid?.next = null
        val left: ListNode? = sortQuickList(head)

        return mergeTwoLists(left, right)
    }

    /**-------------快速排序-------------------*/
    fun sortQuickList(head: ListNode?): ListNode? {
        quickSort(head, null)
        return head
    }

    private fun quickSort(start: ListNode?, end: ListNode?) {
        if (start === end) {
            return
        }

        val pt: ListNode? = partition(start!!, end)
        quickSort(start, pt)
        quickSort(pt?.next, end)
    }

    private fun partition(start: ListNode, end: ListNode?): ListNode {
        val pivotKey = start.value
        var p1: ListNode? = start
        var p2: ListNode? = start.next
        while (p2 !== end) {
            if (p2!!.value < pivotKey) {
                p1 = p1!!.next
                swapValue(p1!!, p2)
            }
            p2 = p2.next
        }

        swapValue(start, p1!!)
        return p1
    }

    private fun swapValue(node1: ListNode, node2: ListNode) {
        val tmp = node1.value
        node1.value = node2.value
        node2.value = tmp
    }

    /**-------------删除倒数第N个节点-------------------*/
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var head = head
        if (n <= 0) {
            return null
        }

        val dummy = ListNode(0)
        dummy.next = head

        var preDelete: ListNode? = dummy
        for (i in 0 until n) {
            if (head == null) {
                return null
            }
            head = head.next
        }
        // 此时head为正数第N个节点
        while (head != null) {
            head = head.next
            preDelete = preDelete?.next
        }
        preDelete?.next = preDelete?.next?.next
        return dummy.next
    }

    /**-------------两个链表是否相交-------------------*/
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        if (headA == null || headB == null) {
            return null
        }

        var currA = headA
        var currB = headB
        var lengthA = 0
        var lengthB = 0

        // 让长的先走到剩余长度和短的一样
        while (currA != null) {
            currA = currA.next
            lengthA++
        }
        while (currB != null) {
            currB = currB.next
            lengthB++
        }

        currA = headA
        currB = headB
        while (lengthA > lengthB) {
            currA = currA?.next
            lengthA--
        }
        while (lengthB > lengthA) {
            currB = currB?.next
            lengthB--
        }

        // 然后同时走到第一个相同的地方
        while (currA !== currB) {
            currA = currA?.next
            currB = currB?.next
        }
        // 返回交叉开始的节点
        return currA
    }
}