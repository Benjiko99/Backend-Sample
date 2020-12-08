package models

import java.util.*

data class ConversionRate(
    val currency: Currency,
    val rate: Float
) {

    constructor(currencyCode: String, rate: Float) : this(Currency.getInstance(currencyCode), rate)

}