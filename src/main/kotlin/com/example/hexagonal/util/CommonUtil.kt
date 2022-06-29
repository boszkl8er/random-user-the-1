package com.example.hexagonal.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

fun customObjectMapper(): ObjectMapper = jacksonObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
    .registerModule(JavaTimeModule())
    .registerModule(SimpleModule().addSerializer(ZonedDateTime::class.java, ZonedDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))))
    .setTimeZone((TimeZone.getTimeZone("Asia/Bangkok")))
    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

fun getLogger(c: () -> Unit): Logger = LoggerFactory.getLogger(c.javaClass.enclosingClass)