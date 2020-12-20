package days

import util.*
import kotlin.math.sqrt

@Suppress("unused")
class Day20 : Day(20) {

    private var tiles = mutableMapOf<Int, CoorMap>()
    val tileSize=10

    override fun partOne(): Any {
        var tile = 0
        val dataList = mutableListOf<String>()
        inputList.forEach {
            when {
                it.contains("Tile") -> tile = it.replace("Tile ", "").replace(":", "").toInt()
                it.isEmpty() -> {
                    tiles[tile] = CoorMap(dataList)
                    dataList.clear()
                }
                else -> dataList.add(it)
            }
        }
        tiles[tile] = CoorMap(dataList)
        dataList.clear()
        var total = 1L

        tiles.forEach { entry ->

            val map = entry.value
            var bordersInCommon = 0
            map.borders.forEach { border ->
                var found=false
                tiles.values.filter { it != map }.forEach {
                    if (it.borders.contains(border)) found  = true
                }
                if (found) bordersInCommon++
            }
            if (bordersInCommon==2) {
//                println("${entry.key} $bordersInCommon")
                total*=entry.key
            }
        }

        return total
    }

    override fun partTwo(): Any {
        var tile = 0
        val dataList = mutableListOf<String>()
        inputList.forEach {
            when {
                it.contains("Tile") -> tile = it.replace("Tile ", "").replace(":", "").toInt()
                it.isEmpty() -> {
                    tiles[tile] = CoorMap(dataList)
                    tiles[tile]!!.id = tile
                    dataList.clear()
                }
                else -> dataList.add(it)
            }
        }
        tiles[tile] = CoorMap(dataList)
        tiles[tile]!!.id = tile
        dataList.clear()

        val initCorner = 3931
        val orientation = 1
        tiles[initCorner]!!.orientation = orientation
//        println("E: ${CoorMap.getE(tiles[initCorner]!!.orientedMap)}")

        val size = sqrt(tiles.size.toDouble()).toInt()

        val tilesRemaining = tiles.keys.toMutableList()
        val map = mutableMapOf<Coor, CoorMap>()
        for (j in 0 until size)
            for (i in 0 until size) {
                if (i == 0 && j == 0) {
                    map[Coor(i, j)] = tiles[initCorner]!!
                    tilesRemaining.remove(initCorner)
                } else if (i > 0) {
                    val prevTile = map[Coor(i - 1, j)]!!
                    val matchingTiles = findMatchingTilesSide(prevTile, tilesRemaining)
                    map[Coor(i, j)] = tiles[matchingTiles[0]]!!
                    tilesRemaining.remove(matchingTiles[0])
                } else {
                    val prevTile = map[Coor(i, j - 1)]!!
                    val matchingTiles = findMatchingTilesUp(prevTile, tilesRemaining)
                    map[Coor(i, j)] = tiles[matchingTiles[0]]!!
                    tilesRemaining.remove(matchingTiles[0])
                }
//                println(map[Coor(i, j)]!!.id)
            }

        val newInputList = mutableListOf<String>()
        repeat(8*size){
            newInputList.add("")
        }
        for (j in 0 until size) {
            for (i in 0 until size) {
                for(y in 1..8){
                    var s= ""
                    for(x in 1..8){
                        s+=map[Coor(i,j)]!!.orientedMap[Coor(x,y)]
                    }
                    newInputList[8*j+(y-1)] += s
                }
            }
        }
        val bigMap = CoorMap(newInputList)
        bigMap.orientation = 5
//        println(bigMap)
        val monsters = findMonsters(bigMap)
//        println("Monsters: $monsters")
        val total = bigMap.map.values.toList().count { it == '#' }
//        println("Total: $total")
//        println("Total-M: ${total-15*monsters}")
        return total-15*monsters
    }

    private val listMonsterPositions= listOf(
        Coor(0,1),
        Coor(1,2),
        Coor(4,2),
        Coor(5,1),
        Coor(6,1),
        Coor(7,2),
        Coor(10,2),
        Coor(11,1),
        Coor(12,1),
        Coor(13,2),
        Coor(16,2),
        Coor(17,1),
        Coor(18,0),
        Coor(18,1),
        Coor(19,1),
    )

    private fun findMonsters(bigMap: CoorMap): Int {
        var monsters = 0
        for (orientation in 0..7){
            bigMap.orientation = orientation
            bigMap.orientedMap.keys.forEach { coor1 ->
                val m = listMonsterPositions.map { coor2 -> coor2+coor1 }.count { bigMap.orientedMap[it] ?: ' ' == '#' }
                if (m==15){
                    monsters++
                }
            }
            if (monsters > 0) return monsters
        }
        return monsters
    }

    private fun findMatchingTile(prevTile: CoorMap, tilesRemaining: MutableList<Int>): Int? {
        tilesRemaining.forEach {
            for (o in 0..7){
                tiles[it]!!.orientation = o
                val border1 = CoorMap.getE(prevTile.orientedMap)
                val border2 =CoorMap.getW(tiles[it]!!.orientedMap)
                if (border1 == border2){
//                    println("Match [${prevTile.id} - ${prevTile.orientation}] W ($border1) - [$it - ${tiles[it]!!.orientation}] E ($border2)" )
                    return it
                }
            }
        }
        return null
    }

    private fun findMatchingTilesSide(prevTile: CoorMap, tilesRemaining: MutableList<Int>): List<Int> {
        val retList = mutableListOf<Int>()
        tilesRemaining.forEach loop@{
            for (o in 0..7){
//                println(prevTile)
                tiles[it]!!.orientation = o
//                println(tiles[it])
                val border1 = CoorMap.getE(prevTile.orientedMap)
                val border2 =CoorMap.getW(tiles[it]!!.orientedMap)
                if (border1 == border2){
//                    println("Match [${prevTile.id} - ${prevTile.orientation}] W ($border1) - [$it - ${tiles[it]!!.orientation}] E ($border2)" )
                    retList.add(it)
                    return retList
                }
            }
        }
        return retList
    }
    private fun findMatchingTilesUp(prevTile: CoorMap, tilesRemaining: MutableList<Int>): List<Int> {
        val retList = mutableListOf<Int>()
        tilesRemaining.forEach loop@{
            for (o in 0..7){
//                println(prevTile)
                tiles[it]!!.orientation = o
//                println(tiles[it])
                val border1 = CoorMap.getS(prevTile.orientedMap)
                val border2 =CoorMap.getN(tiles[it]!!.orientedMap)
                if (border1 == border2){
//                    println("Match [${prevTile.id} - ${prevTile.orientation}] S ($border1) - [$it - ${tiles[it]!!.orientation}] N ($border2)" )
                    retList.add(it)
                    return retList
                }
            }
        }
        return retList
    }
}
