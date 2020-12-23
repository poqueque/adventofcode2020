package days

import util.*

@Suppress("unused")
class Day22 : Day(22) {

    private val resultCache = mutableMapOf<Int,Boolean>()

    private var score = 0

    override fun partOne(): Any {

        val deck1 = mutableListOf<Int>()
        val deck2 = mutableListOf<Int>()

        var d1 = true
        inputList.forEach {
            if (it == "Player 1:")
                d1 = true
            else if (it == "Player 2:")
                d1 = false
            else if (it.isNotEmpty()) {
                if (d1) deck1.add(it.toInt()) else deck2.add(it.toInt())
            }
        }

        while (deck1.isNotEmpty() && deck2.isNotEmpty()) {
            val c1 = deck1.first()
            val c2 = deck2.first()
            deck1.removeAt(0)
            deck2.removeAt(0)
            if (c1 > c2) {
                deck1.add(c1)
                deck1.add(c2)
            } else {
                deck2.add(c2)
                deck2.add(c1)
            }

        }


        deck1.reverse()
        deck2.reverse()

        deck1.addAll(deck2)

        var total = 0
        deck1.forEachIndexed { index, i ->
            total += (index+1)*i
        }

        return total
    }

    override fun partTwo(): Any {

        val deck1 = mutableListOf<Int>()
        val deck2 = mutableListOf<Int>()

        println("Part 2")

        var d1 = true
        deck1.clear()
        deck2.clear()
        inputList.forEach {
            if (it == "Player 1:")
                d1 = true
            else if (it == "Player 2:")
                d1 = false
            else if (it.isNotEmpty()) {
                if (d1) deck1.add(it.toInt()) else deck2.add(it.toInt())
            }
        }

        val p1Wins = playGame(deck1.toMutableList(),deck2.toMutableList())

        return score
    }

    var recursion = 0

    private fun playGame(d1: MutableList<Int>, d2: MutableList<Int>): Boolean {

        recursion++

        val playCache = mutableListOf<Int>()
        var wins1 = false
        while (d1.isNotEmpty() && d2.isNotEmpty()) {
            val c1 = d1.first()
            val c2 = d2.first()
            when (isPlayer1TheWinner(d1,d2,playCache)) {
                true -> {
                    d1.removeAt(0)
                    d2.removeAt(0)
                    d1.add(c1)
                    d1.add(c2)
                }
                false -> {
                    d1.removeAt(0)
                    d2.removeAt(0)
                    d2.add(c2)
                    d2.add(c1)
                }
                else -> {
                    recursion--
                    return true
                }
            }
        }

        d1.reverse()
        d2.reverse()

        if (d1.isEmpty()) wins1 = false
        if (d2.isEmpty()) wins1 = true

        d1.addAll(d2)

        score = 0
        d1.forEachIndexed { index, i ->
            score += (index+1)*i
        }

        recursion--
        return wins1
    }

    private fun isPlayer1TheWinner(d1: MutableList<Int>, d2: MutableList<Int>, cache: MutableList<Int>): Boolean? {
        val cacheHash = d1.hashCode() + d2.hashCode()
        if (cache.contains(cacheHash)) {
            return null
        }
        cache.add(cacheHash)

        if (resultCache.keys.contains(cacheHash)) {
            return resultCache[cacheHash]!!
        }

        if (d1.first() >= d1.size || d2.first() >= d2.size) {
            resultCache[cacheHash] = d1.first() > d2.first()
            return d1.first() > d2.first()
        }

        val newD1 =  d1.drop(1).take(d1.first()).toMutableList()
        val newD2 =  d2.drop(1).take(d2.first()).toMutableList()

        val res =  playGame(newD1,newD2)
        resultCache[cacheHash] = res
        return res
    }
}
