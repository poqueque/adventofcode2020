package days

import util.*

@Suppress("unused")
class Day17 : Day(17) {

    override fun partOne(): Any {

        var initMap = mutableMapOf<Coor3,Char>()
        val initMap2D = inputMap.map.toMutableMap()
        initMap2D.forEach{
            initMap[Coor3(it.key.x,it.key.y,0)] = it.value
        }
        val nextMap = initMap.toMutableMap()
        var occupiedSeats: Int

        var cycles = 0
        do {
            initMap = nextMap.toMutableMap()
            val toAdd = mutableListOf<Coor3>()
            initMap.keys.forEach {
                for (i in (it.x - 1)..(it.x + 1))
                    for (j in (it.y - 1)..(it.y + 1))
                        for (k in (it.z - 1)..(it.z + 1))
                            if (i != it.x || j != it.y || k != it.z) {
                                if (initMap[Coor3(i, j, k)] == null)
                                    toAdd.add(Coor3(i, j, k))
                            }
            }
            toAdd.forEach {
                initMap[it]= '.'
            }

            initMap.keys.forEach {
                var occupied = 0
                for (i in (it.x - 1)..(it.x + 1))
                    for (j in (it.y - 1)..(it.y + 1))
                        for (k in (it.z - 1)..(it.z + 1))
                            if (i != it.x || j != it.y|| k != it.z) {
                                if (initMap[Coor3(i, j, k)] == '#') occupied++
                            }
                var value = '.'
                if (initMap[it]!! == '.') {
                    value = if (occupied == 3) '#' else '.'
                }
                if (initMap[it]!! == '#') {
                    value = if (occupied == 2 || occupied == 3) '#' else '.'
                }
                nextMap[it] = value
            }
            occupiedSeats = 0
            nextMap.keys.forEach {
                if (nextMap[it] == '#') occupiedSeats++
            }
            cycles ++
        } while (cycles < 6)
        return occupiedSeats
    }

    override fun partTwo(): Any {

        var initMap = mutableMapOf<Coor4,Char>()
        val initMap2D = inputMap.map.toMutableMap()
        initMap2D.forEach{
            initMap[Coor4(it.key.x,it.key.y,0,0)] = it.value
        }
        val nextMap = initMap.toMutableMap()
        var occupiedSeats: Int

        var cycles = 0

        do {
            initMap = nextMap.toMutableMap()
            val toAdd = mutableListOf<Coor4>()
            initMap.keys.forEach {
                for (i in (it.x - 1)..(it.x + 1))
                    for (j in (it.y - 1)..(it.y + 1))
                        for (k in (it.z - 1)..(it.z + 1))
                            for (l in (it.w - 1)..(it.w + 1))
                                if (i != it.x || j != it.y || k != it.z || l != it.w) {
                                    if (initMap[Coor4(i, j, k, l)] == null)
                                        toAdd.add(Coor4(i, j, k, l))
                                }
            }
            toAdd.forEach {
                initMap[it]= '.'
            }

            initMap.keys.forEach {
                var occupied = 0
                for (i in (it.x - 1)..(it.x + 1))
                    for (j in (it.y - 1)..(it.y + 1))
                        for (k in (it.z - 1)..(it.z + 1))
                            for (l in (it.w - 1)..(it.w + 1))
                                if (i != it.x || j != it.y|| k != it.z || l!= it.w) {
                                    if (initMap[Coor4(i, j, k, l)] == '#') occupied++
                                }
                var value = '.'
                if (initMap[it]!! == '.') {
                    value = if (occupied == 3) '#' else '.'
                }
                if (initMap[it]!! == '#') {
                    value = if (occupied == 2 || occupied == 3) '#' else '.'
                }
                nextMap[it] = value
            }
            occupiedSeats = 0
            nextMap.keys.forEach {
                if (nextMap[it] == '#') occupiedSeats++
            }
            cycles ++

        } while (cycles < 6)
        return occupiedSeats
    }
}
