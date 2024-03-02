plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.4")
}
