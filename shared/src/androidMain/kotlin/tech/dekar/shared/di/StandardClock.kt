package tech.dekar.shared.di

class StandardClock : DekarClock {
    override fun elapsedRealtime(): Long {
        return System.currentTimeMillis()
    }
}
