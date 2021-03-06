package days

import util.Coor

@Suppress("unused")
class Day11 : Day(11) {

    override fun partOne(): Any {
        var initMap: MutableMap<Coor,Char>
        val nextMap = inputMap.map.toMutableMap()
        var occupiedSeats: Int

        do {
            initMap = nextMap.toMutableMap()
            initMap.keys.forEach {
                var occupied = 0
                for (i in (it.x - 1)..(it.x + 1))
                    for (j in (it.y - 1)..(it.y + 1))
                        if (i != it.x || j != it.y) {
                            if (initMap[Coor(i, j)] == '#') occupied++
                        }
                var value = '.'
                if (initMap[it]!! == 'L') {
                    value = if (occupied == 0) '#' else 'L'
                }
                if (initMap[it]!! == '#') {
                    value = if (occupied >= 4) 'L' else '#'
                }
                nextMap[it] = value
            }
            occupiedSeats = 0
            var diffSeats = 0
            nextMap.keys.forEach {
                if (nextMap[it] == '#') occupiedSeats++
                if (nextMap[it] != initMap[it]) diffSeats++
            }
        } while (diffSeats > 0)
        return occupiedSeats
    }

    override fun partTwo(): Any {
        var initMap: MutableMap<Coor,Char>
        val nextMap = inputMap.map.toMutableMap()
        var occupiedSeats: Int

        do {
            initMap = nextMap.toMutableMap()
            initMap.keys.forEach {
                var movingCoor: Coor
                var occupied = 0
                for (i in -1..1)
                    for (j in -1..1)
                        if (i != 0 || j != 0) {
                            movingCoor = Coor(it.x + i, it.y + j)
                            while (initMap[movingCoor] == '.') movingCoor = Coor(movingCoor.x + i, movingCoor.y + j)
                            if (initMap[movingCoor] == '#') occupied++
                        }
                var value = '.'
                if (initMap[it]!! == 'L') {
                    value = if (occupied == 0) '#' else 'L'
                }
                if (initMap[it]!! == '#') {
                    value = if (occupied >= 5) 'L' else '#'
                }
                nextMap[it] = value
            }
/*
            for (i in 0 until inputMap.maxX) {
                for (j in 0 until inputMap.maxY)
                    print(nextMap[Coor(j, i)])
                println()
            }
*/

            occupiedSeats = 0
            var diffSeats = 0
            nextMap.keys.forEach {
                if (nextMap[it] == '#') occupiedSeats++
                if (nextMap[it] != initMap[it]) diffSeats++
            }
//            println(occupiedSeats)
//            println(diffSeats)
        } while (diffSeats > 0)
        return occupiedSeats
    }
}
