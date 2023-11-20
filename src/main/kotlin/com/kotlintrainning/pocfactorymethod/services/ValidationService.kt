package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.factory.UserValidationFactory
import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.repositories.findUser
import org.springframework.stereotype.Service

@Service
class ValidationService(
    private val businessProfileService: BusinessProfileService,
    private val userValidationFactory: UserValidationFactory) {

    fun process(ucode: String, businessProfile: String): ValidationResponse {
        val user = findUser(ucode)
        val rulesToBeValidated = businessProfileService.getRulesByBusinessProfile(businessProfile)
        val validations = userValidationFactory.getTheValidatorClasses(rulesToBeValidated)
        val validationResponse = ValidationResponse()

        for (validation in validations) {
            validation.verify(user, validationResponse)
        }

        validationResponse.validationStatus = "CONCLUIDO"
        return validationResponse
    }
}