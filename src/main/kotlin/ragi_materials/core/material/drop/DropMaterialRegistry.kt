package ragi_materials.core.material.drop

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.util.getPart

object DropMaterialRegistry {

    private val REGISTRY: LinkedHashMap<Block, IMaterialDrop> = linkedMapOf()

    fun register(block: Block, obj: IMaterialDrop) {
        REGISTRY[block] = obj
    }

    fun getObject(block: Block) = REGISTRY[block] ?: object : IMaterialDrop {
        override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {}
    }

    init {

        register(Blocks.STONE, object : IMaterialDrop {
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

        register(Blocks.SAND, object : IMaterialDrop {
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

        register(Blocks.GRAVEL, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.MAGNETITE), 4 - fortune)
            }
        })

        register(Blocks.GOLD_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.ORE, MaterialRegistry.GOLD, 2 + fortune))
            }
        })

        register(Blocks.IRON_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.ORE, MaterialRegistry.IRON, 2 + fortune))
            }
        })

        register(Blocks.COAL_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.COAL, 2 + fortune))
                addWeightedDrop(drops, world, getPart(PartRegistry.CRYSTAL, MaterialRegistry.ANTHRACITE, 1), 16 - fortune)
            }
        })

        register(Blocks.GLASS, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addDrops(drops, getPart(PartRegistry.DUST, MaterialRegistry.GLASS))
            }
        })

        register(Blocks.LAPIS_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.LAPIS, 8 + fortune * 2))
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.SAPPHIRE, 1 + fortune))
            }
        })

        register(Blocks.SANDSTONE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.NITER), 4 - fortune)
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.BORAX), 4 - fortune)
            }
        })

        register(Blocks.DIAMOND_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.DIAMOND, 2 + fortune))
                addDrops(drops, getPart(PartRegistry.DUST, MaterialRegistry.CARBON, 1 + fortune))
            }
        })

        register(Blocks.LIT_REDSTONE_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.DUST, MaterialRegistry.REDSTONE, 8 + fortune * 2))
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.RUBY, 1 + fortune))
            }
        })

        register(Blocks.NETHERRACK, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.SULFUR), 16)
            }
        })

        register(Blocks.GLOWSTONE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.DUST, MaterialRegistry.GLOWSTONE, 4))
                addDrops(drops, getPart(PartRegistry.ORE, MaterialRegistry.FLUORITE))
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.URANIUM_238), 4)
            }
        })

        register(Blocks.STAINED_GLASS, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addDrops(drops, getPart(PartRegistry.DUST, MaterialRegistry.GLASS))
            }
        })

        register(Blocks.EMERALD_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.EMERALD, 1 + fortune))
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.AQUAMARINE, 1 + fortune))
            }
        })

        register(Blocks.QUARTZ_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.QUARTZ, 2 + fortune * 2))
            }
        })

        register(Blocks.PACKED_ICE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addDrops(drops, getPart(PartRegistry.CRYSTAL, MaterialRegistry.CRYOLITE, 1 + fortune))
            }
        })

        register(Blocks.RED_SANDSTONE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.HEMATITE), 4 - fortune)
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.BAUXITE), 4 - fortune)
                addWeightedDrop(drops, world, getPart(PartRegistry.ORE, MaterialRegistry.RUTILE), 16 - fortune)
            }
        })

        register(Blocks.BONE_BLOCK, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.APATITE), 4)
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.LIME), 4)
                addWeightedDrop(drops, world, getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM), 4)
            }
        })

    }
}