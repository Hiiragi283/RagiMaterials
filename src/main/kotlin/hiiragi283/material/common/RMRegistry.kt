package hiiragi283.material.common

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.createMetalBlock
import hiiragi283.material.api.block.createOreBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.*
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.with
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.item.RespawnBookItem
import hiiragi283.material.common.util.TagUtil
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.util.Identifier

object RMRegistry {

    fun loadBlocks() {
        //Initialize Material Blocks
        MaterialRegistry.getMaterials()
            .filter { it.isMetal() }
            .map { createMetalBlock(it) }
            .forEach { it?.register() }

        //Initialize Material Ore Blocks
        MaterialRegistry.getMaterials()
            .filter { it.hasOre() }
            .map { createOreBlock(it) }
            .forEach { it?.register() }

    }

    fun loadFluids() {
        //Initialize Fluids and Buckets
        MaterialRegistry.getMaterials()
            .map { MaterialFluid(it) }
            .forEach { it.register() }
    }

    fun loadItems() {

        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .asSequence()
                .filter { it.isValid(material) }
                .map { it with material }
                .filterNot { it.isEmpty() }
                .map { MaterialItem.of(it) }
                .toList()
                .forEach { it.register() }
        }
    }

    fun loadToolItems() {
        //Initialize Material Tool Items
        MaterialRegistry.getMaterials()
            .filter { it.hasToolProperty() }
            .flatMap {
                listOf(
                    createMaterialAxe(it),
                    createMaterialFile(it),
                    createMaterialHammer(it),
                    createMaterialHoe(it),
                    createMaterialPickaxe(it),
                    createMaterialShovel(it)
                )
            }
            .forEach { it.register() }
    }

    private val TOOL_TAGS: Map<String, MutableList<Identifier>> = mapOf(
        "axes" to mutableListOf(),
        "files" to mutableListOf(),
        "hammers" to mutableListOf(),
        "hoes" to mutableListOf(),
        "pickaxes" to mutableListOf(),
        "shovels" to mutableListOf(),
        "swords" to mutableListOf()
    )

    fun addToolTag(name: String, tool: HiiragiEntry.ITEM) {
        TOOL_TAGS[name]?.add(tool.getIdentifier())
    }

    fun loadTags() {
        //Initialize Material Item Tags
        MaterialRegistry.getMaterials().forEach { material ->
            RMResourcePack.addItemTag(
                hiiragiId(material.name),
                ShapeRegistry.getShapes()
                    .filter { it.isValid(material) }
                    .map { it with material }
                    .filterNot { it.isEmpty() }
                    .map { it.getTadId() }
                    .let { TagUtil.addTags(it) }
            )
        }

        //Initialize Material Tool Tags
        TOOL_TAGS.forEach {
            RMResourcePack.addItemTag(commonId(it.key), TagUtil.addIds(it.value))
        }
    }

}