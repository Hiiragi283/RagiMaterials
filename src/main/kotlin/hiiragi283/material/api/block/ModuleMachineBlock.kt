package hiiragi283.material.api.block

import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.item.ModuleMachineItemBlock
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.init.HiiragiCreativeTabs
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.*
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("OVERRIDE_DEPRECATION")
class ModuleMachineBlock(val type: MachineType) : HiiragiBlockContainer.Holdable<TileEntityModuleMachine>(
    Material.IRON,
    "machine_${type.lowercase()}",
    { TileEntityModuleMachine() }
) {

    override val itemBlock: HiiragiItemBlock = ModuleMachineItemBlock(this)

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
        defaultState = defaultState.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH)
    }

    fun createMachineStack(material: HiiragiMaterial?, machineProperty: MachineProperty): ItemStack {
        val machineStack: ItemStack = itemStack(material)
        machineStack.getOrCreateSubCompound(HiiragiNBTUtil.BLOCK_ENTITY_TAG)
            .setTag(HiiragiNBTUtil.MACHINE_PROPERTY, machineProperty.serialize())
        return machineStack
    }

    //    HiiragiBlockContainer.Holdable    //

    override fun getDrops(
        drops: NonNullList<ItemStack>,
        world: IBlockAccess,
        pos: BlockPos,
        state: IBlockState,
        fortune: Int
    ) {
        drops.add(getStackWithTileNBT(world, pos, itemStack(getTile<TileEntityModuleMachine>(world, pos)?.material)))
    }

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack = getStackWithTileNBT(world, pos, itemStack(getTile<TileEntityModuleMachine>(world, pos)?.material))

    override fun <T : HiiragiTileEntity> getTileNBT(tile: T): NBTTagCompound =
        NBTTagCompound().also { tag: NBTTagCompound ->
            tag.setTag(
                HiiragiNBTUtil.MACHINE_PROPERTY,
                super.getTileNBT(tile).getCompoundTag(HiiragiNBTUtil.MACHINE_PROPERTY)
            )
        }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, BlockHorizontal.FACING)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(BlockHorizontal.FACING).horizontalIndex

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
    ): IBlockState = defaultState.withProperty(BlockHorizontal.FACING, placer.horizontalFacing.opposite)


    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta).opposite)

    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState =
        state.withRotation(mirrorIn.toRotation(state.getValue(BlockHorizontal.FACING)))

    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState =
        state.withProperty(BlockHorizontal.FACING, rot.rotate(state.getValue(BlockHorizontal.FACING)))

    //    HiiragiEntry    //

    override fun onRegister() {
        super.onRegister()
        HiiragiRegistries.BLOCK_MACHINE.register(type, this)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockColor(): IBlockColor = HiiragiMaterial.BLOCK_COLOR

    @SideOnly(Side.CLIENT)
    override fun getItemColor(): IItemColor = HiiragiMaterial.ITEM_COLOR

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        this.setModelAlt(registryName!!.appendBefore("machine/"))
    }

}