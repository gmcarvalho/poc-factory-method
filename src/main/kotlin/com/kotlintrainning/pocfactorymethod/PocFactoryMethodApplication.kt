package com.kotlintrainning.pocfactorymethod

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration


@Configuration
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class PocFactoryMethodApplication

fun main(args: Array<String>) {
    runApplication<PocFactoryMethodApplication>(*args)
}
