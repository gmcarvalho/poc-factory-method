package com.kotlintrainning.pocfactorymethod.model

enum class DataNotFoundMsgEnum(val msg: String) {
    CPF_NOT_FOUND(msg = "Cpf não encontrado"),
    CNPJ_NOT_FOUND(msg = "Cnpj não encontrado"),
    BIRTHDATE_NOT_FOUND(msg = "Data de nascimento não encontrado"),
    BANK_ACCOUNT_NOT_FOUND(msg = "Conta bancária não encontrado"),
    NAME_NOT_FOUND(msg = "Nome não encontrado")
}