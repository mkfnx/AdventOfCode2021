import giantsquid.giantSquid1
import giantsquid.giantSquid2

fun main(args: Array<String>) {
//    sonarSweep1()
//    sonarSweep2()

//    dive1()
//    dive2()

//    binaryDiagnostic1()
//    binaryDiagnostic2()

//    giantSquid1()
//    giantSquid2()

//    hydrothermalVenture1()
    hydrothermalVenture2()
}

fun getInputListFromFile(fileName: String) : List<String> {
    return object {}.javaClass.getResource(fileName)
        .readText()
        .split("\n")
}