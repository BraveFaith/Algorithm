package com.banyan.algorithm.treenodesort

enum class TreeNodeTag {
    Child,//子节点
    Thread//线索节点
}

data class TreeNode(
    val value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
    var leftTag: TreeNodeTag = TreeNodeTag.Child,
    var rightTag: TreeNodeTag = TreeNodeTag.Child
)
