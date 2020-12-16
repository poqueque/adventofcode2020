package days

import util.splitrim

@Suppress("unused")
class Day07 : Day(7) {

    private var rules = mutableMapOf<String,List<Pair<Int,String>>>()

    private fun init() {
        inputList.forEach {
            val rule = mutableListOf<Pair<Int,String>>()
            val (key,list) = it.splitrim("contain")
            val key1 = key.replace("bags","").trim()
            val data = list.splitrim(",")
            data.forEach { value ->
                val words = value.splitrim(" ")
                val number = words[0]
                val type = "${words[1]} ${words[2]}"
                if (number != "no")
                    rule.add(Pair(number.toInt(),type))
            }
            rules[key1] = rule
        }
    }

    override fun partOne(): Any {
        init()
        val mine = "shiny gold"
        var total = 0
        rules.keys.forEach {
            if (it != mine) {
                println("Checking $it")
                val success = contains(rules, it, mine)
                println(success)
                if (success) total++
            }
        }
        return total
    }

    override fun partTwo(): Any {
        init()

        val mine = "shiny gold"
        return containsNumber(rules,mine) - 1
    }

    private fun contains(rules: Map<String,List<Pair<Int,String>>>, testBag: String, mine: String): Boolean {
        if (testBag == mine) return true
        rules[testBag]!!.forEach {
            if (contains(rules, it.second, mine)) return true
        }
        return false
    }

    private fun containsNumber(rules: Map<String,List<Pair<Int,String>>>, testBag: String): Int {
        var total = 1
        rules[testBag]!!.forEach {
            total += it.first * containsNumber(rules, it.second)
        }
        return total
    }

}
