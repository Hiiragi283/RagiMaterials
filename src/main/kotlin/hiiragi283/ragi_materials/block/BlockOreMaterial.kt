package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.material.OreProperty
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

class BlockOreMaterial(ID: String) : BlockBase(ID, Material.ROCK, -1), ICustomModel, IMaterialBlock {

    companion object {
        val TYPE: PropertyInteger = PropertyInteger.create("type", 0, 15)
    }

    init {
        blockHardness = 3.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(TYPE, 0)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    override fun damageDropped(state: IBlockState): Int = state.getValue(TYPE)

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, TYPE)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(TYPE)

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(TYPE, meta % OreProperty.mapOre1.size)", "hiiragi283.ragi_materials.block.BlockOreMaterial.Companion.TYPE", "hiiragi283.ragi_materials.material.OreProperty"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(TYPE, meta % OreProperty.mapOre1.size)

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    IMaterialBlock    //

    override fun getColor(world: IBlockAccess, pos: BlockPos, state: IBlockState): Color {
        val list = OreProperty.listOre1
        val index = this.getMetaFromState(state) % list.size
        return list[index].second.getColor()
    }

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack): Color {
        val list = OreProperty.listOre1
        val index = stack.metadata % list.size
        return list[index].second.getColor()
    }

}