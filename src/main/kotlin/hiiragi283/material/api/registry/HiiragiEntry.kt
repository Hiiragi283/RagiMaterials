package hiiragi283.material.api.registry

import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.util.setModel
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistryEntry

interface HiiragiEntry<T : IForgeRegistryEntry<T>> {

    fun getObject(): T

    fun onRegister() {}

    fun registerOreDict() {}

    fun registerRecipe() {}

    @SideOnly(Side.CLIENT)
    fun getBlockColor(): IBlockColor? = null

    @SideOnly(Side.CLIENT)
    fun getItemColor(): IItemColor? = null

    @SideOnly(Side.CLIENT)
    fun registerModel() {
    }

    interface BLOCK : HiiragiEntry<Block> {

        val itemBlock: HiiragiItemBlock?

        override fun getObject(): Block = this as Block

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            getObject().setModel()
        }

    }

    interface ITEM : HiiragiEntry<Item> {

        override fun getObject(): Item = this as Item

        @SideOnly(Side.CLIENT)
        override fun registerModel() {
            getObject().setModel()
        }

    }

}