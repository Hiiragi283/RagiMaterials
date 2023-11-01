package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.util.itemStack
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class MaterialItemBlock(block: MaterialBlock) : HiiragiItemBlock(block, Short.MAX_VALUE.toInt()),
    PartConvertible.ITEM {

    final override val shape = block.shape

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        getPart(stack)?.getTranslatedName() ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiMaterial.REGISTRY.getValidIndexValues()
            .filter(HiiragiMaterial::isValidIndex)
            .filter(shape::canCreateMaterialItem)
            .map(::itemStack)
            .forEach(subItems::add)
    }

    //    HiiragiEntry    //

    override fun onRegister() {
        PartConvertible.ITEM.register(this)
    }

}