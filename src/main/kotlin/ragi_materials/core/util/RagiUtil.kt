@file:JvmName("RagiUtil")

package ragi_materials.core.util

import net.minecraft.client.Minecraft
import net.minecraft.command.ICommandSender
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable
import ragi_materials.core.RagiMaterials

fun ItemStack.toBracket() = "<${this.item.registryName}:${this.metadata}> * ${this.count}"

fun ItemStack.toLocation() = ResourceLocation(this.item.registryName!!.toString() + "_" + this.metadata)

fun FluidStack.toBracket() = "<fluid:${this.fluid.name}> * ${this.amount}"

fun Boolean.toInt() = if (this) 1 else 0

fun Int.toBool() = this % 2 != 0

fun List<ItemStack>.toNonNullList(): NonNullList<ItemStack> {
    return NonNullList.withSize(this.size, ItemStack.EMPTY).also {
        for (i in 0 until this.size) {
            it[i] = this[i]
        }
    }
}

fun executeCommand(sender: ICommandSender, command: String) {
    Minecraft.getMinecraft().integratedServer?.server?.getCommandManager()?.executeCommand(sender, command)
}

fun convertArrayTomMap(array: Array<String>): Map<String, String> = array.associate { it.split(";")[0] to it.split(";")[1] }

fun getEnumRarity(name: String): IRarity {
    return when (name) {
        "Uncommon" -> EnumRarity.UNCOMMON
        "Rare" -> EnumRarity.RARE
        "Epic" -> EnumRarity.EPIC
        "Legendary" -> object : IRarity {
            override fun getColor() = TextFormatting.GOLD
            override fun getName() = "Legendary"
        }

        "Mythic" -> object : IRarity {
            override fun getColor() = TextFormatting.RED
            override fun getName() = "Mythic"
        }

        else -> EnumRarity.COMMON
    }
}

//エントリーを削除するメソッド
fun removeRegistryEntry(registry: IForgeRegistry<*>, registryName: ResourceLocation) {
    if (registry is IForgeRegistryModifiable<*>) {
        registry.remove(registryName)
        RagiMaterials.LOGGER.warn("The entry $registryName was removed from ${registry::class.java.name}!")
    } else RagiMaterials.LOGGER.warn("The registry ${registry::class.java.name} is not implementing IForgeRegistryModifiable!")
}

fun remove(registry: IForgeRegistry<*>, registryName: String) {
    removeRegistryEntry(registry, ResourceLocation(registryName))
}