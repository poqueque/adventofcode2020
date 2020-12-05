package days

import kotlin.math.max

class Day05 : Day(5) {

    override fun partOne(): Any {
        var highest = 0
        inputList.forEach {
            val row = it.substring(0, 7).replace("F", "0").replace("B", "1").toInt(2)
            val col = it.substring(7).replace("L", "0").replace("R", "1").toInt(2)
            val id = row * 8 + col
            highest = max(highest, id)
        }
        return highest
    }

    override fun partTwo(): Any {
        val all = (0..822).toMutableList()
        inputList.forEach {
            val row = it.substring(0, 7).replace("F", "0").replace("B", "1").toInt(2)
            val col = it.substring(7).replace("L", "0").replace("R", "1").toInt(2)
            val id = row * 8 + col
            all.remove(id)
        }
        all.forEach {
            if (!all.contains(it - 1) && !all.contains(it + 1)) return it
        }
        return 0
    }
}
