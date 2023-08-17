package hiiragi283.api.item

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.shape.HiiragiShape
import hiiragi283.material.RMCreativeTabs
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
): ItemMaterial = object : ItemMaterial(shape) {

    override fun getRecipe(item: ItemMaterial, material: HiiragiMaterial) {
        recipe.accept(item, material)
    }

    override fun getModel(item: ItemMaterial) {
        model.accept(item)
    }

}

abstract class ItemMaterial(val shape: HiiragiShape) : HiiragiItem(shape.name, 32767) {

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
            .forEach { getRecipe(this, it) }
    }

    abstract fun getRecipe(item: ItemMaterial, material: HiiragiMaterial)

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = MaterialRegistry.getMaterial(stack.metadata)
            if (tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = getModel(this)

    abstract fun getModel(item: ItemMaterial)

}