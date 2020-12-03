package days

import util.Coor
import util.CoorMap
import util.InputReader

abstract class Day(private val dayNumber: Int) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputMap: CoorMap by lazy { InputReader.getInputAsMap(dayNumber) }
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any

    fun savePuzzleInput() { InputReader.savePuzzleInput(dayNumber) }

}
