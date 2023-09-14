package hiiragi283.material.api.registry

import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.event.ShapeRegistryEvent
import hiiragi283.material.api.event.ShapeTypeRegistryEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.createAllParts
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge

object HiiragiRegistries {

    @JvmField
    val BLOCK: HiiragiForgeRegistry<HiiragiEntry.BLOCK, Block> = HiiragiForgeRegistry("Block")

    @JvmField
    val ITEM: HiiragiForgeRegistry<HiiragiEntry.ITEM, Item> = HiiragiForgeRegistry("Item")

    @JvmField
    val MACHINE_RECIPE: HiiragiRegistry<String, IMachineRecipe> = HiiragiRegistry("Machine Recipe")

    @JvmField
    val MATERIAL: HiiragiRegistry<String, HiiragiMaterial> = HiiragiRegistry("Material")

    @JvmField
    val MATERIAL_INDEX: HiiragiRegistry<Int, HiiragiMaterial> = HiiragiRegistry("Material Index")

    @JvmField
    val PART: HiiragiRegistry<String, HiiragiPart> = HiiragiRegistry("Part")

    @JvmField
    val SHAPE: HiiragiRegistry<String, HiiragiShape> = HiiragiRegistry("Shape")

    @JvmField
    val SHAPE_TYPE: HiiragiRegistry<String, HiiragiShapeType> = HiiragiRegistry("ShapeType")

    fun registerShape() {
        val event = ShapeRegistryEvent(SHAPE)
        MinecraftForge.EVENT_BUS.post(event)
        SHAPE.lock()
    }

    fun registerShapeType() {
        val event = ShapeTypeRegistryEvent(SHAPE_TYPE)
        MinecraftForge.EVENT_BUS.post(event)
        SHAPE_TYPE.lock()
    }

    fun registerMaterial() {
        val event = MaterialRegistryEvent(MATERIAL)
        MinecraftForge.EVENT_BUS.post(event)
        MATERIAL.lock()
        MATERIAL_INDEX.sort { (index: Int, _: HiiragiMaterial) -> index }
        MATERIAL_INDEX.lock()
    }

    fun registerPart() {
        createAllParts().forEach { part: HiiragiPart ->
            part.getOreDicts().forEach { oreDict: String ->
                PART.register(oreDict, part)
            }
        }
    }

}