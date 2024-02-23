repositories {
    mavenCentral()
}

dependencies {
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://jitpack.io")
        maven("https://repo.dmulloy2.net/repository/public/")
    }
}