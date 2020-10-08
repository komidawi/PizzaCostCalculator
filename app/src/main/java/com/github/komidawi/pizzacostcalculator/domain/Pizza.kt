package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import java.math.BigDecimal
import java.util.*

/**
 * Domain Pizza object
 */
class Pizza(

    val name: String,

    val size: BigDecimal,

    val price: BigDecimal,

    val ratio: BigDecimal,

    val uuid: UUID = UUID.randomUUID(),

    val id: Long? = null

) {
    override fun toString(): String {
        return "Pizza(name='$name', size=$size, price=$price, ratio=$ratio, uuid=$uuid, id=$id)"
    }
}

fun Pizza.asDatabaseModel(): PizzaEntity =
    PizzaEntity(name, size.toString(), price.toString(), ratio.toString(), uuid.toString(), id)

fun List<Pizza>.asDatabaseModel(): List<PizzaEntity> = map(Pizza::asDatabaseModel)