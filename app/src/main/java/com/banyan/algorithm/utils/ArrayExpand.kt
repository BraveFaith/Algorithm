package com.banyan.algorithm.utils

fun IntArray.print(): String {
    if (size < 1)
        return ""
    val stringBuffer = StringBuffer()
    forEach {
        stringBuffer.append(it)
        stringBuffer.append(",")
    }

    return stringBuffer.substring(0, stringBuffer.length - 1)
}