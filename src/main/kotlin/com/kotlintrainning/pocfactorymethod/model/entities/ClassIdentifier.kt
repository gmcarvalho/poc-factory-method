package com.kotlintrainning.pocfactorymethod.model.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "classIdentifier")
class ClassIdentifier {

    @MongoId
    var id: ObjectId = ObjectId()
    @Indexed(unique=true)
    var name: String = ""
}