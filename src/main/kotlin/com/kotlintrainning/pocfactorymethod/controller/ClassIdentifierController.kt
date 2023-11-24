package com.kotlintrainning.pocfactorymethod.controller

import com.kotlintrainning.pocfactorymethod.model.entities.ClassIdentifier
import com.kotlintrainning.pocfactorymethod.services.ClassIdentifierService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/classIdentifier")
class ClassIdentifierController(private val classIdentifierService: ClassIdentifierService
) {

    @PostMapping("/create")
    fun create(@Validated @RequestBody classIdentifier: ClassIdentifier): ClassIdentifier {
        return classIdentifierService.save(classIdentifier)
    }

    @GetMapping("/findAll")
    fun findAll(): MutableList<ClassIdentifier> {
        return classIdentifierService.findAll()
    }

    @GetMapping("/findByName")
    fun findByName(@RequestParam name: String): ClassIdentifier?{
        return classIdentifierService.findByName(name)
    }
}