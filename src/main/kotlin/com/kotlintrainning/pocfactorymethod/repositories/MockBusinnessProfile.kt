package com.kotlintrainning.pocfactorymethod.repositories

import com.kotlintrainning.pocfactorymethod.model.BusinessProfile

fun findRulesByBusinessProfile(businessProfile: String): BusinessProfile {
    return when(businessProfile){
        "BasicCadastralBusinessProfile" -> BusinessProfile("CheckoutBusinessProfile", mutableListOf("name", "cpf", "cnpj", "birthdate"))
        "HotCardBusinessProfile" -> BusinessProfile("HotCardBusinessProfile", mutableListOf("name", "bankAccount"))
        else -> throw Exception("Grupo de perfil não encontrado")
    }
}