package com.kotlintrainning.pocfactorymethod.model.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "business_profile_validation")
class BusinessProfileValidation {

    @Id
    var id: Long? = null
    val businessProfile: String = ""
    val validationClassIdentifier = ""
}