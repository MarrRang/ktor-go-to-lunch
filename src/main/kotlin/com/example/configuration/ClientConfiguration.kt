package com.example.configuration

import org.koin.dsl.module
import java.net.http.HttpClient

val clientModule = module {
    single<HttpClient> { HttpClient.newHttpClient() }
}