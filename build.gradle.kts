plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "co.edu.uniquindio"
version = "0.0.1-SNAPSHOT"
description = "Breve descripción de la aplicación"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
// Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.simplejavamail:simple-java-mail:8.12.5")
    implementation("org.simplejavamail:batch-module:8.12.5")


    // Spring Boot DevTools (solo en runtime y opcional)
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // MySQL Connector
    //runtimeOnly("com.mysql:mysql-connector-j:8.3.0")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Spring Boot Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    // JWT Seguridad
    implementation("com.nimbusds:nimbus-jose-jwt:9.31")

    // Cloudinary
    implementation("com.cloudinary:cloudinary-http44:1.39.0")


    // OpenAPI / Swagger (versión 2.3.0)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

}

