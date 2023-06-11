@file:JvmName("RagiUtil")

package hiiragi283.material.util

import hiiragi283.material.RagiMaterials
import net.minecraft.client.Minecraft
import net.minecraft.command.ICommandSender
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable

fun ItemStack.toBracket(): String = "<${this.item.registryName}:${this.metadata}> * ${this.count}"

fun ItemStack.toLocation(): ResourceLocation = this.item.registryName!!.append("_" + this.metadata)

fun FluidStack.toBracket(): String = "<fluid:${this.fluid.name}> * ${this.amount}"

//ResourceLocationの末尾に付け足す関数
fun ResourceLocation.append(path: String) = ResourceLocation(this.namespace, this.path + path)

fun Boolean.toInt(): Int = if (this) 1 else 0

fun Int.toBool(): Boolean = this % 2 != 0

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