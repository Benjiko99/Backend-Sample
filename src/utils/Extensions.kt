package utils

import App
import io.ktor.request.*

val ApplicationRequest.language: String
    get() = queryParameters["lang"].takeIf { it in App.languages } ?: App.defaultLanguage

val <T : Enum<*>> Array<T>.allNames
    get() = map { it.name }
