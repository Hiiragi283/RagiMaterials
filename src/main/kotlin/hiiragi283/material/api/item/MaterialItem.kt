package hiiragi283.material.api.item

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.setModelSame
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary
import java.util.function.BiConsumer
import java.util.function.Consumer

fun createItemMaterial(
    shape: HiiragiShape,
    model: Consumer<HiiragiEntry<*>> = Consumer { it.asItem().setModelSame() },
    recipe: BiConsumer<HiiragiEntry<*>, HiiragiMaterial> = BiConsumer { _, _ -> }
): MaterialItem = object : MaterialItem(shape) {

    override fun getRecipe(item: MaterialItem, material: HiiragiMaterial) {
        recipe.accept(item, material)
    }

    override fun getModel(item: MaterialItem) {
        model.accept(item)
    }

}

abstract class MaterialItem(val shape: HiiragiShape) : HiiragiItem(shape.name, 32767) {

    init {
        creativeTab = HiiragiCreativeTabs.MATERIAL_ITEM
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.let { shape.getTranslatedName(it) }
            ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistries.MATERIAL.getValues()
            .filter { it.isValidIndex() && it.isSolid() && shape.isValid(it) }
            .map { getItemStack(it) }
            .sortedBy { it.metadata }
            .forEach { subItems.add(it) }
    }

    //    HiiragiEntry    //

    override fun registerOreDict() {
        HiiragiRegistries.MATERIAL.getValues()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { material ->
                shape.getOreDicts(material).forEach {
                    OreDictionary.registerOre(it, getItemStack(material))
                }
            }
    }

    override fun registerRecipe() {
        HiiragiRegistries.MATERIAL.getValues()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { getRecipe(this, it) }
    }

    abstract fun getRecipe(item: MaterialItem, material: HiiragiMaterial)

    @SideOnly(Side.CLIENT)
    override fun registerItemColor(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            if (tintIndex == 0) HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)?.color ?: -1 else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = getModel(this)

    abstract fun getModel(item: MaterialItem)

}