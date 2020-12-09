import adapters.ZonedDateTimeAdapter
import di.dataModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*
import modules.api.conversionRatesModule
import modules.api.transactionsModule
import org.koin.ktor.ext.Koin
import org.slf4j.event.Level
import java.math.MathContext
import java.time.ZonedDateTime

object App {
    const val defaultLanguage = "en"
    val languages = listOf("en", "cs")

    /** Keep the MathContext consistent across the application */
    val mathContext = MathContext.DECIMAL32!!
}

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module(testing: Boolean = false) {

    install(CallLogging) {
        level = Level.DEBUG
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeAdapter())
        }
    }

    install(Koin) {
        modules(dataModule)
    }

    install(StatusPages) {
        exception<UnknownError> {
            call.respondText(
                "Internal server error",
                ContentType.Text.Plain,
                status = HttpStatusCode.InternalServerError
            )
        }
        exception<IllegalArgumentException> {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/api/") {
            transactionsModule()
            conversionRatesModule()
        }
    }

}

/*fun Routing.v1() {
    get("/v1/hello") {
        call.respondText("Hello from v1 API.")
    }
}*/

/*fun Route.news(
    getNewsInteractor: GetNewsInteractor = GetNewsInteractor()
) = route("/news") {

    get {
        val lang = call.request.language

        val request = GetNewsInteractor.Request(lang)
        when (val response = getNewsInteractor.execute(request)) {
            is GetNewsInteractor.Response.Success -> call.respond(HttpStatusCode.OK, response.news)
            is GetNewsInteractor.Response.Failure -> call.respond(HttpStatusCode.NotFound)
        }
    }
}*/
