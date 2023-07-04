package hiiragi283.material.common

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.MaterialPartBlockItem
import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.material.MaterialPart
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.part.PartType
import hiiragi283.material.common.item.RespawnBookItem
import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack

object RagiRegistry {

    val materialBlockSet: MutableSet<MaterialPart<BlockState>> = mutableSetOf()
    val materialItemSet: MutableSet<MaterialPart<ItemStack>> = mutableSetOf()

    private fun register(entry: HiiragiEntry) {
        entry.register()
        entry.registerModel()
        entry.registerRecipe()
        entry.registerTag()
    }

    private fun register(sets: Set<HiiragiEntry>) {
        sets.forEach(::register)
    }

    fun loadBlocks() {
        //Initialize Material Blocks
        MaterialRegistry.getMaterials().forEach { material ->
            PartRegistry.getParts()
                .filter { it.type == PartType.BLOCK }
                .filter { it.predicate(material) }
                .map { MaterialPartBlock(material, it) }
                .forEach {
                    materialBlockSet.add(it)
                    register(it)
                }
        }

    }

    fun loadBlockItems() {
        //Initialize Material BlockItems
        materialBlockSet
            .filterIsInstance<MaterialPartBlock>()
            .map(::MaterialPartBlockItem)
            .forEach(::register)
    }

    fun loadFluids() {
        //Initialize Fluids and Buckets
        MaterialRegistry.getMaterials()
            .map(::MaterialFluid)
            .forEach(::register)
    }

    fun loadItems() {

        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            PartRegistry.getParts()
                .filter { it.type == PartType.ITEM }
                .filter { it.predicate(material) }
                .map { MaterialPartItem(material, it) }
                .forEach {
                    materialItemSet.add(it)
                    register(it)
                }
        }
    }

}