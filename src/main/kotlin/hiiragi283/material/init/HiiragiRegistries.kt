package hiiragi283.material.init

import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.event.ShapeRegistryEvent
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialType
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.createAllParts
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge

object HiiragiRegistries {

    //    Forge    //

    @Deprecated("Only Internal Use")
    @JvmField
    val BLOCK: HiiragiRegistry<String, Block> = HiiragiRegistry("Block")

    @Deprecated("Only Internal Use")
    @JvmField
    val ITEM: HiiragiRegistry<String, Item> = HiiragiRegistry("Item")

    //    Recipe    //

    @JvmField
    val BLOCK_MACHINE: HiiragiRegistry<MachineType, ModuleMachineBlock> = HiiragiRegistry("Module Machine Block")

    fun registerModuleMachine() {
        MachineType.values()
            .filter { it != MachineType.NONE }
            .map(::ModuleMachineBlock)
            .forEach(ModuleMachineBlock::register)
        BLOCK_MACHINE.lock()
    }

    @JvmField
    val MACHINE_RECIPE: HiiragiRegistry<MachineType, HiiragiRegistry<ResourceLocation, IMachineRecipe>> =
        HiiragiRegistry("Machine Recipe")

    fun initRecipeType() {
        MachineType.values().forEach { type: MachineType ->
            MACHINE_RECIPE.register(type, HiiragiRegistry("Machine Recipe - ${type.name}", true))
        }
        MACHINE_RECIPE.lock()
    }

    @JvmField
    val RECIPE_MODULE: HiiragiRegistry<MachineType, RecipeModuleItem> = HiiragiRegistry("Recipe Module")

    fun registerRecipeModule() {
        MachineType.values()
            .filter { it != MachineType.NONE }
            .map(::RecipeModuleItem)
            .forEach(RecipeModuleItem::register)
        RECIPE_MODULE.lock()
    }

    //    Material    //

    @JvmField
    val MATERIAL: HiiragiRegistry<String, HiiragiMaterial> = HiiragiRegistry("Material")

    @JvmField
    val MATERIAL_INDEX: HiiragiRegistry<Int, HiiragiMaterial> = HiiragiRegistry("Material Index")

    fun registerMaterial() {
        val event = MaterialRegistryEvent()
        MinecraftForge.EVENT_BUS.post(event)
        MATERIAL.sort { (name: String, _: HiiragiMaterial) -> name }
        MATERIAL.lock()
        MATERIAL_INDEX.sort { (index: Int, _: HiiragiMaterial) -> index }
        MATERIAL_INDEX.lock()
    }

    @Deprecated("Only Internal Use")
    @JvmField
    val MATERIAL_BLOCK: HiiragiRegistry<HiiragiShape, PartConvertible.BLOCK> = HiiragiRegistry("Material Block")

    @Deprecated("Only Internal Use")
    @JvmField
    val MATERIAL_ITEM: HiiragiRegistry<HiiragiShape, PartConvertible.ITEM> = HiiragiRegistry("Material Item")

    @JvmField
    val MATERIAL_TYPE: HiiragiRegistry<String, MaterialType> = HiiragiRegistry("Material Type")

    @JvmField
    val MATERIAL_SMELTED: HiiragiRegistry<HiiragiMaterial, Pair<HiiragiMaterial, Int>> =
        HiiragiRegistry("Material Smelted", true)

    //    Part    //

    @Deprecated("Will be removed")
    @JvmField
    val PART: HiiragiRegistry<String, HiiragiPart> = HiiragiRegistry("Part")

    fun registerPart() {
        createAllParts().forEach { part: HiiragiPart ->
            part.getOreDicts().forEach { oreDict: String ->
                PART.register(oreDict, part)
            }
        }
    }

    //    Shape    //

    @JvmField
    val SHAPE: HiiragiRegistry<String, HiiragiShape> = HiiragiRegistry("Shape")

    fun registerShape() {
        val event = ShapeRegistryEvent()
        MinecraftForge.EVENT_BUS.post(event)
        SHAPE.sort { (name: String, _: HiiragiShape) -> name }
        SHAPE.lock()
    }

}