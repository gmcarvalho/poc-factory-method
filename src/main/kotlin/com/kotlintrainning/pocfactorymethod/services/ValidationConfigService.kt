package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.validations.ValidationIdentifier
import org.springframework.stereotype.Service

@Service
class ValidationConfigService(private val instantiatedClasses: List<UserValidationAbstract>) {

    fun loadValidationConfigForProfile(businessProfile: String, validationClassIdentifier: String): List<UserValidationAbstract> {
        val validationClasses = mutableListOf<UserValidationAbstract>()

        for (validationClass in instantiatedClasses){
            val annotation = validationClass.javaClass.getAnnotation(ValidationIdentifier::class.java)

            if (annotation != null && annotation.value == validationClassIdentifier) {
                validationClasses.add(validationClass)
            }
        }

        return validationClasses
    }
}