package hiiragi283.ragi_materials.tile

import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IInteractionObject

abstract class TileItemHandlerBase(type: Int) : TileBase(type), IInteractionObject {

    override fun getDisplayName() = TextComponentTranslation(this.name)

    override fun hasCustomName() = false

}