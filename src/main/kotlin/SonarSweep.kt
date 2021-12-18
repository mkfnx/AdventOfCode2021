fun sonarSweep1() {
    val depths = getInputListFromFile("1i.txt").map { it.toInt() }

    var increases = 0
    for (i in 1 until depths.size) {
        if (depths[i] - depths[i-1] > 0) {
            increases += 1
        }
    }

    print(increases)
}

fun sonarSweep2() {
    val depths = getInputListFromFile("1i.txt").map { it.toInt() }

    var increases = 0
    var i = 0
    while (true) {
        if (i + 3 > depths.size - 1) {
            break
        }

        val window1Sum = depths[i] + depths[i+1] + depths[i+2]
        val window2Sum = depths[i+1] + depths[i+2] + depths[i+3]

        if (window2Sum > window1Sum) {
            increases += 1
        }

        i += 1
    }

    print(increases)
}