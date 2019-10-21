package com.banyan.algorithm.listnodesort

data class ListNode(var value: Int) {
    var next: ListNode?

    init {
        next = null
    }

    fun print(): String {
        val stringBuffer = StringBuffer()
        var node: ListNode? = this
        while (node != null) {
            stringBuffer.append(node.value)
            stringBuffer.append(",")
            node = node.next
        }

        return stringBuffer.toString()
    }
}

