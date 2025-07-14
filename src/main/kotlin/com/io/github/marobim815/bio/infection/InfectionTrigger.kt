package com.io.github.marobim815.bio.infection

import com.io.github.marobim815.bio.BioState
import org.bukkit.entity.Player

interface InfectionTrigger {
    fun checkAndApply(player: Player, state: BioState)
}