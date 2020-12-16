package days

import util.PlaneScreen

@Suppress("unused")
class Day09 : Day(9) {

    override fun partOne(): Any {
        val console = PlaneScreen(inputList,25)
        return console.run()
    }

    override fun partTwo(): Any {
        val numberToCheck = 14360655
        val console = PlaneScreen(inputList,25)
        return console.findWeakness(numberToCheck)
    }
}
