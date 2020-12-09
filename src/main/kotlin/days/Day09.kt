package days

import util.GameConsole
import util.PlaneScreen

class Day09 : Day(9) {

    override fun partOne(): Any {
        val console = PlaneScreen(inputList,25)
        return console.run()
    }

    override fun partTwo(): Any {
        val numberToCheck = 14360655
//        val numberToCheck = 127
        val console = PlaneScreen(inputList,25)
        return console.findWeakness(numberToCheck)
    }
}
