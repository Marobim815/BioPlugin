package com.io.github.marobim815.bio.infection

import com.io.github.marobim815.bio.BioState
import com.io.github.marobim815.bio.PhysiologyManager
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class SoulSandInfection(private val manager: PhysiologyManager) : Listener, InfectionTrigger {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val block = event.clickedBlock ?: return
        if (block.type == Material.SOUL_SAND) {
            val player = event.player
            val state = manager.getState(player)
            checkAndApply(player, state)
        }
    }

    override fun checkAndApply(player: Player, state: BioState) {
        state.temperature += 1.0
        state.immunity -= 20.0
        player.sendMessage(
            text("영혼 모래에 감염되었습니다!\n 체온이 상승하고 면역력이 감소합니다!")
                .color(TextColor.color(255, 0, 0)
            )
        )
        player.playSound(player.location, Sound.ENTITY_ZOMBIE_INFECT, 1f, 1f)
    }
}