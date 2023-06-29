package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialPart
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiResourcePack
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class MaterialPartBlockItem(private val materialBlock: MaterialPartBlock) : HiiragiBlockItem(materialBlock),
    MaterialPart<ItemStack> {

    override fun getName(stack: ItemStack): Text = getPart(defaultStack).getName(getMaterial(defaultStack))

    //    HiiragiBlockItem    //

    override val identifier: Identifier = materialBlock.identifier

    override fun registerModel() {
        RagiResourcePack.addItemModel(materialBlock.identifier, getPart(defaultStack).model)
    }

    //    MaterialPart    //

    override fun getMaterial(obj: ItemStack): HiiragiMaterial = materialBlock.getMaterial(block.defaultState)

    override fun getPart(obj: ItemStack): HiiragiPart = materialBlock.getPart(block.defaultState)

}