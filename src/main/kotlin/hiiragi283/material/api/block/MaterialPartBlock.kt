package hiiragi283.material.api.block

import hiiragi283.material.api.HiiragiBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

fun createMaterialBlock(
    material: HiiragiMaterial,
    shape: HiiragiShape
): MaterialPartBlock {

    val identifier: Identifier = shape.getId(material)
    val tag: Identifier = shape.getTag(material)

    val block = object : MaterialPartBlock() {

        override fun getIdentifier(): Identifier = identifier

        override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
            shape.blockColor(material).getColor(state, view, pos, tintIndex)

        override fun getColor(stack: ItemStack, tintIndex: Int): Int =
            shape.itemColor(material).getColor(stack, tintIndex)

    }

    RagiResourcePack.addBlockState(identifier, shape.state)
    shape.recipes(material).forEach(RagiResourcePack::addRecipe)
    RagiResourcePack.addBlockTag(tag, JTag().add(identifier))
    RagiResourcePack.addItemTag(tag, JTag().add(identifier))

    return block
}

abstract class MaterialPartBlock : HiiragiBlock(FabricBlockSettings.of(Material.METAL)), BlockColorProvider,
    ItemColorProvider