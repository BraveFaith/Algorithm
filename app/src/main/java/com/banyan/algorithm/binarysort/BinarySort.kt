package com.banyan.algorithm.binarysort

object BinarySort {
    /**
     * 用二分法查找目标
     */
    fun searchBinary(nums: IntArray, target: Int): Int {
        val result = intArrayOf(-1,-1)
        result[0]
        var right = nums.size - 1
        var left = 0
        var mid = -1
        while (left <= right) {
            mid = (right + left) / 2
            if (nums[mid] == target)
                return mid
            if (nums[mid] > target)
                right = mid - 1
            if (nums[mid] < target)
                left = mid + 1
        }
        return -1
    }

    /**
     * 搜索左区间
     */
    fun searchLeftBinary(nums: IntArray, target: Int):Int{
        if (nums.isEmpty())
            return -1
        var right = nums.size - 1
        var left = 0
        var mid = -1
        while (left <= right) {
            mid = (right + left) / 2
            if (nums[mid] >= target)
                right = mid - 1
            if (nums[mid] < target)
                left = mid + 1
        }
        if (left >= nums.size || nums[left] != target)
            return -1
        return left
    }

    /**
     * 搜索右区间
     */
    fun searchRightBinary(nums: IntArray, target: Int):Int{
        if (nums.isEmpty())
            return -1
        var right = nums.size - 1
        var left = 0
        var mid = -1
        while (left <= right) {
            mid = (right + left) / 2
            if (nums[mid] > target)
                right = mid - 1
            if (nums[mid] <= target)
                left = mid + 1
        }
        if (right < 0 || nums[right] != target)
            return -1
        return right
    }
}