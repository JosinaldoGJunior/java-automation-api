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
    implementation("io.rest-assured:rest-assured:5.3.2")
    implementation("io.rest-assured:json-schema-validator:5.3.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.github.cdimascio:java-dotenv:5.2.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))

    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("io.qameta.allure:allure-junit5:2.20.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.20.1")

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