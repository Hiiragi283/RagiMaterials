package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialRegistryNew
import hiiragi283.ragi_materials.material.RagiMaterial
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

class BlockOreMaterial(ID: String): BlockBase(ID, Material.ROCK, -1), IMaterialBlock, IMaterialItem {

    val list: List<RagiMaterial> = listOf(
            MaterialRegistryNew.EMERALD //Beryl
            /*MaterialRegistry.SALT, //Rock Salt
            MaterialRegistryNew.COAL,
            MaterialRegistry.GRAPHITE,
            MaterialRegistry.FLUORITE,
            MaterialRegistry.BAUXITE, //Laterite
            MaterialRegistry.ALUMINIUM_OXIDE, //Alumina
            MaterialRegistry.SULFUR,
            MaterialRegistry.NITER,
            MaterialRegistry.CALCIUM_CARBONATE, //Lime
            MaterialRegistry.MAGNETITE,
            MaterialRegistry.PYROLUSITE, //Seabed Nodule
            MaterialRegistry.COPPER,
            MaterialRegistry.SPHALERITE*/
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

    fun getMaterialList(): List<RagiMaterial> = list

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, TYPE)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(TYPE)

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(TYPE, meta % list.size)", "hiiragi283.ragi_materials.block.BlockOreMaterial.Companion.TYPE"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(TYPE, meta % list.size)

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    IMaterialBlock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): RagiMaterial = list[state.getValue(TYPE) % list.size]

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial = list[stack.metadata % list.size]

    override fun setMaterial(stack: ItemStack, material: RagiMaterial): ItemStack = stack
}