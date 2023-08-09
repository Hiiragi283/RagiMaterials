package hiiragi283.material.block

import hiiragi283.api.block.HiiragiBlock
import hiiragi283.api.block.property.HiiragiProperty
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialElements
import hiiragi283.material.RMItems
import hiiragi283.material.util.setModelSame
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

object BlockOreCluster : HiiragiBlock(Material.ROCK, "ore_cluster") {

    init {
        blockHardness = 5.0f
        blockResistance = 5.0f
        defaultState = blockState.baseState.withProperty(HiiragiProperty.TYPE16, 0)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, 15)

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, HiiragiProperty.TYPE16)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(HiiragiProperty.TYPE16)

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "defaultState.withProperty(type, meta % 16)",
            "hiiragi283.chemistry.block.BlockOreCluster.type"
        )
    )
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(HiiragiProperty.TYPE16, meta % 16)

    override fun getStateForPlacement(
        world: World,
        pos: BlockPos,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        meta: Int,
        placer: EntityLivingBase,
        hand: EnumHand
    ): IBlockState = getStateFromMeta(placer.getHeldItem(hand).metadata)

    //    Drops    //

    private val map: Map<Int, Array<Pair<HiiragiMaterial, Int>>> = mapOf(
        0 to arrayOf(MaterialElements.COPPER to 4, MaterialElements.NICKEL to 2, MaterialElements.COBALT to 1)
    )

    override fun quantityDropped(random: Random): Int = 0

    override fun getDrops(
        drops: NonNullList<ItemStack>,
        world: IBlockAccess,
        pos: BlockPos,
        state: IBlockState,
        fortune: Int
    ) {
        val pairs = map[state.getValue(HiiragiProperty.TYPE16)]!!
        fun addDrop(index: Int) {
            pairs.getOrNull(index)?.let {
                drops.add(RMItems.MATERIAL_ORE.getItemStack(it.first, it.second))
            }
        }
        addDrop(0)
        addDrop(1)
        addDrop(2)
    }

    //    HiiragiEntry    //

    override fun registerModel() {
        this.setModelSame()
    }

}