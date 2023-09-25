package hiiragi283.material.api.registry

import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.util.setModel
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistryEntry

interface HiiragiEntry<T : IForgeRegistryEntry<T>> {

    fun asItem(): Item

    fun getItemStack(count: Int = 1, meta: Int = 0): ItemStack = ItemStack(asItem(), count, meta)

    fun getItemStack(material: HiiragiMaterial?, count: Int = 1): ItemStack =
        material?.index?.let { getItemStack(count, it) } ?: ItemStack.EMPTY

    fun getItemStack(part: HiiragiPart): ItemStack {
        val scale: Int = part.shape.scale
        return if (scale >= 144) getItemStack(part.material, scale / 144) else ItemStack.EMPTY
    }

    fun getItemStackWild(count: Int = 1): ItemStack = getItemStack(count, Short.MAX_VALUE.toInt())

    fun getObject(): T

    fun getLocation(): ResourceLocation = getObject().registryName!!

    fun onRegister() {}

    fun registerOreDict() {}

    fun registerRecipe() {}

    @SideOnly(Side.CLIENT)
    fun registerBlockColor(blockColors: BlockColors) {
    }

    @SideOnly(Side.CLIENT)
    fun registerItemColor(itemColors: ItemColors) {
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