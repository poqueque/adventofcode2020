package days

import util.Coor

class Day03 : Day(3) {

    override fun partOne(): Any {
        val map = inputMap.map
        val totX = inputMap.maxX
        val totY = inputMap.maxY
        var trees = 0
        for (i in 0 until totY) {
            if (map[Coor(3 * i % totX, i)] == '#') trees++
        }

        return trees
    }

    override fun partTwo(): Any {
        var map = inputMap.map
        val totX = inputMap.maxX
        val totY = inputMap.maxY
        var product = 1

        var trees = 0
        for (i in 0 until totY) {
            if (map[Coor(i % totX, i)] == '#') trees++
        }
        product *= trees

        trees = 0
        for (i in 0 until totY) {
            if (map[Coor(3 * i % totX, i)] == '#') trees++
        }
        product *= trees

        trees = 0
        for (i in 0 until totY) {
            if (map[Coor(5 * i % totX, i)] == '#') trees++
        }
        product *= trees

        trees = 0
        for (i in 0 until totY) {
            if (map[Coor(7 * i % totX, i)] == '#') trees++
        }
        product *= trees

        trees = 0
        for (i in 0 until totY / 2) {
            if (map[Coor(i % totX, i * 2)] == '#') trees++
        }
        product *= trees

        return product
    }
}
