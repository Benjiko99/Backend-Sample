package modules.api

import data.memory.exceptions.EntityNotFoundException
import interactors.TransactionsInteractor
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import models.TransactionCategory
import org.koin.ktor.ext.inject
import utils.allNames

fun Route.transactionsModule() {
    val interactor by inject<TransactionsInteractor>()

    route("transactions/") {
        get {
            try {
                call.respond(mapOf("items" to interactor.getTransactions()))
            } catch (e: Exception) {
                application.log.error(e)
                call.respond(InternalServerError, e.toString())
            }
        }

        patch("{id}/note") {
            try {
                val id = call.parameters["id"]!!.toInt()
                val note = call.receiveText().removeSurrounding("\"")
                val transaction = interactor.patchTransactionNote(id, note)
                call.respond(transaction)
            } catch (e: EntityNotFoundException) {
                application.log.info(e.message)
                call.respond(BadRequest, e.toString())
            } catch (e: Exception) {
                application.log.error(e)
                call.respond(InternalServerError, e.toString())
            }
        }

        patch("{id}/category") {
            try {
                val id = call.parameters["id"]!!.toInt()
                val category = call.receive<TransactionCategory>()
                val transaction = interactor.patchTransactionCategory(id, category)
                call.respond(transaction)
            } catch (e: ContentTransformationException) {
                application.log.info(e.message)
                call.respond(BadRequest, "Category has to be one of: ${TransactionCategory.values().allNames}")
            } catch (e: EntityNotFoundException) {
                application.log.info(e.message)
                call.respond(BadRequest, e.toString())
            } catch (e: Exception) {
                application.log.error(e)
                call.respond(InternalServerError, e.toString())
            }
        }
    }
}
