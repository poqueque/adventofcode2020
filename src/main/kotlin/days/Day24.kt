package days

import util.*

@Suppress("unused")
class Day24 : Day(24) {

    private var tiles = mutableListOf<Coor>()

    override fun partOne(): Any {
        inputList.forEach {
            val t = simplify(it)
            if (tiles.contains(t)) tiles.remove(t)
            else tiles.add(t)
        }
        return tiles.size
    }

    private fun simplify(it: String): Coor {
        var pointer = 0
        val pos = Coor(0, 0)
        while (pointer < it.length) {
            var c = it[pointer].toString()
            if (c == "s" || c == "n") {
                pointer++
                c += it[pointer].toString()
            }
            pointer++
            when (c) {
                "e" -> pos.x++
                "w" -> pos.x--
                "ne" -> {
                    pos.y--
                    if (pos.y % 2 == 0) pos.x++
                }
                "nw" -> {
                    pos.y--
                    if (pos.y % 2 != 0) pos.x--
                }
                "se" -> {
                    pos.y++
                    if (pos.y % 2 == 0) pos.x++
                }
                "sw" -> {
                    pos.y++
                    if (pos.y % 2 != 0) pos.x--
                }
            }
        }
        return pos
    }

    override fun partTwo(): Any {
        var artTiles = tiles.toMutableList()
        repeat(100) {
            artTiles = doStep(artTiles)
            println("${it+1}: ${artTiles.size}")
        }
        return artTiles.size
    }

    private fun doStep(artTiles: MutableList<Coor>): MutableList<Coor> {
        val blackTilesAfterStep = mutableListOf<Coor>()
        val maxX = artTiles.map { it.x }.maxOrNull()!! + 1
        val maxY = artTiles.map { it.y }.maxOrNull()!! + 1
        val minX = artTiles.map { it.x }.minOrNull()!! - 1
        val minY = artTiles.map { it.y }.minOrNull()!! - 1
        for (i in minX..maxX)
            for (j in minY..maxY) {
                if (artTiles.contains(Coor(i, j))) {
                    //isBlack
                    var blackTiles = 0
                    getNeighbours(Coor(i, j)).forEach {
                        if (artTiles.contains(it)) blackTiles++
                    }
                    if (blackTiles == 1 || blackTiles == 2){
                        blackTilesAfterStep.add(Coor(i,j))
                    }
                } else {
                    //isWhite
                    var blackTiles = 0
                    getNeighbours(Coor(i, j)).forEach {
                        if (artTiles.contains(it)) blackTiles++
                    }
                    if (blackTiles == 2) {
                        blackTilesAfterStep.add(Coor(i, j))
                    }
                }
            }
        return blackTilesAfterStep
    }

    private fun getNeighbours(coor: Coor): List<Coor> {
        val n = mutableListOf<Coor>()
        n.add(Coor(coor.x + 1, coor.y))
        n.add(Coor(coor.x - 1, coor.y))
        if (coor.y % 2 == 0) {
            n.add(Coor(coor.x, coor.y - 1))
            n.add(Coor(coor.x - 1, coor.y - 1))
            n.add(Coor(coor.x, coor.y + 1))
            n.add(Coor(coor.x - 1, coor.y + 1))
        } else {
            n.add(Coor(coor.x + 1, coor.y - 1))
            n.add(Coor(coor.x, coor.y - 1))
            n.add(Coor(coor.x + 1, coor.y + 1))
            n.add(Coor(coor.x, coor.y + 1))
        }
        return n
    }
}
