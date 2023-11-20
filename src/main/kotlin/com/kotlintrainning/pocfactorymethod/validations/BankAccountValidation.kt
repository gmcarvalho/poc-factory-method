package com.kotlintrainning.pocfactorymethod.validations

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.model.DataNotFoundMsgEnum.BANK_ACCOUNT_NOT_FOUND
import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO
import org.springframework.stereotype.Component

@Component
class BankAccountValidation: UserValidationAbstract() {
    override fun supports(rule: String): Boolean = rule.equals("bankAccount", ignoreCase = true)

    override fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean {
        return userDTO.bankAccounts.isNotBlank()
    }

    override fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Conta bancária encontrada. ")
    }

    override fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Conta bancária não foi encontrada. ")
        validationResponse.dataNotFound.add(BANK_ACCOUNT_NOT_FOUND.msg)
    }
}