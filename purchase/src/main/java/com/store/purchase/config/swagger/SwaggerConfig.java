package com.store.purchase.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(title = "Purchase API", version = "v1", description = "The Purchase API. Contains all the operations that can be performed on a Purchase."),
        tags = {@Tag(name = "Purchase")}
)
public class SwaggerConfig {
}
