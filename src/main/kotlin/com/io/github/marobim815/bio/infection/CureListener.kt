package com.io.github.marobim815.bio.infection

import com.io.github.marobim815.bio.PhysiologyManager
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

class CureListener(private val manager: PhysiologyManager) : Listener {
    @EventHandler
    fun onPlayerEat(event: PlayerItemConsumeEvent) {
        manager.applyRecovery(event.player)
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        event.player.playSound(event.player.location, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f)
        manager.applyRecovery(event.player)
    }
}