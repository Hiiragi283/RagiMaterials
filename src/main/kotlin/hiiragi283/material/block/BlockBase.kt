package hiiragi283.material.block

import hiiragi283.material.util.IIdentifiable
import net.minecraft.block.Block
import net.minecraft.util.Identifier

abstract class BlockBase(settings: Settings) : Block(settings), IIdentifiable {

    abstract override fun getIdentifier(): Identifier

}