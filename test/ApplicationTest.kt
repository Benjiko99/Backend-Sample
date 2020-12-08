package com.spiraclesoftware

import io.ktor.http.*
import io.ktor.server.testing.*
import module
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    @Ignore("Cannot verify response until mocking is implemented")
    fun testConversionRates() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/conversion_rates?base=EUR").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                assertEquals("""
                    {
                      "baseCurrency": "EUR",
                      "validityDate": "2020-07-17T12:00:00+00:00",
                      "rates": {
                        "USD": 1.14,
                        "CZK": 26.69
                      }
                    }
                """.trimIndent(), response.content)
            }
        }
    }

}
