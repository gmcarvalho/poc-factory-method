package com.kotlintrainning.pocfactorymethod.model.dto

data class UserDTO(
    val userId: Long = 0L,
    val name: String = "",
    val cpf: String = "",
    val cnpj: String = "",
    val birthdate: String = "",
    val bankAccounts: String = ""
)