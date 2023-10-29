package hiiragi283.material.compat

import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.util.shareOredict
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.oredict.OreDictionary

object HiiragiVanillaPlugin : HiiragiPluginBase("minecraft", "Minecraft", { true }) {

    override fun registerMaterial() {
        MaterialCompats.REDSTONE.register()
        MaterialCompats.LAPIS.register()
        MaterialCompats.GLOWSTONE.register()
        MaterialCompats.ENDER_PEARL.register()
        MaterialCompats.BLAZE.register()
    }

    override fun onInit(event: FMLInitializationEvent) {
        //reloadOreDicts()
        PartDictionary.register(Blocks.STONE, 0, HiiragiShapes.STONE.getPart(MaterialCommons.STONE))
        PartDictionary.register(Blocks.NETHERRACK, 0, HiiragiShapes.STONE.getPart(MaterialCommons.NETHERRACK))
        PartDictionary.register(Blocks.END_STONE, 0, HiiragiShapes.STONE.getPart(MaterialCommons.END_STONE))

        PartDictionary.register(Items.COAL, 0, HiiragiShapes.GEM.getPart(MaterialCommons.COAL))
        PartDictionary.register(Items.COAL, 1, HiiragiShapes.GEM.getPart(MaterialCommons.CHARCOAL))
        PartDictionary.register(
            Items.DYE,
            EnumDyeColor.WHITE.metadata,
            HiiragiShapes.DUST.getPart(MaterialCommons.APATITE)
        )
        PartDictionary.register(Items.ENDER_PEARL, 0, HiiragiShapes.GEM.getPart(MaterialCompats.ENDER_PEARL))
        PartDictionary.register(Items.GUNPOWDER, 0, HiiragiShapes.DUST.getPart(MaterialCommons.GUNPOWDER))
        PartDictionary.register(Items.STICK, 0, HiiragiShapes.STICK.getPart(MaterialCommons.WOOD))
        PartDictionary.register(Items.SUGAR, 0, HiiragiShapes.DUST.getPart(MaterialCommons.SUGAR))
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        shareOredict(HiiragiShapes.DUST.getOreDict(MaterialCommons.GUNPOWDER), "gunpowder")
        shareOredict(HiiragiShapes.GEM.getOreDict(MaterialCompats.ENDER_PEARL), "enderpearl")
    }

