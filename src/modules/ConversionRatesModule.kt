package modules

import interactors.ConversionRatesInteractor
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.conversionRatesModule() {
    val interactor by inject<ConversionRatesInteractor>()

    get("/conversion_rates") {
        val baseCurrency = call.request.queryParameters["baseCurrency"]

        if (baseCurrency == null) {
            call.respond(HttpStatusCode.BadRequest, "Parameter \"baseCurrency\" is required.")
            return@get
        }

        try {
            val conversionRates = interactor.getConversionRates(baseCurrency)
            call.respond(conversionRates!!)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}