package com.foxdev.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform