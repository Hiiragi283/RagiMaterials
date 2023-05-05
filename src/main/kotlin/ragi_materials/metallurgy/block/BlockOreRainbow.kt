package ragi_materials.metallurgy.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.block.BlockBase
import ragi_materials.core.item.ItemBlockBase
import ragi_materials.core.util.dropItemAtPlayer
import ragi_materials.main.client.model.ICustomModel
import ragi_materials.main.client.model.ModelManager
import java.util.*

class BlockOreRainbow(ID: String) : BlockBase(ID, Material.ROCK, 1), ICustomModel {

    override val itemBlock = ItemBlockBase(this)

    init {
        blockHardness = 3.0f
        blockResistance = 3.0f
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    override fun quantityDropped(random: Random): Int = 0 //シフト右クリックでドロップするので，通常の確率は0

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        var result = super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ)
        if (player.isSneaking) {
            dropItemAtPlayer(player, ItemStack(this)) //シフト右クリックで回収
            world.setBlockToAir(pos) //ブロックを消去
            result = true
        }
        return result
    }

    override fun onPlayerDestroy(world: World, pos: BlockPos, state: IBlockState) {
        world.setBlockState(pos, state, 2) //ブロックの破壊を阻止
    }

    override fun harvestBlock(world: World, player: EntityPlayer, pos: BlockPos, state: IBlockState, te: TileEntity?, stack: ItemStack) {
        /*if (!world.isRemote) {
            val builder = LootContext.Builder(world as WorldServer).build()
            val lootTable = world.lootTableManager.getLootTableFromLocation(RagiRegistry.OreRainbow)
            val results = lootTable.generateLootForPools(world.rand, builder)
            for (result in results) {
                if (!result.isEmpty) dropItemAtPlayer(player, result) //生成物を足元にドロップ
            }
        }*/
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    override fun registerCustomModel() {
        ModelManager.setStateMapperAlt(RagiRegistry.BlockOreRainbow, ModelResourceLocation("${RagiMaterials.MOD_ID}:ore", "stone_rainbow"))
        ModelManager.setModel(this)
    }
}