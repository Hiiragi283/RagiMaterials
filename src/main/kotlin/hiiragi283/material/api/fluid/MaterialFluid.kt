package hiiragi283.material.api.fluid

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.fluid.EmptyFluid
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


class MaterialFluid(
    val material: HiiragiMaterial
) : EmptyFluid(), HiiragiEntry.FLUID {

    //    EmptyFluid    //

    private val bucket = MaterialBucketItem(this, material)

    override fun getBucketItem(): Item = bucket


    //    HiiragiEntry    //

    override fun getIdentifier(): Identifier = hiiragiId("fluid_${material.name}")

    override fun register() {
        Registry.register(Registry.FLUID, getIdentifier(), this)
        RagiMaterials.LOGGER.debug("The fluid ${getIdentifier().path} registered!")
        bucket.register() //バケツも同時に登録する
    }

}