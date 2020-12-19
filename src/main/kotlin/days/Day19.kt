package days

import util.*

@Suppress("unused")
class Day19 : Day(19) {

    private var origRules = mutableMapOf<Int,String>()
    private var messages = mutableListOf<String>()
    var rules = mutableListOf<MutableList<String>>()
    private var cache = mutableMapOf<String, MutableList<String>>()

    override fun partOne(): Any {
        inputList.forEach {
            if (it.contains(":")) {
                origRules[it.splitrim(":")[0].toInt()] = it.splitrim(":")[1]
            } else if (it != "") {
                messages.add(it)
            }
        }

        val rule0 = process(origRules[0]!!)

        var total =0
        messages.forEach {
            if (rule0.contains(it)) total++
        }
        return total
    }

    private fun process(rule: String): MutableList<String> {
        if (cache.keys.contains(rule)) {
            return cache[rule]!!
        }

        if (rule.startsWith("\"")) {
            val retVal = mutableListOf(rule.replace("\"", ""))
            cache[rule]=retVal
            return retVal
        }
        if (rule.contains("|")) {
            val pRules = rule.splitrim("|")
            val retVal = mutableListOf<String>()
            retVal.addAll(process(pRules[0]))
            retVal.addAll(process(pRules[1]))
            cache[rule]=retVal
            return retVal
        }

        if (rule.contains(" ")) {
            val pRules = rule.splitrim(" ")
            val retVal = mutableListOf<String>()
            if (pRules.size == 2) {
                process(pRules[0]).forEach { it0 ->
                    process(pRules[1]).forEach{ it1 ->
                        retVal.add(it0+it1)
                    }
                }
            }
            if (pRules.size == 3) {
                process(pRules[0]).forEach { it0 ->
                    process(pRules[1]).forEach{ it1 ->
                        process(pRules[2]).forEach{ it2 ->
                            retVal.add(it0+it1+it2)
                        }
                    }
                }
            }
            cache[rule]=retVal
            return retVal
        }

        if (rule.toIntOrNull() != null){
            val retVal = process(origRules[rule.toInt()]!!)
            cache[rule]=retVal
            return retVal
        }

        println ("ERROR CASE")
        return mutableListOf()
    }

    private fun processOld(rule: String): MutableList<String> {
        if (rule.startsWith("\"")) return mutableListOf(rule.replace("\"", ""))
        if (rule.toIntOrNull() != null) return process(origRules[rule.toInt()]!!)

        if (rule.contains("|")) {
            val pRules = rule.splitrim("|")
            val retVal = mutableListOf<String>()
            pRules.forEach { retVal.addAll(process(it)) }
            return retVal
        }

        val retVals = mutableListOf<String>()
        val parts = rule.splitrim(" ")
        parts.forEach { part ->
            retVals.addAll(process(part))
        }
        return retVals
    }

    override fun partTwo(): Any {

        val rule42 = process(origRules[42]!!)
        val rule31 = process(origRules[31]!!)

        var total =0
        messages.forEach {
            if (isValid(it,rule42,rule31)) {
                println(it)
                total++
            }
        }
        return total
    }

    private fun isValid(it: String, rule42: MutableList<String>, rule31: MutableList<String>): Boolean {
        var reducedIt = it
        var modified = true
        var found42 = 0
        while (modified) {
            modified = false
            rule42.forEach { rule ->
                if (reducedIt.startsWith(rule)) {
                    reducedIt = reducedIt.replaceFirst(rule, "")
                    modified = true
                    found42++
                }
            }
        }

        var found31 = 0
        modified = true
        while (modified) {
            modified = false
            rule31.forEach { rule ->
                if (reducedIt.endsWith(rule)) {
                    reducedIt = reducedIt.dropLast(rule.length)
                    modified = true
                    found31++
                }
            }
        }
        return (found42>1 && found31>0 && found42>found31 && reducedIt.isEmpty())
    }
}
