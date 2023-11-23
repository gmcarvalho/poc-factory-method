package com.kotlintrainning.pocfactorymethod.repositories

import com.kotlintrainning.pocfactorymethod.model.entities.BusinessProfileValidation
import org.springframework.data.mongodb.repository.MongoRepository

interface BusinessProfileValidationRepository: MongoRepository<BusinessProfileValidation, Long> {
    fun findByBusinessProfile(businessProfile: String): BusinessProfileValidation
}