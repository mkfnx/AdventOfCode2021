fun dive1() {
    var x = 0
    var y = 0

    getInputListFromFile("2i.txt").forEach { direction ->
        val commands = direction.split(" ")

        when(commands[0]) {
            "forward" -> {
                x += commands[1].toInt()
            }
            "up" -> {
                y -= commands[1].toInt()
            }
            "down" -> {
                y += commands[1].toInt()
            }
        }
    }

    print(x * y)
}

fun dive2() {
    var x = 0
    var y = 0
    var aim = 0

    getInputListFromFile("2i.txt").forEach { direction ->
        val commands = direction.split(" ")
        val amount = commands[1].toInt()

        when(commands[0]) {
            "forward" -> {
                x += amount
                y += aim * amount
            }
            "up" -> {
                aim -= amount
            }
            "down" -> {
                aim += amount
            }
        }
    }

    print(x * y)
}