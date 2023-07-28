package hiiragi283.material.integration

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.TagUtil
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags
import net.minecraft.tag.BlockTags
import net.minecraft.tag.ItemTags
import net.minecraft.util.Identifier

object RagiIntegrationCore {

    fun init() {

        //    Minecraft    //

        PartRegistry.registerTags(
            mapOf(
                BlockTags.ANVIL to HiiragiPart(HiiragiShape.WILDCARD, MaterialElements.IRON),
                BlockTags.CAULDRONS to HiiragiPart(HiiragiShape.WILDCARD, MaterialElements.IRON),
                BlockTags.COAL_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.COAL),
                BlockTags.COPPER_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialElements.COPPER),
                BlockTags.DIAMOND_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.DIAMOND),
                BlockTags.EMERALD_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.EMERALD),
                BlockTags.GOLD_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialElements.GOLD),
                BlockTags.IRON_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialElements.IRON),
                BlockTags.LAPIS_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.LAPIS),
                BlockTags.LOGS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.PLANKS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.REDSTONE_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.REDSTONE),
                BlockTags.SAPLINGS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.STONE_BRICKS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.STONE),
                BlockTags.STONE_PRESSURE_PLATES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.STONE),
                BlockTags.WOODEN_BUTTONS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.WOODEN_FENCES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.WOODEN_PRESSURE_PLATES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.WOODEN_SLABS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.WOODEN_STAIRS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                BlockTags.WOODEN_TRAPDOORS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ConventionalBlockTags.GLASS_BLOCKS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.GLASS),
                ConventionalBlockTags.GLASS_PANES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.GLASS),
                ConventionalBlockTags.QUARTZ_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.QUARTZ),

                ConventionalItemTags.COAL to HiiragiPart(ShapeRegistry.GEM, MaterialCommon.COAL),
                ConventionalItemTags.DIAMONDS to HiiragiPart(ShapeRegistry.GEM, MaterialCommon.DIAMOND),
                ConventionalItemTags.EMERALDS to HiiragiPart(ShapeRegistry.GEM, MaterialCommon.EMERALD),
                ConventionalItemTags.GLASS_BLOCKS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.GLASS),
                ConventionalItemTags.GLASS_PANES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.GLASS),
                ConventionalItemTags.LAPIS to HiiragiPart(ShapeRegistry.GEM, MaterialCommon.LAPIS),
                ConventionalItemTags.QUARTZ to HiiragiPart(ShapeRegistry.GEM, MaterialCommon.QUARTZ),
                ConventionalItemTags.QUARTZ_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.QUARTZ),
                ConventionalItemTags.REDSTONE_DUSTS to HiiragiPart(ShapeRegistry.DUST, MaterialCommon.REDSTONE),
                ItemTags.ANVIL to HiiragiPart(HiiragiShape.WILDCARD, MaterialElements.IRON),
                ItemTags.COAL_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.COAL),
                ItemTags.COPPER_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialElements.COPPER),
                ItemTags.DIAMOND_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.DIAMOND),
                ItemTags.EMERALD_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.EMERALD),
                ItemTags.GOLD_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialElements.GOLD),
                ItemTags.IRON_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialElements.IRON),
                ItemTags.LAPIS_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.LAPIS),
                ItemTags.LOGS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.PLANKS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.REDSTONE_ORES to HiiragiPart(ShapeRegistry.ORE_STONE, MaterialCommon.REDSTONE),
                ItemTags.SAPLINGS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.STONE_BRICKS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.STONE),
                ItemTags.WOODEN_BUTTONS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.WOODEN_FENCES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.WOODEN_PRESSURE_PLATES to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.WOODEN_SLABS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.WOODEN_STAIRS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
                ItemTags.WOODEN_TRAPDOORS to HiiragiPart(HiiragiShape.WILDCARD, MaterialCommon.WOOD),
            )
        )

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("coal_blocks")) {
            this.add(Identifier("coal_block"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("amethyst_blocks")) {
            this.add(Identifier("amethyst_block"))
            this.add(Identifier("budding_amethyst"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("iron_blocks")) {
            this.add(Identifier("iron_block"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("copper_blocks")) {
            this.add(Identifier("copper_block"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("gold_blocks")) {
            this.add(Identifier("gold_block"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("diamond_blocks")) {
            this.add(Identifier("diamond_block"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("lapis_blocks")) {
            this.add(Identifier("lapis_block"))
        }

        RMResourcePack.addItemTag(TagUtil.getItemTagCommon("quartz_blocks")) {
            this.add(Identifier("smooth_quartz"))
            this.add(Identifier("chiseled_quartz_block"))
            this.add(Identifier("quartz_block"))
            this.add(Identifier("quartz_bricks"))
            this.add(Identifier("quartz_pillar"))
        }

    }

}