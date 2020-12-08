package interactors

import models.Transaction
import data.memory.MemoryDataSource

class TransactionsInteractor(
    private val memory: MemoryDataSource,
) {

    fun getTransactions(): List<Transaction> {
        return memory.getTransactions()
    }

}
