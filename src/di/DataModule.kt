package di

import data.memory.MemoryDataSource
import interactors.ConversionRatesInteractor
import interactors.TransactionsInteractor
import org.koin.dsl.module

val dataModule = module {
    single { MemoryDataSource() }

    single { TransactionsInteractor(get()) }
    single { ConversionRatesInteractor(get()) }
}