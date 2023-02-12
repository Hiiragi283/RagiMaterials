package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
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
        defaultState =
            blockState.baseState.withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false)
                .withProperty(WEST, false).withProperty(TYPE, EnumSalt.EMPTY)
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(2.0F)
        setHarvestLevel("axe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(3.0F)
        soundType = SoundType.WOOD
        tickRandomly = true
        unlocalizedName = registryName
    }

    //あるブロックが隣接したブロックと同じかどうかを判定するメソッド
    private fun canConnectTo(world: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        val state = world.getBlockState(pos)
        val stateN = world.getBlockState(pos.north())
        val stateE = world.getBlockState(pos.east())
        val stateS = world.getBlockState(pos.south())
        val stateW = world.getBlockState(pos.west())
        //facingの値によって比較する方角を指定する
        return when (facing) {
            EnumFacing.NORTH -> {
                state.block == stateN.block
            }

            EnumFacing.EAST -> {
                state.block == stateE.block
            }

            EnumFacing.SOUTH -> {
                state.block == stateS.block
            }

            EnumFacing.WEST -> {
                state.block == stateW.block
            }

            else -> false
        }

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
        return state.withProperty(NORTH, connectN).withProperty(EAST, connectE).withProperty(SOUTH, connectS)
            .withProperty(WEST, connectW)
    }

    //ブロックの当たり判定を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(
        blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos
    ): AxisAlignedBB {
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

    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        val stack = player.getHeldItem(hand)
        val drop = EntityItem(
            world, pos.x.toDouble(), pos.y.toDouble() + 0.5, pos.z.toDouble(), ItemStack(Items.BUCKET)
        ) //空のバケツのEntityItem
        drop.setPickupDelay(0) //即座に回収できるようにする
        //サーバー側の場合
        if (!world.isRemote) {
            //塩田ブロックが空の場合
            if (state.getValue(TYPE) == EnumSalt.EMPTY) {
                //水バケツの場合
                if (stack.item == Items.WATER_BUCKET) {
                    world.setBlockState(pos, state.withProperty(TYPE, EnumSalt.WATER), 2) //stateの更新
                    stack.shrink(1) //stackの縮小
                    world.spawnEntity(drop) //dropをスポーン
                    RagiLogger.infoDebug("Water was placed!")
                }
                //forgeのバケツ，かつNBTタグがnullでない場合
                else if (stack.item.registryName.toString() == "forge:bucketfilled" && stack.tagCompound !== null) {
                    val tag = stack.tagCompound!! //tagを取得
                    //tagがFluidNameを持っている場合
                    if (tag.hasKey("FluidName")) {
                        val fluid = tag.getString("FluidName") //FluidNameの取得
                        //Saltwaterの場合
                        if (fluid == "saltwater") {
                            world.setBlockState(pos, state.withProperty(TYPE, EnumSalt.SALTWATER), 2) //stateの更新
                            stack.shrink(1) //stackの縮小
                            world.spawnEntity(drop) //dropをスポーン
                            RagiLogger.infoDebug("Saltwater was placed!")
                        }
                        //Brineの場合
                        else if (fluid == "brine") {
                            world.setBlockState(pos, state.withProperty(TYPE, EnumSalt.BRINE), 2) //stateの更新
                            stack.shrink(1) //stackの縮小
                            world.spawnEntity(drop) //dropをスポーン
                            RagiLogger.infoDebug("Brine was placed!")
                        }
                    }
                }
            }
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:item.bucket.empty"), SoundCategory.BLOCKS, 1.0f, 1.0f
            ) //SEを再生
            world.scheduleUpdate(pos, this, 200) //tick更新を200 tick後に設定
        }
        return true
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }

    //Random Tickで呼び出されるメソッド
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        super.updateTick(world, pos, state, rand)
        //サーバー側の場合
        if (!world.isRemote) {
            //完成品の場合分け
            val stack: ItemStack = when (state.getValue(TYPE)) {
                EnumSalt.WATER -> ItemStack(RagiInit.ItemDust, 1, 11)
                EnumSalt.SALTWATER -> ItemStack(RagiInit.ItemDust, 1, 12)
                EnumSalt.BRINE -> ItemStack(RagiInit.ItemDust, 1, 3)
                else -> ItemStack.EMPTY
            }
            if (stack.item != Items.AIR) {
                val drop = EntityItem(
                    world, pos.x.toDouble(), pos.y.toDouble() + 0.75, pos.z.toDouble(), stack
                ) //完成品のEntityItem
                drop.setPickupDelay(0) //即座に回収できるようにする
                world.spawnEntity(drop) //dropをスポーン
                world.setBlockState(pos, state.withProperty(TYPE, EnumSalt.EMPTY), 2) //stateの更新
                world.playSound(
                    null, pos, RagiUtils.getSound("minecraft:block.sand.break"), SoundCategory.BLOCKS, 1.0f, 1.0f
                ) //SEを再生
            }
        }
    }

    enum class EnumSalt(val indexInt: Int, private val indexName: String) : IStringSerializable {
        EMPTY(0, "empty"), WATER(1, "water"), SALTWATER(2, "saltwater"), BRINE(3, "brine");

        override fun getName(): String {
            return this.indexName
        }
    }
}