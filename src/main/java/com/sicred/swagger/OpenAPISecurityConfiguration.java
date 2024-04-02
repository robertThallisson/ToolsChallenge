package com.sicred.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =@Info(
        title = "Tools challenge API",
        version = "${api.version}",
        contact = @Contact(
            name = "Robert", email = "rthallissonlopes@gmail.com", url = "https://github.com/robertThallisson"
        ),
        license = @License(
            name = "Open source", url = "https://github.com/robertThallisson"
        ),
        termsOfService = "${tos.uri}",
        description = "${api.description}"
    )
)

public class OpenAPISecurityConfiguration {


}