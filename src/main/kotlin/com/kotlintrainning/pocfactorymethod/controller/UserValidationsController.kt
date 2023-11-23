package com.kotlintrainning.pocfactorymethod.controller

import com.kotlintrainning.pocfactorymethod.model.ValidationResponse

import com.kotlintrainning.pocfactorymethod.services.ValidationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/validations")
class UserValidationsController(private val validationService: ValidationService
) {
    @PostMapping("/user")
    fun validateUser(@RequestParam ucode: String, @RequestParam businessProfile: String): ValidationResponse {
        return validationService.process(ucode, businessProfile)
    }
}