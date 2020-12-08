package models

import java.time.ZonedDateTime

data class Transaction(
    val id: Int,
    val name: String,
    val processingDate: ZonedDateTime,
    val money: Money,
    val transferDirection: TransferDirection,
    val category: TransactionCategory,
    val status: TransactionStatus,
    val statusCode: TransactionStatusCode,
    val attachments: List<String> = emptyList(),
    val cardDescription: String? = null,
    val noteToSelf: String? = null
)
