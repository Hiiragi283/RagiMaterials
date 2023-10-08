package hiiragi283.material.api.material

import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShapes
import net.minecraft.item.EnumDyeColor

object MaterialDyeColor {

    private val COLOR_MAP: Map<String, HiiragiMaterial> = EnumDyeColor.values()
        .map { dyeColor: EnumDyeColor ->
            materialOf("color_${dyeColor.dyeColorName}", -1) {
                color = dyeColor.colorValue
                oreDictAlt.add(dyeColor.dyeColorName)
            }
        }
        .associateBy { it.oreDictAlt[0] }

    fun getMaterial(name: String): HiiragiMaterial? = COLOR_MAP[name]

    fun getMaterial(color: EnumDyeColor): HiiragiMaterial = getMaterial(color.dyeColorName)!!

    fun getPart(color: EnumDyeColor): HiiragiPart = HiiragiShapes.DYE.getPart(getMaterial(color))

}