@file:JvmName("RMIntegrationCore")

package hiiragi283.material.config

import net.minecraftforge.fml.common.Loader

fun enableMek() = Loader.isModLoaded("mekanism")
fun enableTE() = Loader.isModLoaded("thermalfoundation")
fun enableThaum() = Loader.isModLoaded("thaumcraft")