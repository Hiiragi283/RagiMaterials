package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.oredict.OreDictionary
import java.util.*
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockOreDictConv : Block(Material.WOOD) {

    private val registryName = "oredict_converter"
    private val box = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0)

    init {
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(5.0F)
        setHarvestLevel("axe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(5.0F)
        soundType = SoundType.WOOD
        unlocalizedName = registryName
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

    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(blockState: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return box //エンチャント台と同じ
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
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
                    RagiUtils.spawnItemAtPlayer(world, player, result)
                    RagiUtils.soundHypixel(world, pos) //SEを再生
                    RagiLogger.infoDebug("Item was converted!")
                }
            }
        }
        return true
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..1) {
            tooltip.add(I18n.format("text.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}