package com.edu.nefu.labreserve.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @Info(title = "实验室预约系统 API", version = "1.0", description = "实验室预约系统相关接口"))
public class OpenApiConfig {
}