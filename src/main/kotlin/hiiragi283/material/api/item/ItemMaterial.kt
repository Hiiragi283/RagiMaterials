package hiiragi283.material.api.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.HiiragiPart
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

open class ItemMaterial(val part: HiiragiPart) : HiiragiItem(part.name, 32767) {

    init {
        creativeTab = CREATIVE_TAB
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        part.translatedName(MaterialRegistry.getMaterial(stack.metadata))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .map { ItemStack(this, 1, it.index) }
            .forEach { subItems.add(it) }
    }

    //    HiiragiEntry    //

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .forEach {
                OreDictionary.registerOre(part.getOreDict(it), ItemStack(this, 1, it.index))
            }
    }

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .forEach { part.recipe(this, it) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = MaterialRegistry.getMaterial(stack.metadata)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = part.model(this)

}