
import util.InputReader
import util.Parser
import java.util.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun main() {
    println("Advent of Code 2020")
    //https://adventofcode.com/2020/leaderboard/private/view/637864.json
    val leaderboard = Parser.parse(InputReader.getLeaderboard())
    if (leaderboard != null){
        val year = leaderboard.event.toInt()
        println ("Leaderboard $year")
        (1..25).forEach{ day ->
            val clasif = mutableMapOf<String,Long>()
            leaderboard.members.values.forEach {
                if (it.completion_day_level.containsKey(day.toString())) {
                    val name = it.name ?: "<Unknown>"
                    val value = it.completion_day_level[day.toString()]?.`2`?.get_star_ts?.toLong() ?: 0L
                    if (value != 0L) clasif[name] = value
                }
            }
            val sortedClasif = clasif.toList().sortedBy { (_, value) -> value}.toMap()
            println("Day $day")
            val calendar = GregorianCalendar()
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,11)
            calendar.set(Calendar.DAY_OF_MONTH,day)
            calendar.set(Calendar.HOUR,6)
            calendar.set(Calendar.MINUTE,0)
            val millis = calendar.timeInMillis
            sortedClasif.entries.forEach {
                val spent = it.value - millis/1000
                println("${it.key} - ${humanReadable(spent)}")
            }
            println ("")
        }
    }
}

fun humanReadable(spent: Long): String {
    var sec = spent
    var min = spent/60
    sec -= min * 60
    val hour = min/60
    min -= hour * 60
    var retVal = ""
    if (hour > 0) retVal += "$hour:"
    retVal += "${min.toString().padStart(2,'0')}:"
    retVal += sec.toString().padStart(2,'0')
    return retVal
}
