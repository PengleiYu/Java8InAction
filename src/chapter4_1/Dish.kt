package chapter4_1

import java.util.*

/**
 * Created by yupenglei on 17/7/3.
 */
class Dish(val name: String, val vegetarian: Boolean, val calories: Int, val type: Type) {
    companion object {
        val list: List<Dish> = Arrays.asList(
                Dish("pork", false, 800, Type.MEAT),
                Dish("beef", false, 700, Type.MEAT),
                Dish("chicken", false, 400, Type.MEAT),
                Dish("french fries", true, 530, Type.OTHER),
                Dish("rice", true, 350, Type.OTHER),
                Dish("season fruit", true, 120, Type.OTHER),
                Dish("pizza", true, 550, Type.OTHER),
                Dish("prawns", false, 300, Type.FISH),
                Dish("salmon", false, 450, Type.FISH)
        )
    }


    enum class Type { MEAT, FISH, OTHER }

    override fun toString(): String {
        return name
    }


}