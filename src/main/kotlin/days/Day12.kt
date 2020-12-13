package days

import util.Coor
import kotlin.math.abs

class Day12 : Day(12) {

    val dirs = "ESWNESWNESWN"

    override fun partOne(): Any {
        var position= Coor(0,0)
        var heading = 'E'
        inputList.forEach {
            var instruction = it[0]
            val number = it.substring(1).toInt()
            if (instruction == 'F') instruction = heading
            when (instruction){
                'N' -> position = Coor(position.x, position.y+number)
                'S' -> position = Coor(position.x, position.y-number)
                'E' -> position = Coor(position.x+number, position.y)
                'W' -> position = Coor(position.x-number, position.y)
                'R' -> {
                    var posInDirs = dirs.indexOf(heading)
                    posInDirs += number / 90
                    heading = dirs[posInDirs]
                }
                'L' -> {
                    var posInDirs = dirs.indexOf(heading)+4
                    posInDirs -= number / 90
                    heading = dirs[posInDirs]
                }
            }
        }

        return (abs(position.x)+abs(position.y))
    }

    override fun partTwo(): Any {
        var position= Coor(0,0)
        var waypoint= Coor(10,1)
        inputList.forEach {
            val instruction = it[0]
            val number = it.substring(1).toInt()
            when (instruction){
                'N' -> waypoint = Coor(waypoint.x, waypoint.y+number)
                'S' -> waypoint = Coor(waypoint.x, waypoint.y-number)
                'E' -> waypoint = Coor(waypoint.x+number, waypoint.y)
                'W' -> waypoint = Coor(waypoint.x-number, waypoint.y)
                'F' -> position = Coor(position.x+number*waypoint.x, position.y+number*waypoint.y)
                'R' -> {
                    when (number){
                        90 -> waypoint = Coor(waypoint.y, -waypoint.x)
                        180 -> waypoint = Coor(-waypoint.x, -waypoint.y)
                        270 -> waypoint = Coor(-waypoint.y, waypoint.x)
                        else -> println("ERROR: R$number")
                    }
                }
                'L' -> {
                    when (number){
                        270 -> waypoint = Coor(waypoint.y, -waypoint.x)
                        180 -> waypoint = Coor(-waypoint.x, -waypoint.y)
                        90 -> waypoint = Coor(-waypoint.y, waypoint.x)
                        else -> println("ERROR: R$number")
                    }
                }
            }
        }

        return (abs(position.x)+abs(position.y))
    }
}
