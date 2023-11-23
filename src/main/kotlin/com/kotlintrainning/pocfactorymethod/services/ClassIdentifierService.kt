package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.model.entities.ClassIdentifier
import com.kotlintrainning.pocfactorymethod.repositories.ClassIdentifierRepository
import org.springframework.stereotype.Service

@Service
class ClassIdentifierService(private val classIdentifierRepository: ClassIdentifierRepository) {

    fun save(classIdentifier: ClassIdentifier): ClassIdentifier {
        return classIdentifierRepository.save(classIdentifier)
    }

    fun findByName(name: String):ClassIdentifier{
        return classIdentifierRepository.findByName(name)
    }

    fun findAll(): MutableList<ClassIdentifier>{
        return classIdentifierRepository.findAll()
    }
}