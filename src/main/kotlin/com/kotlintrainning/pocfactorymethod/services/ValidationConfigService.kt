package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.repositories.BusinessProfileValidationRepository
import com.kotlintrainning.pocfactorymethod.validations.ValidationIdentifier
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Service

@Service
class ValidationConfigService(
    private val businessProfileValidationRepository: BusinessProfileValidationRepository,
    private val applicationContext: ConfigurableApplicationContext) {

    fun loadValidationConfigForProfile(businessProfile: String): List<UserValidationAbstract> {
        val validations = businessProfileValidationRepository.findByBusinessProfile(businessProfile)
        return validations.mapNotNull { createValidationBean(it.validationClassIdentifier) }
    }

    private fun createValidationBean(identifier: String): UserValidationAbstract? {
        val beanType = try {
            applicationContext.getBeansWithAnnotation(ValidationIdentifier::class.java)
                .values
                .firstOrNull { it::class.java.getAnnotation(ValidationIdentifier::class.java)?.value == identifier }
        } catch (e: ClassNotFoundException) {
            //todo tratar erro
        }

        return beanType as UserValidationAbstract
    }
}