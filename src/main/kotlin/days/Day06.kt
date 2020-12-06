package days

class Day06 : Day(6) {

    override fun partOne(): Any {
        val answers = mutableListOf<String>()
        var total = 0
        inputList.forEach {
            if (it != "") {
                it.forEach{
                    if (!answers.contains(it.toString())) answers.add(it.toString())
                }
            } else {
                total += answers.size
                answers.clear()
            }
        }
        total += answers.size
        answers.clear()
        return total
    }

    override fun partTwo(): Any {
        var answers : String?= null
        var total = 0
        inputList.forEach {
            if (it != "") {
                if (answers == null)
                    answers = it
                else {
                    var newAnswers = ""
                    answers!!.forEach { it2 ->
                        if (it.contains(it2)) newAnswers += it2
                    }
                    answers = newAnswers
                }
            } else {
                println(answers!!.length)
                total += answers!!.length
                answers = null
            }
        }
        println(answers!!.length)
        total += answers!!.length
        answers = null
        return total
    }
}
