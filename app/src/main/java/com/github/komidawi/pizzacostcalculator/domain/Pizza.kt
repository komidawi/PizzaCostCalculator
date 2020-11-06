package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.network.PizzaDto
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pizza

        if (name != other.name) return false
        if (size != other.size) return false
        if (price != other.price) return false
        if (ratio != other.ratio) return false
        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + ratio.hashCode()
        result = 31 * result + uuid.hashCode()
        return result
    }
}


fun Pizza.asDatabaseModel(): PizzaEntity =
    PizzaEntity(name, size.toString(), price.toString(), ratio.toString(), uuid.toString(), id)

fun List<Pizza>.asDatabaseModel(): List<PizzaEntity> = map(Pizza::asDatabaseModel)


fun Pizza.asNetworkModel(): PizzaDto =
    PizzaDto(name, size.toString(), price.toString(), ratio.toString(), uuid.toString(), id)

fun List<Pizza>.asNetworkModel(): List<PizzaDto> = map(Pizza::asNetworkModel)