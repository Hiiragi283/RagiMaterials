package hiiragi283.ragi_materials.base

import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

open class FluidBase(ID: String) : Fluid(ID, ResourceLocation("minecraft:blocks/concrete_powder_white"), ResourceLocation("minecraft:blocks/concrete_powder_white")) {

    override fun getUnlocalizedName(): String = "material.${this.unlocalizedName}"

    override fun getLocalizedName(stack: FluidStack?): String {
        var localized = ""
        if (stack !== null) {
            val material = MaterialUtil.getMaterial(stack.fluid.name)
            if (material.type != TypeRegistry.LIQUID && material.type != TypeRegistry.GAS) {
                localized += "${I18n.format("fluid.molten")} "
            }
        }
        localized += I18n.format(getUnlocalizedName())
        return localized
    }

}