package hiiragi283.material.api.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.MaterialPart
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class MaterialPartItem(
    private val materialPart: MaterialPart
) : HiiragiItem(FabricItemSettings()) {

    constructor(material: HiiragiMaterial, part: HiiragiPart) : this(MaterialPart(material, part))

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
    }

    override fun getName(): Text = materialPart.getName()

    //    HiiragiItem    //

    override fun register() {
        Registry.register(Registry.ITEM, materialPart.getId(), this)
    }

    override fun registerTag() {
        RagiMaterials.RESOURCE_PACK.addTag(
            materialPart.getTag(),
            JTag().add(materialPart.getId())
        )
    }

    override fun registerRecipe() {
        getPart().recipes(getMaterial()).forEach() {
            RagiMaterials.RESOURCE_PACK.addRecipe(it.key, it.value)
        }
    }

    //    MaterialPartItem    //

    fun getMaterial(): HiiragiMaterial = materialPart.material

    fun getPart(): HiiragiPart = materialPart.part

}