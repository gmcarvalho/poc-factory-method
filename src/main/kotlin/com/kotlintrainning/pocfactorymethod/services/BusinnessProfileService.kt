package com.kotlintrainning.pocfactorymethod.services

import com.kotlintrainning.pocfactorymethod.repositories.findRulesByBusinessProfile
import org.springframework.stereotype.Service

@Service
class BusinessProfileService {
    fun getRulesByBusinessProfile(businessProfile: String): List<String> {
        return findRulesByBusinessProfile(businessProfile).rules
    }
}