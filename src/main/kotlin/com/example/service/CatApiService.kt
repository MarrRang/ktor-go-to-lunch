package com.example.service

import org.koin.java.KoinJavaComponent.getKoin
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class CatApiService(private val httpClient: HttpClient) {
    private val baseUrl = getKoin().getProperty<String>("api.cat.host").toString()

    fun getCatRandomData(): String {
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create("$baseUrl/v1/images/search"))
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }
}