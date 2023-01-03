package hiiragi283.ragi_materials.base

import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid

class FluidBase(ID: String) :
        Fluid(ID, ResourceLocation("minecraft:blocks/water_still"), ResourceLocation("minecraft:blocks/water_flow"))