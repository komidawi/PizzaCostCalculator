package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import java.math.BigDecimal
import java.util.*

/**
 * Domain Pizza object
 */
class Pizza(

    val pizzeria: String?,

    val name: String,

    val size: BigDecimal,

    val price: BigDecimal,

    val ratio: BigDecimal,

    val deliveryCost: BigDecimal = BigDecimal.ZERO,

    val uuid: UUID = UUID.randomUUID(),

    val id: Long? = null

) {
    override fun toString(): String {
        return "Pizza(pizzeria=$pizzeria, name='$name', size=$size, price=$price, ratio=$ratio, " +
                "deliveryCost=$deliveryCost, uuid=$uuid, id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pizza

        if (pizzeria != other.pizzeria) return false
        if (name != other.name) return false
        if (size != other.size) return false
        if (price != other.price) return false
        if (ratio != other.ratio) return false
        if (deliveryCost != other.deliveryCost) return false
        if (uuid != other.uuid) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pizzeria?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + ratio.hashCode()
        result = 31 * result + deliveryCost.hashCode()
        result = 31 * result + uuid.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }
}

fun Pizza.asDatabaseModel(): PizzaEntity =
    PizzaEntity(
        pizzeria = pizzeria,
        name = name,
        size = size.toString(),
        price = price.toString(),
        ratio = ratio.toString(),
        deliveryCost = deliveryCost.toString(),
        uuid = uuid.toString(),
        id = id
    )

fun List<Pizza>.asDatabaseModel(): List<PizzaEntity> = map(Pizza::asDatabaseModel)