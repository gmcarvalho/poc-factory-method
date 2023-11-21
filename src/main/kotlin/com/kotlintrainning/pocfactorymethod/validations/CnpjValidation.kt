package com.kotlintrainning.pocfactorymethod.validations

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO
import org.springframework.stereotype.Component
import com.kotlintrainning.pocfactorymethod.model.DataNotFoundMsgEnum.CNPJ_NOT_FOUND


@Component
@ValidationIdentifier("cnpj-validation")
class CnpjValidation: UserValidationAbstract() {

    override fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean {
        return userDTO.cnpj.isNotBlank()
    }

    override fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Cnpj encontrado. ")
    }

    override fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Cnpj não encontrado. ")
        validationResponse.dataNotFound.add(CNPJ_NOT_FOUND.msg)
    }
}