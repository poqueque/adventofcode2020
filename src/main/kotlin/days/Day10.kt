package days

import kotlin.math.pow

@Suppress("unused")
class Day10 : Day(10) {

    override fun partOne(): Any {
        var dif1=0
        var dif3=0
        var last=0
        inputList.map{it.toInt()}.sorted().forEach { joltage->
            if (joltage-last ==1) dif1++
            if (joltage-last ==3) dif3++
            last=joltage
        }
        dif3++
        return dif1*dif3
    }

    override fun partTwo(): Any {
        var last = 0
        val diff = inputList.map{it.toInt()}.sorted().map { joltage->
            val dif = joltage-last
            last=joltage
            dif
        }.toMutableList()
        val groups = mutableListOf(0,0,0,0,0,0,0,0,0)
        var count = 0
        diff.forEach {
            if (it == 1) count++
            if (it == 2) println("There is a 2")
            if (it == 3) {
                groups[count] = groups[count]+1
                count = 0
            }
        }
        groups[count] = groups[count]+1
        return (7.0.pow(groups[4])* 4.0.pow(groups[3]) * 2.0.pow(groups[2])).toLong()
    }
}
