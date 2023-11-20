package com.kotlintrainning.pocfactorymethod.factory

import com.kotlintrainning.pocfactorymethod.interfaces.UserValidationAbstract
import org.springframework.stereotype.Component

@Component
class UserValidationFactory(private val validations: List<UserValidationAbstract>) {
    fun getTheValidatorClasses(rulesToBeValidated: List<String>): List<UserValidationAbstract> {
        return validations.filter { validation ->
            rulesToBeValidated.any { rule -> validation.supports(rule) }
        }
    }
}