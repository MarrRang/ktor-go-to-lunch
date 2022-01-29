package com.example.module

import com.example.service.CatApiService
import org.koin.dsl.module

val catModule = module {
    single { CatApiService(get()) }
}