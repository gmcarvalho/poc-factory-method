package com.kotlintrainning.pocfactorymethod.repositories

import com.kotlintrainning.pocfactorymethod.model.entities.ClassIdentifier
import org.springframework.data.mongodb.repository.MongoRepository

interface ClassIdentifierRepository: MongoRepository<ClassIdentifier, Long> {

    fun findByName(name: String): ClassIdentifier

}