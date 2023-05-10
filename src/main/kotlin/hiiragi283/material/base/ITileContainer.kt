package hiiragi283.material.base

import hiiragi283.material.RagiMaterials
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IInteractionObject

interface ITileContainer : IInteractionObject {

    override fun getDisplayName() = TextComponentTranslation(this.name)

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.${guiID.split(":")[1]}"

    override fun hasCustomName() = false

}