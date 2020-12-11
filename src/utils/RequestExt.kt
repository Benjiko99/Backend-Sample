package utils

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*

suspend inline fun ApplicationCall.respondBadRequest(throwable: Throwable, message: Any) {
    application.log.info(throwable.message)
    respond(HttpStatusCode.BadRequest, message)
}

suspend inline fun ApplicationCall.respondBadRequest(throwable: Throwable) {
    application.log.info(throwable.message)
    respond(HttpStatusCode.BadRequest, throwable.toString())
}

suspend inline fun ApplicationCall.respondServerError(throwable: Throwable) {
    application.log.error(throwable)
    respond(HttpStatusCode.InternalServerError, throwable.toString())
}

val ApplicationRequest.language: String
    get() = queryParameters["lang"].takeIf { it in App.languages } ?: App.defaultLanguage
