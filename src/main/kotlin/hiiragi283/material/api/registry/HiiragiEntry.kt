package hiiragi283.material.api.registry

import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.util.setModel
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistryEntry

@Suppress("DEPRECATION")
interface HiiragiEntry<T : IForgeRegistryEntry<T>> {

    fun getObject(): T

    fun getRegistry(): HiiragiRegistry<String, T>? = null

    fun register(): T = getRegistry()?.let { registry: HiiragiRegistry<String, T> ->
        onRegister()
        return registry.register(getObject().registryName!!.path, getObject())
    } ?: getObject()

    fun registerOptional(predicate: () -> Boolean): T = if (predicate()) register() else getObject()

    fun onRegister() {}

    fun onInit() {}

    @SideOnly(Side.CLIENT)
    fun registerModel() {
    }

    interface BLOCK : HiiragiEntry<Block> {

        val itemBlock: HiiragiItemBlock?

        override fun getObject(): Block = this as Block

        override fun getRegistry(): HiiragiRegistry<String, Block> = HiiragiRegistries.BLOCK

        override fun onRegister() {
            itemBlock?.register()
        }

        @SideOnly(Side.CLIENT)
        fun getBlockColor(): IBlockColor? = null

        @SideOnly(Side.CLIENT)
        fun getItemColor(): IItemColor? = null

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            getObject().setModel()
        }

    }

    interface ITEM : HiiragiEntry<Item> {

        override fun getObject(): Item = this as Item

        override fun getRegistry(): HiiragiRegistry<String, Item> = HiiragiRegistries.ITEM

        @SideOnly(Side.CLIENT)
        fun getItemColor(): IItemColor? = null

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            getObject().setModel()
        }

    }

}