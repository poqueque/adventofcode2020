package days

import util.splitrim

@Suppress("unused")
class Day15 : Day(15) {

    override fun partOne(): Any {
        val list = inputString.splitrim(",").map { it.toInt() }.toMutableList()
        for (i in list.size..2019) {
            val last = list[i - 1]
            val pos = list.dropLast(1).lastIndexOf(last)
            if (pos < 0) list.add(0)
            else list.add(i - 1 - pos)
        }
        println(list)
        return list[2019]
    }

    override fun partTwo(): Any {
        val list = inputString.splitrim(",").map { it.toInt() }.toMutableList()
        val map = mutableMapOf<Int,Int>()
        for (i in 0..list.size-2)
            map[list[i]] = i
        var last = list.last()
        for (i in list.size..29999999) {
            val next = if (map[last] == null) 0 else (i - 1 - map[last]!!)
            map[last] = i-1
            last = next
            if(i%1000000 == 0) println(i)
        }
        return last
    }
}
