package hiiragi283.material.common.util

import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.models.JTextures

fun itemModelLayered(layer: JTextures.() -> JTextures): JModel =
    JModel.model().parent("item/generated").textures(JTextures().layer())