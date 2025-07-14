package com.io.github.marobim815.bio

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class BioEventListener(private val manager: PhysiologyManager) : Listener {
    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val distance = event.from.distance(event.to)
        if (distance > 0.01) {
            manager.updateOnActivity(event.player, intensity = distance)
        }
    }
}