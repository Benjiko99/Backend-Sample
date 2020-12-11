package policy

import models.Money
import java.math.BigDecimal

private val mathContext = App.mathContext

fun Money.add(augend: BigDecimal) = copy(amount = amount.add(augend, mathContext))

fun Money.subtract(subtrahend: BigDecimal) = copy(amount = amount.subtract(subtrahend, mathContext))

fun Money.multiply(multiplicand: BigDecimal) = copy(amount = amount.multiply(multiplicand, mathContext))

fun Money.divide(divisor: BigDecimal) = copy(amount = amount.divide(divisor, mathContext))

fun Money.negate() = copy(amount = amount.negate(mathContext))

/**
 * Compares the effective amounts, ignoring things like extra zeros,
 * which regular equals() would take into account.
 *
 * @return true when comparing 1.0 against 1.00
 */
fun Money.amountEquals(amount: BigDecimal): Boolean {
    return this.amount.compareTo(amount) == 0
}

fun Money.amountEquals(other: Money): Boolean {
    return this.amountEquals(other.amount)
}