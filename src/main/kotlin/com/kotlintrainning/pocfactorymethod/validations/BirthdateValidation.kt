package com.kotlintrainning.pocfactorymethod.validations

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO
import org.springframework.stereotype.Component
import com.kotlintrainning.pocfactorymethod.model.DataNotFoundMsgEnum.BIRTHDATE_NOT_FOUND

@Component
class BirthdateValidation: UserValidationAbstract() {
    override fun supports(rule: String): Boolean = rule.equals("birthdate", ignoreCase = true)

    override fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean {
        return userDTO.birthdate.isNotBlank()
    }

    override fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Data de nascimento encontrada. ")
    }

    override fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Data de nascimento não encontrada. ")
        validationResponse.dataNotFound.add(BIRTHDATE_NOT_FOUND.msg)
    }

}