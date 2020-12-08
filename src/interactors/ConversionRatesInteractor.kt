package interactors

import data.memory.MemoryDataSource
import models.ConversionRates
import java.util.*

class ConversionRatesInteractor(
    private val memory: MemoryDataSource,
) {

    fun getConversionRates(baseCurrency: String): ConversionRates? {
        return memory.getConversionRates(Currency.getInstance(baseCurrency))
    }

}
