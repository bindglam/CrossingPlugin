plugins {
    id("java")
}

group = "io.github.bindglam"
version = "unspecified"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
    maven(url = "https://mvn.lumine.io/repository/maven-public/")
    maven(url = "https://jitpack.io")
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.purpurmc.purpur", "purpur-api", "1.20.4-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:4.14.0")
    compileOnly("org.spigotmc:spigot:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.github.Dwight-Studio:DSMAPI:1.2")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
    compileOnly("net.luckperms:api:5.4")
    compileOnly(project(":Economy"))
    compileOnly(project(":Ground"))
    compileOnly("io.lumine:Mythic-Dist:5.3.5")
    compileOnly("com.github.LoneDev6:API-ItemsAdder:3.6.1")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("net.kyori:adventure-text-minimessage:4.15.0")
    implementation("io.socket:socket.io-client:1.0.0")
    implementation("io.reactivex.rxjava2:rxjava:2.1.16")
    implementation("org.jsoup:jsoup:1.17.2")
}

tasks.test {
    useJUnitPlatform()
}