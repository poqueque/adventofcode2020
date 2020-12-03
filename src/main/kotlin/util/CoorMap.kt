package util

class CoorMap(inputList: List<String>) {

    val map = mutableMapOf<Coor,Char>()
    var maxX = 0
    var maxY = 0

    init {
        map.clear()
        maxX = inputList[0].length
        maxY = inputList.size
        for (y in inputList.indices) {
            val line = inputList[y].toList()
            for (x in line.indices) {
                map[Coor(x, y)] = line[x]
            }
        }
    }

}