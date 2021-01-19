package com.github.komidawi.pizzacostcalculator.screen.list

import android.content.Context
import android.content.res.Resources

// Please remember that resources in strings.xml
// must have the same names as following enum values
enum class SortingMode {
    RATIO_ASCENDING,
    RATIO_DESCENDING,
    PIZZERIA_ASCENDING,
    PIZZERIA_DESCENDING;

    /**
     * Returns a localized label used to represent this enumeration value.  If no label
     * has been defined, then this defaults to the result of [Enum.name].
     *
     * The name of the string resource for the label must match the name of the enumeration
     * value.  For example, for enum value 'ENUM1' the resource would be defined as 'R.string.ENUM1'.
     *
     * @param context   the context that the string resource of the label is in.
     * @return      a localized label for the enum value or the result of name()
     */
    open fun getStringResource(context: Context): String {
        val res: Resources = context.resources
        val resId: Int = res.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) res.getString(resId) else name
    }
}