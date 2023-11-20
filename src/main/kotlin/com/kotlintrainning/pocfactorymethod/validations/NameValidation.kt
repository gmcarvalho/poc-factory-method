package com.kotlintrainning.pocfactorymethod.validations

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO
import org.springframework.stereotype.Component
import com.kotlintrainning.pocfactorymethod.model.DataNotFoundMsgEnum.NAME_NOT_FOUND

@Component
class NameValidation: UserValidationAbstract() {
    override fun supports(rule: String): Boolean = rule.equals("name", ignoreCase = true)

    override fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean {
        return userDTO.name.isNotBlank()
    }

    override fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Nome encontrado")
    }

    override fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Nome não foi encontrado")
        validationResponse.dataNotFound.add(NAME_NOT_FOUND.msg)
    }
}