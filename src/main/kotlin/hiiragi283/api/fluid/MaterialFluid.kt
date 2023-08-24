package hiiragi283.api.fluid

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialState
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.init.SoundEvents
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraftforge.fluids.Fluid

class MaterialFluid(
    val material: HiiragiMaterial,
    still: ResourceLocation = ResourceLocation("blocks/concrete_white"),
    flowing: ResourceLocation = still
) : Fluid(material.name, still, flowing) {

    init {
        color = material.color
        //融点と沸点が有効 -> 標準状態に基づいてプロパティを補完
        if (material.hasTempMelt() && material.hasTempBoil()) {
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
        //とりあえず温度を298 Kにする
        else {
            temperature = 298
        }
    }

    companion object {
        @JvmField
        val EMPTY: MaterialFluid = MaterialFluid(HiiragiMaterial.EMPTY, hiiragiLocation("empty"))
    }

    override fun getEmptySound(): SoundEvent =
        if (material.isSolid()) SoundEvents.ITEM_BUCKET_EMPTY_LAVA else SoundEvents.ITEM_BUCKET_EMPTY

    override fun getFillSound(): SoundEvent =
        if (material.isSolid()) SoundEvents.ITEM_BUCKET_FILL_LAVA else SoundEvents.ITEM_BUCKET_FILL

    override fun getUnlocalizedName(): String = material.translationKey

}