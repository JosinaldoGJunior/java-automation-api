plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "io.github.JosinaldoGJunior"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Suas dependências de implementação (RestAssured, etc.)
    implementation("io.rest-assured:rest-assured:5.3.2")
    implementation("io.rest-assured:json-schema-validator:5.3.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.github.cdimascio:java-dotenv:5.2.2")

    // --- Configuração Limpa do JUnit 5 ---
    // 1. O BOM gerencia todas as versões do JUnit.
    testImplementation(platform("org.junit:junit-bom:5.10.0"))

    // 2. O agregador 'junit-jupiter' já puxa a API e a Engine.
    testImplementation("org.junit.jupiter:junit-jupiter")

    // --- Dependências do Allure ---
    testImplementation("io.qameta.allure:allure-junit5:2.20.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.20.1")

    // --- Opcional ---
    // A dependência abaixo só é necessária se você usar a anotação @Suite para criar suítes de teste.
    // Se não estiver usando, pode remover também para uma limpeza máxima.
    testImplementation("org.junit.platform:junit-platform-suite")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("junit.jupiter.extensions.autodetection.enabled", true)
}



allure {
    version.set("2.20.1")
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.20.1")
            }
        }
    }
}