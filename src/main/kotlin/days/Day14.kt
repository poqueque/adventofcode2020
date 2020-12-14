package days

import util.splitrim
import kotlin.math.pow

class Day14 : Day(14) {

    override fun partOne(): Any {
        val memory = mutableMapOf<Int,Long>()
        var mask = ""
        inputList.forEach {
            val parts = it.splitrim("=")
            if (parts[0]=="mask")
                mask = parts[1]
            else {
                val memPos = parts[0].substring(4,parts[0].length-1).toInt()
                val value = applyMask(parts[1].toLong(),mask)
                memory[memPos] = value
            }
        }
        println(memory)
        return memory.values.toList().sum()
    }

    private fun applyMask(value: Long, mask: String): Long {
        var valueStr = value.toString(2).padStart(36,'0')
        for (i in 0..35){
            if(mask[i] == '0') {
                val chars = valueStr.toCharArray()
                chars[i] = '0'
                valueStr = String(chars)
            }
            if(mask[i] == '1') {
                val chars = valueStr.toCharArray()
                chars[i] = '1'
                valueStr = String(chars)
            }
        }
        return valueStr.toLong(2)
    }

    private fun applyMaskV2(value: Int, mask: String): List<Long> {
        val list = mutableListOf<Long>()
        var valueStr = value.toString(2).padStart(36,'0')
        var xs = 0
        for (i in 0..35){
            if(mask[i] == 'X') {
                val chars = valueStr.toCharArray()
                chars[i] = 'X'
                valueStr = String(chars)
                xs++
            }
            if(mask[i] == '1') {
                val chars = valueStr.toCharArray()
                chars[i] = '1'
                valueStr = String(chars)
            }
        }
        for (i in 0 until 2.0.pow(xs).toInt()) {
            val iStr = i.toString(2).padStart(xs,'0')
            val numbStr = valueStr.toCharArray()
            val indices = valueStr.indexesOf('X')
            for (j in iStr.indices){
                numbStr[indices[j]] = iStr[j]
            }
            val n = numbStr.joinToString("").toLong(2)
            list.add(n)
        }
        return list
    }

    override fun partTwo(): Any {
        val memory = mutableMapOf<Long,Long>()
        var mask = ""
        inputList.forEach {
            val parts = it.splitrim("=")
            if (parts[0]=="mask")
                mask = parts[1]
            else {
                val memPos = parts[0].substring(4,parts[0].length-1).toInt()
                val listMem = applyMaskV2(memPos,mask)
                listMem.forEach { pos ->
                    val value = parts[1].toLong()
                    memory[pos] = value
                }
            }
        }
        return memory.values.toList().sum()
    }
}

private fun CharSequence.indexesOf(c: Char) : List<Int>{
    var index: Int = this.indexOf(c)
    val retVal = mutableListOf<Int>()
    while (index >= 0) {
        retVal.add(index)
        index = this.indexOf(c,startIndex = index + 1)
    }
    return retVal
}
