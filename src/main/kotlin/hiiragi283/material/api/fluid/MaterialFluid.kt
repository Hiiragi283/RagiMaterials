package hiiragi283.material.api.fluid

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.IHiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.fluid.EmptyFluid
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


class MaterialFluid(
    val material: HiiragiMaterial
) : EmptyFluid(), HiiragiEntry, IHiiragiPart.FLUID {

    //    EmptyFluid    //

    private val bucket = MaterialBucketItem(this, material)

    override fun getBucketItem(): Item = bucket


    //    HiiragiEntry    //

    override val identifier: Identifier
        get() = hiiragiId("fluid_${material.name}")

    override fun register() {
        Registry.register(Registry.FLUID, identifier, this)
        RagiMaterials.LOGGER.debug("The fluid ${identifier.path} registered!")
        bucket.register() //バケツも同時に登録する
    }

    override fun registerModel() {
        bucket.registerModel()
    }

    override fun registerTag() {
        bucket.registerTag()
    }

    //    IHiiragiPart    //

    override fun getMaterial(obj: Fluid): HiiragiMaterial = material

    override fun getShape(obj: Fluid): HiiragiShape = HiiragiShape.EMPTY

}