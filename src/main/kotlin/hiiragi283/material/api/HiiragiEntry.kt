package hiiragi283.material.api

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.HiiragiModelManager
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

    fun getItem(): Item

    fun getItemStack(material: HiiragiMaterial, amount: Int = 1): ItemStack = ItemStack(getItem(), amount, material.index)

    /**
     * Returns an object implemented [IForgeRegistryEntry]
     */
    fun getObject(): T

    fun getLocation(): ResourceLocation? = getObject().registryName

    /**
     * Registers an object for give [registry]
     *
     * The object is from [getObject]
     */
    fun register(registry: IForgeRegistry<T>) {
        registry.register(getObject())
    }

    /**
     * Registers Ore Dictionary names on [net.minecraftforge.fml.common.event.FMLInitializationEvent]
     */
    fun registerOreDict() {}

    /**
     * Registers recipes on [net.minecraftforge.fml.common.event.FMLInitializationEvent]
     */
    fun registerRecipe() {}

    /**
     * Registers [net.minecraft.client.renderer.color.IBlockColor] on [net.minecraftforge.client.event.ColorHandlerEvent.Block]
     */
    @SideOnly(Side.CLIENT)
    fun registerColorBlock(blockColors: BlockColors) {
    }

    /**
     * Registers [net.minecraft.client.renderer.color.IItemColor] on [net.minecraftforge.client.event.ColorHandlerEvent.Item]
     */
    @SideOnly(Side.CLIENT)
    fun registerColorItem(itemColors: ItemColors) {
    }

    /**
     * Registers models on [net.minecraftforge.client.event.ModelRegistryEvent]
     */
    @SideOnly(Side.CLIENT)
    fun registerModel() {
    }

    interface BLOCK : HiiragiEntry<Block> {

        override fun getItem(): Item = Item.getItemFromBlock(getObject())

        override fun getObject(): Block = this as Block

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            HiiragiModelManager.setModel(getObject())
        }

    }

    interface ITEM : HiiragiEntry<Item> {

        override fun getItem(): Item = getObject()

        override fun getObject(): Item = this as Item

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            HiiragiModelManager.setModel(getObject())
        }

    }

}