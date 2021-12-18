import kotlin.math.pow

fun binaryDiagnostic1() {
    val binStrs = getInputListFromFile("3i.txt")
    val binNums = binStrs.map { it.toInt(2) }.toMutableList()
    val numSample = binStrs[0]

    var gamma = ""
    var epsilon = ""

    for (i in numSample.indices) {
        val power2 = 2.0.pow((numSample.length - i - 1).toDouble()).toInt()

        var gammaCount = 0
        var epsilonCount = 0
        for (bi in binNums.indices) {
            val bin = binNums[bi]
            if (bin >= power2) {
                gammaCount += 1
                binNums[bi] -= power2
            } else {
                epsilonCount += 1
            }
        }

        if (gammaCount > epsilonCount) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
    }

    println("gamma: $gamma")
    println("epsilon: $epsilon")

    println("gamma: ${gamma.toInt(2)}")
    println("epsilon: ${epsilon.toInt(2)}")

    println(gamma.toInt(2) * epsilon.toInt(2))
}

fun removeItems(binList: MutableList<String>, c: Char) {
    binList.removeIf { s: String -> s.startsWith(c) }
}

fun binaryDiagnostic2() {
    var genRating = 0
    var scrubRating = 0

    val binStrsRef = getInputListFromFile("3i.txt")
    val binStrs = binStrsRef.toMutableList()
    val numSample = binStrsRef[0]

    for (i in numSample.indices) {
        var onesCount = 0
        var zeroesCount = 0

        for (bin in binStrs) {
            if (bin[i] == '1') {
                onesCount += 1
            } else {
                zeroesCount += 1
            }
        }

        binStrs.removeIf { bS: String -> bS[i] == (if (onesCount >= zeroesCount) '0' else '1') }

        if (binStrs.size == 1) {
            genRating = binStrs.first().toInt(2)
            break
        }
    }

    binStrs.clear()
    binStrs.addAll(binStrsRef)
    for (i in numSample.indices) {
        var onesCount = 0
        var zeroesCount = 0

        for (bin in binStrs) {
            if (bin[i] == '1') {
                onesCount += 1
            } else {
                zeroesCount += 1
            }
        }

        binStrs.removeIf { bS: String -> bS[i] == (if (zeroesCount <= onesCount) '1' else '0') }

        if (binStrs.size == 1) {
            scrubRating = binStrs.first().toInt(2)
            break
        }
    }

    println("gen rating: $genRating")
    println("scrub rating: $scrubRating")

    println(genRating * scrubRating)
}
