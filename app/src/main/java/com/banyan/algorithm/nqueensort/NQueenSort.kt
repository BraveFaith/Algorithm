package com.banyan.algorithm.nqueensort

import java.util.*

object NQueenSort {
    fun solveNQueens(n: Int): List<List<String>> {
        val solutions: MutableList<List<String>> =
            ArrayList()
        val queens = IntArray(n)
        Arrays.fill(queens, -1)
        val columns: MutableSet<Int> = HashSet()
        val diagonals1: MutableSet<Int> = HashSet()
        val diagonals2: MutableSet<Int> = HashSet()
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2)
        return solutions
    }

    fun backtrack(
        solutions: MutableList<List<String>>,
        queens: IntArray,
        n: Int,
        row: Int,
        columns: MutableSet<Int>,
        diagonals1: MutableSet<Int>,
        diagonals2: MutableSet<Int>
    ) {
        if (row == n) {
            val board = generateBoard(queens, n)
            solutions.add(board)
        } else {
            for (i in 0 until n) {
                if (columns.contains(i)) {
                    continue
                }
                val diagonal1 = row - i
                if (diagonals1.contains(diagonal1)) {
                    continue
                }
                val diagonal2 = row + i
                if (diagonals2.contains(diagonal2)) {
                    continue
                }
                queens[row] = i
                columns.add(i)
                diagonals1.add(diagonal1)
                diagonals2.add(diagonal2)
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2)
                queens[row] = -1
                columns.remove(i)
                diagonals1.remove(diagonal1)
                diagonals2.remove(diagonal2)
            }
        }
    }

    fun generateBoard(queens: IntArray, n: Int): List<String> {
        val board: MutableList<String> =
            ArrayList()
        for (i in 0 until n) {
            val row = CharArray(n)
            Arrays.fill(row, '.')
            row[queens[i]] = 'Q'
            board.add(String(row))
        }
        return board
    }
}