package hiiragi283.ragi_materials.render.model.fullbottle

import hiiragi283.ragi_materials.Reference
import net.minecraft.client.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ICustomModelLoader
import net.minecraftforge.client.model.IModel
import net.minecraftforge.client.model.ModelDynBucket

object LoaderFullBottle: ICustomModelLoader {

    override fun accepts(location: ResourceLocation): Boolean {
        return location.resourceDomain == Reference.MOD_ID && location.resourcePath.contains("fullbottle")
    }

    override fun loadModel(location: ResourceLocation): IModel {
        return ModelDynBucket()
    }

    override fun onResourceManagerReload(resourceManager: IResourceManager) {}
}