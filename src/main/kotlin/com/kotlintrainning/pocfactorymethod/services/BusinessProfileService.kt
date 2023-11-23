package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.model.entities.BusinessProfile
import com.kotlintrainning.pocfactorymethod.repositories.BusinessProfileRepository
import org.springframework.stereotype.Service

@Service
class BusinessProfileService(private val businessProfileRepository: BusinessProfileRepository) {

    fun save(businessProfile: BusinessProfile): BusinessProfile {
        return businessProfileRepository.save(businessProfile)
    }

    fun findByName(name: String):BusinessProfile{
        return businessProfileRepository.findByName(name)
    }

    fun findAll(): MutableList<BusinessProfile>{
        return businessProfileRepository.findAll()
    }
}