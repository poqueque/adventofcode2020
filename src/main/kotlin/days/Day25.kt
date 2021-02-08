package days

import util.*

@Suppress("unused")
class Day25 : Day(25) {

    override fun partOne(): Any {
        var subjectCard = 7
        var cardPublicKey = inputList[0].toLong()
        var subjectDoor = 7
        var doorPublicKey = inputList[1].toLong()

        var value = 1L
        var loopCard = 0
        while (value != cardPublicKey){
            value *= subjectCard
            value %= 20201227
            loopCard++
        }

        value = 1L
        var loopDoor = 0
        while (value != doorPublicKey){
            value *= subjectDoor
            value %= 20201227
            loopDoor++
        }

        var v1 = 1L
        for (i in 1..loopCard){
            v1 *= doorPublicKey
            v1 %= 20201227
        }
        println(v1)

        v1 = 1L
        for (i in 1..loopDoor){
            v1 *= cardPublicKey
            v1 %= 20201227
        }
        println(v1)

        return v1
    }

    override fun partTwo(): Any {
        return 0
    }
}
