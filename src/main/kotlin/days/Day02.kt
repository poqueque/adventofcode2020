package days

import util.splitrim

class Day02 : Day(2) {

    override fun partOne(): Any {
        var validPass = 0
        inputList.forEach {
            val (policy, password) = it.splitrim(":")
            val (times, letter) = policy.splitrim(" ")
            val (min, max) = times.splitrim("-").map{ num -> num.toInt() }
            val foundLetters = password.toList().filter { it2 -> it2 == letter[0] }.count()
            if (foundLetters in min..max) validPass++
        }
        return validPass
    }

    override fun partTwo(): Any {
        var validPass = 0
        inputList.forEach {
            val (policy, password) = it.splitrim(":")
            val (times, letter) = policy.splitrim(" ")
            val (min, max) = times.splitrim("-").map{ num -> num.toInt() }
            val condition1 = (password[min-1] == letter[0] || password[max-1] == letter[0])
            val condition2 = !(password[min-1] == letter[0] && password[max-1] == letter[0])
            if ( condition1 && condition2) validPass++
        }
        return validPass
    }
}
