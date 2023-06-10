package hiiragi283.material.item

import hiiragi283.material.client.RMModelManager
import hiiragi283.material.creativetab.CreativeTabMaterial
import hiiragi283.material.material_part.IMaterialPart
import hiiragi283.material.material_part.MaterialPart
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.part.HiiragiPart
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class ItemMaterialBase(part: HiiragiPart) : RMItemBase(part.name, 32767), IMaterialPart<ItemStack> {

    init {
        creativeTab = CreativeTabMaterial
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        I18n.format(getPart(stack).translationKey, I18n.format(getMaterial(stack).translationKey))


    //    IRMEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = getMaterial(stack)
            if (!material.isEmpty() && tintIndex == 0) material.getColor().rgb else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel(): Unit = RMModelManager.setModelSame(this)

    //    IMaterialPart    //

    override fun getMaterialPart(obj: ItemStack): MaterialPart = MaterialPartRegistry.getMaterialPart(obj)

}