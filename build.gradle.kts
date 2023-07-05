plugins {
    id("java")
}

group = "lv.side"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("com.github.Zrips:Jobs:v4.17.2") {
        exclude(group = "com.sk89q", module = "worldguard")
        exclude(group = "com.sk89q.worldguard", module = "worldguard-bukkit")
        exclude(group = "com.sk89q", module = "worldedit")
        exclude(group = "com.sk89q.worldedit", module = "worldedit-bukkit")
    }
}
