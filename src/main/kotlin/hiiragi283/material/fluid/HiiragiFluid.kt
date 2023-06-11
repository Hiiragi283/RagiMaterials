package hiiragi283.material.fluid

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.StandardState
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

private val location = ResourceLocation("blocks/concrete_powder_white")

class HiiragiFluid private constructor(val material: HiiragiMaterial) : Fluid(material.getName(), location, location) {

    init {
        color = material.getColor().rgb
        when (material.getState()) {
            //標準状態で固体 -> 温度は融点に等しい
            StandardState.SOLID -> {
                temperature = material.getTempMelt()
                luminosity = 15
            }
            //標準状態で液体 -> 温度は298 K
            StandardState.LIQUID -> {
                temperature = 298
            }
            //標準状態で気体 -> 温度は298 K
            StandardState.GAS -> {
                temperature = 298
                isGaseous = true
                density *= -1 //密度を負にすると上に落ちる
            }
        }
    }

    override fun getUnlocalizedName(): String = "material.${material.getName()}"

    companion object {
        fun register() {
            MaterialRegistry.getMaterials()
                .filter { it.hasTempBoil() && it.hasTempMelt() }
                .map { HiiragiFluid(it) }
                .forEach {
                    FluidRegistry.registerFluid(it)
                    FluidRegistry.addBucketForFluid(it)
                }
        }
    }

}