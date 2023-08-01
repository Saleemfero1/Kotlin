package com.tuple.inventory.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.properties.SwaggerUiConfigParameters
import org.springframework.context.annotation.Bean


class SwaggerConfig {
    var appName = "Inventory"
    @Bean
    fun customOpenAPI(swaggerUiConfigParameters: SwaggerUiConfigParameters?): OpenAPI {
        return OpenAPI().info(Info().title("Sphinx Inventory"))
    }
}