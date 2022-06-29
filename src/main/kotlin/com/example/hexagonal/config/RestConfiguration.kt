package com.example.hexagonal.config

import com.example.hexagonal.properties.Connection
import com.example.hexagonal.properties.RestTemplateProperties;
import com.example.hexagonal.util.customObjectMapper
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfiguration {
    @Bean
    fun restTemplate(restTemplateProperties: RestTemplateProperties) =
        RestTemplate(getCustomHttpRequestFactory(restTemplateProperties))
            .apply {
                messageConverters.add(0, MappingJackson2HttpMessageConverter(customObjectMapper()))
            }

    private fun getCustomHttpRequestFactory(restTemplateProperties: RestTemplateProperties) =
        HttpComponentsClientHttpRequestFactory(getHttpClient(restTemplateProperties.connection))
            .apply {
                setConnectTimeout(restTemplateProperties.timeout.connection)
                setReadTimeout(restTemplateProperties.timeout.read)
                setConnectionRequestTimeout(restTemplateProperties.timeout.request)
            }

    private fun getHttpClient(connection: Connection): HttpClient {
        val connectionManager = PoolingHttpClientConnectionManager()
            .apply {
                maxTotal = connection.maxTotal
                defaultMaxPerRoute = connection.maxPerRoute
            }

        return HttpClientBuilder.create().setConnectionManager(connectionManager).build()
    }
}