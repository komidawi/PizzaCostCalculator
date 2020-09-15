package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun bigDecimalToString(value: BigDecimal?): String? {
        return value.toString()
    }

    @TypeConverter
    fun stringToBigDecimal(value: String?): BigDecimal? {
        return BigDecimal(value)
    }
}