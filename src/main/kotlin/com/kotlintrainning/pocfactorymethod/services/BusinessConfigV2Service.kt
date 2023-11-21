package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import com.kotlintrainning.pocfactorymethod.validations.ValidationIdentifier
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Service

@Service
class BusinessConfigV2Service(
    private val applicationContext: ConfigurableApplicationContext) {

    fun loadValidationConfigForProfile(businessProfile: String, validationClassIdentifier: String): UserValidationAbstract {
        return createValidationBean(validationClassIdentifier)
    }

    private fun createValidationBean(identifier: String): UserValidationAbstract {
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