    fun reloadOreDicts() {
        // tree- and wood-related things
        OreDictionary.registerOre("logWood", ItemStack(Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("logWood", ItemStack(Blocks.LOG2, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("plankWood", ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("slabWood", ItemStack(Blocks.WOODEN_SLAB, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("stairWood", Blocks.OAK_STAIRS)
        OreDictionary.registerOre("stairWood", Blocks.SPRUCE_STAIRS)
        OreDictionary.registerOre("stairWood", Blocks.BIRCH_STAIRS)
        OreDictionary.registerOre("stairWood", Blocks.JUNGLE_STAIRS)
        OreDictionary.registerOre("stairWood", Blocks.ACACIA_STAIRS)
        OreDictionary.registerOre("stairWood", Blocks.DARK_OAK_STAIRS)
        OreDictionary.registerOre("fenceWood", Blocks.OAK_FENCE)
        OreDictionary.registerOre("fenceWood", Blocks.SPRUCE_FENCE)
        OreDictionary.registerOre("fenceWood", Blocks.BIRCH_FENCE)
        OreDictionary.registerOre("fenceWood", Blocks.JUNGLE_FENCE)
        OreDictionary.registerOre("fenceWood", Blocks.DARK_OAK_FENCE)
        OreDictionary.registerOre("fenceWood", Blocks.ACACIA_FENCE)
        OreDictionary.registerOre("fenceGateWood", Blocks.OAK_FENCE_GATE)
        OreDictionary.registerOre("fenceGateWood", Blocks.SPRUCE_FENCE_GATE)
        OreDictionary.registerOre("fenceGateWood", Blocks.BIRCH_FENCE_GATE)
        OreDictionary.registerOre("fenceGateWood", Blocks.JUNGLE_FENCE_GATE)
        OreDictionary.registerOre("fenceGateWood", Blocks.DARK_OAK_FENCE_GATE)
        OreDictionary.registerOre("fenceGateWood", Blocks.ACACIA_FENCE_GATE)
        OreDictionary.registerOre("doorWood", Items.ACACIA_DOOR)
        OreDictionary.registerOre("doorWood", Items.BIRCH_DOOR)
        OreDictionary.registerOre("doorWood", Items.DARK_OAK_DOOR)
        OreDictionary.registerOre("doorWood", Items.OAK_DOOR)
        OreDictionary.registerOre("doorWood", Items.JUNGLE_DOOR)
        OreDictionary.registerOre("doorWood", Items.SPRUCE_DOOR)
        OreDictionary.registerOre("stickWood", Items.STICK)
        OreDictionary.registerOre("treeSapling", ItemStack(Blocks.SAPLING, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("treeLeaves", ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("treeLeaves", ItemStack(Blocks.LEAVES2, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("vine", Blocks.VINE)
        // Ores
        OreDictionary.registerOre("oreGold", Blocks.GOLD_ORE)
        OreDictionary.registerOre("oreIron", Blocks.IRON_ORE)
        OreDictionary.registerOre("oreLapis", Blocks.LAPIS_ORE)
        OreDictionary.registerOre("oreDiamond", Blocks.DIAMOND_ORE)
        OreDictionary.registerOre("oreRedstone", Blocks.REDSTONE_ORE)
        OreDictionary.registerOre("oreEmerald", Blocks.EMERALD_ORE)
        OreDictionary.registerOre("oreQuartz", Blocks.QUARTZ_ORE)
        OreDictionary.registerOre("oreCoal", Blocks.COAL_ORE)
        // ingots/nuggets
        OreDictionary.registerOre("ingotIron", Items.IRON_INGOT)
        OreDictionary.registerOre("ingotGold", Items.GOLD_INGOT)
        OreDictionary.registerOre("ingotBrick", Items.BRICK)
        OreDictionary.registerOre("ingotBrickNether", Items.NETHERBRICK)
        OreDictionary.registerOre("nuggetGold", Items.GOLD_NUGGET)
        OreDictionary.registerOre("nuggetIron", Items.IRON_NUGGET)
        // gems and dusts
        OreDictionary.registerOre("gemDiamond", Items.DIAMOND)
        OreDictionary.registerOre("gemEmerald", Items.EMERALD)
        OreDictionary.registerOre("gemQuartz", Items.QUARTZ)
        OreDictionary.registerOre("gemPrismarine", Items.PRISMARINE_SHARD)
        OreDictionary.registerOre("dustPrismarine", Items.PRISMARINE_CRYSTALS)
        OreDictionary.registerOre("dustRedstone", Items.REDSTONE)
        OreDictionary.registerOre("dustGlowstone", Items.GLOWSTONE_DUST)
        OreDictionary.registerOre("gemLapis", ItemStack(Items.DYE, 1, 4))
        // storage blocks
        OreDictionary.registerOre("blockGold", Blocks.GOLD_BLOCK)
        OreDictionary.registerOre("blockIron", Blocks.IRON_BLOCK)
        OreDictionary.registerOre("blockLapis", Blocks.LAPIS_BLOCK)
        OreDictionary.registerOre("blockDiamond", Blocks.DIAMOND_BLOCK)
        OreDictionary.registerOre("blockRedstone", Blocks.REDSTONE_BLOCK)
        OreDictionary.registerOre("blockEmerald", Blocks.EMERALD_BLOCK)
        OreDictionary.registerOre("blockQuartz", Blocks.QUARTZ_BLOCK)
        OreDictionary.registerOre("blockCoal", Blocks.COAL_BLOCK)
        // crops
        OreDictionary.registerOre("cropWheat", Items.WHEAT)
        OreDictionary.registerOre("cropPotato", Items.POTATO)
        OreDictionary.registerOre("cropCarrot", Items.CARROT)
        OreDictionary.registerOre("cropNetherWart", Items.NETHER_WART)
        OreDictionary.registerOre("sugarcane", Items.REEDS)
        OreDictionary.registerOre("blockCactus", Blocks.CACTUS)
        // misc materials
        OreDictionary.registerOre("dye", ItemStack(Items.DYE, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("paper", ItemStack(Items.PAPER))
        // mob drops
        OreDictionary.registerOre("slimeball", Items.SLIME_BALL)
        OreDictionary.registerOre("enderpearl", Items.ENDER_PEARL)
        OreDictionary.registerOre("bone", Items.BONE)
        OreDictionary.registerOre("gunpowder", Items.GUNPOWDER)
        OreDictionary.registerOre("string", Items.STRING)
        OreDictionary.registerOre("netherStar", Items.NETHER_STAR)
        OreDictionary.registerOre("leather", Items.LEATHER)
        OreDictionary.registerOre("feather", Items.FEATHER)
        OreDictionary.registerOre("egg", Items.EGG)
        // records
        /*OreDictionary.registerOre("record", Items.RECORD_13)
        OreDictionary.registerOre("record", Items.RECORD_CAT)
        OreDictionary.registerOre("record", Items.RECORD_BLOCKS)
        OreDictionary.registerOre("record", Items.RECORD_CHIRP)
        OreDictionary.registerOre("record", Items.RECORD_FAR)
        OreDictionary.registerOre("record", Items.RECORD_MALL)
        OreDictionary.registerOre("record", Items.RECORD_MELLOHI)
        OreDictionary.registerOre("record", Items.RECORD_STAL)
        OreDictionary.registerOre("record", Items.RECORD_STRAD)
        OreDictionary.registerOre("record", Items.RECORD_WARD)
        OreDictionary.registerOre("record", Items.RECORD_11)
        OreDictionary.registerOre("record", Items.RECORD_WAIT)*/
        // blocks
        /*OreDictionary.registerOre("dirt", Blocks.DIRT)
        OreDictionary.registerOre("grass", Blocks.GRASS)
        OreDictionary.registerOre("stone", Blocks.STONE)
        OreDictionary.registerOre("cobblestone", Blocks.COBBLESTONE)
        OreDictionary.registerOre("gravel", Blocks.GRAVEL)
        OreDictionary.registerOre("sand", ItemStack(Blocks.SAND, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("sandstone", ItemStack(Blocks.SANDSTONE, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("sandstone", ItemStack(Blocks.RED_SANDSTONE, 1, OreDictionary.WILDCARD_VALUE))
        OreDictionary.registerOre("netherrack", Blocks.NETHERRACK)
        OreDictionary.registerOre("obsidian", Blocks.OBSIDIAN)
        OreDictionary.registerOre("glowstone", Blocks.GLOWSTONE)
        OreDictionary.registerOre("endstone", Blocks.END_STONE)
        OreDictionary.registerOre("torch", Blocks.TORCH)
        OreDictionary.registerOre("workbench", Blocks.CRAFTING_TABLE)
        OreDictionary.registerOre("blockSlime", Blocks.SLIME_BLOCK)
        OreDictionary.registerOre(
            "blockPrismarine",
            ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.ROUGH.metadata)
        )
        OreDictionary.registerOre(
            "blockPrismarineBrick",
            ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.BRICKS.metadata)
        )
        OreDictionary.registerOre(
            "blockPrismarineDark",
            ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.DARK.metadata)
        )
        OreDictionary.registerOre("stoneGranite", ItemStack(Blocks.STONE, 1, 1))
        OreDictionary.registerOre("stoneGranitePolished", ItemStack(Blocks.STONE, 1, 2))
        OreDictionary.registerOre("stoneDiorite", ItemStack(Blocks.STONE, 1, 3))
        OreDictionary.registerOre("stoneDioritePolished", ItemStack(Blocks.STONE, 1, 4))
        OreDictionary.registerOre("stoneAndesite", ItemStack(Blocks.STONE, 1, 5))
        OreDictionary.registerOre("stoneAndesitePolished", ItemStack(Blocks.STONE, 1, 6))
        OreDictionary.registerOre("blockGlassColorless", Blocks.GLASS)*/
        PartDictionary.register(Blocks.GLASS, 0, HiiragiShapes.BLOCK.getPart(MaterialCommons.GLASS))
        PartDictionary.register(
            Blocks.STAINED_GLASS,
            OreDictionary.WILDCARD_VALUE,
            HiiragiShapes.BLOCK.getPart(MaterialCommons.GLASS)
        )
        //blockGlass{Color} is added below with dyes
        PartDictionary.register(Blocks.GLASS_PANE, 0, HiiragiShapes.PANE.getPart(MaterialCommons.GLASS))
        PartDictionary.register(
            Blocks.STAINED_GLASS_PANE,
            OreDictionary.WILDCARD_VALUE,
            HiiragiShapes.PANE.getPart(MaterialCommons.GLASS)
        )
        //paneGlass{Color} is added below with dyes
        OreDictionary.registerOre("wool", ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE))
        //wool{Color} is added below with dyes
        // chests
        /*OreDictionary.registerOre("chest", Blocks.CHEST)
        OreDictionary.registerOre("chest", Blocks.ENDER_CHEST)
        OreDictionary.registerOre("chest", Blocks.TRAPPED_CHEST)
        OreDictionary.registerOre("chestWood", Blocks.CHEST)
        OreDictionary.registerOre("chestEnder", Blocks.ENDER_CHEST)
        OreDictionary.registerOre("chestTrapped", Blocks.TRAPPED_CHEST)*/
    }

}