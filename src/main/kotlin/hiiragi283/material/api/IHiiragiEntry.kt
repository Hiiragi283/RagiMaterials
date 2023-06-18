package hiiragi283.material.api

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.color.block.BlockColors
import net.minecraft.client.color.item.ItemColors

interface IHiiragiEntry {

    fun register()

    fun registerTag() {}

    fun registerRecipe() {}

    @Environment(EnvType.CLIENT)
    fun registerColorBlock(blockColors: BlockColors) {
    }

    @Environment(EnvType.CLIENT)
    fun registerColorItem(itemColors: ItemColors) {
    }

}