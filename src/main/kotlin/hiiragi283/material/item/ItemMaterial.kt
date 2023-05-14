package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.material.RagiMaterial
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.world.World
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

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        settings.material.getTooltip(tooltip)
    }

    override fun getName(): Text {
        return TranslatableText("item.${getPart()}", getMaterial().getTranslatedName())
    }

    //    ItemBase    //

    override fun getIdentifier(): Identifier = Identifier(RagiMaterials.MOD_ID, "${getPart()}_${getMaterial().name}")

    //    Material    //

    private fun getMaterial(): RagiMaterial = settings.material

    private fun getPart(): String = settings.part

    //    IColorHandler.ITEM    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Color = getMaterial().color

    class Ingot(material: RagiMaterial) : ItemMaterial(material, "ingot")

}