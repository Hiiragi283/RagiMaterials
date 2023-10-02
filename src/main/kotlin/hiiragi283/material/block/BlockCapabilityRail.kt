package hiiragi283.material.block

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.RMReference
import hiiragi283.material.api.block.property.HiiragiProperty
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.tile.TileEntityCapabilityRail
import hiiragi283.material.util.itemStack
import net.minecraft.block.BlockRailBase
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.properties.IProperty
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Mirror
import net.minecraft.util.Mirror.*
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import java.util.*

object BlockCapabilityRail : BlockRailBase(false), ITileEntityProvider, HiiragiEntry.BLOCK {

    init {
        blockHardness = 5.0f
        blockResistance = 5.0f
        creativeTab = HiiragiCreativeTabs.COMMON
        defaultState = blockState.baseState.withProperty(HiiragiProperty.RAIL_SHAPE, EnumRailDirection.NORTH_SOUTH)
        translationKey = "${RMReference.MOD_ID}.capability_rail"
        setRegistryName(RMReference.MOD_ID, "capability_rail")
    }

    //    HiiragiEntry    //

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this)

    //    Block    //

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = itemBlock

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack = itemBlock.itemStack()

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, HiiragiProperty.RAIL_SHAPE)

    override fun getMetaFromState(state: IBlockState): Int = 0 or state.getValue(HiiragiProperty.RAIL_SHAPE).metadata

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "defaultState.withProperty(HiiragiProperty.RAIL_SHAPE, EnumRailDirection.byMetadata(meta and 7))",
            "hiiragi283.material.api.block.property.HiiragiProperty",
            "net.minecraft.block.BlockRailBase.EnumRailDirection"
        )
    )
    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(HiiragiProperty.RAIL_SHAPE, EnumRailDirection.byMetadata(meta and 7))

    @Deprecated("Deprecated in Java")
    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState = when (rot) {
        Rotation.CLOCKWISE_180 -> when (state.getValue(HiiragiProperty.RAIL_SHAPE)) {
            EnumRailDirection.ASCENDING_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_WEST
            )

            EnumRailDirection.ASCENDING_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_EAST
            )

            EnumRailDirection.ASCENDING_NORTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_SOUTH
            )

            EnumRailDirection.ASCENDING_SOUTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_NORTH
            )

            EnumRailDirection.SOUTH_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_WEST
            )

            EnumRailDirection.SOUTH_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_EAST
            )

            EnumRailDirection.NORTH_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.SOUTH_EAST
            )

            EnumRailDirection.NORTH_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.SOUTH_WEST
            )

            else -> state
        }

        Rotation.COUNTERCLOCKWISE_90 -> when (state.getValue(HiiragiProperty.RAIL_SHAPE)) {
            EnumRailDirection.ASCENDING_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_NORTH
            )

            EnumRailDirection.ASCENDING_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_SOUTH
            )

            EnumRailDirection.ASCENDING_NORTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_WEST
            )

            EnumRailDirection.ASCENDING_SOUTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_EAST
            )

            EnumRailDirection.SOUTH_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_EAST
            )

            EnumRailDirection.SOUTH_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.SOUTH_EAST
            )

            EnumRailDirection.NORTH_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.SOUTH_WEST
            )

            EnumRailDirection.NORTH_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_WEST
            )

            EnumRailDirection.NORTH_SOUTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.EAST_WEST
            )

            EnumRailDirection.EAST_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_SOUTH
            )

            else -> state
        }

        Rotation.CLOCKWISE_90 -> when (state.getValue(HiiragiProperty.RAIL_SHAPE)) {
            EnumRailDirection.ASCENDING_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_SOUTH
            )

            EnumRailDirection.ASCENDING_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_NORTH
            )

            EnumRailDirection.ASCENDING_NORTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_EAST
            )

            EnumRailDirection.ASCENDING_SOUTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.ASCENDING_WEST
            )

            EnumRailDirection.SOUTH_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.SOUTH_WEST
            )

            EnumRailDirection.SOUTH_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_WEST
            )

            EnumRailDirection.NORTH_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_EAST
            )

            EnumRailDirection.NORTH_EAST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.SOUTH_EAST
            )

            EnumRailDirection.NORTH_SOUTH -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.EAST_WEST
            )

            EnumRailDirection.EAST_WEST -> state.withProperty(
                HiiragiProperty.RAIL_SHAPE,
                EnumRailDirection.NORTH_SOUTH
            )

            else -> state
        }

        else -> state
    }

    @Deprecated("Deprecated in Java")
    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState {
        val railDirection: EnumRailDirection = state.getValue(HiiragiProperty.RAIL_SHAPE)
        return when (mirrorIn) {
            NONE -> state
            LEFT_RIGHT -> when (railDirection) {
                EnumRailDirection.ASCENDING_NORTH -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.ASCENDING_SOUTH
                )

                EnumRailDirection.ASCENDING_SOUTH -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.ASCENDING_NORTH
                )

                EnumRailDirection.SOUTH_EAST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.NORTH_EAST
                )

                EnumRailDirection.SOUTH_WEST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.NORTH_WEST
                )

                EnumRailDirection.NORTH_WEST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.SOUTH_WEST
                )

                EnumRailDirection.NORTH_EAST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.SOUTH_EAST
                )

                else -> state
            }

            FRONT_BACK -> when (railDirection) {
                EnumRailDirection.ASCENDING_EAST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.ASCENDING_WEST
                )

                EnumRailDirection.ASCENDING_WEST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.ASCENDING_EAST
                )

                EnumRailDirection.SOUTH_EAST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.SOUTH_WEST
                )

                EnumRailDirection.SOUTH_WEST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.SOUTH_EAST
                )

                EnumRailDirection.NORTH_WEST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.NORTH_EAST
                )

                EnumRailDirection.NORTH_EAST -> state.withProperty(
                    HiiragiProperty.RAIL_SHAPE,
                    EnumRailDirection.NORTH_WEST
                )

                else -> state
            }
        }
    }

    //    BlockRailBase    //

    override fun getShapeProperty(): IProperty<EnumRailDirection> = HiiragiProperty.RAIL_SHAPE

    //    ITileEntityProvider    //

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity = TileEntityCapabilityRail()

}