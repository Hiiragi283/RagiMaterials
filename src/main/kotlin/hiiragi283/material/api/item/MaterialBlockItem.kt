package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiBlockItem
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.common.RMItemGroup
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

abstract class MaterialBlockItem(val block: MaterialBlock) :
    HiiragiBlockItem(block, FabricItemSettings().group(RMItemGroup.MATERIAL_BLOCk)), RMItemColorProvider {

    override fun getIdentifier(): Identifier = block.getIdentifier()

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = block.getColor(stack, tintIndex)

}