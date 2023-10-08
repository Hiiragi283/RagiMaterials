package hiiragi283.material

import hiiragi283.material.entity.EntityMinecartTank
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.entity.Entity
import net.minecraftforge.fml.common.registry.EntityRegistry

object HiiragiEntities {

    private var count: Int = 0

    private fun <T : Entity> registerEntity(name: String, clazz: Class<T>) {
        val path = "${RMReference.MOD_ID}.$name"
        EntityRegistry.registerModEntity(
            hiiragiLocation(path),
            clazz,
            path,
            count,
            RagiMaterials.Instance,
            128,
            5,
            true
        )
        count++
    }

    init {
        registerEntity("minecart_tank", EntityMinecartTank::class.java)
    }

}