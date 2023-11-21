package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.repositories.findUser
import org.springframework.stereotype.Service

@Service
class ValidationService(private val validationConfigService: ValidationConfigService) {

    fun process(ucode: String, businessProfile: String): ValidationResponse {
        val user = findUser(ucode)
        val validationBeans = validationConfigService.loadValidationConfigForProfile(businessProfile)

        val validationResponse = ValidationResponse()

        for (validation in validationBeans) {
            validation.verify(user, validationResponse)
        }

        validationResponse.validationStatus = "CONCLUIDO"
        return validationResponse
    }
}