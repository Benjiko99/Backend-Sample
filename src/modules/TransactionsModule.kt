package modules

import interactors.TransactionsInteractor
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.transactionsModule() {
    val interactor by inject<TransactionsInteractor>()

    get("/transactions") {
        try {
            val transactions = interactor.getTransactions()
            call.respond(mapOf("items" to transactions))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}