package com.kotlintrainning.pocfactorymethod.controller

import com.kotlintrainning.pocfactorymethod.model.entities.BusinessProfile
import com.kotlintrainning.pocfactorymethod.services.BusinessProfileService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/businessProfile")
class BusinessProfileController(private val businessProfileService: BusinessProfileService
) {

    @PostMapping("/create")
    fun create(@Validated @RequestBody businessProfile: BusinessProfile): BusinessProfile {
        return businessProfileService.save(businessProfile)
    }

    @GetMapping("/findAll")
    fun findAll(): MutableList<BusinessProfile> {
        return businessProfileService.findAll()
    }

    @GetMapping("/findByName")
    fun findByName(@RequestParam name: String): BusinessProfile?{
        return businessProfileService.findByName(name)
    }
}