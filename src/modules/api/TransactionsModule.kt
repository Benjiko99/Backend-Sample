package modules.api

import data.memory.exceptions.EntityNotFoundException
import interactors.TransactionsInteractor
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.TransactionCategory
import org.koin.ktor.ext.inject
import utils.allNames
import utils.respondBadRequest
import utils.respondServerError

fun Route.transactionsModule() {
    val interactor by inject<TransactionsInteractor>()

    route("transactions/") {
        get {
            try {
                call.respond(mapOf("items" to interactor.getTransactions()))
            } catch (e: Exception) {
                call.respondServerError(e)
            }
        }

        patch("{id}/note") {
            try {
                val id = call.parameters["id"]!!
                val note = call.receiveText().removeSurrounding("\"")
                val transaction = interactor.patchTransactionNote(id, note)
                call.respond(transaction)
            } catch (e: EntityNotFoundException) {
                call.respondBadRequest(e)
            } catch (e: Exception) {
                call.respondServerError(e)
            }
        }

        patch("{id}/category") {
            try {
                val id = call.parameters["id"]!!
                val category = call.receive<TransactionCategory>()
                val transaction = interactor.patchTransactionCategory(id, category)
                call.respond(transaction)
            } catch (e: ContentTransformationException) {
                call.respondBadRequest(e, "Category has to be one of: ${TransactionCategory.values().allNames}")
            } catch (e: EntityNotFoundException) {
                call.respondBadRequest(e)
            } catch (e: Exception) {
                call.respondServerError(e)
            }
        }
    }
}
