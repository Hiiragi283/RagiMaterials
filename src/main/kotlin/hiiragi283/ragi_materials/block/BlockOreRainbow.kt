package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.init.LootTableRegistry
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.storage.loot.LootContext
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockOreRainbow(ID: String): BlockBase(ID, Material.ROCK, 1) {

    init {
        blockHardness = 3.0f
        blockResistance = 3.0f
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    override fun quantityDropped(random: Random): Int = 0 //シフト右クリックでドロップするので，通常の確率は0

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        var result = super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ)
        if (player.isSneaking) {
            RagiUtil.dropItemAtPlayer(player, ItemStack(this)) //シフト右クリックで回収
            world.setBlockToAir(pos) //ブロックを消去
            result = true
        }
        return result
    }

    override fun onBlockDestroyedByPlayer(world: World, pos: BlockPos, state: IBlockState) {
        world.setBlockState(pos, state, 2) //ブロックの破壊を阻止
    }

    override fun harvestBlock(world: World, player: EntityPlayer, pos: BlockPos, state: IBlockState, te: TileEntity?, stack: ItemStack) {
        if (!world.isRemote) {
            val builder = LootContext.Builder(world as WorldServer).build()
            val lootTable = world.lootTableManager.getLootTableFromLocation(LootTableRegistry.OreRainbow)
            val results = lootTable.generateLootForPools(world.rand, builder)
            for (result in results) {
                if (!result.isEmpty) RagiUtil.dropItemAtPlayer(player, result) //生成物を足元にドロップ
            }
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}