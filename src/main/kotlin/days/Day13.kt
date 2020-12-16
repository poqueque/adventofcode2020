package days

import util.splitrim
import java.math.BigInteger

@Suppress("unused")
class Day13 : Day(13) {

    override fun partOne(): Any {
        val time = inputList[0].toInt()
        val list = inputList[1].splitrim(",").filter { it != "x" }.map{it.toInt()}
        var line = 0
        var minTime = Int.MAX_VALUE
        list.forEach {
            val waitTime = (time / it + 1) * it - time
            if (waitTime < minTime) {
                minTime = waitTime
                line = it
            }
        }
        return (minTime*line)
    }

    override fun partTwo(): Any {
        val list = inputList[1].splitrim(",").map{ if (it=="x") 0 else it.toInt()}
        var sum = 0L
        var p = 1L
        list.forEach {
            if (it != 0) p *= it
        }
        list.forEach {
            if (it != 0) {
                val c1 = (it - list.indexOf(it)) %it
                val m1 = it
                val bigM1 = p/m1
                val bigM1ModM1 = bigM1%m1
                val y1 = BigInteger.valueOf(bigM1ModM1).modInverse(BigInteger.valueOf(m1.toLong())).toInt()
                sum += c1*bigM1*y1
            }
        }
        return sum%p
    }
}
