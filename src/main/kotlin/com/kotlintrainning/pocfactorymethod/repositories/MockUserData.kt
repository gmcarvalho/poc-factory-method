package com.kotlintrainning.pocfactorymethod.repositories

import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO

fun findUser(userId: String): UserDTO{
    return when(userId){
        "1" -> UserDTO(userId = 1L, name = "João", cpf = "11122233399", cnpj = "11111111111111", birthdate = "10-07-1993")
        "2" -> UserDTO(userId = 1L, name = "Maria", cpf = "", cnpj = "11111111111111", birthdate = "10-07-1993")
        "3" -> UserDTO(userId = 1L, name = "Irmão do João", cpf = "11122233399", cnpj = "", birthdate = "10-07-1993")
        "4" -> UserDTO(userId = 1L, name = "Irmã da Maria", cpf = "", cnpj = "", birthdate = "")
        "5" -> UserDTO(userId = 1L, name = "Cunhada da Maria", bankAccounts = "122349")
        "6" -> UserDTO(userId = 1L, name = "Cunhada do João", bankAccounts = "")
        else -> throw ClassNotFoundException("User not found")
    }
}