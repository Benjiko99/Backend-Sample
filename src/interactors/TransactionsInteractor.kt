package interactors

import data.memory.MemoryDataSource
import models.Transaction
import models.TransactionCategory

class TransactionsInteractor(
    private val memory: MemoryDataSource,
) {

    fun getTransactions(): List<Transaction> {
        return memory.getTransactions()
    }

    fun getTransaction(id: String): Transaction {
        return memory.getTransaction(id)
    }

    fun patchTransactionNote(id: String, note: String): Transaction {
        val noteOrNull = if (note.isNotBlank()) note else null
        val patched = getTransaction(id).copy(noteToSelf = noteOrNull)
        return putTransaction(patched)
    }

    fun patchTransactionCategory(id: String, category: TransactionCategory): Transaction {
        val patched = getTransaction(id).copy(category = category)
        return putTransaction(patched)
    }

    private fun putTransaction(transaction: Transaction): Transaction {
        return memory.updateTransaction(transaction)
    }

}
