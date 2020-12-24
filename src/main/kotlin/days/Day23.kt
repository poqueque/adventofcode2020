package days

@Suppress("unused")
class Day23 : Day(23) {

    override fun partOne(): Any {
        val cups = mutableListOf<Int>()
        val cmap = mutableMapOf<Int,Int>()
        val first = inputString[0].toString().toInt()
        var last = 0
        inputString.forEach {
            cups.add(it.toString().toInt())
            if (last!=0)
                cmap[last]=it.toString().toInt()
            last = it.toString().toInt()
        }
        cmap[last]=first
        val s = cups.size
        var pointer = 0
        var pointerMap = first
        repeat(100) {
            //List
            val current = cups[pointer]
            var destination = cups[pointer] - 1
            if (destination == 0) destination = s
            val r1 = cups[(pointer + 1) % s]
            val r2 = cups[(pointer + 2) % s]
            val r3 = cups[(pointer + 3) % s]
            cups.remove(r1)
            cups.remove(r2)
            cups.remove(r3)

            while (destination == r1 || destination == r2 || destination == r3) {
                destination--
                if (destination == 0) destination = s
            }
            val destinationPointer = (cups.indexOf(destination) + 1) % s
            cups.addAll(destinationPointer, setOf(r1, r2, r3))
            pointer = (cups.indexOf(current) + 1) % s

            //Map
            val k1=cmap[pointerMap]!!
            val k2=cmap[k1]!!
            val k3=cmap[k2]!!
            var destinationM = pointerMap - 1
            if (destinationM == 0) destinationM = s
            while (destinationM == k1 || destinationM == k2 || destinationM == k3) {
                destinationM--
                if (destinationM == 0) destinationM = s
            }
            val temp = cmap[destinationM]!!
            cmap[pointerMap]=cmap[k3]!!
            cmap[destinationM]=k1
            cmap[k3]=temp
            pointerMap = cmap[pointerMap]!!
        }
        val pos1 = cups.indexOf(1)
//        println(cups.subList(pos1 + 1, s).joinToString("") + cups.subList(0, pos1).joinToString(""))
        var strRes = ""
        var k = cmap[1]
        while (k != 1) {
            strRes += k
            k = cmap[k]
        }
        return strRes
    }

    override fun partTwo(): Any {
        val cups = mutableListOf<Int>()
        val cmap = mutableMapOf<Int,Int>()
        val first = inputString[0].toString().toInt()
        var last = 0
        inputString.forEach {
            cups.add(it.toString().toInt())
            if (last!=0)
                cmap[last]=it.toString().toInt()
            last = it.toString().toInt()
        }
        for (i in 10..1000000){
            cups.add(i)
            cmap[last]=i
            last = i
        }
        cmap[last]=first
        val s = cups.size
        var pointerMap = first
        repeat(10000000) {
            //Map
            val k1=cmap[pointerMap]!!
            val k2=cmap[k1]!!
            val k3=cmap[k2]!!
            var destinationM = pointerMap - 1
            if (destinationM == 0) destinationM = s
            while (destinationM == k1 || destinationM == k2 || destinationM == k3) {
                destinationM--
                if (destinationM == 0) destinationM = s
            }
            val temp = cmap[destinationM]!!
            cmap[pointerMap]=cmap[k3]!!
            cmap[destinationM]=k1
            cmap[k3]=temp
            pointerMap = cmap[pointerMap]!!
        }
        val k1 = cmap[1]!!
        val k2 = cmap[k1]!!.toLong()
        return (k1*k2)
    }
}
