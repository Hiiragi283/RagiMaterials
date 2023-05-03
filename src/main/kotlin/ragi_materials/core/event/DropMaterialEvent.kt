package ragi_materials.core.event

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.enchant.EnchantmentMaterial
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.util.getPart

object DropMaterialEvent {

    private val REGISTRY: LinkedHashMap<Block, Function> = linkedMapOf()

    fun getRegistry() = REGISTRY.toMap()

    fun register(block: Block, obj: Function) {
        REGISTRY[block] = obj
    }

    init {

        register(Blocks.STONE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                when (state.block.getMetaFromState(state)) {
                    1 -> {
                        addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.COPPER), 8 - fortune)
                        addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.MANGANESE), 16 - fortune)
                    }

                    3 -> {
                        addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.ZINC), 8 - fortune)
                        addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.BISMUTH), 16 - fortune)
                    }

                    5 -> {
                        addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.TIN), 8 - fortune)
                        addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.CHROMIUM), 16 - fortune)
                    }

                    else -> {}
                }
            }
        })

        register(Blocks.PLANKS, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.WOOD))
            }
        })

        register(Blocks.SAND, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                val meta = state.block.getMetaFromState(state)
                if (meta == 1) {
                    addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.HEMATITE), 4 - fortune)
                    addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.BAUXITE), 4 - fortune)
                    addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.RUTILE), 16 - fortune)
                } else {
                    addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.SILICON_DIOXIDE), 2 - fortune)
                    addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.BORAX), 8 - fortune)
                }
            }
        })

        register(Blocks.GRAVEL, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.MAGNETITE), 4 - fortune)
            }
        })

        register(Blocks.GOLD_ORE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.ORE, MaterialRegistry.GOLD, 2 + fortune))
            }
        })

        register(Blocks.IRON_ORE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.ORE, MaterialRegistry.IRON, 2 + fortune))
            }
        })

        register(Blocks.COAL_ORE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.CRYSTAL, MaterialRegistry.COAL, 2 + fortune))
                addWeightedDrop(drops, world, getPart(PartRegistry.CRYSTAL, MaterialRegistry.ANTHRACITE, 1), 16 - fortune)
            }
        })

        register(Blocks.LOG, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.WOOD, 6))
            }
        })

        register(Blocks.LOG2, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.WOOD, 6))
            }
        })

        register(Blocks.GLASS, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.GLASS))
            }
        })

        register(Blocks.LAPIS_ORE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.CRYSTAL, MaterialRegistry.LAPIS, 8 + fortune * 2))
                drops.add(getPart(PartRegistry.CRYSTAL, MaterialRegistry.SAPPHIRE, 1 + fortune))
            }
        })

        register(Blocks.SANDSTONE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.NITER), 4 - fortune)
            }
        })

        register(Blocks.OBSIDIAN, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.OBSIDIAN, 4))
            }
        })

        register(Blocks.DIAMOND_ORE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.CRYSTAL, MaterialRegistry.DIAMOND, 2 + fortune))
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.CARBON, 1 + fortune))
            }
        })

        register(Blocks.LIT_REDSTONE_ORE, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                drops.add(getPart(PartRegistry.DUST, MaterialRegistry.REDSTONE, 8 + fortune * 2))
                drops.add(getPart(PartRegistry.CRYSTAL, MaterialRegistry.RUBY, 1 + fortune))
            }
        })

        register(Blocks.BONE_BLOCK, object : Function() {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.APATITE), 4)
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.LIME), 4)
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM), 4)
            }
        })

    }

    @SubscribeEvent
    fun harvestDrops(event: BlockEvent.HarvestDropsEvent) {
        val state = event.state
        //TNTの爆破による破壊など，プレイヤーが関わらない際のクラッシュを回避
        val player: EntityPlayer? = event.harvester
        if (player !== null) {
            val setEnchants = EnchantmentHelper.getEnchantments(player.getHeldItem(EnumHand.MAIN_HAND)).keys
            //EnchantmentMaterialがツールのエンチャントに含まれる場合
            if (EnchantmentMaterial in setEnchants) {
                //blockからドロップ条件を取得
                val function = REGISTRY[state.block] ?: object : Function() {
                    override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {}
                }
                //ドロップ一覧を更新
                function.getDrops(event.drops, event.world, event.pos, state, event.fortuneLevel, player, event.isSilkTouching)
            }
        }
    }

    abstract class Function {

        abstract fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean)

        fun addDrop(drops: MutableList<ItemStack>, stack: ItemStack) {
            drops.add(stack)
        }

        fun addWeightedDrop(drops: MutableList<ItemStack>, world: World, stack: ItemStack, weight: Int) {
            if (world.rand.nextInt(0.coerceAtLeast(weight)) == 0) drops.add(stack)
        }
    }
}