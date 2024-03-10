plugins {
    id("java")
}

group = "io.github.bindglam.battle"
version = "unspecified"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.purpurmc.purpur", "purpur-api", "1.20.4-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}