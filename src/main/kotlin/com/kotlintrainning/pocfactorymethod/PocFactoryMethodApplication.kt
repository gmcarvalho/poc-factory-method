package com.kotlintrainning.pocfactorymethod

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication
class PocFactoryMethodApplication

fun main(args: Array<String>) {
    runApplication<PocFactoryMethodApplication>(*args)
}
