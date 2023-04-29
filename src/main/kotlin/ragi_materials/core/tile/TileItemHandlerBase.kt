package ragi_materials.core.tile

import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IInteractionObject
import ragi_materials.core.RagiMaterials

abstract class TileItemHandlerBase : TileBase(), IInteractionObject {

    override fun getDisplayName() = TextComponentTranslation(this.name)

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.${guiID.split(":")[1]}"

    override fun hasCustomName() = false

}