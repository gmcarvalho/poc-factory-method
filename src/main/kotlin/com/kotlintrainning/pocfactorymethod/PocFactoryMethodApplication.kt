package com.kotlintrainning.pocfactorymethod

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


//@EnableMongock
@EnableMongoRepositories(basePackages = ["com.kotlintrainning.pocfactorymethod.repositories"])
@SpringBootApplication
class PocFactoryMethodApplication

fun main(args: Array<String>) {
    runApplication<PocFactoryMethodApplication>(*args)
}
