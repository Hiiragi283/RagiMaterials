package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.material.RagiMaterial
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import java.awt.Color

abstract class ItemMaterial(private val settings: MaterialItemSettings) : ItemBase(settings), IColorHandler.ITEM {

    constructor(material: RagiMaterial, part: String, scale: Float = 1.0f) : this(
        MaterialItemSettings(
            material,
            part,
            scale
        )
    )

    //    General    //

    override fun appendHoverText(
        itemStack: ItemStack,
        level: Level?,
        list: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        settings.material.getTooltip(list)
    }

    override fun getName(itemStack: ItemStack): Component {
        return TranslatableComponent("item.${getPart()}", getMaterial().getTranslatedName())
    }

    //    ItemBase    //

    override fun getRegistryName(): ResourceLocation =
        ResourceLocation(RagiMaterials.MOD_ID, "${getPart()}_${getMaterial().name}")

    //    Material    //

    private fun getMaterial(): RagiMaterial = settings.material

    private fun getPart(): String = settings.part

    //    IColorHandler.ITEM    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Color = getMaterial().color

    class Ingot(material: RagiMaterial) : ItemMaterial(material, "ingot")

}