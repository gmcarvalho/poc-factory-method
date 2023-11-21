package com.kotlintrainning.pocfactorymethod.interfaces

import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.model.dto.UserDTO

abstract class UserValidationAbstract {

    open fun verify(userDTO: UserDTO, validationResponse: ValidationResponse) {
        if (validate(userDTO, validationResponse))
            onSuccess(userDTO, validationResponse)
        else onFailure(userDTO, validationResponse)
    }

    protected abstract fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean

    protected abstract fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse)

    protected abstract fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse)
}