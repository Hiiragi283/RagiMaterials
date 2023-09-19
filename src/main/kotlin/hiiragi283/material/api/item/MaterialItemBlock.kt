package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class MaterialItemBlock(block: MaterialBlock) : HiiragiItemBlock(block, 32767) {

    val shape = block.shape

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.let(shape::getTranslatedName)
            ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(HiiragiMaterial::isValidIndex)
            .filter(shape::isValid)
            .map { material: HiiragiMaterial -> getItemStack(material) }
            .forEach(subItems::add)
    }

}