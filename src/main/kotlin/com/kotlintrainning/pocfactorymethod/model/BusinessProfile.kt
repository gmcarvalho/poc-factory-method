package com.kotlintrainning.pocfactorymethod.model

data class BusinessProfile(
    val name: String = "",
    val rules: MutableList<String> = mutableListOf()
)
