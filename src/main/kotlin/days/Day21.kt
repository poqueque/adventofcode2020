package days

import util.*

@Suppress("unused")
class Day21 : Day(21) {

    data class Food (
        val ingredients: List<String>,
        val allergens: List<String>
    ) {
        override fun toString(): String {
            return "$ingredients // $allergens"
        }
    }

    var foods = mutableListOf<Food>()

    override fun partOne(): Any {
        val allAllergens = mutableListOf<String>()
        inputList.forEach { line->
            val ingredients = mutableListOf<String>()
            val allergens = mutableListOf<String>()
            val parts = line.splitrim(" ")
            var isAllergen = false
            parts.forEach {
                if (it == "(contains")
                    isAllergen = true
                else {
                    if (isAllergen){
                        val allergen = it.replace(")","").replace(",","")
                        allergens.add(allergen)
                        if (!allAllergens.contains(allergen)) allAllergens.add(allergen)
                    } else {
                        ingredients.add(it)
                    }
                }
            }
            foods.add(Food(ingredients,allergens))
        }
        foods.forEach { food ->
            println(food)
        }

        val canBe = mutableMapOf<String, MutableList<String>>()
        val isForSure = mutableMapOf<String, String>()

        println()
        allAllergens.forEach { allergen ->
            val foodsContainingAllergen = foods.filter { food -> food.allergens.contains(allergen) }.toList()
            var ingredientsIntersect = foodsContainingAllergen[0].ingredients.toList()
            for (i in 1 until foodsContainingAllergen.size){
                ingredientsIntersect = ingredientsIntersect.intersect(foodsContainingAllergen[i].ingredients).toList()
            }
            println("Allergen $allergen can be in $ingredientsIntersect")
            canBe[allergen] = ingredientsIntersect.toMutableList()
        }

        var size = -1
        while (isForSure.size > size) {
            size = isForSure.size
            canBe.keys.forEach { key ->
                if (canBe[key]!!.size == 1) {
                    isForSure[key] = canBe[key]!![0]
                } else {
                    val toRemove = mutableListOf<String>()
                    canBe[key]!!.forEach { ingredient ->
                        if (isForSure.values.contains(ingredient)){
                            toRemove.add(ingredient)
                        }
                    }
                    toRemove.forEach{
                        canBe[key]!!.remove(it)
                    }
                    if (canBe[key]!!.size == 1)
                        isForSure[key] = canBe[key]!![0]
                }
            }
        }

        //Processed
        println()
        isForSure.forEach{
            println("Allergen ${it.key} is in ${it.value}")
        }

        var safe = 0
        foods.forEach { food->
            val ingredients = food.ingredients.toMutableList()
            isForSure.values.forEach{
                ingredients.remove(it)
            }
            println("Safe Ingredients: $ingredients")
            safe += ingredients.size
        }

        return safe
    }

    override fun partTwo(): Any {

        val allAllergens = mutableListOf<String>()
        inputList.forEach { line->
            val ingredients = mutableListOf<String>()
            val allergens = mutableListOf<String>()
            val parts = line.splitrim(" ")
            var isAllergen = false
            parts.forEach {
                if (it == "(contains")
                    isAllergen = true
                else {
                    if (isAllergen){
                        val allergen = it.replace(")","").replace(",","")
                        allergens.add(allergen)
                        if (!allAllergens.contains(allergen)) allAllergens.add(allergen)
                    } else {
                        ingredients.add(it)
                    }
                }
            }
            foods.add(Food(ingredients,allergens))
        }
        foods.forEach { food ->
            println(food)
        }

        val canBe = mutableMapOf<String, MutableList<String>>()
        val isForSure = mutableMapOf<String, String>()

        println()
        allAllergens.forEach { allergen ->
            val foodsContainingAllergen = foods.filter { food -> food.allergens.contains(allergen) }.toList()
            var ingredientsIntersect = foodsContainingAllergen[0].ingredients.toList()
            for (i in 1 until foodsContainingAllergen.size){
                ingredientsIntersect = ingredientsIntersect.intersect(foodsContainingAllergen[i].ingredients).toList()
            }
            println("Allergen $allergen can be in $ingredientsIntersect")
            canBe[allergen] = ingredientsIntersect.toMutableList()
        }

        var size = -1
        while (isForSure.size > size) {
            size = isForSure.size
            canBe.keys.forEach { key ->
                if (canBe[key]!!.size == 1) {
                    isForSure[key] = canBe[key]!![0]
                } else {
                    val toRemove = mutableListOf<String>()
                    canBe[key]!!.forEach { ingredient ->
                        if (isForSure.values.contains(ingredient)){
                            toRemove.add(ingredient)
                        }
                    }
                    toRemove.forEach{
                        canBe[key]!!.remove(it)
                    }
                    if (canBe[key]!!.size == 1)
                        isForSure[key] = canBe[key]!![0]
                }
            }
        }

        //Processed
        println()
        isForSure.forEach{
            println("Allergen ${it.key} is in ${it.value}")
        }

        var safe = 0
        foods.forEach { food->
            val ingredients = food.ingredients.toMutableList()
            isForSure.values.forEach{
                ingredients.remove(it)
            }
            println("Safe Ingredients: $ingredients")
            safe += ingredients.size
        }

        val list = isForSure.keys.toList().sorted()

        return list.map{ isForSure[it] }.joinToString(",")
    }
}
