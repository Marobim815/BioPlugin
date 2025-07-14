package com.io.github.marobim815.bio

import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.*

class PhysiologyManager {
    private val playerStates = mutableMapOf<UUID, BioState>()

    fun getState(player: Player): BioState = playerStates.computeIfAbsent(player.uniqueId) { BioState() }

    fun updateOnActivity(player: Player, intensity: Double) {
        val state = getState(player)
        state.heartRate += (intensity * 3).toInt()
        state.heartRate = state.heartRate.coerceIn(60, 180)

        state.temperature += (intensity * 0.03)
        state.temperature = state.temperature.coerceIn(35.0, 41.0)

        state.immunity -= (intensity * 0.2)
        state.immunity = state.immunity.coerceAtLeast(0.0)

        if (state.temperature >= 50) player.health -= 1
    }

    fun applyRecovery(player: Player) {
        val state = getState(player)
        state.heartRate = (state.heartRate -10).coerceAtLeast(60)
        state.temperature = (state.temperature - 0.1).coerceAtLeast(36.5)
        state.immunity = (state.immunity + 5).coerceAtMost(100.0)
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
    }
}