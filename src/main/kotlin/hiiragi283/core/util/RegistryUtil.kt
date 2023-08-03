@file:JvmName("RegistryUtil")

package hiiragi283.core.util

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries

fun getBlock(location: String): Block? = ForgeRegistries.BLOCKS.getValue(ResourceLocation(location))

fun getItem(location: String): Item? = ForgeRegistries.ITEMS.getValue(ResourceLocation(location))

fun getBlockState(location: String, meta: Int): IBlockState? = getBlock(location)?.getStateFromMeta(meta)

fun getItemStack(location: String, amount: Int, meta: Int): ItemStack? {
    val block = getBlock(location)
    if (block !== null) return ItemStack(block, amount, meta)
    val item = getItem(location)
    return if (item !== null) ItemStack(item, amount, meta) else null
}

fun IBlockState.toItemStack(amount: Int = 1): ItemStack =
    ItemStack(this.block, amount, this.block.getMetaFromState(this))