//package com.example.hexagonal.config
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.retry.annotation.EnableRetry
//import org.springframework.retry.support.RetryTemplate
//
//@Configuration
//@EnableRetry
//class RetryConfiguration {
//    @Bean
//    fun retryTemplate(@Value("\${retry.fixed-backoff:2000}") delayInMillis: Long): RetryTemplate? {
//        return RetryTemplate.builder()
//            .fixedBackoff(delayInMillis)
//            .maxAttempts(4)
//            .build();
//    }
//}
