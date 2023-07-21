package hiiragi283.material.fluid

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialState
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid

private val location = ResourceLocation("blocks/concrete_powder_white")

abstract class HiiragiFluid(name: String) : Fluid(name, location, location) {

    companion object {

        @JvmStatic
        fun of(material: HiiragiMaterial, init: HiiragiFluid.() -> Unit = {}): HiiragiFluid {
            val fluid = object : HiiragiFluid(material.name) {

                init {
                    color = material.color
                    when (material.getState()) {
                        //標準状態で固体 -> 温度は融点に等しい
                        MaterialState.SOLID -> {
                            temperature = material.tempMelt
                            luminosity = 15
                        }
                        //標準状態で液体 -> 温度は298 K
                        MaterialState.LIQUID -> {
                            temperature = 298
                        }
                        //標準状態で気体 -> 温度は298 K
                        MaterialState.GAS -> {
                            temperature = 298
                            isGaseous = true
                            density *= -1 //密度を負にすると上に落ちる
                        }
                    }
                }

                override fun getUnlocalizedName(): String = "material.${material.name}"

            }
            fluid.init()
            return fluid
        }

    }

}