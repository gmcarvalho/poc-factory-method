package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.repositories.findUser
import org.springframework.stereotype.Service

@Service
class ValidationService(
    private val validationConfigService: ValidationConfigService,
    private val businessProfileValidationService: BusinessProfileValidationService,
    private val businessProfileService: BusinessProfileService) {

    fun process(ucode: String, businessProfile: String): ValidationResponse {
        val user = findUser(ucode) //todo busca os dados no banco

        val businessProfile1 = businessProfileService.findByName(businessProfile)

        val validationClassIdentifier = businessProfileValidationService.findValidationByBusinessProfile(businessProfile)
        val validationClasses = validationConfigService.loadValidationConfigForProfile(businessProfile, validationClassIdentifier)

        val validationResponse = ValidationResponse()

        for (validation in validationClasses) {
            validation.verify(user, validationResponse)
        }

        validationResponse.validationStatus = "CONCLUIDO"
        return validationResponse
    }
}