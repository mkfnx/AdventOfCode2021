package giantsquid

class GiantSquidBoardSquare(
    var symbol: String,
    var marked: Boolean = false,
) {

    fun markSymbolIfMatches(symbol: String): Boolean {
        if (this.symbol == symbol) {
            marked = true
            return true
        }
        return false
    }
}
