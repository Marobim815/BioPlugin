package com.io.github.marobim815.bio.hud

import com.io.github.marobim815.bio.BioPlugin
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class BioHUDRenderer(private val plugin: BioPlugin) {
    fun startRendering() {
        object : BukkitRunnable() {
            override fun run() {
                Bukkit.getOnlinePlayers().forEach { player ->
                    val state = plugin.physiologyManager.getState(player)
                    val message = text("${state.heartRate}bpm | ${"%.1f".format(state.immunity)}%")
                    player.sendActionBar(message)
                }
            }
        }.runTaskTimer(plugin, 0L, 40L)
    }
}