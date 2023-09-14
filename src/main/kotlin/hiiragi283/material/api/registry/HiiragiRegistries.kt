package hiiragi283.material.api.registry

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType

object HiiragiRegistries {

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

}