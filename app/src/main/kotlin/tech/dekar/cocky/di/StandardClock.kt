package tech.dekar.cocky.di

class StandardClock : DekarClock {
    override fun elapsedRealtime(): Long {
        return System.currentTimeMillis()
    }
}
