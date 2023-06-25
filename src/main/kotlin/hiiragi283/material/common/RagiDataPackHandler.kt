package hiiragi283.material.common

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier

object RagiDataPackHandler {

    fun load() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(
            object : SimpleSynchronousResourceReloadListener {

                override fun getFabricId(): Identifier = hiiragiId("material")

                override fun reload(manager: ResourceManager) {
                    manager.findResources("material") { it.endsWith(".json") }
                        .map { manager.getResource(it).inputStream }
                        .map { stream -> stream.bufferedReader().use { it.readText() } }
                        .map { HiiragiMaterial.fromJson(it) }
                        .forEach { MaterialRegistry.registerMaterial(it) }
                }

            }
        )
    }

}