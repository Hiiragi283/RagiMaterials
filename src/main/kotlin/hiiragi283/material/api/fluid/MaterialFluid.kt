package hiiragi283.material.api.fluid

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.compat.tcon.HiiragiTConPlugin
import net.minecraft.init.SoundEvents
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraftforge.fluids.Fluid

fun getStill(material: HiiragiMaterial): ResourceLocation = if (material.isSolid() && HiiragiTConPlugin.enabled())
    HiiragiTConPlugin.getResourceLocation("blocks/fluids/molten_metal")
else ResourceLocation("blocks/concrete_white")

fun getFlow(material: HiiragiMaterial): ResourceLocation = if (material.isSolid() && HiiragiTConPlugin.enabled())
    HiiragiTConPlugin.getResourceLocation("blocks/fluids/molten_metal_flow")
else ResourceLocation("blocks/concrete_white")


class MaterialFluid(
    val material: HiiragiMaterial,
    still: ResourceLocation = getStill(material),
    flowing: ResourceLocation = getFlow(material)
) : Fluid(material.name, still, flowing) {

    init {
        color = material.color
        //標準状態で固体 -> 温度は融点に等しい
        if (material.isSolid()) {
            temperature = material.tempMelt
            luminosity = 15
        }
        //標準状態で液体 -> 温度は298 K
        else if (material.isLiquid()) {
            temperature = 298
        } else if (material.isGas()) {
            temperature = 298
            isGaseous = true
            density *= -1 //密度を負にすると上に落ちる
        }
    }

    override fun getEmptySound(): SoundEvent =
        if (material.isSolid()) SoundEvents.ITEM_BUCKET_EMPTY_LAVA else SoundEvents.ITEM_BUCKET_EMPTY

    override fun getFillSound(): SoundEvent =
        if (material.isSolid()) SoundEvents.ITEM_BUCKET_FILL_LAVA else SoundEvents.ITEM_BUCKET_FILL

    override fun getUnlocalizedName(): String = material.translationKey

}