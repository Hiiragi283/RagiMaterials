package hiiragi283.core.util

import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry

data class MetaResourceLocation(
    val location: ResourceLocation,
    val metadata: Int
) : ResourceLocation(location.toString()) {

    constructor(namespace: String, path: String, metadata: Int) : this(ResourceLocation(namespace, path), metadata)

    constructor(metaLocation: String) : this(
        metaLocation.split(":")[0],
        metaLocation.split(":")[1],
        metaLocation.split(":")[2].toInt()
    )

    fun toBlockState(): IBlockState =
        (ForgeRegistries.BLOCKS.getValue(location) ?: Blocks.AIR).getStateFromMeta(metadata)

    fun toItemStack(size: Int = 1): ItemStack = GameRegistry.makeItemStack(location.toString(), metadata, size, null)

    fun toResourceLocation(): ResourceLocation = location.append(":$metadata")

    override fun toString(): String = toResourceLocation().toString()

}