package hiiragi283.material.client

import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.RagiRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack

object RagiMaterialsClient : ClientModInitializer {

    @Environment(EnvType.CLIENT)
    override fun onInitializeClient() {

        ColorProviderRegistry.ITEM.register(object : ItemColorProvider {

            override fun getColor(stack: ItemStack, tintIndex: Int): Int {
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is MaterialPartItem) return item.material.color
                }
                return -1
            }
        }, *RagiRegistry.MaterialItemSet.toTypedArray())
        RagiMaterials.LOGGER.info("Color Provider registered!")

    }
}