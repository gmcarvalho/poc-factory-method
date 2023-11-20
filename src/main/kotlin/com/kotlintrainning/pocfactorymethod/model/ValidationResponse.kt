package com.kotlintrainning.pocfactorymethod.model

import java.time.LocalDateTime

data class ValidationResponse(
    var validationStatus: String = "NAO_CONCLUIDO",
    var createDate: LocalDateTime = LocalDateTime.now(),
    var dataNotFound: MutableList<String> = mutableListOf()
)