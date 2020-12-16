package days

import util.*

@Suppress("unused")
class Day16 : Day(16) {

    private var rules = mutableMapOf<String, List<Int>>()
    private var knownPositions = MutableList(rules.size) { "" }
    private var myTicket = listOf<Int>()

    override fun partOne(): Any {
        var phase = 0
        var errorTotal = 0
        inputList.forEach {
            if (phase == 0) {
                if (it == "your ticket:")
                    phase = 1
                else if (it.contains(":")) {
                    val parts = it.splitrim(":")
                    val list = mutableListOf<Int>()
                    val ranges = parts[1].splitrim("or")
                    ranges.forEach { range ->
                        val fromTo = range.splitrim("-")
                        list.addAll(fromTo[0].toInt()..fromTo[1].toInt())
                    }
                    rules[parts[0]] = list
                }
            } else if (phase == 1) {
                if (it == "nearby tickets:") {
                    phase = 2
                } else if (it.contains(",")) {
                    myTicket = it.splitrim(",").map { value -> value.toInt() }.toList()
                }
            } else {
                if (it.contains(",")) {
                    val ticket = it.splitrim(",").map { value -> value.toInt() }.toList()
                    errorTotal += errorRate(ticket)
                }
            }
        }
        return errorTotal
    }


    private var validTickets = mutableListOf<List<Int>>()

    private fun errorRate(ticket: List<Int>): Int {
        var errorRate = 0
        ticket.forEach { ticketValue ->
            var isValid = false
            rules.values.forEach { rule ->
                if (rule.contains(ticketValue)) isValid = true
            }
            if (!isValid) errorRate += ticketValue
        }
        return errorRate
    }

    private fun errorFree(ticket: List<Int>): Boolean {
        ticket.forEach { ticketValue ->
            var isValid = false
            rules.values.forEach { rule ->
                if (rule.contains(ticketValue)) isValid = true
            }
            if (!isValid) return false
        }
        return true
    }

    override fun partTwo(): Any {
        var phase = 0
        inputList.forEach {
            if (phase == 0) {
                if (it == "your ticket:")
                    phase = 1
                else if (it.contains(":")) {
                    val parts = it.splitrim(":")
                    val list = mutableListOf<Int>()
                    val ranges = parts[1].splitrim("or")
                    ranges.forEach { range ->
                        val fromTo = range.splitrim("-")
                        list.addAll(fromTo[0].toInt()..fromTo[1].toInt())
                    }
                    rules[parts[0]] = list
                }
            } else if (phase == 1) {
                if (it == "nearby tickets:") {
                    phase = 2
                    knownPositions = MutableList(rules.size) { "" }
                } else if (it.contains(",")) {
                    myTicket = it.splitrim(",").map { value -> value.toInt() }.toList()
                }
            } else {
                if (it.contains(",")) {
                    val ticket = it.splitrim(",").map { value -> value.toInt() }.toList()
                    if (errorFree(ticket)) {
                        validTickets.add(ticket)
                    }
                }
            }
        }
        println(validTickets)
        val possibleValues = MutableList(rules.size) { rules.keys.toMutableList() }
        validTickets.forEach { ticket ->
            ticket.forEachIndexed { position, value ->
                val toRemove = mutableListOf<String>()
                possibleValues[position].forEach {
                    if (!rules[it]!!.contains(value)) {
                        toRemove.add(it)
                    }
                }
                possibleValues[position].removeAll(toRemove)
            }
        }

        val foundValues = MutableList(rules.size) { "" }

        repeat(20) {
            possibleValues.forEachIndexed { index, value ->
                if (value.size == 1) {
                    foundValues[index] = value[0]
                }
            }
            foundValues.forEachIndexed { indexFound, valueFound ->
                possibleValues.forEachIndexed { i, _ ->
                    if (i != indexFound) {
                        possibleValues[i].remove(valueFound)
                    }
                }
            }
        }

        var retVal = 1L
        foundValues.forEachIndexed { index, _ ->
            if (foundValues[index].startsWith("departure"))
                retVal *= myTicket[index]
        }

        return retVal
    }
}
