package com.store.product.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(title = "Product API", version = "v1", description = "The Product API. Contains all the operations that can be performed on a Product."),
        tags = {@Tag(name = "Product")}
)
public class SwaggerConfig {
}