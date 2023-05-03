package ragi_materials.core.event

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.RagiMaterials
import ragi_materials.core.enchant.EnchantmentMaterial
import ragi_materials.core.material.IMaterialDropBlock
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.util.getPart

object DropMaterialEvent {

    private val REGISTRY: LinkedHashMap<Block, IMaterialDropBlock> = linkedMapOf()

    fun register(block: Block, obj: IMaterialDropBlock) {
        REGISTRY[block] = obj
    }

    init {

        register(Blocks.STONE, object : IMaterialDropBlock {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                when (state.block.getMetaFromState(state)) {
                    1 -> {
                        drops.add(getPart(PartRegistry.ORE, MaterialRegistry.COPPER))
                    }

                    3 -> {
                        drops.add(getPart(PartRegistry.ORE, MaterialRegistry.TIN))
                    }

                    5 -> {
                        drops.add(getPart(PartRegistry.ORE, MaterialRegistry.ZINC))
                    }

                    else -> {}
                }
            }
        })

        register(Blocks.SAND, object : IMaterialDropBlock {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                val meta = state.block.getMetaFromState(state)
                if (meta == 1) {
                    drops.add(getPart(PartRegistry.ORE, MaterialRegistry.HEMATITE))
                    drops.add(getPart(PartRegistry.ORE, MaterialRegistry.BAUXITE))
                    drops.add(getPart(PartRegistry.ORE, MaterialRegistry.RUTILE))
                } else {
                    drops.add(getPart(PartRegistry.DUST, MaterialRegistry.BORAX))
                    drops.add(getPart(PartRegistry.DUST, MaterialRegistry.SILICON_DIOXIDE))
                }
            }
        })

        register(Blocks.GRAVEL, object : IMaterialDropBlock {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.add(ItemStack(Items.FLINT))
                drops.add(getPart(PartRegistry.ORE, MaterialRegistry.MAGNETITE))
            }
        })

        register(Blocks.BONE_BLOCK, object : IMaterialDropBlock {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.add(getPart(PartRegistry.ORE, MaterialRegistry.LIME))
                RagiMaterials.LOGGER.debug("Lime Ore has dropped!")
            }
        })

    }

    @SubscribeEvent
    fun harvestDrops(event: BlockEvent.HarvestDropsEvent) {
        val state = event.state
        val player = event.harvester
        val setEnchants = EnchantmentHelper.getEnchantments(player.getHeldItem(EnumHand.MAIN_HAND)).keys
        //EnchantmentMaterialがツールのエンチャントに含まれる場合
        if (EnchantmentMaterial in setEnchants) {
            //デフォルトのドロップ一覧をリセット
            event.drops.clear()
            //blockからドロップ条件を取得
            val rule = REGISTRY[state.block] ?: object : IMaterialDropBlock {
                override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {}
            }
            //ドロップ一覧を更新
            rule.getDrops(event.drops, event.world, event.pos, state, event.fortuneLevel, player, event.isSilkTouching)
        }
    }
}