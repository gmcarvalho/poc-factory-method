package com.kotlintrainning.pocfactorymethod.controller

import com.kotlintrainning.pocfactorymethod.model.ValidationResponse
import com.kotlintrainning.pocfactorymethod.services.ValidationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/validations")
class UserValidationsController(private val validationService: ValidationService) {
    @PostMapping("/user")
    fun validateUser(@RequestParam ucode: String, @RequestParam businessProfile: String): ValidationResponse {
        val startTime = System.currentTimeMillis()
        val response = validationService.process(ucode, businessProfile)

        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime
        println("Tempo de execução: $executionTime milissegundos")

        return response
    }
}