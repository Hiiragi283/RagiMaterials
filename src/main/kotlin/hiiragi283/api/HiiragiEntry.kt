package hiiragi283.api

import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.part.HiiragiPart
import hiiragi283.material.util.setModel
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

interface HiiragiEntry<T : IForgeRegistryEntry<T>> {

    fun asItem(): Item

    fun getItemStack(material: HiiragiMaterial, amount: Int = 1): ItemStack = ItemStack(asItem(), amount, material.index)

    fun getItemStack(part: HiiragiPart): ItemStack {
        val scale = part.shape.scale
        return if (scale >= 144) getItemStack(part.material, scale / 144) else ItemStack.EMPTY
    }

    fun getObject(): T

    fun getLocation(): ResourceLocation? = getObject().registryName

    fun register(registry: IForgeRegistry<T>) {
        registry.register(getObject())
    }

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

    interface BLOCK : HiiragiEntry<Block> {

        val itemBlock: HiiragiItemBlock?

        override fun asItem(): Item = itemBlock ?: Item.getItemFromBlock(getObject())

        override fun getObject(): Block = this as Block

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            getObject().setModel()
        }

    }

    interface ITEM : HiiragiEntry<Item> {

        override fun asItem(): Item = getObject()

        override fun getObject(): Item = this as Item

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            getObject().setModel()
        }

    }

}