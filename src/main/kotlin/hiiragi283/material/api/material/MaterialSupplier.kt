package hiiragi283.material.api.material

import hiiragi283.material.api.registry.HiiragiRegistry
import java.util.function.Supplier

class MaterialSupplier(val name: String) : Supplier<HiiragiMaterial> {

    override fun get(): HiiragiMaterial = HiiragiRegistry.getMaterial(name)

}