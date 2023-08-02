package hiiragi283.material.api.item

import hiiragi283.material.RMCreativeTabs
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

open class ItemMaterial(val shape: HiiragiShape) : HiiragiItem(RMReference.MOD_ID, shape.name, 32767) {

    init {
        creativeTab = RMCreativeTabs.MATERIAL_ITEM
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        shape.getTranslatedName(MaterialRegistry.getMaterial(stack.metadata))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .map { getItemStack(it) }
            .filter { it.metadata != 0 }
            .sortedBy { it.metadata }
            .forEach { subItems.add(it) }
    }

    //    HiiragiEntry    //

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { OreDictionary.registerOre(shape.getOreDict(it), getItemStack(it)) }
    }

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { shape.registerRecipe(this, it) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = MaterialRegistry.getMaterial(stack.metadata)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = shape.registerModel(this)

}