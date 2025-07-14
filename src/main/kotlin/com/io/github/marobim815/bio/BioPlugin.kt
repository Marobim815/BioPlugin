package com.io.github.marobim815.bio

import com.io.github.marobim815.bio.hud.BioHUDRenderer
import com.io.github.marobim815.bio.infection.CureListener
import com.io.github.marobim815.bio.infection.ProximityInfection
import com.io.github.marobim815.bio.infection.SoulSandInfection
import com.io.github.marobim815.bio.infection.ZombieTouchInfection
import org.bukkit.plugin.java.JavaPlugin

/**
 * **1. 주소 이동**
 * ```bash
 * cd C:\MinecraftServer\BioServer
 * ```
 *
 * **2. 서버 실행**
 * ```bash
 * java -Xmx2G -Xms1G -jar paper-1.21.7-26.jar nogui
 * ```
 */

class BioPlugin : JavaPlugin() {

    lateinit var physiologyManager: PhysiologyManager

    override fun onEnable() {
        physiologyManager = PhysiologyManager()
        logger.info("Plugin initialized! Hello, World!")
        val moveListener = BioEventListener(physiologyManager)
        val cureListener = CureListener(physiologyManager)
        val zombieInfection = ZombieTouchInfection(physiologyManager)
        val soulSandInfection = SoulSandInfection(physiologyManager)
        val proximityInfection = ProximityInfection(this, physiologyManager)

        BioHUDRenderer(this).startRendering()

        proximityInfection.startInfectionCheck()

        server.pluginManager.registerEvents(moveListener, this)
        server.pluginManager.registerEvents(cureListener, this)
        server.pluginManager.registerEvents(soulSandInfection, this)
        server.pluginManager.registerEvents(zombieInfection, this)
    }
}
