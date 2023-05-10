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
                        addWeightedDrop(drops, world, MaterialRegistry.COPPER.getPart(PartRegistry.ORE), 8 - fortune)
                        addWeightedDrop(drops, world, MaterialRegistry.MANGANESE.getPart(PartRegistry.ORE), 16 - fortune)
                    }

                    3 -> {
                        addWeightedDrop(drops, world, MaterialRegistry.ZINC.getPart(PartRegistry.ORE), 8 - fortune)
                        addWeightedDrop(drops, world, MaterialRegistry.BISMUTH.getPart(PartRegistry.ORE), 16 - fortune)
                    }

                    5 -> {
                        addWeightedDrop(drops, world, MaterialRegistry.TIN.getPart(PartRegistry.ORE), 8 - fortune)
                        addWeightedDrop(drops, world, MaterialRegistry.CHROMIUM.getPart(PartRegistry.ORE), 16 - fortune)
                    }

                    else -> {}
                }
            }
        })

        register(Blocks.SAND, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                val meta = state.block.getMetaFromState(state)
                if (meta == 1) {
                    addWeightedDrop(drops, world, MaterialRegistry.HEMATITE.getPart(PartRegistry.DUST), 4 - fortune)
                    addWeightedDrop(drops, world, MaterialRegistry.BAUXITE.getPart(PartRegistry.DUST), 4 - fortune)
                    addWeightedDrop(drops, world, MaterialRegistry.RUTILE.getPart(PartRegistry.DUST), 16 - fortune)
                } else {
                    addWeightedDrop(drops, world, MaterialRegistry.SILICON_DIOXIDE.getPart(PartRegistry.DUST), 2 - fortune)
                    addWeightedDrop(drops, world, MaterialRegistry.BORAX.getPart(PartRegistry.DUST), 8 - fortune)
                }
            }
        })

        register(Blocks.GRAVEL, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, MaterialRegistry.MAGNETITE.getPart(PartRegistry.ORE), 4 - fortune)
            }
        })

        register(Blocks.GOLD_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.GOLD.getPart(PartRegistry.ORE, 2 + fortune))
            }
        })

        register(Blocks.IRON_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.IRON.getPart(PartRegistry.ORE, 2 + fortune))
            }
        })

        register(Blocks.COAL_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.COAL.getPart(PartRegistry.CRYSTAL, 2 + fortune))
                addWeightedDrop(drops, world, MaterialRegistry.ANTHRACITE.getPart(PartRegistry.CRYSTAL, 1), 16 - fortune)
            }
        })

        register(Blocks.GLASS, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addDrops(drops, MaterialRegistry.GLASS.getPart(PartRegistry.DUST))
            }
        })

        register(Blocks.LAPIS_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.LAPIS.getPart(PartRegistry.CRYSTAL, 8 + fortune * 2))
                addDrops(drops, MaterialRegistry.SAPPHIRE.getPart(PartRegistry.CRYSTAL, 1 + fortune))
            }
        })

        register(Blocks.SANDSTONE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, MaterialRegistry.NITER.getPart(PartRegistry.ORE), 4 - fortune)
                addWeightedDrop(drops, world, MaterialRegistry.BORAX.getPart(PartRegistry.DUST), 4 - fortune)
            }
        })

        register(Blocks.DIAMOND_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.DIAMOND.getPart(PartRegistry.CRYSTAL, 2 + fortune))
                addDrops(drops, MaterialRegistry.CARBON.getPart(PartRegistry.DUST, 1 + fortune))
            }
        })

        register(Blocks.LIT_REDSTONE_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.REDSTONE.getPart(PartRegistry.DUST, 8 + fortune * 2))
                addDrops(drops, MaterialRegistry.RUBY.getPart(PartRegistry.CRYSTAL, 1 + fortune))
            }
        })

        register(Blocks.NETHERRACK, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, MaterialRegistry.SULFUR.getPart(PartRegistry.ORE), 16)
            }
        })

        register(Blocks.GLOWSTONE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.GLOWSTONE.getPart(PartRegistry.DUST, 4))
                addDrops(drops, MaterialRegistry.FLUORITE.getPart(PartRegistry.ORE))
                addWeightedDrop(drops, world, MaterialRegistry.URANIUM_238.getPart(PartRegistry.ORE), 4)
            }
        })

        register(Blocks.STAINED_GLASS, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addDrops(drops, MaterialRegistry.GLASS.getPart(PartRegistry.DUST))
            }
        })

        register(Blocks.EMERALD_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.EMERALD.getPart(PartRegistry.CRYSTAL, 1 + fortune))
                addDrops(drops, MaterialRegistry.AQUAMARINE.getPart(PartRegistry.CRYSTAL, 1 + fortune))
            }
        })

        register(Blocks.QUARTZ_ORE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                drops.clear()
                addDrops(drops, MaterialRegistry.QUARTZ.getPart(PartRegistry.CRYSTAL, 2 + fortune * 2))
            }
        })

        register(Blocks.PACKED_ICE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addDrops(drops, MaterialRegistry.CRYOLITE.getPart(PartRegistry.CRYSTAL, 1 + fortune))
            }
        })

        register(Blocks.RED_SANDSTONE, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, MaterialRegistry.HEMATITE.getPart(PartRegistry.ORE), 4 - fortune)
                addWeightedDrop(drops, world, MaterialRegistry.BAUXITE.getPart(PartRegistry.ORE), 4 - fortune)
                addWeightedDrop(drops, world, MaterialRegistry.RUTILE.getPart(PartRegistry.ORE), 16 - fortune)
            }
        })

        register(Blocks.BONE_BLOCK, object : IMaterialDrop {
            override fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean) {
                addWeightedDrop(drops, world, MaterialRegistry.APATITE.getPart(PartRegistry.DUST), 4)
                addWeightedDrop(drops, world, MaterialRegistry.LIME.getPart(PartRegistry.DUST), 4)
                addWeightedDrop(drops, world, MaterialRegistry.GYPSUM.getPart(PartRegistry.DUST), 4)
            }
        })

    }
}