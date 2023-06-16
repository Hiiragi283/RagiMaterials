@file:JvmName("RagiUtil")

package hiiragi283.material.util

import hiiragi283.material.RagiMaterials
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.command.ICommandSender
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable

fun ItemStack.toLocation(): ResourceLocation = this.item.registryName!!.append(":" + this.metadata)

fun IBlockState.toLocation(): ResourceLocation =
    this.block.registryName!!.append(":" + this.block.getMetaFromState(this))

fun FluidStack.toLocation(addAmount: Boolean): ResourceLocation {
    val location = ResourceLocation("fluid", this.fluid.name)
    if (addAmount) location.append(":" + this.amount)
    return location
}

//ResourceLocationの末尾に付け足す関数
fun ResourceLocation.append(path: String) = ResourceLocation(this.namespace, this.path + path)

fun executeCommand(sender: ICommandSender, command: String) {
    Minecraft.getMinecraft().integratedServer?.getCommandManager()?.executeCommand(sender, command)
}

fun getEnumRarity(name: String): IRarity {
    return when (name) {
        "Uncommon" -> EnumRarity.UNCOMMON
        "Rare" -> EnumRarity.RARE
        "Epic" -> EnumRarity.EPIC
        else -> EnumRarity.COMMON
    }
}

//エントリーを削除するメソッド
fun removeRegistryEntry(registry: IForgeRegistry<*>, registryName: ResourceLocation): Boolean {
    return if (registry is IForgeRegistryModifiable<*>) {
        registry.remove(registryName)
        RagiMaterials.LOGGER.warn("The entry $registryName was removed from ${registry::class.java.name}!")
        true
    } else {
        RagiMaterials.LOGGER.warn("The registry ${registry::class.java.name} is not implementing IForgeRegistryModifiable!")
        false
    }
}

fun remove(registry: IForgeRegistry<*>, registryName: String): Boolean =
    removeRegistryEntry(registry, ResourceLocation(registryName))