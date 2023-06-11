package hiiragi283.material.init

import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

interface IRMEntry<T : IForgeRegistryEntry<T>> {

    fun register(registry: IForgeRegistry<T>)

    fun registerMaterialPart() {}

    fun registerOreDict() {}

    fun registerRecipe() {}

    @SideOnly(Side.CLIENT)
    fun registerColorBlock(blockColors: BlockColors) {
    }

    @SideOnly(Side.CLIENT)
    fun registerColorItem(itemColors: ItemColors) {
    }

    @SideOnly(Side.CLIENT)
    fun registerModel() {
    }

}