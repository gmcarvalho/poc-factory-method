package com.kotlintrainning.pocfactorymethod.validations

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO
import org.springframework.stereotype.Component
import com.kotlintrainning.pocfactorymethod.model.DataNotFoundMsgEnum.CPF_NOT_FOUND

@Component
class CPFValidation: UserValidationAbstract() {
    override fun supports(rule: String): Boolean = rule.equals("cpf", ignoreCase = true)

    override fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean {
        return userDTO.cpf.isNotBlank()
    }

    override fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Cpf foi encontrado")
    }

    override fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Cnpj não foi encontrado")
        validationResponse.dataNotFound.add(CPF_NOT_FOUND.msg)
    }
}