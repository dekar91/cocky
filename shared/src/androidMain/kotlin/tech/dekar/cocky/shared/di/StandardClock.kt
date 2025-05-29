package tech.dekar.cocky.shared.di

class StandardClock : DekarClock {
    override fun elapsedRealtime(): Long {
        return System.currentTimeMillis()
    }
}
