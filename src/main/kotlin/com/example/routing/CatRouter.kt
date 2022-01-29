package com.example.routing

import com.example.service.CatApiService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Routing.cat() {
    val catApiService: CatApiService by inject()

    route("/cat") {
        get {
            call.respond(catApiService.getCatRandomData())
        }
    }
}