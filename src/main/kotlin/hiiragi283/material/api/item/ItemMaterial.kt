package hiiragi283.material.api.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

private val CREATIVE_TAB = object : CreativeTabs("${RagiMaterials.MODID}.material") {
    override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
}

open class ItemMaterial(val shape: HiiragiShape) : HiiragiItem(shape.name, 32767) {

    init {
        creativeTab = CREATIVE_TAB
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
            .map { ItemStack(this, 1, it.index) }
            .filter { it.metadata != 0 }
            .forEach { subItems.add(it) }
    }

    //    HiiragiEntry    //

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach {
                OreDictionary.registerOre(shape.getOreDict(it), ItemStack(this, 1, it.index))
            }
    }

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { shape.getRecipe(this, it) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = MaterialRegistry.getMaterial(stack.metadata)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = shape.getModel(this)

}