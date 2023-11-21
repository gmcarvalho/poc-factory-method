package com.kotlintrainning.pocfactorymethod.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


//@Configuration
class DataSourceConfig {

    //@Primary
    //@Bean
    //@ConfigurationProperties(prefix = "spring.datasource")
    //fun springBatchDataSource(): DataSource {
    //    return DataSourceBuilder.create().build()
    //}

    //@Primary
    //@Bean
    //fun customDataSource(): DataSource? {
    //    val dataSource = DriverManagerDataSource()
    //    dataSource.setDriverClassName(env.getProperty("custom.datasource.driver-class-name"))
    //    dataSource.url = env.getProperty("custom.datasource.url")
    //    dataSource.username = env.getProperty("custom.datasource.username")
    //    dataSource.password = env.getProperty("custom.datasource.password")
    //    return dataSource
    //}



}