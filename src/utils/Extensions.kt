package utils

import io.ktor.request.*

val ApplicationRequest.language: String
    get() = queryParameters["lang"].takeIf { it in App.languages } ?: App.defaultLanguage