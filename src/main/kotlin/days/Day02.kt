package days

class Day02 : Day(2) {

    override fun partOne(): Any {
        var validPass = 0
        inputList.forEach {
            val (policy, password) = it.split(":").map{ it2 -> it2.trim() }
            val (times, letter) = policy.split(" ").map{ it2 -> it2.trim() }
            val (min, max) = times.split("-").map{ num -> num.toInt() }
            val foundLetters = password.toList().filter { it2 -> it2 == letter[0] }.count()
            if (foundLetters in min..max) validPass++
        }
        return validPass
    }

    override fun partTwo(): Any {
        var validPass = 0
        inputList.forEach {
            val (policy, password) = it.split(":").map{ it2 -> it2.trim() }
            val (times, letter) = policy.split(" ").map{ it2 -> it2.trim() }
            val (min, max) = times.split("-").map{ num -> num.toInt() }
            val condition1 = (password[min-1] == letter[0] || password[max-1] == letter[0])
            val condition2 = !(password[min-1] == letter[0] && password[max-1] == letter[0])
            if ( condition1 && condition2) validPass++
        }
        return validPass
    }
}
