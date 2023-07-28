package hiiragi283.material.common.util

import net.minecraft.data.client.Models
import net.minecraft.data.client.TextureKey
import net.minecraft.util.Identifier
import pers.solid.brrp.v1.model.ModelJsonBuilder

object ModelUtil {

    fun createSimple(layer0: String, layer1: String? = null): ModelJsonBuilder =
        ModelJsonBuilder().parent(Models.GENERATED).addTexture(TextureKey.LAYER0, layer0).addTexture("layer1", layer1)

    fun createSimple(layer0: Identifier, layer1: Identifier? = null): ModelJsonBuilder =
        createSimple(layer0.toString(), layer1?.toString())

}