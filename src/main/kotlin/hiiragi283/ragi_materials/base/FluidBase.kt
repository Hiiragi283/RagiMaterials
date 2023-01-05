package hiiragi283.ragi_materials.base

import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid

open class FluidBase(ID: String) :
    Fluid(
        ID,
        ResourceLocation("minecraft:blocks/wool_colored_white"),
        ResourceLocation("minecraft:blocks/wool_colored_white")
    )