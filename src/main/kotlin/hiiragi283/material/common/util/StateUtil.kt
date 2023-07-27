package hiiragi283.material.common.util

import net.devtech.arrp.json.blockstate.JState
import net.minecraft.util.Identifier

object StateUtil {

    fun createSimple(model: Identifier): JState = JState.state(JState.variant(JState.model(model)))

}