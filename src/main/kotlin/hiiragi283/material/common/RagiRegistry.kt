package hiiragi283.material.common

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.with
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.item.ForgeFileItem
import hiiragi283.material.common.item.ForgeHammerItem
import hiiragi283.material.common.item.RespawnBookItem
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.json.tags.JTag

object RagiRegistry {

    fun loadBlocks() {
        //Initialize Material Blocks
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.type == HiiragiShape.Type.BLOCK }
                .map { it with material }
                .filterNot { it.isEmpty() }
                .map { MaterialBlock.of(it) }
        }
    }

    fun loadFluids() {
        //Initialize Fluids and Buckets
        MaterialRegistry.getMaterials()
            .map { MaterialFluid(it) }
            .forEach { it.register() }
    }

    fun loadItems() {

        ForgeFileItem.register()
        ForgeHammerItem.register()
        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.type == HiiragiShape.Type.ITEM }
                .map { it with material }
                .filterNot { it.isEmpty() }
                .map { MaterialItem.of(it) }
                .forEach { it.register() }
        }
    }

    fun loadMaterialTags() {

        MaterialRegistry.getMaterials().forEach { material ->

            val jTag = JTag.tag()

            ShapeRegistry.getShapes()
                .map { it with material }
                .filterNot { it.isEmpty() }
                .forEach { jTag.tag(it.getTadId()) }

            RagiResourcePack.addItemTag(hiiragiId(material.name), jTag)

        }

    }

}