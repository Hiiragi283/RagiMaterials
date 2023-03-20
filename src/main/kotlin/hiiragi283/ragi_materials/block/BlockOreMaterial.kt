package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

class BlockOreMaterial(ID: String): BlockBase(ID, Material.ROCK, -1) {

    val list: List<Color> = listOf(
            MaterialRegistry.SALT.color, //Rock Salt
            RagiColorManager.mixColor(MaterialRegistry.EMERALD.color, MaterialRegistry.AQUAMARINE.color), //Beryl
            MaterialRegistry.FLUORITE.color, //Fluorite
            MaterialRegistry.BAUXITE.color, //Laterite
            RagiColorManager.mixColor(MaterialRegistry.RUBY.color, MaterialRegistry.SAPPHIRE.color), //Corundum
            MaterialRegistry.SULFUR.color, //Sulfur
            MaterialRegistry.NITER.color, //Niter
            MaterialRegistry.LIME.color, //Lime
            RagiColorManager.mixColor(MaterialRegistry.MANGANESE.color, MaterialRegistry.IRON.color, MaterialRegistry.COBALT.color), //Nodule
            MaterialRegistry.MAGNETITE.color, //Magnetite
            MaterialRegistry.COPPER.color, //Copper
            MaterialRegistry.SPHALERITE.color //Sphalerite
    )

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

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(TYPE, meta % list.size)", "hiiragi283.ragi_materials.block.BlockOreMaterial.Companion.TYPE"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(TYPE, meta % list.size)

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}