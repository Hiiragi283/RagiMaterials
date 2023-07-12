package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.IHiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

class MaterialPartBlock(
    val materialH: HiiragiMaterial,
    val shape: HiiragiShape
) : HiiragiBlock(FabricBlockSettings.of(Material.METAL)), IHiiragiPart.BLOCK {

    override val identifier: Identifier = shape.getId(materialH)
    private val tag: Identifier = shape.getTag(materialH)

    //    HiiragiBlock    //

    override fun registerModel() = RagiResourcePack.addBlockState(identifier, shape.state)

    override fun registerRecipe(): Unit =
        shape.recipes(materialH).forEach(RagiResourcePack::addRecipe)


    override fun registerTag() {
        RagiResourcePack.addBlockTag(tag, JTag().add(identifier))
        RagiResourcePack.addItemTag(tag, JTag().add(identifier))
    }

    //    IHiiragiPart    //

    override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
        getMaterial(state).color

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = materialH.color

    override fun getMaterial(obj: BlockState): HiiragiMaterial = materialH

    override fun getShape(obj: BlockState): HiiragiShape = shape

}