import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun hydrothermalVenture1() {
    val input = getInputListFromFile("5i.txt")

    val lines = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

    var maxX = 0
    var maxY = 0

    for (line in input) {
        val ranges = line.split(" -> ")
        val start = ranges[0].split(",")
        val end = ranges[1].split(",")

        val x1 = start[0].toInt()
        val y1 = start[1].toInt()
        val x2 = end[0].toInt()
        val y2 = end[1].toInt()

        lines.add(
            (x1 to y1) to (x2 to y2)
        )

        if (x1 > maxX) maxX = x1
        if (x2 > maxX) maxX = x2
        if (y1 > maxY) maxY = y1
        if (y2 > maxY) maxY = y2
    }

    val maxSize = max(maxX, maxY)
    val field = Array(maxSize + 1) { Array(maxSize + 1) { 0 } }

    for (line in lines) {
        if (line.first.second == line.second.second) {
            val start = min(line.first.first, line.second.first)
            val end = max(line.first.first, line.second.first)
            drawHorizontalLine(field, line.first.second, start, end)
        } else if (line.first.first == line.second.first) {
            val start = min(line.first.second, line.second.second)
            val end = max(line.first.second, line.second.second)
            drawVerticalLine(field, line.first.first, start, end)
        }
    }

    printFieldAndOverlapCount(field)
}

fun hydrothermalVenture2() {
    val input = getInputListFromFile("5i.txt")

    val lines = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

    var maxX = 0
    var maxY = 0

    for (line in input) {
        val ranges = line.split(" -> ")
        val start = ranges[0].split(",")
        val end = ranges[1].split(",")

        val x1 = start[0].toInt()
        val y1 = start[1].toInt()
        val x2 = end[0].toInt()
        val y2 = end[1].toInt()

        lines.add(
            (x1 to y1) to (x2 to y2)
        )

        if (x1 > maxX) maxX = x1
        if (x2 > maxX) maxX = x2
        if (y1 > maxY) maxY = y1
        if (y2 > maxY) maxY = y2
    }

    val maxSize = max(maxX, maxY)
    val field = Array(maxSize + 1) { Array(maxSize + 1) { 0 } }

    for (line in lines) {
        if (line.first.second == line.second.second) {
            val start = min(line.first.first, line.second.first)
            val end = max(line.first.first, line.second.first)
            drawHorizontalLine(field, line.first.second, start, end)
        } else if (line.first.first == line.second.first) {
            val start = min(line.first.second, line.second.second)
            val end = max(line.first.second, line.second.second)
            drawVerticalLine(field, line.first.first, start, end)
        } else if (isDiagonal(line)) {
            val start: Pair<Int, Int>?
            val end: Pair<Int, Int>?
            if (line.first.first - line.second.first < 0) {
                start = line.first
                end = line.second
            } else {
                start = line.second
                end = line.first
            }

            if (end.second - start.second > 0) {
                drawDownRightDiagonal(field, start, end)
            } else {
                drawUpRightDiagonal(field, start, end)
            }
        }
    }

    printFieldAndOverlapCount(field)
}

private fun printFieldAndOverlapCount(field: Array<Array<Int>>) {
    var overlapCount = 0
    for (i in field.indices) {
        for (j in field.first().indices) {
//            print("${field[i][j]} ")

            if (field[i][j] > 1) {
                overlapCount += 1
            }
        }
//        println()
    }
    println("overlapCount: $overlapCount")
}

private fun isDiagonal(line: Pair<Pair<Int, Int>, Pair<Int, Int>>) =
    abs(line.second.second - line.first.second) == abs(line.second.first - line.first.first)

private fun drawHorizontalLine(field: Array<Array<Int>>, axis: Int, start: Int, finish: Int) {
    for (i in start .. finish) {
        field[axis][i] += 1
    }
}

private fun drawVerticalLine(field: Array<Array<Int>>, axis: Int, start: Int, finish: Int) {
    for (i in start .. finish) {
        field[i][axis] += 1
    }
}

private fun drawDownRightDiagonal(field: Array<Array<Int>>, start: Pair<Int, Int>, finish: Pair<Int, Int>) {
    val lineLength = getDiagonalLength(start, finish)
    for (i in 0 .. lineLength) {
        field[start.second + i][start.first + i] += 1
    }
}

private fun drawUpRightDiagonal(field: Array<Array<Int>>, start: Pair<Int, Int>, finish: Pair<Int, Int>) {
    val lineLength = getDiagonalLength(start, finish)
    for (i in 0 .. lineLength) {
        field[start.second - i][start.first + i] += 1
    }
}

private fun getDiagonalLength(start: Pair<Int, Int>, finish: Pair<Int, Int>) =
    finish.first - start.first


