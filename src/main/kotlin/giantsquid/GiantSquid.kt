package giantsquid

import getInputListFromFile

fun giantSquid1() {
    // read input
    val input = getInputListFromFile("4t.txt")

    // save called numbers
    val numbers = input.first().split(",")

    // create boards
    val boards = getBoardsByRows(input)

    // mark called number in boards and check win condition
    for (n in numbers) {
        for (bi in boards.indices) {
            if (boards[bi].markAndCheckWin(n)) {
                println("bi: $bi")
                calculateAnswer(boards[bi], n)
                return
            }
        }
    }
    print("no answer")
}

fun giantSquid2() {
    // read input
    val input = getInputListFromFile("4i.txt")

    // save called numbers
    val numbers = input.first().split(",")

    // create boards
    val boards = getBoardsByRows(input)

    // mark called number in boards and check win condition
    var winningBoardsCount = 0
    for (n in numbers) {
        for (bi in boards.indices) {
            val currentBoard = boards[bi]

            if (currentBoard.hasWon().not() && currentBoard.markAndCheckWin(n)) {
                winningBoardsCount += 1

                if (winningBoardsCount == boards.size) {
                    calculateAnswer(boards[bi], n)
                }
            }
        }
    }
}

private fun getBoardsByRows(input: List<String>): List<GiantSquidBoard> {
    var index = 2
    val boards = mutableListOf<GiantSquidBoard>()

    while (index + 4 <= input.size) {
        boards.add(
            GiantSquidBoard(
                listOf(
                    getRowItems(input, index),
                    getRowItems(input, index + 1),
                    getRowItems(input, index + 2),
                    getRowItems(input, index + 3),
                    getRowItems(input, index + 4),
                )
            )
        )
        index += 6
    }

    return boards
}

private fun getRowItems(input: List<String>, i: Int) =
    input[i].split(" ").filter { it.isNotEmpty() }

private fun calculateAnswer(board: GiantSquidBoard, number: String) {
    val unmarkedSum = board.getUnmarkedSum()
    println("sum: $unmarkedSum")
    println("n: ${number.toInt()}")

    val answer = unmarkedSum * number.toInt()
    println("answer: $answer")
}

