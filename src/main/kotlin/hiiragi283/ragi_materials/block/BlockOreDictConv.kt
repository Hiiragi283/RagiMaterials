package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.oredict.OreDictionary
import java.util.*
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.world.IBlockAccess

class BlockOreDictConv: Block(Material.WOOD) {

    private val registryName = "oredict_converter"
    private val box = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0)

    //コンストラクタの初期化
    init {
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(5.0F)
        setHarvestLevel("axe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(5.0F)
        soundType = SoundType.WOOD
        unlocalizedName = registryName
    }

    //ブロックの当たり判定を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(
        blockState: IBlockState,
        worldIn: IBlockAccess,
        pos: BlockPos
    ): AxisAlignedBB {
        return box //エンチャント台と同じ
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //Blockstateからブロックを取得し、更にそこからアイテムを取得して返す
        return Item.getItemFromBlock(state.block)
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

    //ブロックを右クリックした時に呼ばれるメソッド
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
        if (!world.isRemote) {
            //プレイヤーが利き手に持っているアイテムを取得
            val stack = player.getHeldItem(hand)
            val count = stack.count
            var result = ItemStack.EMPTY
            //stackががEMPTYでない場合
            if (stack.item != Items.AIR) {
                //鉱石辞書の数値IDの配列を取得
                val arrayIDs = OreDictionary.getOreIDs(stack)
                //配列内の各IDに対して実行
                for (id in arrayIDs) {
                    //IDからString型の鉱石辞書を取得
                    val oreDict = OreDictionary.getOreName(id)
                    //鉱石辞書から紐づいたstackのNonNullListを取得
                    val listStacks = OreDictionary.getOres(oreDict)
                    //NonNullList内の各stackOreに対して実行
                    for (stackOre in listStacks) {
                        //stackOreのmodidがragi_materialsの場合
                        if (stackOre.item.registryName.toString().split(":")[0] == Reference.MOD_ID) {
                            result = ItemStack(stackOre.item, count, stackOre.metadata) //resultにstackOreを代入し終了
                            break
                        }
                    }
                }
                //resultがEMPTYでない場合
                if (result.item !== Items.AIR) {
                    stack.shrink(count) //stackを1つ減らす
                    val drop = EntityItem(
                        world, pos.x.toDouble(), pos.y.toDouble() + 1.0, pos.z.toDouble(), result
                    ) //ドロップアイテムを生成
                    drop.setPickupDelay(0) //即座に回収できるようにする
                    world.spawnEntity(drop) //dropをスポーン
                    RagiUtils.soundHypixel(world, pos) //SEを再生
                    RagiLogger.infoDebug("Item was converted!")
                }
            }
        }
        return true
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }
}