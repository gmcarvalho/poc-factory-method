package com.kotlintrainning.pocfactorymethod.model.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "businessProfile")
@CompoundIndex(name = "name_1_classIdentifier.name_1", def = "{'name' : 1, 'classIdentifier.name' : 1}", unique = true)
class BusinessProfile {

    @MongoId
    var id: ObjectId = ObjectId()
    @Indexed(unique=true)
    var name: String = ""
    var classIdentifier: MutableList<ClassIdentifier> = mutableListOf()

}