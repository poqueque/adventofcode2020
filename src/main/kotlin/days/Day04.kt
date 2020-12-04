package days

import util.splitrim

class Day04 : Day(4) {

    override fun partOne(): Any {
        var validPass = 0
        val fields = mutableListOf<String>()
        inputList.forEach {
            if (it == "") {
                if (validatePass(fields)) validPass++
                fields.clear()
            } else {
                fields.addAll(it.splitrim(" "))
            }
        }
        if (validatePass(fields)) validPass++
        return validPass
    }

    private fun validatePass(fields: MutableList<String>): Boolean {
        var byr = false
        var iyr = false
        var eyr = false
        var hgt = false
        var hcl = false
        var ecl = false
        var pid = false
        var cid = false
        fields.forEach {
            val (code, value) = it.splitrim(":")
            if (code == "byr") byr = true
            if (code == "iyr") iyr = true
            if (code == "eyr") eyr = true
            if (code == "hgt") hgt = true
            if (code == "hcl") hcl = true
            if (code == "ecl") ecl = true
            if (code == "pid") pid = true
            if (code == "cid") cid = true
        }
        return (byr && iyr && eyr && hgt && hcl && ecl && pid)
    }

    override fun partTwo(): Any {
        var validPass = 0
        val fields = mutableListOf<String>()
        inputList.forEach {
            if (it == "") {
                if (validatePass2(fields)) validPass++
                fields.clear()
            } else {
                fields.addAll(it.splitrim(" "))
            }
        }
        if (validatePass2(fields)) validPass++
        return validPass
    }

    private fun validatePass2(fields: MutableList<String>): Boolean {
        var byr = false
        var iyr = false
        var eyr = false
        var hgt = false
        var hcl = false
        var ecl = false
        var pid = false
        var cid = false
        fields.forEach {
            val (code, value) = it.splitrim(":")
            if (code == "byr" && value.toInt() >= 1920 && value.toInt() <= 2002) byr = true
            if (code == "iyr" && value.toInt() >= 2010 && value.toInt() <= 2020) iyr = true
            if (code == "eyr" && value.toInt() >= 2020 && value.toInt() <= 2030) eyr = true
            if (code == "hgt"
                && ((value.takeLast(2) == "in" && value.dropLast(2).toInt() >= 59 && value.dropLast(2).toInt() <= 76)
                        || (value.takeLast(2) == "cm" && value.dropLast(2).toInt() >= 150 && value.dropLast(2)
                    .toInt() <= 193))
            ) hgt = true
            if (code == "hcl"
                && value.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$".toRegex())
            ) hcl = true
            if (code == "ecl"
                && (value == "amb" || value == "blu" || value == "brn" || value == "gry" || value == "grn" || value == "hzl" || value == "oth")
            ) ecl = true
            if (code == "pid"
                && value.matches("^\\d{9}$".toRegex())
            ) pid = true
            if (code == "cid") cid = true
        }
        return (byr && iyr && eyr && hgt && hcl && ecl && pid)
    }
}
