package ragi_materials.metallurgy.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiRegistry
import ragi_materials.core.block.BlockContainerHoldable
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.getPart
import ragi_materials.core.util.toBool
import ragi_materials.core.util.toInt
import ragi_materials.metallurgy.tile.TileBloom
import ragi_materials.metallurgy.tile.TileBloomery
import java.util.*

class BlockBloomery : BlockContainerHoldable<TileBloomery>("bloomery", Material.ROCK, TileBloomery::class.java, 4) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(world: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return when (face) {
            //下 -> SOLID
            EnumFacing.DOWN -> BlockFaceShape.SOLID
            //それ以外 -> UNDEFINED
            else -> BlockFaceShape.UNDEFINED
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(blockState: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0)

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        val stack = ItemStack(this)
        drops.add(stack)
        world.getTileEntity(pos)?.let {
            if (it is TileBase) drops.add(getPart(PartRegistry.ORE, it.material))
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiProperty.ACTIVE)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(RagiProperty.ACTIVE).toInt()

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(RagiProperty.ACTIVE, meta.toBool())", "ragi_materials.core.block.property.RagiProperty", "ragi_materials.core.util.toBool"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(RagiProperty.ACTIVE, meta.toBool())

    fun isActive(state: IBlockState): Boolean = state.getValue(RagiProperty.ACTIVE)

    fun setActive(state: IBlockState, active: Boolean): IBlockState = state.withProperty(RagiProperty.ACTIVE, active)

    //    Event    //

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        //Bloomeryから素材の情報を取得
        var material = RagiMaterial.EMPTY
        var tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileBloomery) material = tile.material
        world.destroyBlock(pos, false) //Bloomeryを破壊

        //Bloomに取得した素材の情報を代入する
        world.setBlockState(pos, RagiRegistry.BlockBloom.defaultState) //Bloomを設置
        //playSound(world, pos, soundType.breakSound)
        tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileBloom) tile.material = material
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}