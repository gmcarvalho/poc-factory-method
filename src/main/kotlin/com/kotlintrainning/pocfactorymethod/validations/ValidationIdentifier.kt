package com.kotlintrainning.pocfactorymethod.validations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ValidationIdentifier(val value: String)
