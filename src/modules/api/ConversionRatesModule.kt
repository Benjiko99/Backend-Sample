package modules.api

import interactors.ConversionRatesInteractor
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.conversionRatesModule() {
    val interactor by inject<ConversionRatesInteractor>()

    get("conversion-rates") {
        val baseCurrency = call.request.queryParameters["baseCurrency"]

        if (baseCurrency == null) {
            call.respond(BadRequest, "Parameter \"baseCurrency\" is required.")
            return@get
        }

        try {
            val conversionRates = interactor.getConversionRates(baseCurrency)

            if (conversionRates == null) {
                call.respond(NotFound, "Rates not found for currency: $baseCurrency")
                return@get
            }

            call.respond(conversionRates)
        } catch (e: Exception) {
            call.respond(InternalServerError, e.toString())
        }
    }
}