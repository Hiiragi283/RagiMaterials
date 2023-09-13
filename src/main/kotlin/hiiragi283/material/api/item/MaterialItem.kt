package hiiragi283.material.api.item

import hiiragi283.material.RMCreativeTabs
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.api.material.HiiragiMaterial
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
        creativeTab = RMCreativeTabs.MATERIAL_ITEM
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        shape.getTranslatedName(HiiragiRegistry.getMaterial(stack.metadata))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistry.getMaterials()
            .filter { it.isValidIndex() && it.isSolid() && shape.isValid(it) }
            .map { getItemStack(it) }
            .sortedBy { it.metadata }
            .forEach { subItems.add(it) }
    }

    //    HiiragiEntry    //

    override fun registerOreDict() {
        HiiragiRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { material ->
                shape.getOreDicts(material).forEach {
                    OreDictionary.registerOre(it, getItemStack(material))
                }
            }
    }

    override fun registerRecipe() {
        HiiragiRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { getRecipe(this, it) }
    }

    abstract fun getRecipe(item: MaterialItem, material: HiiragiMaterial)

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = HiiragiRegistry.getMaterial(stack.metadata)
            if (tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = getModel(this)

    abstract fun getModel(item: MaterialItem)

}