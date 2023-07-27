package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiItem
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RMItemGroup
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

open class MaterialItem(val part: HiiragiPart) :
    HiiragiItem(FabricItemSettings().group(RMItemGroup.MATERIAL_ITEM)), RMItemColorProvider {

    constructor(shape: HiiragiShape, material: HiiragiMaterial) : this(HiiragiPart(shape, material))

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = part.material.color

    override fun getIdentifier(): Identifier = part.getId()

    override fun getName(stack: ItemStack): Text = part.getText()

    override fun register(): Item {

        val item = super.register()

        RMResourcePack.addItemModel(getIdentifier(), part.shape.getModel())
        part.getRecipe().forEach(RMResourcePack::addRecipe)
        RMResourcePack.addItemTag(part.getCommonId(), getIdentifier())
        RMResourcePack.addItemTag(hiiragiId(part.material.name), part.getCommonId(), true)

        return item

    }

}