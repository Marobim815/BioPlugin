package com.io.github.marobim815.bio.infection

import com.io.github.marobim815.bio.BioState
import com.io.github.marobim815.bio.PhysiologyManager
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class ZombieTouchInfection(private val manager: PhysiologyManager) : Listener, InfectionTrigger {
    @EventHandler
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        val player = event.entity as? Player ?: return
        val damager = event.damager

        if (damager is Zombie) {
            val state = manager.getState(player)
            checkAndApply(player, state)
        }
    }

    override fun checkAndApply(player: Player, state: BioState) {
        state.immunity -= 15.0
        player.sendMessage(text("좀비에 물려 감염되었습니다!")
            .color(TextColor.color(255, 0, 0))
        )
        player.playSound(player.location, Sound.ENTITY_ZOMBIE_INFECT, 1f, 1f)
    }

}