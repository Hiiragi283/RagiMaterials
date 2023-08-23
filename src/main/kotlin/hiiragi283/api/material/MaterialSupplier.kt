package hiiragi283.api.material

import hiiragi283.api.HiiragiRegistry
import java.util.function.Supplier

class MaterialSupplier(val name: String) : Supplier<HiiragiMaterial> {

    override fun get(): HiiragiMaterial = HiiragiRegistry.getMaterial(name)

}