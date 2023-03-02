package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.IStringSerializable
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandlerItem
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockSaltPond : Block(Material.WOOD) {

    //private変数の宣言
    companion object {
        val NORTH: PropertyBool = PropertyBool.create("north")
        val EAST: PropertyBool = PropertyBool.create("east")
        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val TYPE: PropertyEnum<EnumSalt> = PropertyEnum.create("type", EnumSalt::class.java)
    }

    private val registryName = "salt_pond"
    private val box = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)

    init {
        defaultState = blockState.baseState.withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(TYPE, EnumSalt.EMPTY)
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(2.0F)
        setHarvestLevel("axe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(3.0F)
        soundType = SoundType.WOOD
        unlocalizedName = registryName
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..2) {
            tooltip.add(I18n.format("text.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //あるブロックが隣接したブロックと同じかどうかを判定するメソッド
    private fun canConnectTo(world: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        val posTo = pos.offset(facing)
        val stateTo = world.getBlockState(posTo)
        val shape = stateTo.getBlockFaceShape(world, posTo, facing)
        return stateTo.block != Blocks.AIR && (stateTo.block == this || shape == BlockFaceShape.SOLID || shape == BlockFaceShape.UNDEFINED)
    }

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, NORTH, EAST, SOUTH, WEST, TYPE)
    }

    @Deprecated("Deprecated in Java")
    override fun getActualState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState {
        val connectN = canConnectTo(world, pos, EnumFacing.NORTH)
        val connectE = canConnectTo(world, pos, EnumFacing.EAST)
        val connectS = canConnectTo(world, pos, EnumFacing.SOUTH)
        val connectW = canConnectTo(world, pos, EnumFacing.WEST)
        return state.withProperty(NORTH, connectN).withProperty(EAST, connectE).withProperty(SOUTH, connectS).withProperty(WEST, connectW)
    }

    //面の種類を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(worldIn: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return when (face) {
            //下 -> SOLID
            EnumFacing.DOWN -> BlockFaceShape.SOLID
            //それ以外 -> UNDEFINED
            else -> BlockFaceShape.UNDEFINED
        }
    }

    //ブロックの当たり判定を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(state: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return box
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //Blockstateからブロックを取得し、更にそこからアイテムを取得して返す
        return Item.getItemFromBlock(state.block)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        val type = state.getValue(TYPE)
        return type.indexInt
    }

    //メタデータからBlockstateを得るメソッド
    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return when (meta % 4) {
            1 -> this.defaultState.withProperty(TYPE, EnumSalt.WATER)
            2 -> this.defaultState.withProperty(TYPE, EnumSalt.SALTWATER)
            3 -> this.defaultState.withProperty(TYPE, EnumSalt.BRINE)
            else -> this.defaultState.withProperty(TYPE, EnumSalt.EMPTY)
        }
    }

    //ブロックがフルブロックかどうかを判定するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    //ブロックが光を透過するかを判定するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        //サーバー側，かつ塩田ブロックが空の場合
        if (!world.isRemote && state.getValue(TYPE) == EnumSalt.EMPTY) {
            val stack = player.getHeldItem(hand)
            //アイテムのIDに"bucket"が含まれない場合
            if (!stack.item.registryName!!.resourcePath.contains("bucket")) {
                val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents !== null)) {
                    when (fluidItem.tankProperties[0].contents!!.fluid) {
                        //水の場合
                        FluidRegistry.getFluid("water") -> {
                            placeFluid(world, pos, state, fluidItem, EnumSalt.WATER)
                            RagiLogger.infoDebug("Water was placed!")
                        }
                        //Saltwaterの場合
                        FluidRegistry.getFluid("saltwater") -> {
                            placeFluid(world, pos, state, fluidItem, EnumSalt.SALTWATER)
                            RagiLogger.infoDebug("Saltwater was placed!")
                        }
                        //Brineの場合
                        FluidRegistry.getFluid("brine") -> {
                            placeFluid(world, pos, state, fluidItem, EnumSalt.BRINE)
                            RagiLogger.infoDebug("Brine was placed!")
                        }
                    }
                } else RagiLogger.infoDebug("The content is null!")
            }
        }
        return true
    }

    //液体を設置した際の挙動をまとめたメソッド
    private fun placeFluid(world: World, pos: BlockPos, state: IBlockState, fluidItem: IFluidHandlerItem, type: EnumSalt) {
        //サーバー側
        if (!world.isRemote) {
            fluidItem.drain(1000, true) //液体を1000 mb汲み取る
            world.setBlockState(pos, state.withProperty(TYPE, type), 2) //stateの更新
            world.scheduleUpdate(pos, this, 200) //tick更新を200 tick後に設定
            world.playSound(null, pos, RagiUtils.getSound("minecraft:item.bucket.empty"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
        }
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }

    //Random Tickで呼び出されるメソッド
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        //サーバー側の場合
        if (!world.isRemote) {
            //完成品の場合分け
            val stack: ItemStack = when (state.getValue(TYPE)) {
                EnumSalt.WATER -> ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.SALT.index)
                EnumSalt.SALTWATER -> ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.MAGNESIUM_CHLORIDE.index)
                EnumSalt.BRINE -> ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.LITHIUM_CHLORIDE.index)
                else -> ItemStack.EMPTY
            }
            if (stack.item != Items.AIR) {
                val drop = EntityItem(world, pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.75, pos.z.toDouble() + 0.5, stack) //完成品のEntityItem
                drop.setPickupDelay(0) //即座に回収できるようにする
                drop.motionX = 0.0
                drop.motionY = 0.0
                drop.motionZ = 0.0 //ドロップ時の飛び出しを防止
                world.spawnEntity(drop) //dropをスポーン
                world.setBlockState(pos, state.withProperty(TYPE, EnumSalt.EMPTY), 2) //stateの更新
                world.playSound(null, pos, RagiUtils.getSound("minecraft:block.sand.break"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
            }
        }
    }

    enum class EnumSalt(val indexInt: Int, val fluid: String) : IStringSerializable {
        EMPTY(0, "empty"), WATER(1, "water"), SALTWATER(2, "saltwater"), BRINE(3, "brine");

        override fun getName(): String {
            return this.fluid
        }
    }
}