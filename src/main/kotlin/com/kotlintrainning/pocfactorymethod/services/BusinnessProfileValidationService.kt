package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.repositories.BusinessProfileValidationRepository
import org.springframework.stereotype.Service

@Service
class BusinessProfileValidationService(private val businessProfileValidationRepository: BusinessProfileValidationRepository) {
    fun findValidationByBusinessProfile(businessProfile: String): String{
        return businessProfileValidationRepository.findByBusinessProfile(businessProfile).validationClassIdentifier
    }
}