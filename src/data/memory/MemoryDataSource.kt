package data.memory

import App
import data.memory.exceptions.EntityNotFoundException
import models.*
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*

class MemoryDataSource {

    private val transactions = mutableListOf(
        Transaction(
            1,
            "Paypal *Epic Games",
            ZonedDateTime.parse("2020-07-15T20:45:00+00:00"),
            Money(BigDecimal(59.99, App.mathContext), Currency.getInstance("EUR")),
            TransferDirection.OUTGOING,
            TransactionCategory.ENTERTAINMENT,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            listOf(
                "https://i.imgur.com/pSgwleG.jpg",
                "https://i.imgur.com/6eyO3O4.jpg"
            ),
            "MASTERCARD **4620",
            "Red Dead Redemption 2",
        ),
        Transaction(
            2,
            "Paypal *Epic Games",
            ZonedDateTime.parse("2020-07-15T20:41:00+00:00"),
            Money(BigDecimal(59.99, App.mathContext), Currency.getInstance("EUR")),
            TransferDirection.OUTGOING,
            TransactionCategory.ENTERTAINMENT,
            TransactionStatus.DECLINED,
            TransactionStatusCode.SPENDING_LIMIT_EXCEEDED,
            emptyList(),
            "MASTERCARD **4620",
            "Red Dead Redemption 2",
        ),
        Transaction(
            3,
            "Payment from Dean-Charles Chapman",
            ZonedDateTime.parse("2020-07-15T10:00:00+00:00"),
            Money(BigDecimal(100, App.mathContext), Currency.getInstance("EUR")),
            TransferDirection.INCOMING,
            TransactionCategory.TRANSFERS,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
        ),
        Transaction(
            4,
            "Take-out Burger",
            ZonedDateTime.parse("2020-07-15T09:30:56+00:00"),
            Money(BigDecimal(5.49, App.mathContext), Currency.getInstance("USD")),
            TransferDirection.OUTGOING,
            TransactionCategory.RESTAURANTS,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            emptyList(),
            "VISA **9400",
        ),
        Transaction(
            5,
            "PlayStation Store",
            ZonedDateTime.parse("2020-06-19T09:00:00+00:00"),
            Money(BigDecimal(1699, App.mathContext), Currency.getInstance("CZK")),
            TransferDirection.OUTGOING,
            TransactionCategory.ENTERTAINMENT,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            listOf(
                "https://i.imgur.com/v3EKlmM.jpg",
                "https://i.imgur.com/QXffOBF.jpg",
                "https://i.imgur.com/CJGOGl7.jpg"
            ),
            "VISA **9400",
            "The Last of Us Part 2"
        ),
        Transaction(
            6,
            "Rent Payment",
            ZonedDateTime.parse("2020-05-28T12:32:56+00:00"),
            Money(BigDecimal(400, App.mathContext), Currency.getInstance("EUR")),
            TransferDirection.OUTGOING,
            TransactionCategory.TRANSFERS,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            emptyList(),
            "VISA **9400",
        ),
        Transaction(
            7,
            "Birthday Gift",
            ZonedDateTime.parse("2020-05-25T15:42:56+00:00"),
            Money(BigDecimal(80, App.mathContext), Currency.getInstance("USD")),
            TransferDirection.INCOMING,
            TransactionCategory.TRANSFERS,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            emptyList(),
        ),
        Transaction(
            8,
            "Groceries",
            ZonedDateTime.parse("2020-05-25T12:33:24+00:00"),
            Money(BigDecimal(15.49, App.mathContext), Currency.getInstance("EUR")),
            TransferDirection.OUTGOING,
            TransactionCategory.GROCERIES,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            emptyList(),
            "VISA **9400",
        ),
        Transaction(
            9,
            "Groceries",
            ZonedDateTime.parse("2020-05-24T14:24:56+00:00"),
            Money(BigDecimal(17.49, App.mathContext), Currency.getInstance("EUR")),
            TransferDirection.OUTGOING,
            TransactionCategory.GROCERIES,
            TransactionStatus.COMPLETED,
            TransactionStatusCode.SUCCESSFUL,
            emptyList(),
            "VISA **9400",
        ),
    )

    private val conversionRates = mutableMapOf<Currency, ConversionRates>(
        Currency.getInstance("EUR") to ConversionRates(
            baseCurrency = Currency.getInstance("EUR"),
            validityDate = ZonedDateTime.now(),
            rates = listOf(
                ConversionRate("USD", 1.14f),
                ConversionRate("CZK", 26.69f),
            )
        ),
        Currency.getInstance("USD") to ConversionRates(
            baseCurrency = Currency.getInstance("USD"),
            validityDate = ZonedDateTime.now(),
            rates = listOf(
                ConversionRate("EUR", 0.87f),
                ConversionRate("CZK", 23.34f),
            )
        ),
        Currency.getInstance("CZK") to ConversionRates(
            baseCurrency = Currency.getInstance("CZK"),
            validityDate = ZonedDateTime.now(),
            rates = listOf(
                ConversionRate("EUR", 0.0375f),
                ConversionRate("USD", 0.0428f),
            )
        )
    )

    fun getTransactions(): List<Transaction> {
        return transactions
    }

    fun getTransaction(id: Int): Transaction {
        return transactions.find { it.id == id }
            ?: throw EntityNotFoundException(id)
    }

    fun getConversionRates(baseCurrency: Currency): ConversionRates? {
        return conversionRates[baseCurrency]
    }

    fun updateTransaction(transaction: Transaction): Transaction {
        transactions.find { it.id == transaction.id }!!.let { found ->
            transactions.indexOf(found).let { index ->
                transactions.set(index, transaction)
            }
        }
        return transaction
    }
}
