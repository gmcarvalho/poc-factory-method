package com.kotlintrainning.pocfactorymethod.model.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId


//@Entity
//@Table(name = "business_profile_validation")
@Document(collection = "business_profile_validation")
class BusinessProfileValidation {

    @MongoId
    var id: ObjectId = ObjectId()
    var businessProfile: String = ""
    var validationClassIdentifier = ""
}