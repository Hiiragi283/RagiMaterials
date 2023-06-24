package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.models.JTextures
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

abstract class MaterialPartItem(
    val material: HiiragiMaterial,
    val part: HiiragiPart
) : HiiragiItem(FabricItemSettings()) {

    private val identifier: Identifier = part.getId(material)
    private val tag: Identifier = part.getTag(material)

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        material.appendTooltip(tooltip, part)
    }

    override fun getName(): Text = part.getName(material)

    //    HiiragiItem    //

    fun register() {
        Registry.register(Registry.ITEM, identifier, this)
    }

    override fun registerModel() {
        RagiMaterials.RESOURCE_PACK.addModel(
            JModel.model()
                .parent("item/generated")
                .textures(
                    JTextures()
                        .layer0(getTexture())
                ),
            hiiragiId("item/" + identifier.path)
        )
    }

    abstract fun getTexture(): String

    override fun registerTag() {
        RagiMaterials.RESOURCE_PACK.addTag(commonId("items/" + tag.path), JTag().add(identifier))
    }

}