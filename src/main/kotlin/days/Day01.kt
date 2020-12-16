package days

@Suppress("unused")
class Day01 : Day(1) {

    override fun partOne(): Any {
        inputList.forEach {
            val n = it.toInt()
            if (inputList.contains((2020-n).toString()))
                return (2020-n)*n
        }
        return "Not found"
    }

    override fun partTwo(): Any {
        inputList.forEach {
            val n = it.toInt()
            inputList.forEach { it2 ->
                val n2 = it2.toInt()
                if (inputList.contains((2020-n-n2).toString()))
                    return (2020-n-n2)*n*n2
            }
        }
        return "Not found"
    }
}
