package com.io.github.marobim815.bio.infection

import com.io.github.marobim815.bio.PhysiologyManager
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class ProximityInfection(
    private val plugin: JavaPlugin,
    private val manager: PhysiologyManager
) {
    fun startInfectionCheck() {
        object : BukkitRunnable() {
            override fun run() {
                val playes = Bukkit.getOnlinePlayers()

                for (infected in playes) {
                    val infectedState = manager.getState(infected)
                    if (infectedState.immunity < 50) {
                        for (target in playes) {
                            if (infected == target) continue
                            if (infected.location.distance(target.location) <= 3.0) {
                                val targetState = manager.getState(target)
                                targetState.immunity -= 2.0
                                target.sendMessage(text("근처 감염 플레이어로부터 바이러스에 노출되었습니다!").color(TextColor.color(225, 0,0)))
                                target.playSound(target.location, Sound.ENTITY_ZOMBIE_INFECT, 1f, 1f)
                                infected.playSound(infected.location, Sound.ENTITY_ZOMBIE_INFECT, 1f, 1f)
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 100L)
    }
}