package giantsquid

class GiantSquidBoard(
    squareSymbols: List<List<String>>
) {
    private var board: List<List<GiantSquidBoardSquare>> = emptyList()
    private var hasWon = false

    init {
        board = squareSymbols.map { row ->
            row.map {
                GiantSquidBoardSquare(it.removeSurrounding(" "))
            }
        }
    }

    fun markAndCheckWin(symbol: String): Boolean {
        markSquare(symbol)

        hasWon = checkWin()
        return hasWon
    }

    fun getUnmarkedSum() = board.sumOf { row ->
        row.filter { it.marked.not() }.sumOf { it.symbol.toInt() }
    }

    fun hasWon() = hasWon

    private fun markSquare(symbol: String) {
        for (row in board) {
            for (square in row) {
                var matches = false
                if (square.markSymbolIfMatches(symbol)) {
                    matches = true
                }
            }
        }
    }

    private fun checkWin(): Boolean {
        return checkRowWin() || checkColumnWin()
    }

    private fun checkRowWin(): Boolean {
        for (row in board) {
            var win = true
            for (square in row) {
                if (square.marked.not()) {
                    win = false
                    break
                }
            }
            if (win) {
                return true
            }
        }
        return false
    }

    private fun checkColumnWin(): Boolean {
        for (i in board.indices) {
            var win = true
            for (j in board[i].indices) {
                if (board[j][i].marked.not()) {
                    win = false
                    break
                }
            }
            if (win) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        var str = ""
        for (row in board) {
            for (square in row) {
                str += if (square.marked) {
                    "** "
                } else if (square.symbol.toInt() < 10) {
                    " ${square.symbol} "
                } else {
                    "${square.symbol} "
                }
            }
            str += "\n"
        }
        return str
    }
}
