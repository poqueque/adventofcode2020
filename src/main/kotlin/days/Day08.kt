package days

import util.GameConsole

class Day08 : Day(8) {

    override fun partOne(): Any {
        val console = GameConsole(inputList)
        return console.run()
    }

    override fun partTwo(): Any {
        for (i in inputList.indices) {
            val list = inputList.toMutableList()
            if (list[i].startsWith("jmp")) list[i] = list[i].replace("jmp","nop")
            else if (list[i].startsWith("nop")) list[i] = list[i].replace("nop","jmp")
            val console = GameConsole(list)
            val result = console.run()
            if (result.first) return result
        }
        return "NONE"
    }
}
