package hiiragi283.ragi_materials.integration

import hiiragi283.ragi_materials.unused.BlockForgeFurnaceOld
import hiiragi283.ragi_materials.unused.BlockLitForgeFurnace
import hiiragi283.ragi_materials.unused.FFHelper
import net.minecraft.block.BlockDispenser
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object PluginVanilla {

    fun loadPostInit() {
        overrideProperty()
        registerDispenser()
    }

    //バニラのブロックの様々な情報を上書きするメソッド
    private fun overrideProperty() {
        //光源レベルの上書き
        //イワライナー氏から着想を得ました
        Blocks.BROWN_MUSHROOM.setLightLevel(0.0F)
        Blocks.LIT_REDSTONE_ORE.setLightLevel(0.0F)
        Blocks.POWERED_COMPARATOR.setLightLevel(0.0F)
        Blocks.POWERED_REPEATER.setLightLevel(0.0F)
        Blocks.REDSTONE_TORCH.setLightLevel(0.0F)
        Blocks.REDSTONE_WIRE.setLightLevel(0.0F)
        //光の透過率を上書きする
        Blocks.FLOWING_LAVA.setLightOpacity(0)
        Blocks.FLOWING_WATER.setLightOpacity(0)
        Blocks.ICE.setLightOpacity(0)
        Blocks.LAVA.setLightOpacity(0)
        Blocks.WATER.setLightOpacity(0)
        //採掘レベルの新規追加
        Blocks.CACTUS.setHarvestLevel("axe", 0)
        Blocks.CARPET.setHarvestLevel("axe", 0)
        Blocks.GLASS.setHarvestLevel("pickaxe", 0)
        Blocks.GLASS_PANE.setHarvestLevel("pickaxe", 0)
        Blocks.HAY_BLOCK.setHarvestLevel("axe", 0)
        Blocks.LEAVES2.setHarvestLevel("axe", 0)
        Blocks.LEAVES.setHarvestLevel("axe", 0)
        Blocks.LEVER.setHarvestLevel("pickaxe", 0)
        Blocks.NETHER_WART_BLOCK.setHarvestLevel("axe", 0)
        Blocks.PISTON.setHarvestLevel("pickaxe", 0)
        Blocks.SEA_LANTERN.setHarvestLevel("pickaxe", 0)
        Blocks.SKULL.setHarvestLevel("axe", 0)
        Blocks.SPONGE.setHarvestLevel("shovel", 0)
        Blocks.STAINED_GLASS.setHarvestLevel("pickaxe", 0)
        Blocks.STAINED_GLASS_PANE.setHarvestLevel("pickaxe", 0)
        Blocks.STICKY_PISTON.setHarvestLevel("pickaxe", 0)
    }

    //ディスペンサーに機能を登録するメソッド
    private fun registerDispenser() {
        //燃料を投入
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.COAL, object : BehaviorDefaultDispenseItem() {
            override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
                //各値の取得
                val world = source.world
                val pos = source.blockPos.offset(source.blockState.getValue(BlockDispenser.FACING))
                val state = world.getBlockState(pos)
                val block = state.block
                //blockがForge Furnaceの場合
                return if (block is BlockForgeFurnaceOld && stack.item == Items.COAL) {
                    FFHelper.setFuel(world, pos, state, stack) //燃料を投入
                } else if (block is BlockLitForgeFurnace) stack else super.dispenseStack(source, stack)
            }
        })
    }
}