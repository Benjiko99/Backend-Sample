package interactors

import models.Transaction
import data.memory.MemoryDataSource
import models.TransactionCategory

class TransactionsInteractor(
    private val memory: MemoryDataSource,
) {

    fun getTransactions(): List<Transaction> {
        return memory.getTransactions()
    }

    fun getTransaction(id: Int): Transaction? {
        return getTransactions().find { it.id == id }
    }

    fun updateTransactionNote(id: Int, note: String): Transaction {
        val transaction = getTransaction(id)!!.copy(noteToSelf = note)
        return updateTransaction(transaction)
    }

    fun updateTransactionCategory(id: Int, category: TransactionCategory): Transaction {
        val transaction = getTransaction(id)!!.copy(category = category)
        return updateTransaction(transaction)
    }

    private fun updateTransaction(transaction: Transaction): Transaction {
        return memory.updateTransaction(transaction)
    }

}
