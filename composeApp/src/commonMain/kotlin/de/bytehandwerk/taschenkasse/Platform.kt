package de.bytehandwerk.taschenkasse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform