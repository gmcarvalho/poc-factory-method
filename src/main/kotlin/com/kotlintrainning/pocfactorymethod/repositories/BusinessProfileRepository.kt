package com.kotlintrainning.pocfactorymethod.repositories

import com.kotlintrainning.pocfactorymethod.model.entities.BusinessProfile
import org.springframework.data.mongodb.repository.MongoRepository

interface BusinessProfileRepository: MongoRepository<BusinessProfile, Long> {

    fun findByName(name: String): BusinessProfile?

}