package models

import java.time.ZonedDateTime
import java.util.*

data class ConversionRates(
    val baseCurrency: Currency,
    val validityDate: ZonedDateTime,
    val rates: List<ConversionRate>
)
