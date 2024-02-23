plugins {
    id("java")
}

group = "io.github.bindglam"
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
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
    compileOnly("com.github.Dwight-Studio:DSMAPI:1.2")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
    compileOnly(project(":Economy"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

tasks.test {
    useJUnitPlatform()
}