package com.kotlintrainning.pocfactorymethod.model.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "business_profile_validation")
class BusinessProfileValidation {

    @Id
    var id: Long? = null
    @Column(name = "business_profile")
    val businessProfile: String = ""
    @Column(name = "validation_class_identifier")
    val validationClassIdentifier = ""
}