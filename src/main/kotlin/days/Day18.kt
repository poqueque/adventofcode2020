package days

@Suppress("unused")
class Day18 : Day(18) {

    override fun partOne(): Any {
        return process(inputList)
    }

    private fun process(inputList: List<String>): Any {
        return inputList.map {
            evaluate(it)
        }.sum()
    }

    override fun partTwo(): Any {
        return inputList.map {
            var r = evaluateWithPrecedence(it)
            while (r.toLongOrNull() == null)
                r = evaluateWithPrecedence(it)
            r.toLong()
        }.sum()
    }

    private fun evaluateWithPrecedence(expr: String): String {
        println(expr)
        var retStr = expr
        val pattern1 = Regex("\\(([0-9]+)\\)")
        val match1 = pattern1.find(retStr)
        if (match1 != null) {
            val value = match1.groups[1]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern1, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern2p = Regex("\\(([0-9]+) \\+ ([0-9]+)\\)")
        val match2p = pattern2p.find(retStr)
        if (match2p != null) {
            val value = match2p.groups[1]!!.value.toLong() + match2p.groups[2]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern2p, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern2 = Regex("([0-9]+) \\+ ([0-9]+)")
        val match2 = pattern2.find(retStr)
        if (match2 != null) {
            val value = match2.groups[1]!!.value.toLong() + match2.groups[2]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern2, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern3 = Regex("\\(([0-9]+) \\* ([0-9]+)\\)")
        val match3 = pattern3.find(retStr)
        if (match3 != null) {
            val value = match3.groups[1]!!.value.toLong() * match3.groups[2]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern3, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern33 = Regex("\\(([0-9]+) \\* ([0-9]+) \\* ([0-9]+)\\)")
        val match33 = pattern33.find(retStr)
        if (match33 != null) {
            val value =
                match33.groups[1]!!.value.toLong() * match33.groups[2]!!.value.toLong() * match33.groups[3]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern33, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern34 = Regex("\\(([0-9]+) \\* ([0-9]+) \\* ([0-9]+) \\* ([0-9]+)\\)")
        val match34 = pattern34.find(retStr)
        if (match34 != null) {
            val value =
                match34.groups[1]!!.value.toLong() * match34.groups[2]!!.value.toLong() * match34.groups[3]!!.value.toLong() * match34.groups[4]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern34, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern35 = Regex("\\(([0-9]+) \\* ([0-9]+) \\* ([0-9]+) \\* ([0-9]+) \\* ([0-9]+)\\)")
        val match35 = pattern35.find(retStr)
        if (match35 != null) {
            val value =
                match35.groups[1]!!.value.toLong() * match35.groups[2]!!.value.toLong() * match35.groups[3]!!.value.toLong() * match35.groups[4]!!.value.toLong() * match35.groups[5]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern35, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern36 = Regex("\\(([0-9]+) \\* ([0-9]+) \\* ([0-9]+) \\* ([0-9]+) \\*([0-9]+) \\* ([0-9]+)\\)")
        val match36 = pattern36.find(retStr)
        if (match36 != null) {
            val value =
                match36.groups[1]!!.value.toLong() * match36.groups[2]!!.value.toLong() * match36.groups[3]!!.value.toLong() * match36.groups[4]!!.value.toLong() * match36.groups[5]!!.value.toLong() * match36.groups[6]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern36, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        val pattern4 = Regex("([0-9]+) \\* ([0-9]+)")
        val match4 = pattern4.find(retStr)
        if (match4 != null) {
            val value = match4.groups[1]!!.value.toLong() * match4.groups[2]!!.value.toLong()
            retStr = retStr.replaceFirst(pattern4, value.toString())
            return evaluateWithPrecedence(retStr)
        }
        return retStr
    }

    private fun evaluate(expr: String): Long {
        val groups = mutableListOf<Long>()
        val operation = mutableListOf<Char>()
        var level = 0
        groups.add(0)
        operation.add(' ')

        expr.forEach {
            if (it == '(') {
                level++
                groups.add(0)
                operation.add(' ')
            } else if (it == ')') {
                groups[level - 1] = operate(groups[level - 1], groups[level], operation[level - 1])
                level--
                groups.removeLast()
                operation.removeLast()
            } else if (it == '+' || it == '-' || it == '*') {
                operation[level] = it
            } else if (it != ' ') {
                val numVal = it.toString().toLong()
                groups[level] = operate(groups[level], numVal, operation[level])
            }
        }
        return groups[0]
    }

    private fun operate(first: Long, second: Long, op: Char): Long {
        if (op == ' ' || first == 0L) {
            return second
        }
        if (op == '+') return first + second
        if (op == '-') return first - second
        if (op == '*') return first * second
        println("CASE NOT CONSIDERED")
        return 0
    }
}
